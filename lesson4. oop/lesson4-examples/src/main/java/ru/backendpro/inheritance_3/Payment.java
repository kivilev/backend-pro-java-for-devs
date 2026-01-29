package ru.backendpro.inheritance_3;

class Payment {
    private final Long id;        // final поле — нельзя изменить
    private final double amount;

    public Payment(Long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    // Попытка изменить final поле вызовет ошибку:
    // public void setId(Long id) { this.id = id; } // ОШИБКА!
}
