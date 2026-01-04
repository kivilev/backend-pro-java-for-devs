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
@Schema(description = "Запрос на обновление свойств платежа")
public class PaymentUpdateRequestDto {

    @Schema(description = "Дополнительные свойства платежа для добавления", example = "{\"note\": \"Updated note\", \"ip\": \"192.168.1.1\"}")
    private Map<String, String> details;
}

