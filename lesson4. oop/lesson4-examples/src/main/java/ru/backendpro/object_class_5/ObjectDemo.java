package ru.backendpro.object_class_5;

/**
 * ЛЕКЦИЯ 4 - ООП: ОСНОВЫ
 * ЧАСТЬ 6: Класс Object и метод toString()
 * 
 * ЦЕЛЬ ДЕМОНСТРАЦИИ:
 * - Показать, что все классы наследуются от Object
 * - Продемонстрировать метод toString()
 * - Показать проблемы дефолтной реализации
 * - Научить правильно переопределять метод toString()
 * 
 * СТРУКТУРА:
 * Блок 1: Класс Object - корень иерархии
 * Блок 2: Метод toString() - плохая и хорошая реализация
 */

public class ObjectDemo {
    
    public static void main(String[] args) {
        
        System.out.println("=== БЛОК 1: КЛАСС OBJECT - КОРЕНЬ ИЕРАРХИИ ===\n");
        
        // В Java ВСЕ классы неявно наследуются от Object
        // Даже если мы не пишем "extends Object" - это происходит автоматически
        
        Cat cat1 = new Cat("Мурзик", 3);
        
        // Эти методы доступны у ЛЮБОГО объекта, т.к. они унаследованы от Object:
        System.out.println("Методы, унаследованные от Object:");
        System.out.println("toString(): " + cat1.toString());
        System.out.println("getClass(): " + cat1.getClass());
        
        // Обратите внимание на вывод toString() - он нечитаемый!
        // Формат: ИмяКласса@хеш-код в 16-ричной системе
        // Это дефолтная реализация из Object - она бесполезна для нас, т.к. нет информации о полях объекта
        
        System.out.println("\n=== БЛОК 2: МЕТОД toString() - ПЛОХАЯ И ХОРОШАЯ РЕАЛИЗАЦИЯ ===\n");
        
        // ПЛОХО: используем дефолтный toString() из Object
        Cat badCat = new Cat("Барсик", 5);
        System.out.println("Плохо (дефолтный toString): " + badCat);
        // Вывод: Cat@15db9742 - бесполезно!
        
        // ХОРОШО: переопределяем toString() для читаемого вывода
        GoodCat goodCat = new GoodCat("Мурка", 2);
        System.out.println("Хорошо (переопределённый toString): " + goodCat);
        // Вывод: GoodCat{name='Мурка', age=2} - понятно и информативно!
        
        // Итог:
        // 1. Все классы наследуются от Object
        // 2. ВСЕГДА переопределяйте toString() для читаемого вывода
        
    }
}
