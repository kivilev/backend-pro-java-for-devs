package ru.backendpro.entities;

public class Client {

    private final Long id;

    private String email;

    private String phoneNumber;

    private String status;

    // clientData - список данных клиента (добавим в лекции про коллекции)
    // accounts - список счетов клиента (добавим в лекции про коллекции)

    public Client(Long id, String email, String phoneNumber, String status) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
