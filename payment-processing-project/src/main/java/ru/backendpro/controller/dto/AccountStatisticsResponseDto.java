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
@Schema(description = "Статистика по счетам")
public class AccountStatisticsResponseDto {

    @Schema(description = "Общее количество счетов", example = "200")
    private Long totalAccounts;

    @Schema(description = "Общий баланс всех счетов", example = "1000000.00")
    private BigDecimal totalBalance;

    @Schema(description = "Количество активных счетов", example = "180")
    private Long activeAccounts;
}