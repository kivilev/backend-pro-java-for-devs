package ru.backendpro.polymorphism_and_abstraction_4;

class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void introduce() {
        System.out.println("Я человек: " + name);
    }
}
