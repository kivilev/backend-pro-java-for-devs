package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.backendpro.entity.AccountStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на обновление статуса счета")
public class AccountStatusUpdateRequestDto {

    @NotNull(message = "Status is required")
    @Schema(description = "Новый статус счета", example = "BLOCKED", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = {"ACTIVE", "BLOCKED"})
    private AccountStatus status;
}

