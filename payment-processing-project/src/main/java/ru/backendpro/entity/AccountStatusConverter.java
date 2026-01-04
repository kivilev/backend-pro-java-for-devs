package ru.backendpro.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountStatus status) {
        if (status == null) {
            return null;
        }
        return status.getId();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return AccountStatus.fromId(id);
    }
}

