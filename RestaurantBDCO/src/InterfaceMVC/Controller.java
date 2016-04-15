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
public class Controller {
    
    private ReservationFactory reservFactory;
    private AbstractView View;
    
    public AbstractView getView(){
        return this.View;
    }
    
    public void setView(AbstractView v){
        this.View = v;
    }
    
    public ArrayList<Reservation> getReservationList(){
        return this.reservFactory.reservations();
    }

}
