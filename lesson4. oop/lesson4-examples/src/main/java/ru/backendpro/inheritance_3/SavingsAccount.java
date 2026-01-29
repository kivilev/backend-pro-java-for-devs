package ru.backendpro.inheritance_3;

class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.05; // 5% для сберегательного счёта
    }

    @Override
    public String getAccountType() {
        return "Сберегательный счёт";
    }
}
