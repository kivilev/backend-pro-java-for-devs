package ru.backendpro.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentStatus status) {
        if (status == null) {
            return null;
        }
        return status.getId();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return PaymentStatus.fromId(id);
    }
}

