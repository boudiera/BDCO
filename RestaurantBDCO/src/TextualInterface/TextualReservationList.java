/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import java.util.*;
import java.lang.*;
import Modele.*;
import InterfaceMVC.*;

/**
 * Vue textuelle affichant la liste des réservations à venir
 *
 * @author mourinf
 */
public class TextualReservationList extends AbstractView {

    private final static TextualReservationList viewTextualReservation = new TextualReservationList();

    private TextualReservationList() {
    }

    /**
     *
     * @return TextualReservationList Retourne une instanciation unique de
     * l'affichage textuel
     */
    public static TextualReservationList singletonViewTextualReservationList() {
        return TextualReservationList.viewTextualReservation;
    }

    /**
     * Méthode affichant dans un tableau les reservations connues et à venir
     *
     * @param view Boolean
     */
    public void showView(boolean view) {

        if (!view) {
            return;
        }
        //Récupération de la liste des réservations de la Base de données
        ArrayList<Reservation> listReservations = this.getController().getReservationList();

        System.out.println("--------------------------------------~AFFICHAGE DES RESERVATIONS~---------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.print("| Reservation |");
        System.out.print("      Nom     |");
        System.out.print("     Date     |");
        System.out.print("   Telephone  |");
        System.out.print("     Heure    |");
        System.out.print(" nbPersonnes  |");
        System.out.println("  Code Table   ");

        for (Reservation reservation : listReservations) {

            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("|      " + reservation.getCodeReservation() + "      |");
            System.out.print(" " + reservation.printClientName() + "|"); //le nom ne devrait pas dépasser 13 caractères
            System.out.print("  " + reservation.getDate().writeDay() + "/" + reservation.getDate().writeMonth() + "/" + reservation.getDate().writeYear() + "  |");
            System.out.print("  " + reservation.getPhone() + "  |");
            System.out.print("     " + reservation.getDate().writeHour() + "h" + reservation.getDate().writeMin() + "    |");
            System.out.print(reservation.printNbPeople());

            // affichage code Table
            System.out.print("       ");
            for (Table table : reservation.getCodeTable()) {
                System.out.print(table.getCodeTable() + " ");
            }
            System.out.println();
        }
        System.out.println("");
        System.out.println("");
        gestionEvent();
    }

    /**
     * Gestion des entrées clavier: ajout réservation ou quitter l'application
     */
    public void gestionEvent() {

        do {
            afficheChoix();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            if (choix.equalsIgnoreCase("r")) {
                this.getController().setView(new TextualAjoutReservation(this.getController()));
            } else if (choix.equalsIgnoreCase("q")) {
                System.exit(0);
            }

        } while (true);

    }

    /**
     * Affichage des consignes
     */
    private void afficheChoix() {
        System.out.println("Appuyez sur r pour entrer une nouvelle reservation \n"
                + "Appuyez sur q pour quitter");
    }

}
