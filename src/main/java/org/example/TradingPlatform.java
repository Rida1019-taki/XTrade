package org.example;

import java.util.*;


public class TradingPlatform {
    private Admin admin;


    public TradingPlatform() {
        admin = new Admin();
    }


    // ===== ASSETS =====
    public void ajouterActif(Asset asset) {
        admin.ajouterAsset(asset);
    }


    public void afficherActifs() {
        admin.afficherActifs();
    }


    // ===== TRADERS =====
    public void ajouterTrader(Trader trader) {
        admin.ajouterTrader(trader);
    }


    // ===== OPERATIONS =====
    public void acheterActif(Trader trader, Asset asset, int qte) {
        trader.acheter(asset, qte);
    }


    public void vendreActif(Trader trader, Asset asset, int qte) {
        trader.vendre(asset, qte);
    }


    public void consulterPortfolio(Trader trader) {
        trader.afficherPortfolio();
    }


    public void consulterHistorique(Trader trader) {
        trader.afficherHistorique();
    }

    public Admin getAdmin() {
        return admin;
    }
}