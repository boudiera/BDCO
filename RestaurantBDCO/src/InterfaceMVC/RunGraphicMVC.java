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

        AbstractView MainView = GlobalGraphicView.singletonGlobalGraphicView();
        Controller Ctrl = new Controller();
        
        MainView.setController(Ctrl);
        Ctrl.setView(MainView);
    }
}
