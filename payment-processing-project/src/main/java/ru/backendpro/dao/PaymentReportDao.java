package ru.backendpro.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.backendpro.controller.dto.PaymentStatisticsByCurrencyResponseDto;
import ru.backendpro.controller.dto.PaymentStatisticsResponseDto;
import ru.backendpro.controller.dto.TopClientByPaymentsResponseDto;
import ru.backendpro.entity.PaymentStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentReportDao {

    private final JdbcTemplate jdbcTemplate;

    public List<PaymentStatisticsByCurrencyResponseDto> getPaymentStatisticsByCurrency(ZonedDateTime startDate, ZonedDateTime endDate) {
        String sql = """
                SELECT 
                    currency_code,
                    COALESCE(SUM(amount), 0) as total_amount,
                    COUNT(*) as payment_count,
                    COALESCE(AVG(amount), 0) as average_amount
                FROM payment
                WHERE payment_date >= ? AND payment_date <= ?
                GROUP BY currency_code
                ORDER BY 2 DESC
                """;

        return jdbcTemplate.query(
                sql,
                new PaymentStatisticsByCurrencyRowMapper(),
                startDate,
                endDate
        );
    }

    public List<TopClientByPaymentsResponseDto> getTopClientsByPayments(int limit, ZonedDateTime startDate, ZonedDateTime endDate) {
        String sql = """
                SELECT 
                    c.id as client_id,
                    c.email,
                    COALESCE(SUM(p.amount), 0) as total_payment_amount,
                    COUNT(p.id) as payment_count
                FROM client c
                LEFT JOIN account a ON a.client_id = c.id
                LEFT JOIN payment p ON (p.source_account_id = a.id OR p.target_account_id = a.id)
                    AND p.payment_date >= ? AND p.payment_date <= ?
                    AND p.status = ?
                GROUP BY c.id, c.email
                ORDER BY COALESCE(SUM(p.amount), 0) DESC
                LIMIT ?
                """;

        return jdbcTemplate.query(
                sql,
                new TopClientByPaymentsRowMapper(),
                startDate,
                endDate,
                PaymentStatus.COMPLETED.getId(),
                limit
        );
    }

    private static class PaymentStatisticsRowMapper implements RowMapper<PaymentStatisticsResponseDto> {
        private final ZonedDateTime periodStart;
        private final ZonedDateTime periodEnd;

        public PaymentStatisticsRowMapper(ZonedDateTime periodStart, ZonedDateTime periodEnd) {
            this.periodStart = periodStart;
            this.periodEnd = periodEnd;
        }

        @Override
        public PaymentStatisticsResponseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return PaymentStatisticsResponseDto.builder()
                    .totalAmount(rs.getBigDecimal("total_amount"))
                    .totalCount(rs.getLong("total_count"))
                    .completedCount(rs.getLong("completed_count"))
                    .failedCount(rs.getLong("failed_count"))
                    .cancelledCount(rs.getLong("cancelled_count"))
                    .pendingCount(rs.getLong("pending_count"))
                    .averageAmount(rs.getBigDecimal("average_amount"))
                    .minAmount(rs.getBigDecimal("min_amount"))
                    .maxAmount(rs.getBigDecimal("max_amount"))
                    .periodStart(periodStart)
                    .periodEnd(periodEnd)
                    .build();
        }
    }

    private static class PaymentStatisticsByCurrencyRowMapper implements RowMapper<PaymentStatisticsByCurrencyResponseDto> {
        @Override
        public PaymentStatisticsByCurrencyResponseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return PaymentStatisticsByCurrencyResponseDto.builder()
                    .currencyCode(rs.getString("currency_code"))
                    .totalAmount(rs.getBigDecimal("total_amount"))
                    .paymentCount(rs.getLong("payment_count"))
                    .averageAmount(rs.getBigDecimal("average_amount"))
                    .build();
        }
    }

    private static class TopClientByPaymentsRowMapper implements RowMapper<TopClientByPaymentsResponseDto> {
        @Override
        public TopClientByPaymentsResponseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return TopClientByPaymentsResponseDto.builder()
                    .clientId(rs.getLong("client_id"))
                    .email(rs.getString("email"))
                    .totalPaymentAmount(rs.getBigDecimal("total_payment_amount"))
                    .paymentCount(rs.getLong("payment_count"))
                    .build();
        }
    }
}

