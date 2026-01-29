package org.example;

public abstract class Asset {
    protected String code;
    protected String nom;
    protected double prix;


    public Asset(String code, String nom, double prix) {
        if (prix <= 0) throw new IllegalArgumentException("Prix invalide");
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    }


    public String getCode() { return code; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) {
        if (prix <= 0) throw new IllegalArgumentException("Prix invalide");
        this.prix = prix;
    }


    public abstract String getType();
}