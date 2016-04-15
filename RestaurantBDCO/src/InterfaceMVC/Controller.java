/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import Modele.Factory;
import Modele.Reservation;
import Modele.ReservationFactory;
import java.util.ArrayList;

/**
 *
 * @author Iago Felipe Trentin
 */
public class Controller {
    
    private AbstractView View;
    
    public AbstractView getView(){
        return this.View;
    }
    
    public void setView(AbstractView v){
        this.View = v;
        this.View.showView(true);
    }
    
    public ArrayList<Reservation> getReservationList(){
<<<<<<< HEAD
        //return this.reservFactory.reservations();
        return Factory.reservations.getReservationsList();
=======
        return Factory.reservations.reservations();
>>>>>>> db8b3bcdbb1b791f458af0efa5e47e014a239319
    }

}
