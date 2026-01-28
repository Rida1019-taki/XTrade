package org.example;

public class Asset {
    private String code;
    private String nom;
    private String unitPrice;

    public Asset(String code, String nom, String unitPrice) {
        this.code = code;
        this.nom = nom;
        this.unitPrice = unitPrice;
    }

    public String getCode() {
        
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
