/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import Modele.*;
import java.util.ArrayList;

/**
 *
 * @author mourinf
 */
public class TestTextuel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Reservation> LR = new ArrayList<Reservation>();
        ReservationDate date= new ReservationDate(1994,05,16,20,45);
        ArrayList<Table> num= new ArrayList<Table>();
        num.add(new Table(5, null,5,4, 3));
        num.add(new Table(7, null,5,4, 3));
        ArrayList<Table> num2= new ArrayList<Table>();
        num2.add(new Table(1, null,5,4, 3));
        
        LR.add(new Reservation(1,num, 55, "Patrick", "0476556969",date,Service.Evening));
        LR.add(new Reservation(2,num2, 1, "José","0476686969",date,Service.Midday));
        Textual_ReservationList IT_text= new Textual_ReservationList(LR);
        IT_text.PrintReservations();
        IT_text.gestionEvent();

    }
    
}
