package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ответ с информацией о счете")
public class AccountResponseDto {

    @Schema(description = "Идентификатор счета", example = "1")
    private Long id;

    @Schema(description = "Идентификатор клиента", example = "1")
    private Long clientId;

    @Schema(description = "Номер счета", example = "ACC-2024-001")
    private String accountNumber;

    @Schema(description = "Код валюты", example = "USD")
    private String currencyCode;

    @Schema(description = "Баланс счета", example = "1000.00")
    private BigDecimal balance;

    @Schema(description = "Статус счета", example = "ACTIVE", allowableValues = {"ACTIVE", "BLOCKED"})
    private String status;
}
