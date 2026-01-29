package ru.backendpro.entities;

public class ClientData {

    private final Long id;

    private final Client client;

    private final ClientDataField field;

    private String fieldValue;

    public ClientData(Long id, Client client, ClientDataField field, String fieldValue) {
        this.id = id;
        this.client = client;
        this.field = field;
        this.fieldValue = fieldValue;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public ClientDataField getField() {
        return field;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", client=" + client +
                ", field=" + field +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}
