package ru.backendpro.polymorphism_and_abstraction_4;

class Client extends Person {
    private Long clientId;

    public Client(Long clientId, String name) {
        super(name);
        this.clientId = clientId;
    }

    @Override
    public void introduce() {
        System.out.println("Я клиент #" + clientId + ": " + name);
    }
}
