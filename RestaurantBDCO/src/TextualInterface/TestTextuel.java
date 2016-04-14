/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import Modele.ListReservations;
import Modele.Reservation;
import Modele.ReservationFactory;
import java.sql.Date;
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
        Date date= new Date(0) ;
        ArrayList<Integer> num = new ArrayList<>();
        num.add(5);
        LR.addReservation(new Reservation(num, 3, "Patrick", date, 1245, "0675214899"));
        Textual_ReservationList IT_text= new Textual_ReservationList(LR);
        IT_text.PrintReservations();
        IT_text.gestionEvent();

    }
    
}
