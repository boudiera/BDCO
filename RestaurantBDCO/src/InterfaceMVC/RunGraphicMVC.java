/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

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

        AbstractView MainView = GlobalGraphicView.singletonGlobalGraphicView();     // Create the instance of the View (Graphic view is a Singleton)
        Controller Ctrl = new Controller();     // Create the instance of the Controller
        
        MainView.setController(Ctrl);   // Tells the View which Controller is going to control it
        Ctrl.setView(MainView);         // Tells the Controller what is the primary View it is going to control
    }
}
