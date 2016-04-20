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

    private WindowView activeWindow;
    private EnumView enumWindow = EnumView.ReservationList;
    
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
                Factory.singletonFactory().addObserver(this.activeWindow);
                break;
            case ReservationCreation:
                this.activeWindow = new FrameReservationCreation();
                break;
            case ResevationDetails:
                this.activeWindow = new FrameReservationDetails();
                SingletonListCommande.singletonListCommande().addObserver(this.activeWindow);
                break;
            case Commande:
                if(this.activeWindow instanceof FrameReservationDetails){
                    this.activeWindow = new FrameCommande((FrameReservationDetails) this.activeWindow);
                }else{
                    this.activeWindow = this.activeWindow;
                }
                break;
            case Menu:
                if(this.activeWindow instanceof FrameCommande){
                    this.activeWindow = new FrameMenu((FrameCommande) this.activeWindow);
                }else{
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
        if(this.getController().getViewType() == null){
            this.getController().setView(EnumView.ReservationList);
        }else{
            this.setActiveGraphicView(this.getController().getViewType());
        }
        
        this.activeWindow.setEnabled(b);
        this.activeWindow.setVisible(b);
    }
    
    public void showView(WindowView wv){
        wv.setEnabled(true);
        wv.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.activeWindow.update(o, arg);
    }
}
