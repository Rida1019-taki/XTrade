package org.example;

import java.util.*;

public class Portfolio<T extends Asset> {
    private Map<T, Integer> actifs = new HashMap<>();


    public void ajouterActif(T actif, int qte) {
        if (qte <= 0) throw new IllegalArgumentException("Quantité invalide");
        actifs.put(actif, actifs.getOrDefault(actif, 0) + qte);
    }


    public void retirerActif(T actif, int qte) {
        if (!actifs.containsKey(actif)) throw new IllegalArgumentException("Actif non détenu");
        if (qte <= 0) throw new IllegalArgumentException("Quantité invalide");


        int reste = actifs.get(actif) - qte;
        if (reste < 0) throw new IllegalArgumentException("Quantité insuffisante");
        if (reste == 0) actifs.remove(actif);
        else actifs.put(actif, reste);
    }


    public double calculerValeurTotale() {
        double total = 0;
        for (Map.Entry<T, Integer> e : actifs.entrySet()) {
            total += e.getKey().getPrix() * e.getValue();
        }
        return total;
    }


    public void afficher() {
        for (Map.Entry<T, Integer> e : actifs.entrySet()) {
            System.out.println(e.getKey().getNom() + " | qte=" + e.getValue());
        }
        System.out.println("Valeur totale = " + calculerValeurTotale());
    }


    public Map<T, Integer> getActifs() {
        return actifs;
    }
}
