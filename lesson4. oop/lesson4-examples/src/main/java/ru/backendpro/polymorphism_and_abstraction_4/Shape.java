package ru.backendpro.polymorphism_and_abstraction_4;

abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Абстрактный метод (без реализации)
    public abstract double calculateArea();

    // Абстрактный метод
    public abstract double calculatePerimeter();

    // Обычный метод (с реализацией)
    public void printInfo() {
        System.out.println("Фигура цвета " + color +
            ", площадь: " + calculateArea() +
            ", периметр: " + calculatePerimeter());
    }
}
