package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.backendpro.entity.ClientStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сводка по клиенту с информацией о счетах")
public class ClientAccountSummaryResponseDto {

    @Schema(description = "Идентификатор клиента", example = "1")
    private Long clientId;

    @Schema(description = "Email адрес клиента", example = "client@example.com")
    private String email;

    @Schema(description = "Номер телефона клиента", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "Статус клиента", example = "ACTIVE", allowableValues = {"DEACTIVATED", "ACTIVE", "BLOCKED"})
    private ClientStatus status;

    @Schema(description = "Дата создания клиента", example = "2024-01-15T10:30:00Z")
    private ZonedDateTime createdAt;

    @Schema(description = "Количество счетов у клиента", example = "3")
    private Long accountCount;

    @Schema(description = "Общий баланс по всем счетам", example = "50000.00")
    private BigDecimal totalBalance;

    @Schema(description = "Количество активных счетов", example = "2")
    private Long activeAccountCount;
}

