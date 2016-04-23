/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import InterfaceMVC.AbstractView;
import InterfaceMVC.Controller;
import Modele.Commande;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arnaud
 */
public class TextualMenuCommande extends AbstractView {

    private int codeReservation;
    private int nbcommandes;
    ArrayList<Commande> commandes;

    public TextualMenuCommande(int codeReservation, int nbcommandes, Controller controller) {
        this.codeReservation = codeReservation;
        this.nbcommandes = nbcommandes;
        commandes = new ArrayList<>();
        this.setController(controller);

    }

    @Override
    public void showView(boolean b) {
        commandes = this.getController().getCommande(codeReservation);
        System.out.println("--------- Réservation numéro : " + codeReservation + " ---------------\n");

        System.out.println("Nouvelle(s) commande(s)  : ");

        if (commandes.isEmpty()) {
            System.out.println("Aucune \n");
        } else {
            System.out.println("");
            for (Commande c : commandes) {
                System.out.println("Commande " + c.getIdentifier());
                c.printArticle();
                System.out.println("");
            }
        }
        gestionChoix();
    }

    private void afficheChoix() {
        System.out.println("Appuyez sur 'c' pour prendre une nouvelle commande \n"
                + "Entrez 'delete' puis selectionnez un numero de commande pour la supprimer \n"
                + "Appuyez sur 'f' pour produire la facture des commandes de cette reservation \n"
                + "Appuyer sur 'v' pour valider la et les nouvelles commandes prises \n"
                + "Appuyez sur 'q' pour quitter et annuler toutes les nouvelles commandes prises");
    }

    private void gestionChoix() {

        do {
            afficheChoix();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            if (choix.equalsIgnoreCase("c")) {
                this.getController().setView(new TextualPriseDeCommande(this.getController(), codeReservation, nbcommandes));
            } else if (choix.equalsIgnoreCase("f")) {
                this.getController().setView(new TextualFacture(this.getController(), codeReservation));
            } else if (choix.equalsIgnoreCase("delete")) {
                gestionAnnulationCommande();
            } else if (choix.equalsIgnoreCase("v")) {
                for (Commande c : commandes) {
                    // On ajoute toutes les commandes à la BD
                    this.getController().endCommande(c);
                    // On supprime les commandes du niveau applicatif qui ne nous sont plus utiles
                    this.getController().deleteCommande(codeReservation, c);
                }
                this.getController().setView(TextualSelectionReservation.singletonViewTextualReservationList());
            } else if (choix.equalsIgnoreCase("q")) {
                System.out.println("-------------- Annulation des commandes ! ------------");
                for (Commande c : commandes) {
                    // On supprime les commandes du niveau applicatif qui ne nous sont plus utiles  
                    this.getController().deleteCommande(codeReservation, c);
                }
                this.getController().setView(TextualSelectionReservation.singletonViewTextualReservationList());
            }

        } while (true);

    }

    private int gestionAnnulationCommande() {
        int numCommande = 0;
        try {
            System.out.println(" Numero de commande à supprimer : ");
            Scanner sc = new Scanner(System.in);
            numCommande = sc.nextInt();

        } catch (Exception e) {
            System.out.println(" Le numéro de la commande a supprimer doit être un entier");
            return -1;
        }

        if (numCommande <= 0 || numCommande > commandes.size()) {
            System.out.println(" Le numéro de la commande à supprimer doit affiché sur la liste des commandes la reservation");
            return -1;
        }
        System.out.println(" ------- > Suppresion de la commande " + numCommande);
        this.getController().deleteCommande(codeReservation, commandes.get(numCommande - 1));
        this.getController().setView(this.getController().getView());        // Actualisation de la vue

        return 0;
    }

}
