package org.example;

import java.util.*;


public class Trader extends Person{
    private double solde;
    private Portfolio<Asset> portfolio;
    private List<Transaction> historique;


    public Trader(String nomComplet, double solde) {
        super(nomComplet);
        if (solde < 0) throw new IllegalArgumentException("Solde invalide");
        this.solde = solde;
        this.portfolio = new Portfolio<>();
        this.historique = new ArrayList<>();
    }


    public void acheter(Asset asset, int qte) {
        if (qte <= 0) throw new IllegalArgumentException("Quantité invalide");
        double cout = asset.getPrix() * qte;
        if (solde < cout) throw new IllegalArgumentException("Solde insuffisant");


        portfolio.ajouterActif(asset, qte);
        solde -= cout;
        historique.add(new Transaction("ACHAT", asset, qte, asset.getPrix()));
    }


    public void vendre(Asset asset, int qte) {
        portfolio.retirerActif(asset, qte);
        solde += asset.getPrix() * qte;
        historique.add(new Transaction("VENTE", asset, qte, asset.getPrix()));
    }


    public void afficherPortfolio() {
        System.out.println("--- Portfolio de " + getNomcomplet() + " ---");
        portfolio.afficher();
    }


    public void afficherHistorique() {
        System.out.println("--- Historique ---");
        for (Transaction t : historique) {
            System.out.println(t);
        }
    }

    public double getSolde() {
        return solde;
    }

    public Portfolio<Asset> getPortfolio() {
        return portfolio;
    }

    public List<Transaction> getHistorique() {
        return historique;
    }
}
