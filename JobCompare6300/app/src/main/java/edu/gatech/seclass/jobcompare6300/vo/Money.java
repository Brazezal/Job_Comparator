package edu.gatech.seclass.jobcompare6300.vo;

public class Money {
    private double amount;
    private String currency;


    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }


    public boolean lte(int value) {
        return amount <= value;
    }

    public boolean lte(Money value) {
        return amount <= value.getAmount();
    }

    public boolean gte(int value) {
        return amount >= value;
    }

    public boolean gte(Money value) {
        return amount >= value.getAmount();
    }

    public boolean lt(int value) {
        return amount < value;
    }

    public boolean lt(Money value) {
        return amount < value.getAmount();
    }

    public boolean gt(int value) {
        return amount > value;
    }

    public boolean gt(Money value) {
        return amount > value.getAmount();
    }

    public boolean eq(int value) {
        return amount == value;
    }

    public boolean eq(Money value) {
        return amount == value.getAmount();
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}