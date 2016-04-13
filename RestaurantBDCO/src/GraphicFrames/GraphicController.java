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
public class GraphicController {
    FrameReservationList Principal;
    
    FrameReservationCreation ReservationCreation;
    FrameReservationDetails ReservationDetails;
    FrameCommande Commande;
    
    public void start(){
        this.Principal = new FrameReservationList();
        this.Principal.setVisible(true);
    };
}
