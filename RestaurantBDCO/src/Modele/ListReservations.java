package Modele;

import java.util.ArrayList;
import java.util.List;

public class ListReservations {
    private List<Reservation> ListReservations;

    public ListReservations() {
        this.ListReservations=new ArrayList<>();
        updateListReservations();
    }

    public void addReservation(Reservation res){
        this.ListReservations.add(res);
    }

    public void deleteReservation(Reservation res){
        this.ListReservations.remove(res);
    }

    public List<Reservation> getListReservations() {
        return ListReservations;
    }

    public void setListReservations(List<Reservation> ListReservations) {
        this.ListReservations = ListReservations;
    }
    
    public void updateListReservations(){
        
        // Call factory method !!
        // Update the current ArrayList of Reservations !!
        
    }

}
