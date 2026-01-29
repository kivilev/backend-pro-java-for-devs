package ru.backendpro.inheritance_3;

class Cat extends Animal {
    private String name;

    public Cat(String name) {
        super("Кот"); // 1. Вызов конструктора суперкласса (ОБЯЗАТЕЛЬНО ПЕРВАЯ СТРОКА!)
        this.name = name;
        System.out.println("  [Конструктор Cat вызван для: " + name + "]");
    }

    @Override
    public void makeSound() {
        super.makeSound(); // 2. Вызов метода суперкласса
        System.out.println(name + " мяукает: Мяу-мяу!");
    }

    public void printInfo() {
        // 3. Доступ к полю суперкласса
        System.out.println("Вид: " + super.species + ", Имя: " + this.name);
    }
}
