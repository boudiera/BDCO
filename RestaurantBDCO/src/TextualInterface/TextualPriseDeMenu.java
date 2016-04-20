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
     ArrayList<TypeArticle>  TypeArticlePropose;
    private boolean commandeFinie = false;

    public TextualPriseDeMenu(Controller controller, Menu menu, AbstractView viewCommande, Commande commande) {
        this.menu = menu;
        this.viewCommande = viewCommande;
        this.commande = commande;
        TypeArticlePropose = new ArrayList<>();
        setController(controller);
    }

    @Override
    public void showView(boolean b) {
        ArrayList<TypeArticle> typeArticlesMenu;
        typeArticlesMenu=getController().getTypeArticleMenu(this.menu.getName());
        
     
        while (!commandeFinie) {
            int i = 1;
            System.out.println("--------------------- PRISE DE MENU  " + this.menu.getName() + "  -------------------------");
         
            if (typeArticlesMenu.contains(TypeArticle.ENTREE)){
                System.out.println(i+".Entrées ");
                i++;
                TypeArticlePropose.add(TypeArticle.ENTREE);
            }    
            // On a forcement un plat
                System.out.println(i+".Plats ");
                i++;
                TypeArticlePropose.add(TypeArticle.PLAT);
            if (typeArticlesMenu.contains(TypeArticle.DESSERT)){
                System.out.println(i+".Desserts ");
                i++;
                TypeArticlePropose.add(TypeArticle.DESSERT);
            }    
            if (typeArticlesMenu.contains(TypeArticle.BOISSON)){
                System.out.println(i+".Boissons ");
                i++;
                TypeArticlePropose.add(TypeArticle.BOISSON);
            }   
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
        this.getController().addArticleCommande(menu, commande);
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
        int valChoix = 0;
        try {
            valChoix = Integer.parseInt(choix);
            if (valChoix<= 0 || valChoix > TypeArticlePropose.size())
                throw new Exception();
            afficheListeArticle(valChoix);
        }
            catch (Exception e){     
        }                  
        if (choix.equalsIgnoreCase("v")) {
            if (this.getController().verifyMenu(menu)) {
                this.commandeFinie = true;
            } else {
                System.out.println("Le menu doit contenir un plat et un autre article");
            }
        } else if (choix.equalsIgnoreCase("q")) {
            System.out.println(" ------------- Prise de Menu annulée ------------");
            this.getController().setView(this.viewCommande);

        } 

    }

    private void afficheListeArticle(int choix) {
        ArrayList<Article> choixArticles = new ArrayList<>();
        String titreSousMenu = "";
        int compteur=1;
        TypeArticle type = TypeArticlePropose.get(choix-1);
        switch (type) {
            case ENTREE:
                // Appel d'une fonction du controller qui nous renvois une liste d'article d'entrées pour un menu donné
                choixArticles= this.getController().getArticlesMenu(TypeArticle.ENTREE, this.menu.getName());
                //choixArticles.add(new Article("Salade", TypeArticle.ENTREE, 12, "caca"));
                //choixArticles.add(new Article("Saucisse", TypeArticle.ENTREE, 10, "ppp"));
                //choixArticles.add(new Article("Carotte", TypeArticle.ENTREE, 5, "lol"));
                titreSousMenu = "--------------------- Affichage des entrées disponibles -------------------------\n";

                break;
            case PLAT:
                choixArticles= this.getController().getArticlesMenu(TypeArticle.PLAT, this.menu.getName());

                //choixArticles.add(new Article("Steak", TypeArticle.PLAT, 12, "viande"));
                //choixArticles.add(new Article("Poulet", TypeArticle.PLAT, 10, "viande"));
                //choixArticles.add(new Article("Poisson panné", TypeArticle.PLAT, 5, "poisson"));
                // Appel d'une fonction du controller qui nous renvois une liste d'article de plats  pour un menu donné
                titreSousMenu = "--------------------- Affichage des plats disponibles -------------------------\n";
                break;
            case DESSERT:// Appel d'une fonction du controller qui nous renvois une liste d'article de desserts  pour un menu donné
                choixArticles= this.getController().getArticlesMenu(TypeArticle.DESSERT, this.menu.getName());
                //choixArticles.add(new Article("creme", TypeArticle.DESSERT, 12, "viande"));
                //choixArticles.add(new Article("chocolat", TypeArticle.DESSERT, 10, "viande"));
                titreSousMenu = "--------------------- Affichage des desserts disponibles -------------------------\n";
                break;
            case BOISSON:// Appel d'une fonction du controller qui nous renvois une liste d'article de boissons  pour un menu donné
                choixArticles= this.getController().getArticlesMenu(TypeArticle.BOISSON, this.menu.getName());
                titreSousMenu = "--------------------- Affichage des boissons disponibles -------------------------\n";
                break;
            default:
                choixArticles=null;
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
                System.out.println("\nSelectionnez l'article voulu ou appuyez sur q pour revenir au menu");
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
                System.out.println("la valeur entrée est un entier positif et doit etre affichée dans la liste des articles ");
            }
        }

       
        this.getController().addArticleMenu(choixArticles.get(articleIndex-1), menu);
      
        System.out.println(" ------ >> Article " + choixArticles.get(articleIndex - 1).getName() + " selectionné en quantité " + quantite);
       return 0;
    }

}


