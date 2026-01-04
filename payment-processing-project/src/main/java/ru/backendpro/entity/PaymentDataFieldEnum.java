package ru.backendpro.entity;

import lombok.Getter;

@Getter
public enum PaymentDataFieldEnum {
    CLIENT_SOFTWARE(1, "clientSoftware"),
    IP(2, "ip"),
    NOTE(3, "note"),
    IS_CHECKED_FRAUD(4, "isCheckedFraud");

    private final Integer id;
    private final String jsonFieldName;

    PaymentDataFieldEnum(Integer id, String jsonFieldName) {
        this.id = id;
        this.jsonFieldName = jsonFieldName;
    }

    public static PaymentDataFieldEnum fromId(Integer id) {
        for (PaymentDataFieldEnum field : PaymentDataFieldEnum.values()) {
            if (field.id.equals(id)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentDataFieldEnum id: " + id);
    }

    public static PaymentDataFieldEnum fromJsonFieldName(String jsonFieldName) {
        for (PaymentDataFieldEnum field : PaymentDataFieldEnum.values()) {
            if (field.jsonFieldName.equals(jsonFieldName)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentDataFieldEnum jsonFieldName: " + jsonFieldName);
    }
}

