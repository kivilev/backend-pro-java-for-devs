package ru.backendpro.inheritance_3;

class Animal {
    protected String species;

    public Animal(String species) {
        this.species = species;
        System.out.println("  [Конструктор Animal вызван для: " + species + "]");
    }

    public void makeSound() {
        System.out.println(species + " издаёт звук");
    }
}
