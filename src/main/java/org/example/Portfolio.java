package org.example;

import java.util.HashMap;
import java.util.Map;

public class Portfolio<T extends Asset> {

    private Map<T, Integer> actifs = new HashMap<>();

    public void ajouter(T actif, int quantite) {
        if (quantite <= 0) throw new IllegalArgumentException("Quantité invalide");
        actifs.put(actif, actifs.getOrDefault(actif, 0) + quantite);
    }

    public void retirer(T actif, int quantite) {
        int qteActuelle = actifs.getOrDefault(actif, 0);
        if (quantite <= 0 || qteActuelle < quantite)
            throw new IllegalArgumentException("Quantité insuffisante");

        if (qteActuelle == quantite)
            actifs.remove(actif);
        else
            actifs.put(actif, qteActuelle - quantite);
    }

    public double calculerValeurTotale() {
        double total = 0;
        for (Map.Entry<T, Integer> e : actifs.entrySet()) {
            total += e.getKey().getPrix() * e.getValue();
        }
        return total;
    }

    public Map<T, Integer> getActifs() {
        return actifs;
    }
}
