package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Статистика по клиентам")
public class ClientStatisticsResponseDto {

    @Schema(description = "Общее количество клиентов", example = "100")
    private Long totalClients;

    @Schema(description = "Количество активных клиентов", example = "80")
    private Long activeClients;

    @Schema(description = "Количество заблокированных клиентов", example = "20")
    private Long blockedClients;
}