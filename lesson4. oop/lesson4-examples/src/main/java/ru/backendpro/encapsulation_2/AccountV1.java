package ru.backendpro.encapsulation_2;

class AccountV1 {
    private double balance; // double — может быть неточность

    public AccountV1(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
