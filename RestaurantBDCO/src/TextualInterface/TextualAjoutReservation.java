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
 *
 * @author mourinf
 */
public class TextualAjoutReservation extends AbstractView {

    public TextualAjoutReservation(Controller controller) {
        this.setController(controller);
    }

    /**
     * Affichage de la vue: consigne et lecture des entrées clavier. Gestion des
     * erreurs d'entrées.
     *
     * @param b boolean
     */
    @Override
    public void showView(boolean b) {
        String jour, mois, annee, heure, minutes;
        ArrayList<Table> codeTable = new ArrayList<>();
        String nbPersonnes;
        String nomClient;
        String tel;
        String choix;

        String service;
        String localisation = "";
        ReservationDate date;
        boolean choixLocalisationfini;
        boolean verificationFini = false;
        boolean validationConfirm = false;
        int valeurChoix = 0;
        int i;
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

        //Verification des informations rentrées, en appelant la fonction du controller VerifyAddReservation
        while (!verificationFini) {
            try {
                // On demande au controlleur de vérifier la validité des champs rentrés

                if (!validationConfirm) // On verifie que l'on ne vient pas de deja valider le reservation
                {
                    this.getController().verifyAddReservation(annee, mois, jour, heure, minutes, nbPersonnes, tel, service, nomClient);
                }

                // 0n cherche une localisation possible, on récupère grâce au controlleur une HashMap indexé sur le nom des localisation et qui renvoit une liste de tables qui peuvent être occupées
                HashMap<String, ArrayList<Table>> listTablesOccupeesParLocalisation = this.getController().getTablesLibresParLocalisation(annee, mois, jour, service, nbPersonnes,Integer.parseInt(heure),Integer.parseInt(minutes));

                Set<String> s = listTablesOccupeesParLocalisation.keySet();

                Iterator<String> iterator = s.iterator();

                // Tableau qui contient les nomes de zones , indexé par des entiers 
                ArrayList<String> nomZone = new ArrayList<>();
                System.out.println("Endroit(s) de localisation possible --->");

                i = 1;

                //Affichage des choix à l'utilisateur
                while (iterator.hasNext()) {
                    String nomLocalisation = iterator.next();
                    nomZone.add(nomLocalisation);
                    System.out.println(i + ". " + nomLocalisation);
                    i++;
                }

                //Gestion du choix de localisation. Cette étape se lance si toutes les informations sont valides
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
                verificationFini = true; //

                //Recupération des exceptions
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
                } else if (e instanceof ExistReservationException) {
                    do {
                        choix = lectureEntree();
                    } while (!choix.equalsIgnoreCase("v"));
                    validationConfirm = true;
                }
            }
        }

        //Toutes les informations sont bonnes, on affiche le récapitulatif avant la validation
        date = new ReservationDate(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour), Integer.parseInt(heure), Integer.parseInt(minutes));
        System.out.println("----------------------- RECAPITULATIF DE LA RESERVATION ------------------------ ");
        System.out.println("1.Date : " + date.writeDayMonth());
        System.out.println("2.Heure : " + date.writeHourMin());
        System.out.println("3.Service : " + service);
        System.out.println("4.Localisation : " + localisation);
        System.out.println("5.Nombre de personne : " + nbPersonnes);
        System.out.println("6.Nom client : " + nomClient);
        System.out.println("7.Telephone : " + tel);

        // on peut créer l'objet reservation si on a la validation
        do {
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
     * @return String. Retourne le choix entré par l'utilisateur. Quitte
     * l'application si on entre le caractère q.
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
