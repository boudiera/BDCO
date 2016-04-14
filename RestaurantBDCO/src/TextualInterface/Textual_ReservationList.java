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
        /*for (Reservation r : this.listReservations.getListeRes()){
            System.out.println("nom: " + r.getNomClient() + " date: "+ r.getHeure()/100 + ":" + r.getHeure()%100 + " tel: " + r.getTel() );
        }*/
    }
    
    public void afficheMenu(){
        System.out.println("Tapez le numero de la reservation pour l'afficher \n appuyez sur c pour entrer une nouvelle reservation \n appuyez sur q pour quitter");
    }
    
    public void gestionEvent(){

     
        do { 
            afficheMenu();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
             if (choix.equals("c"))
                    System.out.println("ajout reservation");
                    
             else if (choix.equals("q"))
                   System.exit(0);
              
        } while (true);
      
      
      
    }
}
