/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.ViewType;
import FactoriesLayer.*;
import InterfaceMVC.*;
import Modele.Factory;
import java.util.*;

/**
 *
 * @author trentini
 */
public class GlobalGraphicView extends AbstractView implements Observer{

    final private static GlobalGraphicView GLOBAL_GRAPHIC_VIEW = new GlobalGraphicView();

    private WindowView activeWindow;
    private ViewType enumWindow;
    
    private GlobalGraphicView(){
    }
    
    public static GlobalGraphicView singletonGlobalGraphicView(){
        if(GLOBAL_GRAPHIC_VIEW.activeWindow == null){
            GLOBAL_GRAPHIC_VIEW.setActiveGraphicView(ViewType.ReservationList);
        }
        return GlobalGraphicView.GLOBAL_GRAPHIC_VIEW;
    }
    
    public WindowView getActiveView(){
        return this.activeWindow;
    }
    
    public ViewType getEnumWindowOfActiveView(){
        return this.enumWindow;
    }
    
    public void setActiveGraphicView(ViewType window){
        
       if(GLOBAL_GRAPHIC_VIEW.activeWindow != null){
            if(this.activeWindow.isSingleton()){
                this.activeWindow.setEnabled(false);    //the old window is set to disabled (if singleton, it means we can reactivate the window the next time it is set)
            }else{
                Factory.singletonFactory().deleteObserver(this.activeWindow);   //if not singleton, the observer is deleted after it changes the view (it means it is no longer shown)
            }
        }
        
        this.enumWindow = window;
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
                this.activeWindow = new FrameCommande(); //TO-DO: make cast not needed!
                break;
            default:
                this.activeWindow = FrameReservationList.singletonFrameReservationList();
                break;
        }

        Factory.singletonFactory().addObserver(this.activeWindow);
    }
    
    @Override
    public void showView(boolean b) {
        if(this.getController().getViewType() == null){
            this.setActiveGraphicView(ViewType.ReservationList);
        }else{
            this.setActiveGraphicView(this.getController().getViewType());
        }
        
        this.activeWindow.setEnabled(b);
        this.activeWindow.setVisible(b);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.activeWindow.update(o, arg);
    }
}
