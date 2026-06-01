package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TradingPlatform platform = new TradingPlatform();
        Scanner sc = new Scanner(System.in);

        Trader trader1 = new Trader("Ahmed", 10000);
        Trader trader2 = new Trader("Sara", 15000);
        platform.ajouterTrader(trader1);
        platform.ajouterTrader(trader2);

        Asset apple = new Stock("AAPL", "Apple", 150);
        Asset bitcoin = new CryptoCurrency("BTC", "Bitcoin", 30000);
        platform.ajouterAsset(apple);
        platform.ajouterAsset(bitcoin);

        int choix;

        do {
            System.out.println("\n===== Xtraid =====");
            System.out.println("1. Ajouter une transaction");
            System.out.println("2. Afficher transactions d’un trader");
            System.out.println("3. Afficher transactions BUY");
            System.out.println("4. Afficher transactions SELL");
            System.out.println("5. Volume par trader");
            System.out.println("6. Top N traders");
            System.out.println("7. Asset le plus échangé");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            choix = sc.nextInt();

            switch (choix) {

                case 1 -> {
                    System.out.print("Trader : ");
                    int t = sc.nextInt();
                    Trader trader = (t == 1) ? trader1 : trader2;

                    System.out.print("Asset (1=AAPL, 2=BTC) : ");
                    int a = sc.nextInt();
                    Asset asset = (a == 1) ? apple : bitcoin;

                    System.out.print("Type (BUY/SELL) : ");
                    String type = sc.next();

                    System.out.print("Quantité : ");
                    int qty = sc.nextInt();

                    System.out.print("Montant : ");
                    double montant = sc.nextDouble();

                    Transaction transaction = new Transaction(
                            trader,
                            asset,
                            type,
                            qty,
                            montant,
                            LocalDateTime.now()
                    );

                    platform.ajouterTransaction(transaction);
                    System.out.println("Transaction ajoutée !");
                }

                case 2 -> {
                    System.out.print("Trader : ");
                    int t = sc.nextInt();
                    Trader trader = (t == 1) ? trader1 : trader2;

                    platform.transactionsParTrader(trader)
                            .forEach(System.out::println);
                }

                case 3 -> platform.filtrerParType("BUY")
                        .forEach(System.out::println);

                case 4 -> platform.filtrerParType("SELL")
                        .forEach(System.out::println);

                case 5 -> platform.volumeParTrader()
                        .forEach((trader, volume) ->
                                System.out.println(trader.getNomcomplet() + " -> " + volume)
                        );

                case 6 -> {
                    System.out.print("Donner N : ");
                    int n = sc.nextInt();
                    platform.topNTraders(n)
                            .forEach(e ->
                                    System.out.println(e.getKey().getNomcomplet() + " : " + e.getValue())
                            );
                }

                case 7 -> platform.assetLePlusEchange()
                        .ifPresent(asset ->
                                System.out.println("Asset le plus echange : " + asset.getNom())
                        );

                case 0 -> System.out.println("Au revoir !");

                default -> System.out.println("Choix invalide");
            }

        } while (choix != 0);

        sc.close();
    }
}

