package ru.backendpro.inheritance_3;

class CardPayment extends BasePayment {
    public CardPayment(double amount) {
        super(amount);
    }

    // Попытка переопределить final метод вызовет ошибку:
    // @Override
    // public double getAmount() { return amount * 2; } // ОШИБКА!

    @Override
    public void process() {
        System.out.println("Обработка платежа картой: " + amount);
    }
}
