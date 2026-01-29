package ru.backendpro.object_class_5;

/**
 * ХОРОШИЙ ПРИМЕР: правильно переопределяем toString()
 */
public class GoodCat {
    private String name;
    private int age;
    
    public GoodCat(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        // Возвращаем читаемое представление объекта
        return "GoodCat{name='" + name + "', age=" + age + "}";
    }
}
