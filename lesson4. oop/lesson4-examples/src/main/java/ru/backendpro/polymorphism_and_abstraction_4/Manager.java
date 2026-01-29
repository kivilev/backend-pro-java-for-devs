package ru.backendpro.polymorphism_and_abstraction_4;

class Manager extends Employee {
    private int teamSize;

    public Manager(String name, int teamSize) {
        super(name, "Менеджер");
        this.teamSize = teamSize;
    }

    @Override
    public void introduce() {
        System.out.println("Я менеджер: " + name + ", команда из " + teamSize + " человек");
    }
}
