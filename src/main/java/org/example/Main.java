package org.example;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static TradingPlatform platform = new TradingPlatform();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Menu Admin");
            System.out.println("2. Menu Trader");
            System.out.println("3. Quitter");
            System.out.print("Choisir une option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    traderMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Merci d'avoir utilisé la plateforme !");
                    break;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    // ===== Menu Admin =====
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("1. Ajouter un actif");
            System.out.println("2. Afficher les actifs");
            System.out.println("3. Ajouter un trader");
            System.out.println("4. Retour");
            System.out.print("Choisir une option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Type (Action/Crypto): ");
                    String type = sc.nextLine();
                    System.out.print("Code: ");
                    String code = sc.nextLine();
                    System.out.print("Nom: ");
                    String nom = sc.nextLine();
                    System.out.print("Prix: ");
                    double prix = sc.nextDouble();
                    sc.nextLine();
                    if (type.equalsIgnoreCase("Action")) {
                        System.out.print("Entreprise: ");
                        String entreprise = sc.nextLine();
                        platform.ajouterActif(new Stock(code, nom, prix, entreprise));
                    } else {
                        System.out.print("Blockchain: ");
                        String blockchain = sc.nextLine();
                        platform.ajouterActif(new CryptoCurrency(code, nom, prix, blockchain));
                    }
                    System.out.println("Actif ajouté avec succès !");
                    break;
                case 2:
                    platform.afficherActifs();
                    break;
                case 3:
                    System.out.print("Nom Trader: ");
                    String nomTrader = sc.nextLine();
                    System.out.print("Solde initial: ");
                    double solde = sc.nextDouble();
                    sc.nextLine();
                    platform.ajouterTrader(new Trader(nomTrader, solde));
                    System.out.println("Trader ajouté avec succès !");
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    // ===== Menu Trader =====
    private static void traderMenu() {
        System.out.print("Entrer le nom du trader: ");
        String nomTrader = sc.nextLine();
        Trader trader = null;
        for (Trader t : platform.getAdmin().getTraders()) {
            if (t.getNomcomplet().equalsIgnoreCase(nomTrader)) {
                trader = t;
                break;
            }
        }
        if (trader == null) {
            System.out.println("Trader non trouvé !");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- Menu Trader ---");
            System.out.println("1. Acheter un actif");
            System.out.println("2. Vendre un actif");
            System.out.println("3. Consulter le portfolio");
            System.out.println("4. Consulter l'historique");
            System.out.println("5. Retour");
            System.out.print("Choisir une option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Code de l'actif à acheter: ");
                    String codeAchat = sc.nextLine();
                    Asset actifAchat = null;
                    for (Asset a : platform.getAdmin().getMarche()) {
                        if (a.getCode().equalsIgnoreCase(codeAchat)) {
                            actifAchat = a;
                            break;
                        }
                    }
                    if (actifAchat == null) {
                        System.out.println("Actif non trouvé !");
                        break;
                    }
                    System.out.print("Quantité: ");
                    int qteAchat = sc.nextInt();
                    sc.nextLine();
                    try {
                        trader.acheter(actifAchat, qteAchat);
                        System.out.println("Achat effectué avec succès !");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Code de l'actif à vendre: ");
                    String codeVente = sc.nextLine();
                    Asset actifVente = null;
                    for (Asset a : trader.getPortfolio().getActifs().keySet()) {
                        if (a.getCode().equalsIgnoreCase(codeVente)) {
                            actifVente = a;
                            break;
                        }
                    }
                    if (actifVente == null) {
                        System.out.println("Actif non trouvé dans le portfolio !");
                        break;
                    }
                    System.out.print("Quantité: ");
                    int qteVente = sc.nextInt();
                    sc.nextLine();
                    try {
                        trader.vendre(actifVente, qteVente);
                        System.out.println("Vente effectuée avec succès !");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    trader.afficherPortfolio();
                    break;
                case 4:
                    trader.afficherHistorique();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Option invalide !");
            }
        }
    }
}
