package ru.backendpro.inheritance_3;

class Client extends Person {
    private Long clientId;
    private String phoneNumber;

    public Client(Long clientId, String name, String email, String phoneNumber) {
        super(name, email); // Вызов конструктора суперкласса
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
    }

    public void makePayment(double amount) {
        System.out.println("💳 Клиент " + name + " совершает платёж: " + amount + " руб.");
    }

    @Override
    public String getInfo() {
        return "Client{id=" + clientId + ", name='" + name + "', phone='" + phoneNumber + "'}";
    }
}
