package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на создание платежа")
public class PaymentCreateRequestDto {

    @NotNull(message = "Payment date is required")
    @Schema(description = "Дата платежа", example = "2024-01-15T10:30:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private ZonedDateTime paymentDate;
    
    @NotBlank(message = "Source account number is required")
    @Schema(description = "Номер счета отправителя", example = "ACC-2024-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sourceAccountNumber;

    @NotBlank(message = "Target account number is required")
    @Schema(description = "Номер счета получателя", example = "ACC-2024-002", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetAccountNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Digits(integer = 17, fraction = 2, message = "Amount must have at most 2 decimal places")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    @Schema(description = "Сумма платежа", example = "500.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;

    @NotBlank(message = "Currency code is required")
    @Schema(description = "Код валюты", example = "USD", requiredMode = Schema.RequiredMode.REQUIRED)
    private String currencyCode;

    @Schema(description = "Дополнительные детали платежа", example = "{\"note\": \"Payment for services\"}")
    private Map<String, String> details;
}
