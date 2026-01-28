package org.example;

public class Trader extends Person {
    private double balance;

    public Trader(String nomComplet , double balance) {
        super(nomComplet);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
