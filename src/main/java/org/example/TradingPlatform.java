package org.example;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TradingPlatform {

    private List<Transaction> transactions = new ArrayList<>();
    private List<Trader> traders = new ArrayList<>();
    private List<Asset> assets = new ArrayList<>();


    public void ajouterTrader(Trader trader) {
        traders.add(trader);
    }

    public void ajouterAsset(Asset asset) {
        assets.add(asset);
    }

    public void ajouterTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public List<Transaction> transactionsParTrader(Trader trader) {
        return transactions.stream()
                .filter(t -> t.getTrader().equals(trader))
                .toList();
    }

    public List<Transaction> filtrerParType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equals(type))
                .toList();
    }

    public List<Transaction> filtrerParAsset(Asset asset) {
        return transactions.stream()
                .filter(t -> t.getAsset().equals(asset))
                .toList();
    }

    public List<Transaction> filtrerParDate(LocalDateTime debut, LocalDateTime fin) {
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(debut)
                        && !t.getDate().isAfter(fin))
                .toList();
    }

    public List<Transaction> trierParDate() {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .toList();
    }

    public List<Transaction> trierParMontant() {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getMontant))
                .toList();
    }


    public Map<Asset, Integer> volumeParAsset() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getAsset,
                        Collectors.summingInt(Transaction::getQuantity)
                ));
    }

    public double montantTotalParType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equals(type))
                .mapToDouble(Transaction::getMontant)
                .sum();
    }


    public Map<Trader, Integer> volumeParTrader() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getTrader,
                        Collectors.summingInt(Transaction::getQuantity)
                ));
    }

    public Map<Trader, Long> nombreOrdresParTrader() {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getTrader,
                        Collectors.counting()
                ));
    }

    public List<Map.Entry<Trader, Integer>> topNTraders(int n) {
        return volumeParTrader().entrySet().stream()
                .sorted(Map.Entry.<Trader, Integer>comparingByValue().reversed())
                .limit(n)
                .toList();
    }



    public Optional<Asset> assetLePlusEchange() {
        return volumeParAsset().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public double totalBuy() {
        return montantTotalParType("BUY");
    }

    public double totalSell() {
        return montantTotalParType("SELL");
    }

    public List<String> zjzdnbz(){
        return assets.stream()
                .filter(t -> t.getPrix() > 100)
                .map(r -> r.getNom())
                .toList();
    }
    public Integer zfzdv(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return list.stream().filter(f -> f % 2 == 0).mapToInt(r -> r).sum();
    }

    public List<String> vefvef(){
        List<String> csc = List.of("ffve" , "jkebfek");
        return csc.stream().filter(r -> r.length() > 4).toList();
    }
    public List<String> cc(){
        List<String> cf = new ArrayList<>();
        cf.add("jefbejb");
        return cf.stream().filter(e -> e.startsWith("j")).toList();
    }

}

