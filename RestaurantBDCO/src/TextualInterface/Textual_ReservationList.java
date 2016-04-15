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
         int i = 0;
         System.out.println("--------------------------------AFFICHAGE DES RESERVATIONS--------------------------------");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.print("| Reservation |");
            System.out.print("      Nom     |");
            System.out.print("     Date     |"); 
            System.out.print("    Telephone |");
            System.out.print("     Heure    |"); 
            System.out.println("  Code Table   ");
         for (Reservation reservation : this.listReservations.getList()){
            i++;
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.print("|      "+i+"      |");
            System.out.print(" "+reservation.AffichageNomClient()+"|");
            System.out.print("    "+reservation.getDate().getDay()+"/"+ reservation.getDate().getMonth()+"/"+reservation.getDate().getYear()+"  |");
            System.out.print("   "+reservation.getPhone()+" |");
            System.out.print("     "+reservation.getDate().getHours()+"h"+reservation.getDate().getMinutes()+"    |");
            System.out.print("       ");
            for (int codetable: reservation.getCodeTable()){
                 System.out.print(codetable+" ");
            }
             System.out.println();
         }
          System.out.println("");
          System.out.println("");
    }
    
    public void afficheMenu(){
        System.out.println("Appuyez sur c pour entrer une nouvelle reservation \n"
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
