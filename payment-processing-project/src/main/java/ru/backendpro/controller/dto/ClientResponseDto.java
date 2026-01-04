package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.backendpro.entity.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ответ с информацией о клиенте")
public class ClientResponseDto {

    @Schema(description = "Идентификатор клиента", example = "1")
    private Long id;

    @Schema(description = "Email адрес клиента", example = "client@example.com")
    private String email;

    @Schema(description = "Номер телефона клиента", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "Статус клиента", example = "ACTIVE", allowableValues = {"DEACTIVATED", "ACTIVE", "BLOCKED"})
    private ClientStatus status;

    @Schema(description = "Дополнительные свойства клиента", example = "{\"firstName\": \"John\", \"lastName\": \"Doe\"}")
    private Map<String, String> properties;
}
