package ru.backendpro.entities;

public class ClientDataField {

    private final Integer fieldId;

    private String name;

    private String description;

    public ClientDataField(Integer fieldId, String name, String description) {
        this.fieldId = fieldId;
        this.name = name;
        this.description = description;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientDataField{" +
                "fieldId=" + fieldId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

