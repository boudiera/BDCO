/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import FactoriesLayer.ConcreteRequeteFactory;
import FactoriesLayer.ConnectionInfo;
import FactoriesLayer.TheConnection;
import GraphicPackage.*;

/**
 *
 * @author Iago Felipe Trentin
 */
public class RunGraphicMVC {
    
    /**
     * @param args the command line arguments
     */
    public static void run(String[] args) {
        // TODO code application logic here

        AbstractView MainView = GlobalGraphicView.singletonGlobalGraphicView();
        Controller GC = new Controller();
        
        ((GlobalGraphicView) MainView).setActiveView(EnumWindow.ReservationList);
        MainView.setController(GC);
        
        ConcreteRequeteFactory.singletonConcreteRequeteFactory(new TheConnection(new ConnectionInfo()));

        GC.setView(MainView);
    }
}
