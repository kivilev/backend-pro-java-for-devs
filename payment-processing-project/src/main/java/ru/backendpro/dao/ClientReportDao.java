package ru.backendpro.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.backendpro.controller.dto.ClientStatisticsResponseDto;
import ru.backendpro.entity.ClientStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class ClientReportDao {

    private final JdbcTemplate jdbcTemplate;

    public ClientStatisticsResponseDto getClientStatistics() {
        String sql = """
                SELECT 
                    COUNT(*) as total_clients,
                    COUNT(*) FILTER (WHERE status = ?) as active_clients,
                    COUNT(*) FILTER (WHERE status = ?) as blocked_clients
                FROM client
                """;

        return jdbcTemplate.queryForObject(
                sql,
                new ClientStatisticsRowMapper(),
                ClientStatus.ACTIVE.getId(),
                ClientStatus.BLOCKED.getId()
        );
    }

    private static class ClientStatisticsRowMapper implements RowMapper<ClientStatisticsResponseDto> {
        @Override
        public ClientStatisticsResponseDto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return ClientStatisticsResponseDto.builder()
                    .totalClients(rs.getLong("total_clients"))
                    .activeClients(rs.getLong("active_clients"))
                    .blockedClients(rs.getLong("blocked_clients"))
                    .build();
        }
    }
}

