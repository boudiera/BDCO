/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import InterfaceMVC.EnumView;
import InterfaceMVC.*;
import Modele.Factory;
import Modele.SingletonListCommande;
import java.util.*;

/**
 *
 * @author trentini
 */
public class GlobalGraphicView extends AbstractView implements Observer{
    final private static GlobalGraphicView GLOBAL_GRAPHIC_VIEW = new GlobalGraphicView();

    private WindowView activeWindow;        // The instance of the last used/created window
    private EnumView enumWindow = EnumView.ReservationList;     // The type of the last used/created window
    
    private GlobalGraphicView(){
    }
    
    public static GlobalGraphicView singletonGlobalGraphicView(){
        return GlobalGraphicView.GLOBAL_GRAPHIC_VIEW;
    }
    
    public WindowView getActiveView(){
        return this.activeWindow;
    }
    
    public EnumView getEnumView(){
        return this.enumWindow;
    }
    
    public void setActiveGraphicView(EnumView window){
        this.enumWindow = window;
        switch(window){
            case ReservationList:
                this.activeWindow = FrameReservationList.singletonFrameReservationList();
                // The window "Reservation List" observes the list fo Reservations, and every time it changes it updates the list itself
                Factory.singletonFactory().addObserver(this.activeWindow);
                break;
            case ReservationCreation:
                this.activeWindow = new FrameReservationCreation();
                break;
            case ResevationDetails:
                this.activeWindow = new FrameReservationDetails();
                // The window "Reservation Details" observes the list of Commandes, and every time it changes it updates the list itself
                SingletonListCommande.singletonListCommande().addObserver(this.activeWindow);
                break;
            case Commande:
                if(this.activeWindow instanceof FrameReservationDetails){
                    this.activeWindow = new FrameCommande((FrameReservationDetails) this.activeWindow);
                }else{  // Only creates a "Commande" window if the current active window is a "Reservation Details"
                    this.activeWindow = this.activeWindow;
                }
                break;
            case Menu:
                if(this.activeWindow instanceof FrameCommande){
                    this.activeWindow = new FrameMenu((FrameCommande) this.activeWindow);
                }else{  // Only creates a "Menu" window if the current active window is a "Commande"
                    this.activeWindow = this.activeWindow;
                }
                break;
            default:
                this.activeWindow = FrameReservationList.singletonFrameReservationList();
                break;
        }
    }
    
    @Override
    public void showView(boolean b) {
        // If there is no active view, tet the instance of the primary view
        if(this.getController().getViewType() == null){  
            this.getController().setView(EnumView.ReservationList);
        }else{
            this.setActiveGraphicView(this.getController().getViewType());
        }
        
        this.activeWindow.setEnabled(b);
        this.activeWindow.setVisible(b);
    }
    
    public void showView(WindowView wv){    // Gets a graphic window and set it visible and enable
        wv.setEnabled(true);
        wv.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {  // Updates the current view
        this.activeWindow.update(o, arg);
    }
}
