/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import GraphicPackage.*;
import static GraphicPackage.GlobalGraphicView.singletonGlobalGraphicView;

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

        AbstractView MainView = singletonGlobalGraphicView();
        ControllerGraphic GC = new ControllerGraphic();
        
        ((GlobalGraphicView) MainView).setWindow(EnumWindow.ReservationList);
        MainView.setController(GC);

        GC.setView(MainView);
        GC.startView();
    }
}
