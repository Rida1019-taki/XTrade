package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TradingPlatform {

    private List<Trader> traders = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void ajouterTrader(Trader t) { traders.add(t); }
    public void ajouterActif(Asset a) { assets.add(a); }

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

    public void afficherActifs() {
        for (Asset a : assets) {
            System.out.println(a.getCode() + " | " + a.getNom() + " | " + a.getPrix());
        }
    }
    public void acheterActif(int idTrader, String codeActif, int qte) {
        Trader t = findTrader(idTrader);
        Asset a = findAsset(codeActif);

        double montant = a.getPrix() * qte;
        if (t.getSolde() < montant)
            throw new IllegalArgumentException("Solde insuffisant");

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

    public void afficherHistorique() {
        transactions.forEach(System.out::println);
    }

    public void consulterPortefeuille(int idTrader) {
        Trader t = findTrader(idTrader);
        t.getPortfolio().getActifs()
                .forEach( (a, q)
                        -> System.out.println(a.getNom() + " : " + q) );
        System.out.println("Valeur totale = " + t.getPortfolio().calculerValeurTotale());
    }

    public void changerPrixAsset(String codeActif, double nouveauPrix) {
        if (nouveauPrix <= 0) throw new IllegalArgumentException("Prix invalide");
        Asset a = findAsset(codeActif); a.setPrix(nouveauPrix);
        System.out.println("Prix de l'actif " + a.getNom() + " changé à " + nouveauPrix);
    }

    public Trader getTraderById(int id) {
        return traders.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Trader non trouvé"));
    }

    public Asset getAssetByCode(String code) {
        return assets.stream()
                .filter(a -> a.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Actif non trouvé"));
    }

    public void ajouterTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getTransactionsByTrader(int idTrader) {
        return transactions.stream()
                .filter(t -> t.getTrader().getId() == idTrader)
                .toList();
    }

    public List<Transaction> filterByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .toList();
    }

    public List<Transaction> filterByAsset(String codeActif) {
        return transactions.stream()
                .filter(t -> t.getAsset().getCode().equalsIgnoreCase(codeActif))
                .toList();
    }

    public List<Transaction> filterByDate(LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(start)
                        && !t.getDate().isAfter(end))
                .toList();
    }

    public List<Transaction> sortByDate() {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .toList();
    }

    public List<Transaction> sortByMontant() {
        return transactions.stream()
                .sorted(Comparator.comparingDouble(
                        t -> t.getAsset().getPrix() * t.getQuantite()
                ))
                .toList();
    }

    public Map<String, Double> volumeParActif() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getAsset().getCode(),
                        Collectors.summingDouble(
                                t -> t.getAsset().getPrix() * t.getQuantite()
                        )
                ));
    }

    public Map<String, Double> totalBuySell() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getType,
                        Collectors.summingDouble(
                                t -> t.getAsset().getPrix() * t.getQuantite()
                        )
                ));
    }

    public double getVolumeByTrader(int idTrader) {
        return transactions.stream()
                .filter(t -> t.getTrader().getId() == idTrader)
                .mapToDouble(t ->
                        t.getAsset().getPrix() * t.getQuantite()
                )
                .sum();
    }

    public long getNombreOrdresByTrader(int idTrader) {
        return transactions.stream()
                .filter(t -> t.getTrader().getId() == idTrader)
                .count();
    }

    public List<Trader> getTopTraders(int n) {
        return traders.stream()
                .sorted((t1, t2) -> Double.compare(
                        getVolumeByTrader(t2.getId()),
                        getVolumeByTrader(t1.getId())
                ))
                .limit(n)
                .toList();
    }


    public Optional<Map.Entry<String, Double>> instrumentPlusEchange() {
        return volumeParActif().entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }
}

