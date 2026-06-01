# XTrade - Application de Trading Simplifiée

## Contexte du projet
La société XTrade, spécialisée dans les solutions informatiques financières, souhaite développer une application de trading simplifiée destinée à la formation et à la simulation des opérations de marché.

Cette application a pour objectif de permettre à des utilisateurs, appelés traders, de gérer un portefeuille d’investissement virtuel et d’effectuer des opérations d’achat et de vente d’actifs financiers (actions et crypto-monnaies) dans un environnement contrôlé.

Chaque trader disposera d’un capital virtuel lui permettant d’investir sur un marché simulé proposant une liste d’actifs financiers avec des prix prédéfinis.

Le système devra assurer le suivi des portefeuilles, la mise à jour des soldes, ainsi que l’enregistrement de l’historique des transactions.

## Fonctionnalités principales

- **Ajouter des actifs et Afficher les actifs disponibles** : permet d’afficher la liste de tous les actifs financiers avec leurs informations principales (code, nom, prix unitaire, type d’actif).
- **Ajouter un trader** : permet d’ajouter un trader avec les informations suivantes (Identifiant, Nom, Solde initial)
- **Créer un portefeuille** : permet d’associer un portefeuille à un trader afin de stocker ses actifs financiers.
- **Consulter le portefeuille** : permet d’afficher (Les actifs détenus, Les quantités associées, La valeur totale du portefeuille)
- **Acheter un actif** : permet d’acheter un actif financier en vérifiant (Que le trader dispose d’un solde suffisant, Que la quantité est strictement positive)
- **Vendre un actif** : permet de vendre un actif financier en vérifiant (Que l’actif existe dans le portefeuille, Que la quantité détenue est suffisante)
- **Historique des transactions** : Enregistrement et affichage de l’historique des opérations (Type d’opération, Actif, Quantité, Prix, Date)
- **Validation des données** : interdire les montants négatifs ou nuls, empêcher la vente d’un actif non détenu, vérifier l’unicité du code des actifs

## Bonus

- Calcul des gains et pertes
- Calcul de la performance globale du trader
- Export des transactions
- Export de l’historique vers un fichier CSV ou Excel, déclenché depuis le menu console.

## Conception – Diagramme de classes

Le diagramme doit inclure au minimum :
`Person`, `Trader` (héritage de `Person`), `Asset` (classe abstraite), `Stock` (héritage de `Asset`), `CryptoCurrency` (héritage de `Asset`), `Portfolio`, `Transaction`, `TradingPlatform` / `Market`.

## Les classes JAVA demandées

- **Person** : Représente une personne de manière générale.
- **Trader** : Hérite de `Person` et représente un utilisateur du système de trading.
- **Asset** : Classe abstraite représentant un actif financier générique.
- **Stock** : Hérite de `Asset` et représente une action.
- **CryptoCurrency** : Hérite de `Asset` et représente une crypto-monnaie.
- **Portfolio<T>** : Classe générique représentant le portefeuille d’un trader.
- **Transaction** : Représente une opération de trading.
- **TradingPlatform / Market** : Gère les traders, les actifs et centralise les opérations métier.
- **Main** : Classe principale offrant une interface de menu console.
