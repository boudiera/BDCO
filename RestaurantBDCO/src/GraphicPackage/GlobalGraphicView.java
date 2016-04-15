/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.*;

/**
 *
 * @author trentini
 */
public class GlobalGraphicView extends AbstractView {

    final private static GlobalGraphicView globalGraphicView = new GlobalGraphicView();

    private javax.swing.JFrame activeWindow;
    
    private GlobalGraphicView(){
    }
    
    public static GlobalGraphicView singletonGlobalGraphicView(){
        return GlobalGraphicView.globalGraphicView;
    }
    
    public void setWindow(EnumWindow window){
        
        if(this.activeWindow != null) this.activeWindow.setEnabled(false);
        
        switch(window){
            case ReservationList:
                this.activeWindow = FrameReservationList.singletonFrameReservationList();
                break;
            case ReservationCreation:
                this.activeWindow = new FrameReservationCreation();
                break;
            case ResevationDetails:
                this.activeWindow = new FrameReservationDetails();
                break;
            case Commande:
                this.activeWindow = null;
                break;
        }
    }
    
    @Override
    public void showView(boolean b) {
        this.activeWindow.setEnabled(true);
        this.activeWindow.setVisible(b);
    }
}
