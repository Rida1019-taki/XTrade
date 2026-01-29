package org.example;

public class CryptoCurrency extends Asset {
    private String blockchain;


    public CryptoCurrency(String code, String nom, double prix, String blockchain) {
        super(code, nom, prix);
        this.blockchain = blockchain;
    }


    @Override
    public String getType() {
        return "CRYPTO";
    }
}
