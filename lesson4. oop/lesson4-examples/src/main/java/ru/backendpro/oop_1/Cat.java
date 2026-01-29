package ru.backendpro.oop_1;

class Cat {
    // ПОЛЯ КЛАССА (instance fields) — данные каждого конкретного кота
    String name;
    int age;
    String color;
    String currentState;

    // КОНСТРУКТОР — специальный метод для создания объекта
    // Имя совпадает с именем класса, нет возвращаемого типа
    Cat(String name, int age, String color) {
        // this.name — поле объекта
        // name — параметр конструктора
        this.name = name;
        this.age = age;
        this.color = color;
        this.currentState = "SLEEPING"; // начальное состояние
    }

    // ПЕРЕГРУЖЕННЫЙ КОНСТРУКТОР — упрощённый вариант создания
    Cat(String name) {
        this(name, 0, "unknown"); // вызов другого конструктора через this()
    }

    // МЕТОДЫ КЛАССА (instance methods) — поведение кота
    void meow() {
        currentState = "MEOWING";
        System.out.println(name + " мяукает. Состояние: " + currentState);
    }

    void eat() {
        currentState = "EATING";
        System.out.println(name + " ест. Состояние: " + currentState);
    }

    void sleep() {
        currentState = "SLEEPING";
        System.out.println(name + " спит. Состояние: " + currentState);
    }

    void play() {
        currentState = "PLAYING";
        System.out.println(name + " играет. Состояние: " + currentState);
    }
}
