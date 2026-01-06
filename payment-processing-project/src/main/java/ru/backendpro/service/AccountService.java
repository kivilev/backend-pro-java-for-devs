package ru.backendpro.service;

import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.PessimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backendpro.dao.repository.AccountRepository;
import ru.backendpro.dao.repository.ClientRepository;
import ru.backendpro.dao.repository.CurrencyRepository;
import ru.backendpro.controller.dto.AccountCreateRequestDto;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.AccountStatus;
import ru.backendpro.entity.Client;
import ru.backendpro.entity.Currency;
import ru.backendpro.exception.AccountLockTimeoutException;
import ru.backendpro.exception.BusinessException;
import ru.backendpro.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public Account createAccount(AccountCreateRequestDto request) {
        return createAccount(request.getClientId(), request.getCurrencyCode(), request.getInitialBalance());
    }

    @Transactional
    public Account createAccount(Long clientId, String currencyCode, BigDecimal initialBalance) {
        log.info("Creating account: clientId={}, currency={}, initialBalance={}", clientId, currencyCode, initialBalance);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    log.error("Client not found for account creation: clientId={}", clientId);
                    return new ResourceNotFoundException("Client not found with id: " + clientId);
                });

        Currency currency = currencyRepository.findByAlfa3(currencyCode.toUpperCase(Locale.ROOT))
                .orElseThrow(() -> {
                    log.error("Currency not found: currencyCode={}", currencyCode);
                    return new ResourceNotFoundException("Currency not found with code: " + currencyCode);
                });

        if (accountRepository.findByClientIdAndCurrencyAlfa3(clientId, currencyCode.toUpperCase(Locale.ROOT)).isPresent()) {
            log.warn("Account already exists: clientId={}, currency={}", clientId, currencyCode);
            throw new BusinessException("Account already exists for client " + clientId + " and currency " + currencyCode);
        }

        Account account = Account.builder()
                .client(client)
                .accountNumber(UUID.randomUUID().toString())
                .currency(currency)
                .balance(initialBalance)
                .status(AccountStatus.ACTIVE)
                .build();

        Account savedAccount = accountRepository.save(account);
        log.info("Account created successfully: id={}, accountNumber={}, clientId={}, currency={}",
                savedAccount.getId(), savedAccount.getAccountNumber(), clientId, currencyCode);
        
        // TODO: Отправить событие в Kafka о создании счета (account.created)
        
        return savedAccount;
    }

    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with number: " + accountNumber));
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    @Transactional
    public void updateBalance(String accountNumber, BigDecimal amount) {
        log.debug("Updating account balance: accountNumber={}, amount={}", accountNumber, amount);
        try {
            Account account = accountRepository.findByAccountNumberWithLock(accountNumber)
                    .orElseThrow(() -> {
                        log.error("Account not found for balance update: accountNumber={}", accountNumber);
                        return new ResourceNotFoundException("Account not found with number: " + accountNumber);
                    });

            BigDecimal oldBalance = account.getBalance();
            BigDecimal newBalance = account.getBalance().add(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                log.warn("Insufficient funds for balance update: accountNumber={}, currentBalance={}, requestedAmount={}",
                        accountNumber, oldBalance, amount);
                throw new BusinessException("Insufficient funds");
            }

            account.setBalance(newBalance);
            accountRepository.save(account);
            log.info("Account balance updated: accountNumber={}, oldBalance={}, newBalance={}, amount={}",
                    accountNumber, oldBalance, newBalance, amount);
            
            // TODO: Отправить событие в Kafka об изменении баланса счета (account.balance.changed)
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Account lock timeout during balance update: accountNumber={}", accountNumber, e);
            throw new AccountLockTimeoutException(
                    "Account lock timeout for account: " + accountNumber + ". Account is locked by another session.", e);
        }
    }

    @Transactional
    public Account updateAccountStatus(Long id, AccountStatus newStatus) {
        log.info("Updating account status: id={}, newStatus={}", id, newStatus);
        Account account = getAccountById(id);
        
        if (account.getStatus() == newStatus) {
            log.warn("Account is already in requested status: id={}, status={}", id, newStatus);
            throw new BusinessException("Account is already in status: " + newStatus);
        }
        
        AccountStatus oldStatus = account.getStatus();
        account.setStatus(newStatus);
        Account savedAccount = accountRepository.save(account);
        log.info("Account status updated: id={}, oldStatus={}, newStatus={}", id, oldStatus, newStatus);
        
        // TODO: Отправить событие в Kafka об изменении статуса счета (account.status.changed)
        
        return savedAccount;
    }
}
