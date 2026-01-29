package ru.backendpro.entities;

public class Account {

    private final Long id;

    private Client client;

    private final String accountNumber;

    private final Currency currency;

    private double balance;

    private String status;

    public Account(Long id, Client client, String accountNumber, Currency currency, double balance, String status) {
        ValidationUtils.requirePositive(id, "id");
        ValidationUtils.requireNonNull(client, "Client");
        ValidationUtils.requireNonBlank(accountNumber, "accountNumber");
        ValidationUtils.requireNonNull(currency, "Currency");
        ValidationUtils.requireNonNegative(balance, "balance");
        ValidationUtils.requireNonBlank(status, "status");
        this.id = id;
        this.client = client;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.status = status;
    }

    public Account(Long id, Client client, String accountNumber, Currency currency) {
        this(id, client, accountNumber, currency, 0, "ACTIVE");
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        ValidationUtils.requireNonNull(client, "Client");
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        ValidationUtils.requireNonNegative(balance, "balance");
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        ValidationUtils.requireNonBlank(status, "status");
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", accountNumber='" + accountNumber + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
