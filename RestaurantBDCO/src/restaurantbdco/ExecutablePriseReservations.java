/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantbdco;

import InterfaceBaseDeDonnees.ConcreteInsertionFactory;
import InterfaceBaseDeDonnees.ConcreteRequeteFactory;
import InterfaceBaseDeDonnees.ConnectionInfo;
import InterfaceBaseDeDonnees.TheConnection;
import InterfaceMVC.Controller;
import Modele.Factory;
import TextualInterface.TextualReservationList;

/**
 *
 * @author mourinf
 */
public class ExecutablePriseReservations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();

        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);

        Factory.singletonFactory().setRequeteFactory(new ConcreteRequeteFactory(connection));
        Factory.singletonFactory().setInsertionFactory(new ConcreteInsertionFactory(connection));

        TextualReservationList.singletonViewTextualReservationList().setController(controller);
        controller.setView(TextualReservationList.singletonViewTextualReservationList());

    }

}
