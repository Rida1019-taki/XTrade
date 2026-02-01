package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TradingPlatform platform = new TradingPlatform();
        int choix = -1;

        while (choix != 0) {
            System.out.println(
                    """
                    
                    
                        ██╗  ██╗████████╗██████╗  █████╗ ██╗██████╗ 
                        ╚██╗██╔╝╚══██╔══╝██╔══██╗██╔══██╗██║██╔══██╗
                         ╚███╔╝    ██║   ██████╔╝███████║██║██║  ██║
                         ██╔██╗    ██║   ██╔══██╗██╔══██║██║██║  ██║
                        ██╔╝ ██╗   ██║   ██║  ██║██║  ██║██║██████╔╝
                        ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═════╝ 
                    
                                        X t r a i d
                    """
            );


            System.out.println("\n====== XTRADE MENU ======");
            System.out.println("1. Ajouter un actif");
            System.out.println("2. Afficher les actifs");
            System.out.println("3. Ajouter un trader");
            System.out.println("4. Acheter un actif");
            System.out.println("5. Vendre un actif");
            System.out.println("6. Consulter portefeuille");
            System.out.println("7. Historique des transactions");
            System.out.println("8. Changer prix d'un actif");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            choix = sc.nextInt();

            try {
                switch (choix) {

                    case 1: {
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
                        break;
                    }

                    case 2:
                        platform.afficherActifs();
                        break;

                    case 3: {
                        System.out.print("Nom : ");
                        String nom = sc.next();
                        System.out.print("Solde initial : ");
                        double solde = sc.nextDouble();

                        platform.ajouterTrader(new Trader(nom, solde));
                        System.out.println("Trader ajouté.");
                        break;
                    }

                    case 4: {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        System.out.print("Code Actif : ");
                        String code = sc.next();
                        System.out.print("Quantité : ");
                        int qte = sc.nextInt();

                        platform.acheterActif(id, code, qte);
                        System.out.println("Achat effectué.");
                        break;
                    }

                    case 5: {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        System.out.print("Code Actif : ");
                        String code = sc.next();
                        System.out.print("Quantité : ");
                        int qte = sc.nextInt();

                        platform.vendreActif(id, code, qte);
                        System.out.println("Vente effectuée.");
                        break;
                    }

                    case 6: {
                        System.out.print("ID Trader : ");
                        int id = sc.nextInt();
                        platform.consulterPortefeuille(id);
                        break;
                    }

                    case 7:
                        platform.afficherHistorique();
                        break;

                    case 8: {
                        System.out.print("Code Actif : ");
                        String code = sc.next();
                        System.out.print("Nouveau prix : ");
                        double prix = sc.nextDouble();

                        platform.changerPrixAsset(code, prix);
                        break;
                    }

                    case 0:
                        System.out.println("Au revoir 👋");
                        break;

                    default:
                        System.out.println("Choix invalide !");
                }

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        sc.close();
    }
}
