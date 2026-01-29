package ru.backendpro.oop_1;

/**
 Автор: Денис Кивилёв
 Online-школа: Backend-pro.ru

 Описание: ДЕМО-КОД ДЛЯ ЛЕКЦИИ 4: ООП — ЧАСТЬ 2. ООП В JAVA

 Этот класс показывает, как текстовое описание из Части 1
 превращается в реальные классы и объекты:
 
 ШАГ ЗА ШАГОМ:
 1. Берём описание сущности "Кот" из первого файла
 2. Превращаем свойства в поля класса
 3. Превращаем действия в методы класса
 4. Добавляем конструкторы для создания объектов
 5. Создаём несколько объектов и работаем с ними
 
 То же самое делаем для Car.
 
 ВАЖНО ДЛЯ ПРЕПОДАВАТЕЛЯ:
 - Показывайте код постепенно, по одному классу
 - Объясняйте связь с первым файлом: "Помните, мы описали свойства кота? Вот они стали полями"
 - Подчеркните разницу между классом (чертёж) и объектом (конкретный экземпляр)
 - Обратите внимание на this — ссылка на текущий объект
 
 */
public class OopInJavaDemo {

    public static void main(String[] args) {
        System.out.println("=== Лекция 4. Часть 2. ООП в Java: Классы и Объекты ===\n");

        // ========================================
        // БЛОК 1: КЛАСС CAT
        // ========================================
        System.out.println("БЛОК 1: Класс Cat (Кот)\n");
        System.out.println("Создаём двух котов (два объекта класса Cat):\n");
        
        // СОЗДАНИЕ ОБЪЕКТОВ через оператор new
        // Cat — это ТИП (класс)
        // barsik — это ПЕРЕМЕННАЯ, хранящая ссылку на объект
        // new Cat(...) — создание объекта в heap
        Cat barsik = new Cat("Барсик", 2, "рыжий");
        Cat murzik = new Cat("Мурзик"); // используем перегруженный конструктор

        System.out.println("Объект 1: " + barsik.name + ", возраст " + barsik.age + ", цвет " + barsik.color);
        System.out.println("Объект 2: " + murzik.name + ", возраст " + murzik.age + ", цвет " + murzik.color);
        System.out.println();

        System.out.println("Вызываем методы у разных объектов:");
        barsik.meow();
        barsik.play();
        murzik.eat();
        murzik.sleep();
        
        System.out.println("\nОБРАТИТЕ ВНИМАНИЕ:");
        System.out.println("- barsik и murzik — это РАЗНЫЕ объекты");
        System.out.println("- У каждого свои значения полей (name, age, color, currentState)");
        System.out.println("- Когда вызываем barsik.play(), меняется состояние только у barsik\n");

        // ========================================
        // БЛОК 2: КЛАСС CAR
        // ========================================
        System.out.println("\n" + "=".repeat(60));
        System.out.println("БЛОК 2: Класс Car (Автомобиль)\n");

        System.out.println("Создаём автомобиль:\n");
        Car car = new Car("Toyota Camry", "чёрный");
        
        System.out.println("Попытка поехать без топлива:");
        car.startEngine(); // не получится — нет топлива
        
        System.out.println("\nЗаправляем и едем:");
        car.refuel(20);
        car.startEngine();
        car.drive(50);
        car.drive(30);
        car.stopEngine();

        System.out.println("\nОБРАТИТЕ ВНИМАНИЕ:");
        System.out.println("- Методы могут проверять состояние объекта (engineOn, fuelInLiters)");
        System.out.println("- Методы могут изменять состояние объекта (mileage, fuelInLiters)");
        System.out.println("- Это и есть инкапсуляция логики внутри класса\n");
    }
}