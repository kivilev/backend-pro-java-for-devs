package ru.backendpro.controller.mapper;

import org.springframework.stereotype.Component;
import ru.backendpro.controller.dto.AccountResponseDto;
import ru.backendpro.entity.Account;

@Component
public class AccountMapper {

    public AccountResponseDto mapToResponse(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .clientId(account.getClient().getId())
                .accountNumber(account.getAccountNumber())
                .currencyCode(account.getCurrency().getAlfa3())
                .balance(account.getBalance())
                .status(account.getStatus() != null ? account.getStatus().name() : null)
                .build();
    }
}

