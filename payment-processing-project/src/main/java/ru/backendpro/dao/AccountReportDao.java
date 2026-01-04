package ru.backendpro.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.backendpro.controller.dto.AccountStatisticsResponseDto;
import ru.backendpro.entity.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class AccountReportDao {

    private final JdbcTemplate jdbcTemplate;

    public AccountStatisticsResponseDto getAccountStatistics() {
        String sql = """
                SELECT 
                    COUNT(*) as total_accounts,
                    COALESCE(SUM(balance), 0) as total_balance,
                    COUNT(*) FILTER (WHERE status = ?) as active_accounts
                FROM account
                """;

        return jdbcTemplate.queryForObject(
                sql,
                new AccountStatisticsRowMapper(),
                AccountStatus.ACTIVE.getId()
        );
    }

    private static class AccountStatisticsRowMapper implements RowMapper<AccountStatisticsResponseDto> {
        @Override
        public AccountStatisticsResponseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return AccountStatisticsResponseDto.builder()
                    .totalAccounts(rs.getLong("total_accounts"))
                    .totalBalance(rs.getBigDecimal("total_balance"))
                    .activeAccounts(rs.getLong("active_accounts"))
                    .build();
        }
    }
}

