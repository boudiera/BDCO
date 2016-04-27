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
import Modele.*;
import TextualInterface.TextualSelectionReservation;
import java.util.ArrayList;

/**
 *
 * @author mourinf
 */
public class ExecutablePriseCommandes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Controller controller = new Controller();

        ConnectionInfo info = new ConnectionInfo();
        TheConnection connection = new TheConnection(info);

        Factory.singletonFactory().setRequeteFactory(new ConcreteRequeteFactory(connection));
        Factory.singletonFactory().setInsertionFactory(new ConcreteInsertionFactory(connection));

        TextualSelectionReservation.singletonViewTextualReservationList().setController(controller);
        controller.setView(TextualSelectionReservation.singletonViewTextualReservationList());

    }

}
