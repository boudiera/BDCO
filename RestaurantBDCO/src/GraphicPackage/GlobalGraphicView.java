/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import FactoriesLayer.*;
import InterfaceMVC.*;
import java.util.*;

/**
 *
 * @author trentini
 */
public class GlobalGraphicView extends AbstractView implements Observer{

    final private static GlobalGraphicView GLOBAL_GRAPHIC_VIEW = new GlobalGraphicView();

    private WindowView activeWindow;
    private EnumWindow enumWindow;
    
    private GlobalGraphicView(){
    }
    
    public static GlobalGraphicView singletonGlobalGraphicView(){
        return GlobalGraphicView.GLOBAL_GRAPHIC_VIEW;
    }
    
    public WindowView getActiveView(){
        return this.activeWindow;
    }
    
    public EnumWindow getEnumWindowOfActiveView(){
        return this.enumWindow;
    }
    
    public void setActiveView(EnumWindow window){
        
        if(this.activeWindow != null) this.activeWindow.setEnabled(false);
        
        this.enumWindow = window;
        switch(window){
            case ReservationList:
                ConcreteRequeteFactory.singletonConcreteRequeteFactory().deleteObserver(this.activeWindow);
                this.activeWindow = FrameReservationList.singletonFrameReservationList();
                break;
            case ReservationCreation:
                this.activeWindow = new FrameReservationCreation();
                break;
            case ResevationDetails:
                this.activeWindow = new FrameReservationDetails();
                break;
            case Commande:
                this.activeWindow = (WindowView) new FrameCommande(); //TO-DO: make cast not needed!
                break;
        }

        ConcreteRequeteFactory.singletonConcreteRequeteFactory().addObserver(this.activeWindow);
        showView(true);
    }
    
    @Override
    public void showView(boolean b) {
        this.activeWindow.setEnabled(b);
        this.activeWindow.setVisible(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.activeWindow.update(o, arg);
    }
}
