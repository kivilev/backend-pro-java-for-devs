package ru.backendpro.inheritance_3;

class PremiumAccount extends BankAccount {
    public PremiumAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.10; // 10% для премиум счёта
    }

    @Override
    public String getAccountType() {
        return "Премиум счёт";
    }
}
