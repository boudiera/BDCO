/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoriesLayer;

import Modele.Reservation;
import java.util.ArrayList;

/**
 *
 * @author michecle
 */
public class TestMainJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        // TODO code application logic here
        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);
        ConcreteReservationFactory fact = new ConcreteReservationFactory(connection);
        
        ArrayList<Reservation> reserv = fact.getReservationsList();
        
        for (Reservation r : reserv) {
            System.out.println(r.getCodeReservation());
        }
    }
    
}
