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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Arnaud
 */
public class TextualFacture extends AbstractView {

    int codeReservation;
    float prix = 0;

    public TextualFacture(Controller controller, int codeReservation) {
        setController(controller);
        this.codeReservation = codeReservation;
    }

    @Override
    public void showView(boolean b) {
        String choix;
        System.out.println("----------------------- FACTURE --------------------");
        ArrayList<Article> listArticle = this.getController().getFacture(codeReservation);

        HashMap<String, Integer> regroupeQuantite = new HashMap<>();
        HashMap<String, Float> regroupePrix = new HashMap<>();
        for (Article a : listArticle) {
            a.printArticle();
            prix = prix + a.getPrice() * a.getQuantity();
        }

        System.out.println("--->>> Prix total : " + prix);
        this.getController().AjoutPrix(codeReservation, prix);
        do {
            System.out.println("Appuyer sur v pour valider et revenir au menu des réservations");
            Scanner sc = new Scanner(System.in);
            choix = sc.nextLine();

        } while (!choix.equalsIgnoreCase("v"));
        this.getController().setView(TextualSelectionReservation.singletonViewTextualReservationList());
    }

}
