/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import Modele.Reservation;
import Modele.ReservationFactory;
import java.util.ArrayList;

/**
 *
 * @author Iago Felipe Trentin
 */
public class AbstractController {
    
    private ReservationFactory reservFactory;
    private AbstractView Principal;
    
    
    public void setView(AbstractView mainView){
        this.Principal = mainView;
    }
    
    public ArrayList<Reservation> getReservationList(){
        return this.reservFactory.reservations();
    }

}
