/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import FactoriesLayer.ConcreteReservationFactory;
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
       
        
        Textual_ReservationList IT_text= Textual_ReservationList.singletonViewTextualReservationList();
        Controller controller = new Controller();
        
        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);
        Factory.reservations = new ConcreteReservationFactory(connection);
       
        IT_text.setController(controller);
        IT_text.showView(true);
       

    }
    
}

