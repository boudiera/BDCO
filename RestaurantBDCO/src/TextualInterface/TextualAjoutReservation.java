/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import InterfaceMVC.AbstractView;
import InterfaceMVC.Exceptions.ReservationException;
import InterfaceMVC.Controller;
import InterfaceMVC.Exceptions.*;
import Modele.Reservation;
import Modele.ReservationDate;
import Modele.Service;
import Modele.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Vue textuelle d'ajout de reservation et de prise d'informations associées
 * @author mourinf
 */
public class TextualAjoutReservation extends AbstractView {

    public TextualAjoutReservation(Controller controller) {
        this.setController(controller);
    }

    /**
     * Affichage de la vue: consigne et lecture des entrées clavier. Gestion des erreurs d'entrées.
     * @param b 
     *      boolean
     */
    @Override
    public void showView(boolean b) {
        String jour, mois, annee, heure, minutes;
        ArrayList<Table> codeTable = new ArrayList<>();
        String nbPersonnes;
        String nomClient;
        String tel;
        String choix;
        boolean verificationFini = false;
        String service;
        String localisation = "";
        ReservationDate date;

        // Creation de l'objet Textual_AjoutReservation au fur et à mesure que l'on rentre les champs de donnée
        System.out.println("----------------------Création d'une réservation ---------------- \n");
        System.out.println(" Veuillez entrer la date de la reservation : (xx/xx/xxxx) ");
        System.out.println(" Jour ( Entier ): ");
        jour = lectureEntree();

        System.out.println(" Mois ( Entier ): ");
        mois = lectureEntree();

        System.out.println(" Année ( Entier ) :  ");
        annee = lectureEntree();

        System.out.println(" Veuillez entrer l'horaire de la reservation au format 24h");
        System.out.println("Heure ( Entier ) : ");
        heure = lectureEntree();

        System.out.println("Minutes (Entier)  :");
        minutes = lectureEntree();

        System.out.println("Veuillez entrer le service ( MIDI ou SOIR ) : ");
        service = lectureEntree();

        System.out.println(" Veuillez entrer le nombre de personne : ");
        nbPersonnes = lectureEntree();

        System.out.println(" Veuillez entrer le nom du client : ");
        nomClient = lectureEntree();

        System.out.println(" Veuillez entrer le numero de telephone ");
        tel = lectureEntree();

        while (!verificationFini) {
            try {
                this.getController().verifyAddReservation(annee, mois, jour, heure, minutes, nbPersonnes, tel, service, nomClient);

                HashMap<String, ArrayList<Table>> listTablesOccupeesParLocalisation = this.getController().getTablesLibresByLocalisation(annee, mois, jour, service, nbPersonnes);

                Set<String> s = listTablesOccupeesParLocalisation.keySet();
                Iterator<String> iterator = s.iterator();
                // Tableau qui contient les nomes de zones , indexé par des entiers ( pour la selection des zones après )
                ArrayList<String> nomZone = new ArrayList<>();
                System.out.println("Endroit(s) de localisation possible --->");
                int i = 1;
                while (iterator.hasNext()) {
                    String nomLocalisation = iterator.next();
                    nomZone.add(nomLocalisation);
                    System.out.println(i + ". " + nomLocalisation);
                    i++;

                }

                boolean choixLocalisationfini;
                int valeurChoix = 0;
                do {
                    choixLocalisationfini = false;
                    System.out.println("Choississez la localisation voulue");
                    choix = lectureEntree();
                    try {
                        valeurChoix = Integer.parseInt(choix);
                        choixLocalisationfini = true;
                    } catch (Exception e) {
                        System.out.println(" La valeur entrée doit être un entier positif");
                    }
                } while (!choixLocalisationfini && valeurChoix > 0 && valeurChoix < listTablesOccupeesParLocalisation.size());

                localisation = nomZone.get(valeurChoix - 1);
                codeTable = listTablesOccupeesParLocalisation.get(localisation);
                System.out.println("Vous avez choisis la zone " + localisation);
                for (Table a : listTablesOccupeesParLocalisation.get(localisation)) {
                    System.out.println(a.getCodeTable() + " Location " + a.getLocation());

                }

                verificationFini = true;
            } catch (ReservationException e) {
                System.out.println(e.getMessage());
                if (e instanceof WrongDateException || e instanceof MonthException || e instanceof JourException || e instanceof ParseDateException || e instanceof RestaurantCompletException) {
                    System.out.println(" Veuillez entrer la date de la reservation : (xx/xx/xxxx) ");
                    System.out.println(" Jour ( Entier ): ");
                    jour = lectureEntree();

                    System.out.println(" Mois ( Entier ): ");
                    mois = lectureEntree();

                    System.out.println(" Année ( Entier ) :  ");
                    annee = lectureEntree();

                    if (e instanceof RestaurantCompletException) {
                        System.out.println("Restaurant complet à la date saisie");
                        System.out.println(" Veuillez entrer l'horaire de la reservation au format 24h");
                        System.out.println("Heure ( Entier ) : ");
                        heure = lectureEntree();

                        System.out.println("Minutes (Entier)  :");
                        minutes = lectureEntree();

                    }

                } else if (e instanceof HeureException || e instanceof MinuteException || e instanceof ParseHeureException) {
                    System.out.println(" Veuillez entrer l'horaire de la reservation au format 24h");
                    System.out.println("Heure ( Entier ) : ");
                    heure = lectureEntree();

                    System.out.println("Minutes (Entier)  :");
                    minutes = lectureEntree();
                } else if (e instanceof ParseServiceException) {
                    System.out.println("Veuillez entrer le service ( MIDI ou SOIR )");
                    service = lectureEntree();
                } else if (e instanceof NbPersonneException || e instanceof ParseNbPersonneException) {
                    System.out.println(" Veuillez entrer le nombre de personne : ");
                    nbPersonnes = lectureEntree();
                } else if (e instanceof ParseNomClientException) {
                    System.out.println(" Veuillez entrer le nom du client : ");
                    nomClient = lectureEntree();
                } else if (e instanceof TelephoneException || e instanceof ParseTelephoneException) {
                    System.out.println(" Veuillez entrer le numero de telephone ");
                    tel = lectureEntree();
                }

            }

        }

        date = new ReservationDate(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour), Integer.parseInt(heure), Integer.parseInt(minutes));
        System.out.println("----------------------- RECAPITULATIF DE LA RESERVATION ------------------------ ");
        System.out.println("1.Date : " + date.writeDayMonth());
        System.out.println("2.Heure : " + date.writeHourMin());
        System.out.println("3.Service : " + service);
        System.out.println("4.Localisation : " + localisation);
        System.out.println("5.Nombre de personne : " + nbPersonnes);
        System.out.println("6.Nom client : " + nomClient);
        System.out.println("7.Telephone : " + tel);

        do {
            //On vérifie si une meme personne a pas déjà reservé pour le même jour et pour le même service
            for (Reservation r : this.getController().getReservationList()) {
                if (r.getClientName().equals(nomClient) && r.getDate().isSameDay(date) && r.getService().equals(Service.valueOf(service))) {
                    System.out.println("Une réservation au nom de " + nomClient + " pour le même jour et le même service a déjà été enregistrée, voulez-vous valider quand même?");
                }
            }
            System.out.println("Appuyer sur v pour valider ou q pour l'annuler : ");
            choix = lectureEntree();
            if (choix.equalsIgnoreCase("v")) {
                this.getController().creerReservation(codeTable, Integer.parseInt(nbPersonnes), Integer.parseInt(heure), Integer.parseInt(minutes), nomClient, tel, new java.sql.Date(Integer.parseInt(annee) - 1900, Integer.parseInt(mois) - 1, Integer.parseInt(jour)), Service.valueOf(service));
                System.out.println(">>>>>>>>>>>>>>>> Reservation validée ! <<<<<<<<<<<<<<<<<<<");

                this.getController().setView(TextualReservationList.singletonViewTextualReservationList());
                return;
            } else if (choix.equalsIgnoreCase("q")) {
                System.out.println(" ------------- ANNULATION DE LA RESERVATION ------------");
                this.getController().setView(TextualReservationList.singletonViewTextualReservationList());
            }
        } while (true);

    }

    /**
     * 
     * @return 
     *      String. Retourne le choix entré par l'utilisateur. Quitte l'application si on entre le caractère q. 
     */
    private String lectureEntree() {

        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();

        if (choix.equalsIgnoreCase("q")) {
            System.out.println(" ------------- ANNULATION DE LA RESERVATION ------------");
            this.getController().setView(TextualReservationList.singletonViewTextualReservationList());
        }
        return choix;
    }

}
