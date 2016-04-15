/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import FactoriesLayer.ConcreteRequeteFactory;
import FactoriesLayer.ConnectionInfo;
import FactoriesLayer.TheConnection;
import InterfaceMVC.Controller;
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
        ReservationDate date= new ReservationDate(1994,9,38,20,45);
        ArrayList<Table> num= new ArrayList<Table>();
        num.add(new Table(5, null,5,4, 3));
        num.add(new Table(7, null,5,4, 3));
        ArrayList<Table> num2= new ArrayList<Table>();
        num2.add(new Table(1, null,5,4, 3));
        
        LR.add(new Reservation(1,num, 55, "Patrick", "04765569695",date,Service.SOIR));
        LR.add(new Reservation(2,num2, 1, "José","04766869695",date,Service.MIDI));


       
        
        Textual_ReservationList IT_text= Textual_ReservationList.singletonViewTextualReservationList();
        Controller controller = new Controller();
        
        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);
        Factory.reservations = ConcreteRequeteFactory.singletonConcreteRequeteFactory(connection);
       
        IT_text.setController(controller);
        IT_text.showView(true);
       

    }
    
}

