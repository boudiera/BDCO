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
    AbstractView Principal;
    
    ViewReservationDetails ReservationDetails;
    ViewCommande Commande;

    public void startView(){
        this.Principal.showView(true);
    };
    
    public void buttonAddNewReservation(){
        this.Principal.showView(false);

        ViewReservationCreation ReservationCreation = new ViewReservationCreation(this);
        ReservationCreation.setVisible(true);
    }

    public void closeFrameReservationCreation() {
        this.Principal.showView(true);
    }
}
