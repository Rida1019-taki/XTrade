package org.example;

import java.time.LocalDate;

public class Transaction {

    private String type;
    private Trader trader;
    private Asset asset;
    private int quantite;
    private LocalDate date;

    public Transaction(String type, Trader trader, Asset asset, int quantite) {
        this.type = type;
        this.trader = trader;
        this.asset = asset;
        this.quantite = quantite;
        this.date = LocalDate.now();
    }

    public String getType() { return type; }
    public Trader getTrader() { return trader; }
    public Asset getAsset() { return asset; }
    public int getQuantite() { return quantite; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return type + " | " + trader.getNomcomplet() + " | "
                + asset.getCode() + " | QTE: " + quantite
                + " | Date: " + date;
    }
}


