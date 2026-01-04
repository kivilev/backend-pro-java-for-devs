package ru.backendpro.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Запрос на обновление клиента")
public class ClientUpdateRequestDto {

    @Schema(description = "Дополнительные свойства клиента для обновления", example = "{\"firstName\": \"John\", \"lastName\": \"Doe\"}")
    private Map<String, String> properties;
}

