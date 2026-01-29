package ru.backendpro.polymorphism_and_abstraction_4;

class Employee extends Person {
    private String position;

    public Employee(String name, String position) {
        super(name);
        this.position = position;
    }

    @Override
    public void introduce() {
        System.out.println("Я сотрудник (" + position + "): " + name);
    }
}
