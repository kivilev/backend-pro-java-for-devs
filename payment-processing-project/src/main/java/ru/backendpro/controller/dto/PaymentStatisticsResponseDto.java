package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Статистика по платежам")
public class PaymentStatisticsResponseDto {

    public PaymentStatisticsResponseDto(
            BigDecimal totalAmount,
            Long totalCount,
            Long completedCount,
            Long failedCount,
            Long cancelledCount,
            Long pendingCount,
            Double averageAmount,
            BigDecimal minAmount,
            BigDecimal maxAmount,
            ZonedDateTime periodStart,
            ZonedDateTime periodEnd) {
        this.totalAmount = totalAmount;
        this.totalCount = totalCount;
        this.completedCount = completedCount != null ? completedCount : 0L;
        this.failedCount = failedCount != null ? failedCount : 0L;
        this.cancelledCount = cancelledCount != null ? cancelledCount : 0L;
        this.pendingCount = pendingCount != null ? pendingCount : 0L;
        this.averageAmount = averageAmount != null ? BigDecimal.valueOf(averageAmount) : BigDecimal.ZERO;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    @Schema(description = "Общая сумма платежей", example = "150000.00")
    private BigDecimal totalAmount;

    @Schema(description = "Общее количество платежей", example = "150")
    private Long totalCount;

    @Schema(description = "Количество успешных платежей", example = "120")
    private Long completedCount;

    @Schema(description = "Количество неуспешных платежей", example = "20")
    private Long failedCount;

    @Schema(description = "Количество отмененных платежей", example = "10")
    private Long cancelledCount;

    @Schema(description = "Количество платежей в ожидании", example = "0")
    private Long pendingCount;

    @Schema(description = "Средняя сумма платежа", example = "1000.00")
    private BigDecimal averageAmount;

    @Schema(description = "Минимальная сумма платежа", example = "10.00")
    private BigDecimal minAmount;

    @Schema(description = "Максимальная сумма платежа", example = "50000.00")
    private BigDecimal maxAmount;

    @Schema(description = "Дата начала периода", example = "2024-01-01T00:00:00Z")
    private ZonedDateTime periodStart;

    @Schema(description = "Дата окончания периода", example = "2024-12-31T23:59:59Z")
    private ZonedDateTime periodEnd;
}