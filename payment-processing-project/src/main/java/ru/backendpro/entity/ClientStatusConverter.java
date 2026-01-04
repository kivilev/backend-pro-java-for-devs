package ru.backendpro.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClientStatusConverter implements AttributeConverter<ClientStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClientStatus status) {
        if (status == null) {
            return null;
        }
        return status.getId();
    }

    @Override
    public ClientStatus convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return ClientStatus.fromId(id);
    }
}

