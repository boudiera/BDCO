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
 * Vue de prise de commande. Propose un choix selon les différents types
 * d'article.
 *
 * @author Arnaud
 */
public class TextualPriseDeCommande extends AbstractView {

    private boolean commandeFinie = false;

    private Commande commande;
    private int numCommande;
    private int codeReservation;
    private int codeCarte;

    public TextualPriseDeCommande(Controller controller, int codeReservation, int numCommande) {
        this.numCommande = numCommande;
        this.codeReservation = codeReservation;
        this.commande = new Commande(codeReservation, String.valueOf(this.numCommande + 1), new ArrayList<Article>());
        setController(controller);
    }

    /**
     * Vue principale affichant les types d'articles selectionnables par
     * l'utilisateur. Enregistre la commande une fois celle-ci validée.
     *
     * @param b boolean
     */
    @Override
    public void showView(boolean b) {

        this.codeCarte = this.getController().getCodeCarte(codeReservation);
        while (!commandeFinie) {
            System.out.println("--------------------- PRISE DE COMMANDE -------------------------");
            System.out.println("1.Entrées ");
            System.out.println("2.Plats ");
            System.out.println("3.Desserts ");
            System.out.println("4.Boissons ");
            System.out.println("5.Menu ");
            System.out.println("");
            System.out.println("Commande Actuelle : ");
            if (this.commande.getListArticles().size() == 0) {
                System.out.println("Vide ");
            } else { //Affichage de l'état actuel de la commande
                this.commande.printArticle();
                System.out.println("Prix : " + this.commande.getPrice());
            }
            lectureEntreeMenu();
        }
   
        System.out.println("------------- Commande numéro " + this.numCommande + " Enregistrée ------------");
         // Appel d'une fonction du controller qui enregistre la commande dans l'application et la base de donnée
        this.getController().addCommande(commande);
        // Appel de la fonction du controlleur qui enregistre la commande dans la BD    
        this.getController().setView(new TextualMenuCommande(this.commande.getCodeReservation(), numCommande + 1, this.getController()));
    }

    private void afficheChoixMenu() {
        System.out.println("\nEntrez un chiffre entre 1 et 5 pour afficher une liste d'articles du type souhaité\n"
                + "Appuyez sur 'q' pour annuler la commande\n"
                + "Appuyez sur 'v' pour valider la commande");
    }

    /**
     * Lecture des entrées clavier et affichage des listes d'articles
     * correspondantes.
     */
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
            this.getController().setView(new TextualMenuCommande(this.commande.getCodeReservation(), numCommande, this.getController()));
            System.exit(0);
        }
    }

    /**
     *
     * @param choix int. Entier compris entre 1 et 5 qui permet de selectionner
     * la liste d'article à afficher selon le type.
     */
    private void afficheListeArticle(int choix) {
        ArrayList<Article> choixArticles = new ArrayList<>();
        boolean estVueMenu = false;
        String titreSousMenu = "";

        switch (choix) {
            case 1:
                // Appel d'une fonction du controller qui nous renvois une liste d'article d'entrées
                titreSousMenu = "--------------------- Affichage des entrées disponibles -------------------------\n";
                choixArticles = this.getController().getArticles(codeCarte, TypeArticle.ENTREE);
                break;
            case 2:
                // Appel d'une fonction du controller qui nous renvois une liste d'article de plats
                titreSousMenu = "--------------------- Affichage des plats disponibles -------------------------\n";
                choixArticles = this.getController().getArticles(codeCarte, TypeArticle.PLAT);
                break;
            case 3:// Appel d'une fonction du controller qui nous renvois une liste d'article de desserts
                titreSousMenu = "--------------------- Affichage des desserts disponibles -------------------------\n";
                choixArticles = this.getController().getArticles(codeCarte, TypeArticle.DESSERT);
                break;
            case 4:// Appel d'une fonction du controller qui nous renvois une liste d'article de boissons
                titreSousMenu = "--------------------- Affichage des boissons disponibles -------------------------\n";
                choixArticles = this.getController().getArticles(codeCarte, TypeArticle.BOISSON);
                break;
            case 5:// Appel d'une fonction du controller qui nous renvois une liste d'article de menu
                titreSousMenu = "--------------------- Affichage des menu disponibles -------------------------\n";
                choixArticles = this.getController().getArticles(codeCarte, TypeArticle.MENU);
                estVueMenu = true;
                break;
            default:
                break;
        }
        System.out.println(titreSousMenu);
        for (Article article : choixArticles) { //Affichage des articles de la liste selectionnée
            System.out.println("Article n°" + (choixArticles.indexOf(article) + 1) + " " + article.toString());
        }
        lectureEntreeSousMenu(choixArticles, estVueMenu); //appel de la méthode pour enregistrer les choix de l'utilisateur
    }

    /**
     * Méthode gérant les lectures des entrées clavier et enregistrant les choix de l'utilisateur.
     * @param choixArticles
     *          ArrayList<Article> liste d'articles disponibles pour un type donné
     * @param estVueMenu 
     *          boolean. True si le type selectionné est un menu, false sinon.
     */
    private void lectureEntreeSousMenu(ArrayList<Article> choixArticles, boolean estVueMenu) {
        boolean articleSelectione = false;
        boolean quantiteSelectione = false;
        int articleIndex = 0;
        int quantite = 1;
        String choix;
        while (!articleSelectione) {
            try { //lecture entrée clavier, gestion exceptions
                System.out.println("\nSelectionnez l'article voulu ou appuyer sur q pour revenir au menu");
                Scanner sc = new Scanner(System.in);
                choix = sc.nextLine();
                if (choix.equalsIgnoreCase("q")) {
                    return;
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
        // Si on a choisi un menu comme article la quantité est 1, sinon on demande l'information à l'utilisateur.
        if (!estVueMenu) {
            while (!quantiteSelectione) {
                try {
                    System.out.println("Selectionnez la quantité voulue de " + choixArticles.get(articleIndex - 1).getName());
                    Scanner sc = new Scanner(System.in);
                    choix = sc.nextLine();

                    if (choix.equalsIgnoreCase("q")) {
                        return;
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
        } // Si on a choisi un menu -> ouverture d'une vue de menu
        else {
            this.getController().setView(new TextualPriseDeMenu(this.getController(), (Menu) choixArticles.get(articleIndex - 1), this, commande));
        }
        // Ajout à la liste des articles + actualisation du prix
        for (int i = 0; i < quantite; i++) {
            this.getController().addArticleCommande(choixArticles.get(articleIndex - 1), commande);
        }
        System.out.println(" ------ >> Article " + choixArticles.get(articleIndex - 1).getName() + " selectionné en quantité " + quantite);
    }
    
}
