package ru.backendpro.polymorphism_and_abstraction_4;

class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(Long id, double amount, String cardNumber) {
        super(id, amount);
        this.cardNumber = maskCardNumber(cardNumber);
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public void process() {
        System.out.println("  [Обработка платежа картой " + cardNumber + "]");
        this.status = "COMPLETED";
    }

    @Override
    public double calculateCommission() {
        return amount * 0.02; // 2% для карт
    }

    @Override
    public String getPaymentMethod() {
        return "Банковская карта " + cardNumber;
    }
}
