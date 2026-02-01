package org.example;

public class Trader extends Person {

    private double solde;
    private Portfolio<Asset> portfolio;

    public Trader(String nom, double solde) {
        super(nom);
        if (solde <= 0) throw new IllegalArgumentException("Solde invalide");
        this.solde = solde;
        this.portfolio = new Portfolio<>();
    }

    public double getSolde() {
        return solde;
    }

    public void debiter(double montant) {
        solde -= montant;
    }

    public void crediter(double montant) {
        solde += montant;
    }

    public Portfolio<Asset> getPortfolio() {
        return portfolio;
    }
}

