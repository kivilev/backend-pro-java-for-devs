package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.backendpro.entity.ClientStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на обновление статуса клиента")
public class ClientStatusUpdateRequestDto {

    @NotNull(message = "Status is required")
    @Schema(description = "Новый статус клиента", example = "BLOCKED", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = {"DEACTIVATED", "ACTIVE", "BLOCKED"})
    private ClientStatus status;
}

