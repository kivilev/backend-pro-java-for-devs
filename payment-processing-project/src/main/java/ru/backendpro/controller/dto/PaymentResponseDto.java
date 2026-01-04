package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.backendpro.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ответ с информацией о платеже")
public class PaymentResponseDto {

    @Schema(description = "Идентификатор платежа", example = "1")
    private Long id;

    @Schema(description = "Дата платежа", example = "2024-01-15T10:30:00Z")
    private ZonedDateTime paymentDate;

    @Schema(description = "Номер счета отправителя", example = "ACC-2024-001")
    private String sourceAccountNumber;

    @Schema(description = "Номер счета получателя", example = "ACC-2024-002")
    private String targetAccountNumber;

    @Schema(description = "Сумма платежа", example = "500.00")
    private BigDecimal amount;

    @Schema(description = "Код валюты", example = "USD")
    private String currencyCode;

    @Schema(description = "Статус платежа", example = "COMPLETED", allowableValues = {"PENDING", "COMPLETED", "FAILED", "CANCELLED"})
    private PaymentStatus status;

    @Schema(description = "Описание статуса платежа", example = "Payment completed successfully")
    private String statusDescription;

    @Schema(description = "Дополнительные детали платежа", example = "{\"description\": \"Payment for services\", \"reference\": \"REF-123\"}")
    private Map<String, String> details;

    @Schema(description = "Дата и время обработки платежа", example = "2024-01-15T10:30:05Z")
    private ZonedDateTime processedAt;
}
