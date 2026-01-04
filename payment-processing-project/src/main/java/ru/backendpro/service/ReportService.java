package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backendpro.dao.AccountReportDao;
import ru.backendpro.dao.ClientReportDao;
import ru.backendpro.dao.PaymentReportDao;
import ru.backendpro.dao.repository.ClientRepository;
import ru.backendpro.dao.repository.PaymentRepository;
import ru.backendpro.controller.dto.AccountStatisticsResponseDto;
import ru.backendpro.controller.dto.ClientAccountSummaryResponseDto;
import ru.backendpro.controller.dto.ClientStatisticsResponseDto;
import ru.backendpro.controller.dto.PaymentStatisticsByCurrencyResponseDto;
import ru.backendpro.controller.dto.PaymentStatisticsResponseDto;
import ru.backendpro.controller.dto.TopClientByPaymentsResponseDto;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final PaymentRepository paymentRepository;
    private final PaymentReportDao paymentReportDao;
    private final ClientReportDao clientReportDao;
    private final ClientRepository clientRepository;
    private final AccountReportDao accountReportDao;

    @Transactional(readOnly = true)
    public PaymentStatisticsResponseDto getPaymentStatistics(ZonedDateTime startDate, ZonedDateTime endDate) {
        return paymentRepository.getPaymentStatistics(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<PaymentStatisticsByCurrencyResponseDto> getPaymentStatisticsByCurrency(ZonedDateTime startDate, ZonedDateTime endDate) {
        return paymentReportDao.getPaymentStatisticsByCurrency(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public ClientStatisticsResponseDto getClientStatistics() {
        return clientReportDao.getClientStatistics();
    }

    @Transactional(readOnly = true)
    public AccountStatisticsResponseDto getAccountStatistics() {
        return accountReportDao.getAccountStatistics();
    }

    @Transactional(readOnly = true)
    public List<TopClientByPaymentsResponseDto> getTopClientsByPayments(int limit, ZonedDateTime startDate, ZonedDateTime endDate) {
        return paymentReportDao.getTopClientsByPayments(limit, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public PaymentStatisticsResponseDto getPaymentStatisticsLastDays(int days) {
        ZonedDateTime endDate = ZonedDateTime.now();
        ZonedDateTime startDate = endDate.minusDays(days);
        return getPaymentStatistics(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<ClientAccountSummaryResponseDto> getClientsAccountSummary() {
        return clientRepository.findAllClientsWithAccountSummary();
    }
}