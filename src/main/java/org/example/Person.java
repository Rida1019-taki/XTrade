package org.example;

public class Person {
    private int id;
    private String nomcomplet;
    private int compteur = 1;

    public Person(String nomcomplet) {
        this.id = compteur;
        compteur++;
        this.nomcomplet = nomcomplet;
    }

    public int getId() {
        return id;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public int getCompteur() {
        return compteur;
    }
}
