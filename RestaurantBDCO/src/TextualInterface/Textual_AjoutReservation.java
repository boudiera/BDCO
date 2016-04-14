/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;


import Modele.Service;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mourinf
 */
public class Textual_AjoutReservation {
   ArrayList<Integer> codeTable;
   String nbPersonnes;
   String nomClient;
   Date date;
   String tel;
   Service service = null;
    public Textual_AjoutReservation () throws Exception{
        String jour,mois,annee,heure,minute;
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
        this.nbPersonnes = lectureEntree();
        
        
        
        System.out.println(" Veuillez entrer le nom du client : ");
        this.nomClient = lectureEntree();
        
       
        this.date = new Date(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour),Integer.parseInt(heure),Integer.parseInt(minute));
         
        System.out.println(" Veuillez entrer le nom du client : ");
        this.nomClient = lectureEntree();
        
        System.out.println(" Veuillez entrer le numero de telephone ");
        this.tel = lectureEntree();
       
        System.out.println(" Veuillez entrer  numero de telephone ");
        
    
   
    }
    
    private String lectureEntree() throws Exception {
        
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
               
        if (choix.equalsIgnoreCase("q"))
                   throw  new Exception("Annulation de la reservation");
        return choix;
    }
    
    
   
}
