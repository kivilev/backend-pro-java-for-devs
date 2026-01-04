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
@Schema(description = "Статистика по платежам по валютам")
public class PaymentStatisticsByCurrencyResponseDto {

    @Schema(description = "Код валюты", example = "USD")
    private String currencyCode;

    @Schema(description = "Общая сумма платежей", example = "50000.00")
    private BigDecimal totalAmount;

    @Schema(description = "Количество платежей", example = "50")
    private Long paymentCount;

    @Schema(description = "Средняя сумма платежа", example = "1000.00")
    private BigDecimal averageAmount;
}