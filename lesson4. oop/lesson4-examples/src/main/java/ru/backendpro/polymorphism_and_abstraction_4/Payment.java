package ru.backendpro.polymorphism_and_abstraction_4;

abstract class Payment {
    protected Long id;
    protected double amount;
    protected String status;

    public Payment(Long id, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = "PENDING";
    }

    // Абстрактные методы (каждый тип платежа реализует по-своему)
    public abstract void process();
    public abstract double calculateCommission();
    public abstract String getPaymentMethod();

    // Обычные методы (общие для всех платежей)
    public void cancel() {
        this.status = "CANCELLED";
        System.out.println("  [Платёж #" + id + " отменён]");
    }

    public String getStatus() {
        return status;
    }

    public void printReceipt() {
        System.out.println("┌─ ЧЕК ─────────────────────────────────┐");
        System.out.println("│ Платёж #" + id);
        System.out.println("│ Способ: " + getPaymentMethod());
        System.out.println("│ Сумма: " + amount + " руб.");
        System.out.println("│ Комиссия: " + calculateCommission() + " руб.");
        System.out.println("│ Статус: " + status);
        System.out.println("└───────────────────────────────────────┘");
    }
}
