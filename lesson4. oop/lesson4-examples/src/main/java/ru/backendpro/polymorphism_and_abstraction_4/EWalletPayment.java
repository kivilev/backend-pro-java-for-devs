package ru.backendpro.polymorphism_and_abstraction_4;

class EWalletPayment extends Payment {
    private String walletId;

    public EWalletPayment(Long id, double amount, String walletId) {
        super(id, amount);
        this.walletId = walletId;
    }

    @Override
    public void process() {
        System.out.println("  [Обработка платежа через кошелёк " + walletId + "]");
        this.status = "COMPLETED";
    }

    @Override
    public double calculateCommission() {
        return amount * 0.015; // 1.5% для кошельков
    }

    @Override
    public String getPaymentMethod() {
        return "Электронный кошелёк " + walletId;
    }
}
