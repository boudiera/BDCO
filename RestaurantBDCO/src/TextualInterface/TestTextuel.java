/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import Modele.ListReservations;
import Modele.Reservation;
import Modele.ReservationFactory;
import java.util.Date;
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
        ListReservations LR= new ListReservations();
        Date date= new Date(1994,05,16,20,45);
        ArrayList<Integer> num = new ArrayList<>();
        num.add(5);
        num.add(4);
        ArrayList<Integer> num2 = new ArrayList<>();
        num2.add(6);
        
        LR.addReservation(new Reservation(num, 3, "Patrick", date,"0476556969",Service.Evening));
        LR.addReservation(new Reservation(num2, 1, "José",date,"0476686969",Service.Midday));
        Textual_ReservationList IT_text= new Textual_ReservationList(LR);
        IT_text.PrintReservations();
        IT_text.gestionEvent();

    }
    
}
