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
@Schema(description = "Топ клиент по объему платежей")
public class TopClientByPaymentsResponseDto {

    @Schema(description = "Идентификатор клиента", example = "1")
    private Long clientId;

    @Schema(description = "Email клиента", example = "client@example.com")
    private String email;

    @Schema(description = "Общая сумма платежей", example = "50000.00")
    private BigDecimal totalPaymentAmount;

    @Schema(description = "Количество платежей", example = "25")
    private Long paymentCount;
}