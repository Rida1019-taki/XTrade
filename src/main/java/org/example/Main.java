package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TradingPlatform platform = new TradingPlatform();

        int choix = -1;

        while (choix != 0) {

            System.out.println("\n========= XTRADE MENU =========");
            System.out.println("1. Ajouter Trader");
            System.out.println("2. Ajouter Actif");
            System.out.println("3. Ajouter Transaction");
            System.out.println("4. Transactions d’un Trader");
            System.out.println("5. Filtrer par Type (BUY/SELL)");
            System.out.println("6. Filtrer par Actif");
            System.out.println("7. Trier par Date");
            System.out.println("8. Trier par Montant");
            System.out.println("9. Volume total par Actif");
            System.out.println("10. Total BUY / SELL");
            System.out.println("11. Volume d’un Trader");
            System.out.println("12. Nombre d’ordres d’un Trader");
            System.out.println("13. Top N Traders");
            System.out.println("14. Instrument le plus échangé");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            choix = sc.nextInt();

            try {

                switch (choix) {

                    case 1 -> {
                        System.out.print("Nom : ");
                        String nom = sc.next();
                        System.out.print("Solde initial : ");
                        double solde = sc.nextDouble();

                        platform.ajouterTrader(new Trader(nom, solde));
                        System.out.println("Trader ajouté.");
                    }

                    case 2 -> {
                        System.out.print("Code : ");
                        String code = sc.next();
                        System.out.print("Nom : ");
                        String nom = sc.next();
                        System.out.print("Prix : ");
                        double prix = sc.nextDouble();
                        System.out.print("Type (1=Stock, 2=Crypto) : ");
                        int type = sc.nextInt();

                        if (type == 1)
                            platform.ajouterActif(new Stock(code, nom, prix));
                        else
                            platform.ajouterActif(new CryptoCurrency(code, nom, prix));

                        System.out.println("Actif ajouté avec succès.");
                    }

                    case 3 -> {
                        System.out.print("Type (BUY/SELL) : ");
                        String type = sc.next();
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        System.out.print("Code Actif : ");
                        String code = sc.next();
                        System.out.print("Quantité : ");
                        int qte = sc.nextInt();

                        Trader trader = platform.getTraderById(id);
                        Asset asset = platform.getAssetByCode(code);

                        platform.ajouterTransaction(
                                new Transaction(type, trader, asset, qte)
                        );

                        System.out.println("Transaction ajoutée.");
                    }

                    case 4 -> {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        platform.getTransactionsByTrader(id)
                                .forEach(System.out::println);
                    }

                    case 5 -> {
                        System.out.print("Type (BUY/SELL) : ");
                        String type = sc.next();
                        platform.filterByType(type)
                                .forEach(System.out::println);
                    }

                    case 6 -> {
                        System.out.print("Code Actif : ");
                        String code = sc.next();
                        platform.filterByAsset(code)
                                .forEach(System.out::println);
                    }

                    case 7 ->
                            platform.sortByDate()
                                    .forEach(System.out::println);

                    case 8 ->
                            platform.sortByMontant()
                                    .forEach(System.out::println);

                    case 9 -> {
                        System.out.println("=== Volume par Actif ===");
                        for (Map.Entry<String, Double> e :
                                platform.volumeParActif().entrySet()) {
                            System.out.println(e.getKey() + " -> " + e.getValue());
                        }
                    }

                    case 10 -> {
                        System.out.println("=== Total BUY / SELL ===");
                        for (Map.Entry<String, Double> e :
                                platform.totalBuySell().entrySet()) {
                            System.out.println(e.getKey() + " -> " + e.getValue());
                        }
                    }

                    case 11 -> {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        System.out.println("Volume = "
                                + platform.getVolumeByTrader(id));
                    }

                    case 12 -> {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        System.out.println("Nombre ordres = "
                                + platform.getNombreOrdresByTrader(id));
                    }

                    case 13 -> {
                        System.out.print("N : ");
                        int n = sc.nextInt();
                        platform.getTopTraders(n)
                                .forEach(t ->
                                        System.out.println(
                                                t.getNomcomplet() +
                                                        " -> " +
                                                        platform.getVolumeByTrader(t.getId())
                                        )
                                );
                    }

                    case 14 -> {
                        platform.instrumentPlusEchange()
                                .ifPresent(e ->
                                        System.out.println(
                                                "Instrument: "
                                                        + e.getKey()
                                                        + " | Volume: "
                                                        + e.getValue()
                                        ));
                    }

                    case 0 -> System.out.println("Au revoir 👋");

                    default -> System.out.println("Choix invalide !");
                }

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        sc.close();
    }
}
