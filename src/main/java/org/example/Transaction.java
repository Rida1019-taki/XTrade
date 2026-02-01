package org.example;

import java.time.LocalDateTime;

public class Transaction {

    private String type;
    private Trader trader;
    private Asset asset;
    private int quantite;
    private double prix;
    private LocalDateTime date;

    public Transaction(String type, Trader trader, Asset asset, int quantite) {
        this.type = type;
        this.trader = trader;
        this.asset = asset;
        this.quantite = quantite;
        this.prix = asset.getPrix();
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return type + " | " + asset.getNom() +
                " | Qte: " + quantite +
                " | Prix: " + prix +
                " | Date: " + date;
    }
}

