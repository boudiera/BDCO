/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantbdco;

import GraphicFrames.*;

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

        AbstractView MainView = ViewReservationList.singletonFrameReservationList();
        ControllerGraphic GC = new ControllerGraphic();
        
        MainView.addController(GC);
        GC.setMainView(MainView);
        GC.start();
    }
}
