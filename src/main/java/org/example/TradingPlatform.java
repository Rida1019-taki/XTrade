package org.example;

import java.util.ArrayList;
import java.util.List;

public class TradingPlatform {

    private List<Trader> traders = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void ajouterTrader(Trader t) {
        traders.add(t);
    }

    public void ajouterActif(Asset a) {
        for (Asset x : assets) {
            if (x.getCode().equals(a.getCode()))
                throw new IllegalArgumentException("Code actif deja existant");
        }
        assets.add(a);
    }

    public void afficherActifs() {
        for (Asset a : assets) {
            System.out.println(a.getCode() + " | " + a.getNom() + " | " + a.getPrix());
        }
    }

    private Trader findTrader(int id) {
        return traders.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    private Asset findAsset(String code) {
        return assets.stream()
                .filter(a -> a.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

    public void acheterActif(int idTrader, String codeActif, int qte) {
        Trader t = findTrader(idTrader);
        Asset a = findAsset(codeActif);

        double montant = a.getPrix() * qte;
        if (t.getSolde() < montant) throw new IllegalArgumentException("Solde insuffisant");

        t.debiter(montant);
        t.getPortfolio().ajouter(a, qte);
        transactions.add(new Transaction("ACHAT", t, a, qte));
    }

    public void vendreActif(int idTrader, String codeActif, int qte) {
        Trader t = findTrader(idTrader);
        Asset a = findAsset(codeActif);

        t.getPortfolio().retirer(a, qte);
        t.crediter(a.getPrix() * qte);
        transactions.add(new Transaction("VENTE", t, a, qte));
    }

    public void consulterPortefeuille(int idTrader) {
        Trader t = findTrader(idTrader);
        t.getPortfolio().getActifs().forEach(
                (a, q) -> System.out.println(a.getNom() + " : " + q)
        );
        System.out.println("Valeur totale = " + t.getPortfolio().calculerValeurTotale());
    }

    public void afficherHistorique() {
        transactions.forEach(System.out::println);
    }

    public void changerPrixAsset(String codeActif, double nouveauPrix) {
        if (nouveauPrix <= 0)
            throw new IllegalArgumentException("Prix invalide");

        Asset a = findAsset(codeActif);
        a.setPrix(nouveauPrix);

        System.out.println("Prix de l'actif " + a.getNom() +
                " changé à " + nouveauPrix);
    }

}
