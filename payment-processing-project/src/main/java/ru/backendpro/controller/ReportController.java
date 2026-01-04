package ru.backendpro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.backendpro.controller.dto.AccountStatisticsResponseDto;
import ru.backendpro.controller.dto.ClientAccountSummaryResponseDto;
import ru.backendpro.controller.dto.ClientStatisticsResponseDto;
import ru.backendpro.controller.dto.PaymentStatisticsByCurrencyResponseDto;
import ru.backendpro.controller.dto.PaymentStatisticsResponseDto;
import ru.backendpro.controller.dto.TopClientByPaymentsResponseDto;
import ru.backendpro.service.ReportService;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Reporting and statistics APIs")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/payments/statistics/perid")
    @Operation(summary = "Получить статистику по платежам за период")
    public PaymentStatisticsResponseDto getPaymentStatistics(
            @Parameter(description = "Дата начала периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @Parameter(description = "Дата окончания периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        return reportService.getPaymentStatistics(startDate, endDate);
    }

    @GetMapping("/payments/statistics/last-days")
    @Operation(summary = "Получить статистику по платежам за последние N дней")
    public PaymentStatisticsResponseDto getPaymentStatisticsLastDays(
            @Parameter(description = "Количество дней", example = "30")
            @RequestParam(defaultValue = "30") int days) {
        return reportService.getPaymentStatisticsLastDays(days);
    }

    @GetMapping("/payments/statistics/by-currency")
    @Operation(summary = "Получить статистику по платежам, сгруппированную по валютам")
    public List<PaymentStatisticsByCurrencyResponseDto> getPaymentStatisticsByCurrency(
            @Parameter(description = "Дата начала периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @Parameter(description = "Дата окончания периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        return reportService.getPaymentStatisticsByCurrency(startDate, endDate);
    }

    @GetMapping("/clients/statistics")
    @Operation(summary = "Получить статистику по клиентам")
    public ClientStatisticsResponseDto getClientStatistics() {
        return reportService.getClientStatistics();
    }

    @GetMapping("/clients/top-by-payments")
    @Operation(summary = "Получить топ N клиентов по объему платежей")
    public List<TopClientByPaymentsResponseDto> getTopClientsByPayments(
            @Parameter(description = "Количество клиентов в топе", example = "10")
            @RequestParam(defaultValue = "10") int limit,
            @Parameter(description = "Дата начала периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @Parameter(description = "Дата окончания периода")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        return reportService.getTopClientsByPayments(limit, startDate, endDate);
    }

    @GetMapping("/accounts/statistics")
    @Operation(summary = "Получить статистику по счетам")
    public AccountStatisticsResponseDto getAccountStatistics() {
        return reportService.getAccountStatistics();
    }

    @GetMapping("/accounts/clients-summary")
    @Operation(summary = "Get account summary for all clients")
    public List<ClientAccountSummaryResponseDto> getClientsAccountSummary() {
        return reportService.getClientsAccountSummary();
    }

}