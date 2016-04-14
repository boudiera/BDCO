/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;
import java.util.*;
import java.lang.*;
import Modele.ListReservations;
import Modele.Reservation;



/**
 *
 * @author mourinf
 */
public class Textual_ReservationList {
    ListReservations listReservations;
    
    public Textual_ReservationList (ListReservations listReservations){
        this.listReservations=listReservations;
    }
    
    public void PrintReservations(){
<<<<<<< HEAD
         int i = 1;
         System.out.println("------------- AFFICHAGE DES RESERVATIONS ---------- \n");
         
         for (Reservation reservation : this.listReservations.getListReservations()){
            i++;
            System.out.print("Reservation "+ i);
            System.out.print(" Nom : " + reservation.getNomClient());
            System.out.print(" Date : "+ reservation.getDate().getDay()+"/"+ reservation.getDate().getMonth()+"/"+reservation.getDate().getYear());
            System.out.print(" Telephone : " + reservation.getTel());
            
            System.out.print(" Heure : " + reservation.getDate().getHours()+"h"+reservation.getDate().getMinutes());
           
            System.out.print(" Code Table : ");
            for (int codetable: reservation.getCodeTable()){
                 System.out.print(codetable+" ");
            }
             System.out.println("");
         }
          System.out.println("");
          System.out.println("");
=======
        /*for (Reservation r : this.listReservations.getListeRes()){
            System.out.println("nom: " + r.getNomClient() + " date: "+ r.getHeure()/100 + ":" + r.getHeure()%100 + " tel: " + r.getTel() );
        }*/
>>>>>>> fe80b54008ba26a77aa303c0ed54e09a75a7180b
    }
    
    public void afficheMenu(){
        System.out.println("Tapez le numero de la reservation pour l'afficher \n"
                + "Appuyez sur c pour entrer une nouvelle reservation \n"
                + "Appuyez sur q pour quitter");
    }
    
    public void gestionEvent(){

        do { 
            afficheMenu();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
             if (choix.equalsIgnoreCase("c")){
               
               try {
                   Textual_AjoutReservation newReservation = new Textual_AjoutReservation();
               }
               catch (Exception e){
                   System.out.println(e.getMessage());
               }     
               
               
             }       
             else if (choix.equalsIgnoreCase("q"))
                   System.exit(0);
              
        } while (true);
      
      
      
    }
}
