package ru.backendpro.polymorphism_and_abstraction_4;

class BankTransferPayment extends Payment {
    private String bankAccount;

    public BankTransferPayment(Long id, double amount, String bankAccount) {
        super(id, amount);
        this.bankAccount = bankAccount;
    }

    @Override
    public void process() {
        System.out.println("  [Обработка банковского перевода на счёт " + bankAccount + "]");
        this.status = "COMPLETED";
    }

    @Override
    public double calculateCommission() {
        return amount * 0.01; // 1% для переводов
    }

    @Override
    public String getPaymentMethod() {
        return "Банковский перевод на " + bankAccount;
    }
}
