package ru.backendpro.object_class_5;

/**
 * не переопределяем методы Object
 * Используем дефолтный метод toString() из Object
  */
public class Cat {
    private String name;
    private int age;
    
    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Используем дефолтные методы toString(), equals(), hashCode() из Object
}
