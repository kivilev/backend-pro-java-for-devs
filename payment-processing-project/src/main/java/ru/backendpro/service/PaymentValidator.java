package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.AccountStatus;
import ru.backendpro.exception.BusinessException;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentValidator {

    public void validatePaymentTransfer(Account sourceAccount, Account targetAccount,
                                         BigDecimal amount, String currencyAlfa3) {
        log.debug("Validating payment transfer: sourceAccount={}, targetAccount={}, amount={}, currency={}",
                sourceAccount.getId(), targetAccount.getId(), amount, currencyAlfa3);
        validateNotSameAccount(sourceAccount, targetAccount);
        validateAccountsActive(sourceAccount, targetAccount);
        validateSufficientFunds(sourceAccount, amount);
        validateCurrencyMatch(sourceAccount, targetAccount, currencyAlfa3);
        log.debug("Payment transfer validation passed");
    }

    private void validateNotSameAccount(Account sourceAccount, Account targetAccount) {
        if (sourceAccount.getId().equals(targetAccount.getId())) {
            log.warn("Validation failed: Cannot transfer to the same account. AccountId={}", sourceAccount.getId());
            throw new BusinessException("Cannot transfer to the same account");
        }
    }

    private void validateAccountsActive(Account sourceAccount, Account targetAccount) {
        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) {
            log.warn("Validation failed: Source account is not active. AccountId={}, Status={}",
                    sourceAccount.getId(), sourceAccount.getStatus());
            throw new BusinessException("Source account is not active");
        }

        if (targetAccount.getStatus() != AccountStatus.ACTIVE) {
            log.warn("Validation failed: Target account is not active. AccountId={}, Status={}",
                    targetAccount.getId(), targetAccount.getStatus());
            throw new BusinessException("Target account is not active");
        }
    }

    private void validateSufficientFunds(Account sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            log.warn("Validation failed: Insufficient funds. AccountId={}, Balance={}, RequiredAmount={}",
                    sourceAccount.getId(), sourceAccount.getBalance(), amount);
            throw new BusinessException("Insufficient funds");
        }
    }

    private void validateCurrencyMatch(Account sourceAccount, Account targetAccount, String currencyAlfa3) {
        if (!sourceAccount.getCurrency().getAlfa3().equals(currencyAlfa3)) {
            log.warn("Validation failed: Source account currency mismatch. AccountId={}, AccountCurrency={}, PaymentCurrency={}",
                    sourceAccount.getId(), sourceAccount.getCurrency().getAlfa3(), currencyAlfa3);
            throw new BusinessException("Source account currency does not match payment currency");
        }

        if (!targetAccount.getCurrency().getAlfa3().equals(currencyAlfa3)) {
            log.warn("Validation failed: Target account currency mismatch. AccountId={}, AccountCurrency={}, PaymentCurrency={}",
                    targetAccount.getId(), targetAccount.getCurrency().getAlfa3(), currencyAlfa3);
            throw new BusinessException("Target account currency does not match payment currency");
        }
    }
}

