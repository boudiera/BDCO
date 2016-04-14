package Modele;

import java.util.ArrayList;
import java.util.List;

public class ListReservations {
    private static ListReservations singletonListReservations = null;
    
    private List<Reservation> List;

    private ListReservations() {
        this.List=new ArrayList<>();
        updateListReservations();
    }
    
    public static ListReservations singletonListReservations(){
        if(ListReservations.singletonListReservations == null){
            ListReservations.singletonListReservations = new ListReservations();
        }

        return singletonListReservations;
    }

    public void addReservation(Reservation res){
        this.List.add(res);
    }

    public void deleteReservation(Reservation res){
        this.List.remove(res);
    }

    public List<Reservation> getList() {
        updateListReservations();
        return List;
    }

    public void setList(List<Reservation> List) {
        this.List = List;
    }
    
    public void updateListReservations(){
        
        // Call factory method !!
        // Update the current ArrayList of Reservations !!
        
    }

}
