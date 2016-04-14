/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicFrames;

/**
 *
 * @author trentini
 */
public class ControllerGraphic {
    AbstractView Principal;
    
    ViewReservationDetails ReservationDetails;
    ViewCommande Commande;
    
    public void setMainView(AbstractView mainView){
        this.Principal = mainView;
    }
    
    public void start(){
        this.Principal.setVisible(true);
    };
    
    public void buttonAddNewReservation(){
        this.Principal.setVisible(false);

        ViewReservationCreation ReservationCreation = new ViewReservationCreation(this);
        ReservationCreation.setVisible(true);
    }

    void closeFrameReservationCreation() {
        this.Principal.setVisible(true);
    }
}
