package org.example;

public class Stock {
    private String exchange;

    public Stock(String exchange) {
        this.exchange = exchange;
    }

    public String getNomDuEntreprise() {
        return exchange;
    }

    public void setNomDuEntreprise(String exchange) {
        this.exchange = exchange;
    }
}
