/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import GraphicPackage.ViewCommande;
import GraphicPackage.ViewReservationCreation;
import GraphicPackage.ViewReservationDetails;

/**
 *
 * @author trentini
 */
public class ControllerGraphic extends Controller{
   
    ViewReservationDetails ReservationDetails;
    ViewCommande Commande;

    public void startView(){
        this.getView().showView(true);
    };
    
    public void buttonAddNewReservation(){
        this.getView().showView(false);

        ViewReservationCreation ReservationCreation = new ViewReservationCreation(this);
        ReservationCreation.setVisible(true);
    }

    public void closeFrameReservationCreation() {
        this.getView().showView(true);
    }
}
