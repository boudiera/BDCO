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
import Modele.ReservationDate;
import Modele.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mourinf
 */
public class Textual_AjoutReservation extends AbstractView {

    public Textual_AjoutReservation (Controller controller) {
        this.setController(controller);
    }

    @Override
    public void showView(boolean b){
        String jour,mois,annee,heure,minutes;
        ArrayList<Integer> codeTable;
        String nbPersonnes;
        String nomClient;
        String tel;
        String choix;
        boolean verificationFini = false;
        String service;
        ReservationDate date;
        
        // Creation de l'objet Textual_AjoutReservation au fur et à mesure que l'on rentre les champs de donnée
        System.out.println("----------------------Creation d'une réservation ---------------- \n");
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
        
        
        /// FAIRE LA FONCTION QUI TROUVES LES TABLES LIBRES + AFFICHER
        
        
        while (!verificationFini){
            try {
                this.getController().verifyAddReservation(annee,mois,jour,heure,minutes, nbPersonnes, tel,service, nomClient);
                verificationFini = true;
            } catch (ReservationException e) {
                System.out.println(e.getMessage());
                if (e instanceof WrongDateException || e instanceof MonthException || e instanceof JourException || e instanceof ParseDateException ){
                    System.out.println(" Veuillez entrer la date de la reservation : (xx/xx/xxxx) ");
                    System.out.println(" Jour ( Entier ): ");
                    jour = lectureEntree();

                    System.out.println(" Mois ( Entier ): ");
                    mois = lectureEntree();

                    System.out.println(" Année ( Entier ) :  ");
                    annee = lectureEntree();

                }
              
                else if (e instanceof HeureException || e instanceof MinuteException || e instanceof ParseHeureException){
                            System.out.println(" Veuillez entrer l'horaire de la reservation au format 24h");
                            System.out.println("Heure ( Entier ) : ");
                            heure = lectureEntree();

                            System.out.println("Minutes (Entier)  :");
                            minutes = lectureEntree();
                } 
                else if (e instanceof ParseServiceException){
                    System.out.println("Veuillez entrer le service ( MIDI ou SOIR )");
                    service = lectureEntree();
                } 
              
                  else if (e instanceof NbPersonneException || e instanceof ParseNbPersonneException){
                    System.out.println(" Veuillez entrer le nombre de personne : ");
                    nbPersonnes = lectureEntree();
                } 
            
                 else if (e instanceof ParseNomClientException){
                     System.out.println(" Veuillez entrer le nom du client : ");
                     nomClient = lectureEntree();
                } 
                  else if (e instanceof TelephoneException || e instanceof ParseTelephoneException){
                    System.out.println(" Veuillez entrer le numero de telephone ");
                     tel = lectureEntree();
                } 
                   
            }   
            
        }
         date = new ReservationDate(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour),Integer.parseInt(heure),Integer.parseInt(minutes));
         System.out.println("----------------------- RECAPITULATIF DE LA RESERVATION ------------------------ ");
         System.out.println("1.Date : " + date.writeDayMonth());
         System.out.println("2.Heure : " + date.writeHourMin());
         System.out.println("3.Service : " + service);
         System.out.println("4.Nombre de personne : " + nbPersonnes);
         System.out.println("5.Nom client : " + nomClient);
         System.out.println("6.Telephone : " + tel);
        
         
         
         do { 
            System.out.println("Appuyer sur v pour valider ou q pour l'annuler : ");
            choix = lectureEntree();
            if (choix.equalsIgnoreCase("v")){
                     System.out.println(">>>>>>>>>>>>>>>> Reservation validée ! <<<<<<<<<<<<<<<<<<<");
                     // APPEL DE LA FONCTION DU CONTROLLER QUI ENVOIT LA RESERVATION DANS LA BASE DE DONNEE
                     this.getController().setView(Textual_ReservationList.singletonViewTextualReservationList());
                   return;         
            }
        } while (true);
    
    }
    
   
    private String lectureEntree() {
        
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
       
        if (choix.equalsIgnoreCase("q")){
                   System.out.println(" ------------- ANNULATION DE LA RESERVATION ------------");
                   this.getController().setView(Textual_ReservationList.singletonViewTextualReservationList());           
        }           
        return choix;
    }
    
    
   
}
