package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Schema(description = "Запрос на создание счета")
public class AccountCreateRequestDto {

    @NotNull(message = "Client ID is required")
    @Schema(description = "Идентификатор клиента", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long clientId;

    @NotBlank(message = "Currency code is required")
    @Schema(description = "Код валюты", example = "USD", requiredMode = Schema.RequiredMode.REQUIRED)
    private String currencyCode;

    @NotNull(message = "Initial balance is required")
    @PositiveOrZero
    @Schema(description = "Начальный баланс счета", example = "1000.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal initialBalance;
}
