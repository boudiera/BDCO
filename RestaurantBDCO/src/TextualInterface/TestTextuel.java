/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextualInterface;

import FactoriesLayer.ConcreteInsertionFactory;
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
        

  

        Controller controller = new Controller();

        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);

        Factory.singletonFactory().setRequeteFactory(new ConcreteRequeteFactory(connection));
        Factory.singletonFactory().setInsertionFactory(new ConcreteInsertionFactory(connection));
        // Decomenter pour lancer le test sur l'executable des reservations
       // TextualReservationList.singletonViewTextualReservationList().setController(controller);
       // controller.setView(TextualReservationList.singletonViewTextualReservationList());
       
        // Decomenter pour lancer le test sur l'executable des commandes

      TextualSelectionReservation.singletonViewTextualReservationList().setController(controller);
       controller.setView(TextualSelectionReservation.singletonViewTextualReservationList());

    }

}
