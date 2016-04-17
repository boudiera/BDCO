/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import InterfaceMVC.AbstractView;
import InterfaceMVC.Controller;
import java.util.Scanner;

/**
 *
 * @author Arnaud
 */
public class TextualMenuCommande extends AbstractView {

    int codeReservation;
    int nbcommandes;
    public TextualMenuCommande(int codeReservation,int nbcommandes ,Controller controller) {
        this.codeReservation = codeReservation;
        this.nbcommandes = nbcommandes;
        this.setController(controller);
    }

    @Override
    public void showView(boolean b) {
        System.out.println("--------- Réservation numéro : " + codeReservation + " ---------------");
        gestionEvent();
    }

    private void afficheChoix() {
        System.out.println("Appuyez sur c pour prendre une nouvelle commande \n"
                + "Appuyez sur f pour produire la facture \n"
                + "Appyer sur q pour quitter");
    }

    private void gestionEvent() {

        do {
            afficheChoix();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            if (choix.equalsIgnoreCase("c")) {
                this.getController().setView(new TextualPriseDeCommande(this.getController(),codeReservation,nbcommandes));          
            } else if (choix.equalsIgnoreCase("f")) {
                this.getController().setView(new TextualFacture(this.getController(), codeReservation));          
            } else if (choix.equalsIgnoreCase("q")) {
                System.exit(0);
            }

        } while (true);

    }

}
