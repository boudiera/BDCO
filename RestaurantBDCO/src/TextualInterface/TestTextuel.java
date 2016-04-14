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
        ListReservations LR = ListReservations.singletonListReservations();
        ReservationDate date= new ReservationDate(1994,05,16,20,45);
        ListTables num = new ListTables();
        num.add(5);
        num.add(4);
        ListTables num2 = new ListTables();
        num2.add(6);
        
        LR.addReservation(new Reservation(num, 3, "Patrick", "0476556969",date,Service.Evening));
        LR.addReservation(new Reservation(num2, 1, "José","0476686969",date,Service.Midday));
        Textual_ReservationList IT_text= new Textual_ReservationList(LR);
        IT_text.PrintReservations();
        IT_text.gestionEvent();

    }
    
}
