package org.example;

import java.time.LocalDateTime;

public class Transaction {

    private Trader trader;
    private Asset asset;
    private String type;
    private int quantity;
    private double montant;
    private LocalDateTime date;

    public Transaction(Trader trader, Asset asset, String type,
                       int quantity, double montant, LocalDateTime date) {

        if (!type.equals("BUY") && !type.equals("SELL"))
            throw new IllegalArgumentException("Type doit être BUY ou SELL");

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantité invalide");

        if (montant <= 0)
            throw new IllegalArgumentException("Montant invalide");

        this.trader = trader;
        this.asset = asset;
        this.type = type;
        this.quantity = quantity;
        this.montant = montant;
        this.date = date;
    }

    // ===== Getters =====
    public Trader getTrader() {
        return trader;
    }

    public Asset getAsset() {
        return asset;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getMontant() {
        return montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader.getNomcomplet() +
                ", asset=" + asset.getNom() +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", montant=" + montant +
                ", date=" + date +
                '}';
    }
}
