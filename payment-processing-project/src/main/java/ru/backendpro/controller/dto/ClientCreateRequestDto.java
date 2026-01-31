package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.backendpro.entity.ClientStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на создание клиента")
public class ClientCreateRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Email адрес клиента", example = "client@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "Номер телефона клиента", example = "+1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

    @NotNull(message = "Status is required")
    @Schema(description = "Статус клиента", example = "ACTIVE", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = {"INACTIVE", "ACTIVE", "BLOCKED"})
    private ClientStatus status;

    @Schema(description = "Дополнительные свойства клиента", example = "{\"firstName\": \"John\", \"lastName\": \"Doe\", \"middleName\": \"Michael\", \"birthday\": \"1990-01-15\", \"passportSeries\": \"1234\", \"passportNumber\": \"567890\"}")
    private Map<String, String> properties;
}
