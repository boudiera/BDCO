/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;


import InterfaceMVC.AbstractView;
import Modele.Service;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mourinf
 */
public class Textual_AjoutReservation extends AbstractView {

    public Textual_AjoutReservation () {
    }

    @Override
    public void showView(boolean b){
        String jour,mois,annee,heure,minute;
        ArrayList<Integer> codeTable;
        String nbPersonnes;
        String nomClient;
        Date date;
        String tel;
        Service service = null;
        // Creation de l'objet Textual_AjoutReservation au fur et à mesure que l'on rentre les champs de donnée
        System.out.println("----------------------Creation d'une réservation ---------------- \n");
  
        System.out.println(" Veuillez entrer la date de la reservation : ");
        System.out.println(" Jour : ");
        jour = lectureEntree();
        
        System.out.println(" Mois : ");
        mois = lectureEntree();
        
        System.out.println(" Année : ");
        annee = lectureEntree();
        
        System.out.println(" Veuillez entrer l'horaire de la reservation");
        System.out.println("Heure : ");
        heure = lectureEntree();
       
        System.out.println("Minute");
        minute = lectureEntree();
 
        
        //Service
        System.out.println(" Veuillez entrer le nombre de personne : ");
        nbPersonnes = lectureEntree();
        
        
        
        System.out.println(" Veuillez entrer le nom du client : ");
        nomClient = lectureEntree();
        
       
        date = new Date(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour),Integer.parseInt(heure),Integer.parseInt(minute));
         
        System.out.println(" Veuillez entrer le nom du client : ");
        nomClient = lectureEntree();
        
        System.out.println(" Veuillez entrer le numero de telephone ");
        tel = lectureEntree();
       
        
    }
    
    
    
    private String lectureEntree() {
        
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
               
        if (choix.equalsIgnoreCase("q"))
                   this.getController().setView(Textual_ReservationList.singletonViewTextualReservationList());
        return choix;
    }
    
    
   
}
