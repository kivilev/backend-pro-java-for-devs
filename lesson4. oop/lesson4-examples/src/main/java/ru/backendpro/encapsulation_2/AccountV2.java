package ru.backendpro.encapsulation_2;

import java.math.BigDecimal;

class AccountV2 {
    private BigDecimal balance; // Изменили тип!

    public AccountV2(double balance) {
        this.balance = java.math.BigDecimal.valueOf(balance);
    }

    // API остался прежним!
    public double getBalance() {
        return balance.doubleValue(); // Преобразуем для обратной совместимости
    }

    public void deposit(double amount) {
        this.balance = balance.add(java.math.BigDecimal.valueOf(amount));
    }
}
