package ru.backendpro.polymorphism_and_abstraction_4;

class PaymentProcessor {
    public void processPayments(Payment[] payments) {
        System.out.println("Обработка платежей:\n");
        for (Payment payment : payments) {
            payment.process();
            payment.printReceipt();
            System.out.println();
        }
    }

    public double calculateTotalCommission(Payment[] payments) {
        double total = 0;
        for (Payment payment : payments) {
            total += payment.calculateCommission();
        }
        return total;
    }
}
