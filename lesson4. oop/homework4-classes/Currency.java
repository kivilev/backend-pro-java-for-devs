package ru.backendpro.entities;

public class Currency {

    private final Integer currencyId;

    private final String alpha3;

    private final String description;

    public Currency(Integer currencyId, String alpha3, String description) {
        this.currencyId = currencyId;
        this.alpha3 = alpha3;
        this.description = description;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public String getAlfa3() {
        return alpha3;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId=" + currencyId +
                ", alpha3='" + alpha3 + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
