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
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Arnaud
 */
public class TextualPriseDeCommande extends AbstractView {

   // private float prix;
    //private int codeReservation;
    private boolean commandeFinie = false;
    //private List<Article> listeArticle;
    private Commande commande;

    public TextualPriseDeCommande(Controller controller, int codeReservation) {
        setController(controller);
        this.commande = new Commande(codeReservation, "default", new ArrayList<Article>());
    }

    @Override
    public void showView(boolean b) {

        while (!commandeFinie) {

            System.out.println("--------------------- PRISE DE COMMANDE -------------------------");
            System.out.println("1.Entrées ");
            System.out.println("2.Plats ");
            System.out.println("3.Desserts ");
            System.out.println("4.Boissons ");
            System.out.println("5.Menu ");

            lectureEntreeMenu();

        }
        // La commande est finie, on crée un objet commande et on l'envoi au controller
        // Appel d'une fonction du controller qui enregistre la commande dans l'application
        System.out.println(" ------------- Commande Enregistrée ------------");
        // Identifier est defaut car on ne l'utilise pas dans cet executable
        this.getController().AjoutCommande(this.commande.getCodeReservation(), this.commande.getIdentifier(), this.commande.getListArticles());
        this.getController().setView(new TextualMenuCommande(this.commande.getCodeReservation(), this.getController()));
    }

    private void afficheChoixMenu() {
        System.out.println("\nEntrez un chiffre entre 1 et 5 pour afficher une liste d'articles du type souhaité\n"
                + "Appuyer sur q pour annuler la commande\n"
                + "Appuyer sur v pour valider la commande");
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
        } else if (choix.equalsIgnoreCase("5")) {
            afficheListeArticle(5);
        } else if (choix.equalsIgnoreCase("v")) {
            this.commandeFinie = true;
        } else if (choix.equalsIgnoreCase("q")) {
            System.out.println(" ------------- Commande Annulée ------------");
            this.getController().setView(new TextualMenuCommande(this.commande.getCodeReservation(), this.getController()));
            System.exit(0);
        }

    }

    private void afficheListeArticle(int choix) {
        ArrayList<Article> choixArticles = new ArrayList<>();
        choixArticles.add(new Article("Salade", TypeArticle.Entrée,12, "caca"));
        boolean affichageListeArticleFini = false;
        while (!affichageListeArticleFini) {
            switch (choix) {
                case 1:
                    // Appel d'une fonction du controller qui nous renvois une liste d'article d'entrées
                    System.out.println("--------------------- Affichage des entrées disponibles -------------------------");
                    break;
                case 2:
                    // Appel d'une fonction du controller qui nous renvois une liste d'article de plats
                    System.out.println("--------------------- Affichage des plats disponibles -------------------------");
                    break;
                case 3:// Appel d'une fonction du controller qui nous renvois une liste d'article de desserts
                    System.out.println("--------------------- Affichage des desserts disponibles -------------------------");
                    break;
                case 4:// Appel d'une fonction du controller qui nous renvois une liste d'article de boissons
                    System.out.println("--------------------- Affichage des boissons disponibles -------------------------");
                    break;
                case 5:// Appel d'une fonction du controller qui nous renvois une liste d'article de menu
                    System.out.println("--------------------- Affichage des menu disponibles -------------------------");
                    break;
                default:
                    break;
            }
            for (Article article : choixArticles){
                System.out.println(article.toString());
            }
            affichageListeArticleFini = lectureEntreeSousMenu(choixArticles);
        }
    }

    private boolean lectureEntreeSousMenu(ArrayList<Article> choixArticles) {

        boolean articleSelectione = false;
        boolean quantiteSelectione = false;
        int articleIndex = 0;
        int quantite = 0;
        String choix;
        while (!articleSelectione) {
            try {
                System.out.println("Selectionnez l'article voulu ou appuyer sur q pour revenir au menu");
                Scanner sc = new Scanner(System.in);
                choix = sc.nextLine();
                if (choix.equalsIgnoreCase("q")) {
                        return true;
                }
                articleIndex = Integer.parseInt(choix);
                if (articleIndex > choixArticles.size() ||  articleIndex < 0) {
                    throw new Exception();
                }
                articleSelectione = true;
            } catch (Exception E) {
                System.out.println("la valeur entrée est un entier positif et doit etre affiché dans la liste des articles ");
            }
        }

        while (!quantiteSelectione) {
            try {
                System.out.println("Selectionnez la quantité voulue pour cet article ou appuyez sur q pour revenir à la selection des articles");
                Scanner sc = new Scanner(System.in);
                choix = sc.nextLine();
                
                if (choix.equalsIgnoreCase("q")) {
                        return false;
                }
                quantite = Integer.parseInt(choix);
                quantiteSelectione = true;
                if (quantite <= 0) {
                    throw new Exception();
                }
            } catch (Exception E) {
                System.out.println("la valeur entrée est un entier positif ");
            }
        }
        // Ajout a la liste des articles + actualisation du prix
        for (int i = 0; i < quantite ;i++)
            this.commande.getListArticles().add(choixArticles.get(articleIndex-1));
        this.commande.setPrice( this.commande.getPrice()+ choixArticles.get(articleIndex-1).getPrice());
        System.out.println(" ------ >> Article " + choixArticles.get(articleIndex-1).getName() + " selectionné en quantité " + quantite );
        return false;
    }

}
