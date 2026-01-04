package ru.backendpro.entity;

import lombok.Getter;

@Getter
public enum ClientDataFieldEnum {
    FIRST_NAME(1, "firstName"),
    LAST_NAME(2, "lastName"),
    BIRTHDAY(3, "birthday"),
    PASSPORT_SERIES(4, "passportSeries"),
    PASSPORT_NUMBER(5, "passportNumber"),
    MIDDLE_NAME(8, "middleName");

    private final Integer id;
    private final String jsonFieldName;

    ClientDataFieldEnum(Integer id, String jsonFieldName) {
        this.id = id;
        this.jsonFieldName = jsonFieldName;
    }

    public static ClientDataFieldEnum fromId(Integer id) {
        for (ClientDataFieldEnum field : ClientDataFieldEnum.values()) {
            if (field.id.equals(id)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Unknown ClientDataFieldEnum id: " + id);
    }

    public static ClientDataFieldEnum fromJsonFieldName(String jsonFieldName) {
        for (ClientDataFieldEnum field : ClientDataFieldEnum.values()) {
            if (field.jsonFieldName.equals(jsonFieldName)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Unknown ClientDataFieldEnum jsonFieldName: " + jsonFieldName);
    }
}

