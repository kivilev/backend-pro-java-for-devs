package ru.backendpro.encapsulation_2;

class GoodAccount {
    private double balance;        // Приватное поле
    private String accountNumber;  // Приватное поле

    // Конструктор
    public GoodAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        // Используем setter для валидации
        setBalance(initialBalance);
    }

    // GETTER — получение значения
    public double getBalance() {
        return balance;
    }

    // SETTER с валидацией — установка значения
    private void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                "Баланс не может быть отрицательным: " + balance
            );
        }
        this.balance = balance;
    }

    // Метод для пополнения счёта
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма пополнения должна быть положительной: " + amount
            );
        }
        this.balance += amount;
        // Пополнение на amount руб. Баланс: balance
    }

    // Метод для снятия средств
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма снятия должна быть положительной: " + amount
            );
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                "Недостаточно средств. Баланс: " + balance + ", запрошено: " + amount
            );
        }
        this.balance -= amount;
        // Снятие amount руб. Баланс: balance
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
