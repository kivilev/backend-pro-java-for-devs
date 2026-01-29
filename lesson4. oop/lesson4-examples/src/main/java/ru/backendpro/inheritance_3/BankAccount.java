package ru.backendpro.inheritance_3;

class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double calculateInterest() {
        return balance * 0.01; // 1% базовая ставка
    }

    public String getAccountType() {
        return "Обычный счёт";
    }
}
