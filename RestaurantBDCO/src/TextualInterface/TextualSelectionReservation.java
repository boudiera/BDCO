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
public class TextualSelectionReservation extends AbstractView{
    private final static TextualSelectionReservation viewTextualSelectionReservation = new TextualSelectionReservation();
    
    private TextualSelectionReservation(){
    }
    
    public static TextualSelectionReservation singletonViewTextualReservationList(){
        return TextualSelectionReservation.viewTextualSelectionReservation;
    }
    
    public void showView(boolean view){
        
        if (!view){
            return;
        }
        
        ArrayList<Reservation> listReservations = this.getController().getReservationList();
        //ArrayList<Reservation> listReservationsTriee=this.getController().triListeReservation(listReservations);

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
            System.out.print("  "+reservation.getDate().writeDay()+"/"+ reservation.getDate().writeMonth()+"/"+reservation.getDate().writeYear()+"  |");
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
    
    private void afficheChoix(){
        System.out.println("Entrez un code de reservation pour voir les commandes associées \n"
                + "Appuyez sur q pour quitter");
    }
    
    public void gestionEvent(){
        int valeurChoix=0;
        boolean finChoix=false;
        ArrayList<Reservation> listReservation=this.getController().getReservationList();
        do { 
            afficheChoix();
            boolean choixOK=false;
            afficheChoix();
            Scanner sc = new Scanner(System.in);
            String choix = sc.nextLine();
            if (choix.equalsIgnoreCase("q"))
                   System.exit(0);
            else{
                try {
                    valeurChoix = Integer.parseInt(choix);
                    for(Reservation res : listReservation){
                        if (res.getCodeReservation()==valeurChoix)
                            finChoix=true;    
                }
                }
                catch (Exception e){  
                    System.out.println(" La valeur entrée doit être un entier positif");
                }    
            }  
        } while (!finChoix); 
    }
}
