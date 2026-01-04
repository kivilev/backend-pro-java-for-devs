package ru.backendpro.controller;

import ru.backendpro.controller.mapper.AccountMapper;
import ru.backendpro.controller.dto.AccountCreateRequestDto;
import ru.backendpro.controller.dto.AccountResponseDto;
import ru.backendpro.controller.dto.AccountStatusUpdateRequestDto;
import ru.backendpro.entity.Account;
import ru.backendpro.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Account management APIs")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping
    @Operation(summary = "Create a new account")
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountCreateRequestDto request) {
        log.info("POST /api/v1/accounts - Creating account: clientId={}, currency={}, initialBalance={}",
                request.getClientId(), request.getCurrencyCode(), request.getInitialBalance());
        Account account = accountService.createAccount(request);
        AccountResponseDto response = accountMapper.mapToResponse(account);
        log.info("Account created via API: id={}, accountNumber={}", account.getId(), account.getAccountNumber());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID")
    public AccountResponseDto getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return accountMapper.mapToResponse(account);
    }

    @GetMapping("/number/{accountNumber}")
    @Operation(summary = "Get account by account number")
    public AccountResponseDto getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        return accountMapper.mapToResponse(account);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get accounts by client ID")
    public List<AccountResponseDto> getAccountsByClientId(@PathVariable Long clientId) {
        List<Account> accounts = accountService.getAccountsByClientId(clientId);
        return accounts.stream()
                .map(accountMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update account status")
    public AccountResponseDto updateAccountStatus(@PathVariable Long id,
                                                  @Valid @RequestBody AccountStatusUpdateRequestDto request) {
        log.info("PATCH /api/v1/accounts/{}/status - Updating account status: newStatus={}", id, request.getStatus());
        Account account = accountService.updateAccountStatus(id, request.getStatus());
        log.info("Account status updated via API: id={}, status={}", id, account.getStatus());
        return accountMapper.mapToResponse(account);
    }
}
