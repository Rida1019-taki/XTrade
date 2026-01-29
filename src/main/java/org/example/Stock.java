package org.example;

public class Stock extends Asset {
    private String entreprise;


    public Stock(String code, String nom, double prix, String entreprise) {
        super(code, nom, prix);
        this.entreprise = entreprise;
    }


    @Override
    public String getType() {
        return "ACTION";
    }
}
