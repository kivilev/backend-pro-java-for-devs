package ru.backendpro.oop_1;

class Car {
    // ПОЛЯ
    String model;
    String color;
    double mileage;        // пробег в км
    double fuelInLiters;   // топливо в литрах
    boolean engineOn;      // заведён ли двигатель

    // ПОЛНЫЙ КОНСТРУКТОР
    Car(String model, String color, double mileage, double fuelInLiters) {
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.fuelInLiters = fuelInLiters;
        this.engineOn = false; // по умолчанию двигатель заглушен
    }

    // УПРОЩЁННЫЙ КОНСТРУКТОР (для новой машины)
    Car(String model, String color) {
        this(model, color, 0.0, 0.0); // делегируем в полный конструктор
    }

    // МЕТОДЫ
    void startEngine() {
        if (!engineOn) {
            engineOn = true;
            System.out.println(model + ": двигатель запущен.");
        } else {
            System.out.println(model + ": двигатель уже работает.");
        }
    }

    void stopEngine() {
        if (engineOn) {
            engineOn = false;
            System.out.println(model + ": двигатель заглушен.");
        } else {
            System.out.println(model + ": двигатель уже заглушен.");
        }
    }

    void drive(double kilometers) {
        if (!engineOn) {
            System.out.println(model + ": нельзя ехать — двигатель заглушен.");
            return;
        }
        if (fuelInLiters <= 0) {
            System.out.println(model + ": нельзя ехать — нет топлива.");
            return;
        }

        double fuelNeeded = kilometers * 0.1; // упрощённый расход: 0.1 л/км
        if (fuelInLiters < fuelNeeded) {
            System.out.println(model + ": недостаточно топлива для поездки.");
            return;
        }

        mileage += kilometers;
        fuelInLiters -= fuelNeeded;
        System.out.println(model + " проехал " + kilometers + " км. " +
                "Пробег: " + mileage + " км, топлива осталось: " +
                String.format("%.1f", fuelInLiters) + " л");
    }

    void refuel(double liters) {
        fuelInLiters += liters;
        System.out.println(model + " заправили на " + liters + " л. " +
                "Всего топлива: " + fuelInLiters + " л");
    }
}
