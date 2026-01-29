package ru.backendpro.inheritance_3;

class BasePayment {
    protected double amount;

    public BasePayment(double amount) {
        this.amount = amount;
    }

    // final метод — нельзя переопределить
    public final double getAmount() {
        return amount;
    }

    public void process() {
        System.out.println("Обработка платежа: " + amount);
    }
}
