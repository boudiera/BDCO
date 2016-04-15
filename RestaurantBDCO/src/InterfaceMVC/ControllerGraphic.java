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
public class ControllerGraphic extends AbstractController{
    AbstractView Principal;
    
    ViewReservationDetails ReservationDetails;
    ViewCommande Commande;

    public void startView(){
        this.Principal.setVisible(true);
    };
    
    public void buttonAddNewReservation(){
        this.Principal.setVisible(false);

        ViewReservationCreation ReservationCreation = new ViewReservationCreation(this);
        ReservationCreation.setVisible(true);
    }

    public void closeFrameReservationCreation() {
        this.Principal.setVisible(true);
    }
}
