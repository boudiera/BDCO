/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import InterfaceMVC.AbstractView;
import InterfaceMVC.Controller;
import Modele.Article;
import Modele.Commande;
import Modele.Menu;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arnaud
 */
public class TextualPriseDeMenu extends AbstractView {

    Menu menu;
    AbstractView viewCommande;
    Commande commande;
    private boolean commandeFinie = false;

    public TextualPriseDeMenu(Controller controller, Menu menu, AbstractView viewCommande, Commande commande) {
        this.menu = menu;
        this.viewCommande = viewCommande;
        this.commande = commande;
        setController(controller);
    }

    @Override
    public void showView(boolean b) {

        while (!commandeFinie) {

            System.out.println("--------------------- PRISE DE MENU  " + this.menu.getName() + "  -------------------------");
            System.out.println("1.Entrées ");
            System.out.println("2.Plats ");
            System.out.println("3.Desserts ");
            System.out.println("4.Boissons ");
            System.out.println("");
            System.out.println("Choix du menu selectionné -> ");
            if (this.menu.getList().isEmpty()) {
                System.out.println("Vide ");
            } else {
                this.menu.printMenu();
            }

            lectureEntreeMenu();

        }
        // La commande est finie, on crée un objet commande et on l'envoi au controller
        // Appel d'une fonction du controller qui enregistre la commande dans l'application
        System.out.println("------------- Menu " + this.menu.getName() + " Enregistré ------------");
        this.getController().ajoutArticleCommande(menu, commande);
        this.getController().setView(this.viewCommande);

    }

    private void afficheChoixMenu() {
        System.out.println("\nEntrez un chiffre entre 1 et 5 pour afficher une liste d'articles du type souhaité\n"
                + "Appuyez sur q pour annuler la prise du menu\n"
                + "Appuyez sur v pour valider le menu");
    }

    private void lectureEntreeMenu() {

        afficheChoixMenu();
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        if (choix.equalsIgnoreCase("1")) {
            afficheListeArticle(1);
        } else if (choix.equalsIgnoreCase("2")) {
            afficheListeArticle(2);
        } else if (choix.equalsIgnoreCase("3")) {
            afficheListeArticle(3);
        } else if (choix.equalsIgnoreCase("4")) {
            afficheListeArticle(4);
        } else if (choix.equalsIgnoreCase("v")) {
            if (this.getController().menuValid(menu)) {
                this.commandeFinie = true;
            } else {
                System.out.println("Le menu doit contenir un plat ");
            }
        } else if (choix.equalsIgnoreCase("q")) {
            System.out.println(" ------------- Prise de Menu annulée ------------");
            this.getController().setView(this.viewCommande);

        }

    }

    private void afficheListeArticle(int choix) {
        ArrayList<Article> choixArticles = new ArrayList<>();
        String titreSousMenu = "";

        switch (choix) {
            case 1:
                // Appel d'une fonction du controller qui nous renvois une liste d'article d'entrées pour un menu donné
                choixArticles.add(new Article("Salade", TypeArticle.Entrée, 12, "caca"));
                choixArticles.add(new Article("Saucisse", TypeArticle.Entrée, 10, "ppp"));
                choixArticles.add(new Article("Carotte", TypeArticle.Entrée, 5, "lol"));
                titreSousMenu = "--------------------- Affichage des entrées disponibles -------------------------\n";

                break;
            case 2:
                choixArticles.add(new Article("Steak", TypeArticle.Plat, 12, "viande"));
                choixArticles.add(new Article("Poulet", TypeArticle.Plat, 10, "viande"));
                choixArticles.add(new Article("Poisson panné", TypeArticle.Plat, 5, "poisson"));
                // Appel d'une fonction du controller qui nous renvois une liste d'article de plats  pour un menu donné
                titreSousMenu = "--------------------- Affichage des plats disponibles -------------------------\n";
                break;
            case 3:// Appel d'une fonction du controller qui nous renvois une liste d'article de desserts  pour un menu donné
                choixArticles.add(new Article("creme", TypeArticle.Dessert, 12, "viande"));
                choixArticles.add(new Article("chocolat", TypeArticle.Dessert, 10, "viande"));
                titreSousMenu = "--------------------- Affichage des desserts disponibles -------------------------\n";
                break;
            case 4:// Appel d'une fonction du controller qui nous renvois une liste d'article de boissons  pour un menu donné
                titreSousMenu = "--------------------- Affichage des boissons disponibles -------------------------\n";
                break;
            default:
                System.exit(0);
                break;
        }
    
            System.out.println(titreSousMenu);
            for (Article article : choixArticles) {
                System.out.println("Article n°" + (choixArticles.indexOf(article) + 1) + " " + article.toString());
            }

            lectureEntreeSousMenu(choixArticles);
        }
    

    private int lectureEntreeSousMenu(ArrayList<Article> choixArticles) {

        boolean articleSelectione = false;
        int articleIndex = 0;
        int quantite = 1;
        String choix = "";
        while (!articleSelectione) {
            try {
                System.out.println("\nSelectionnez l'article voulu ou appuyer sur q pour revenir au menu");
                Scanner sc = new Scanner(System.in);
                choix = sc.nextLine();
                if (choix.equalsIgnoreCase("q")) {
                    return -1;
                }
                articleIndex = Integer.parseInt(choix);
                if (articleIndex > choixArticles.size() || articleIndex < 0) {
                    throw new Exception();
                }
                articleSelectione = true;
            } catch (Exception E) {
                System.out.println("la valeur entrée est un entier positif et doit etre affiché dans la liste des articles ");
            }
        }

        // Si on a choisi un menu -> ouverture d'une vue de menu
        // if (articleIndex == 5)
        // Ajout a la liste des articles + actualisation du prix
        this.getController().ajoutArticleMenu(choixArticles.get(articleIndex - 1), menu);
        //this.commande.setPrice(this.commande.getPrice() + choixArticles.get(articleIndex - 1).getPrice());
        System.out.println(" ------ >> Article " + choixArticles.get(articleIndex - 1).getName() + " selectionné en quantité " + quantite);
       return 0;
    }

}

