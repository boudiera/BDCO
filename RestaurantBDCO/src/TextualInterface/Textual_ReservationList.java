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
 *
 * @author mourinf
 */
public class Textual_ReservationList extends AbstractView {

  
    private final static Textual_ReservationList viewTextualReservation = new Textual_ReservationList();
    
    private Textual_ReservationList (){
    }
    
    public static Textual_ReservationList singletonViewTextualReservationList(){
        return Textual_ReservationList.viewTextualReservation;
    }
    public void showView(boolean view){
        
        if (!view){
            return;
        }
        
        ArrayList<Reservation> listReservations = this.getController().getReservationList();
        /*
        ReservationDate date= new ReservationDate(1994,05,16,20,45);
        ArrayList<Table> num= new ArrayList<Table>();
        num.add(new Table(5, null,5,4, 3));
        num.add(new Table(7, null,5,4, 3));
        ArrayList<Table> num2= new ArrayList<Table>();
        num2.add(new Table(1, null,5,4, 3));
        
        listReservations.add(new Reservation(1,num, 55, "Patrick", "0476556969",date,Service.SOIR));
        listReservations.add(new Reservation(2,num2, 1, "José","0476686969",date,Service.MIDI));
        */
       
        
        
         
         System.out.println("--------------------------------------~AFFICHAGE DES RESERVATIONS~---------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("| Reservation |");
            System.out.print("      Nom     |");
            System.out.print("     Date     |"); 
            System.out.print("   Telephone  |");
            System.out.print("     Heure    |");
            System.out.print(" nbPersonnes  |");
            System.out.println("  Code Table   ");
            
         for (Reservation reservation : listReservations){
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("|      "+reservation.getCodeReservation()+"      |");
            System.out.print(" "+reservation.printClientName()+"|");
            System.out.print("  "+reservation.getDate().writeDay()+"/"+ reservation.getDate().writeMonth()+"/"+reservation.getDate().getYear()+"  |");
            System.out.print("  "+reservation.getPhone()+"  |");
            System.out.print("     "+reservation.getDate().writeHour()+"h"+reservation.getDate().writeMin()+"    |");
            
            
            System.out.print(reservation.printNbPeople());
            // affichage code Table
            System.out.print("       ");
            for (Table table : reservation.getCodeTable()){
                 System.out.print(table.getCodeTable()+" ");
            }
             System.out.println();
         }
          System.out.println("");
          System.out.println("");
          gestionEvent();
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
                   this.getController().setView(new Textual_AjoutReservation(this.getController()));          
             }       
             else if (choix.equalsIgnoreCase("q"))
                   System.exit(0);
              
        } while (true);
      
    }
}
