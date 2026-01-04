package ru.backendpro.controller.mapper;

import org.springframework.stereotype.Component;
import ru.backendpro.controller.dto.ClientResponseDto;
import ru.backendpro.entity.Client;
import ru.backendpro.entity.ClientData;
import ru.backendpro.entity.ClientDataFieldEnum;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientMapper {

    public ClientResponseDto mapToResponse(Client client) {
        Map<String, String> properties = new HashMap<>();
        for (ClientData data : client.getClientData()) {
            ClientDataFieldEnum fieldEnum = ClientDataFieldEnum.fromId(data.getField().getFieldId());
            properties.put(fieldEnum.getJsonFieldName(), data.getFieldValue());
        }

        return ClientResponseDto.builder()
                .id(client.getId())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .status(client.getStatus())
                .properties(properties)
                .build();
    }
}

