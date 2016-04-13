package Modele;

import java.util.ArrayList;
import java.util.List;

public class ListReservations {
	private List<Reservation> listeRes;
	
	public ListReservations() {
		this.listeRes=new ArrayList<Reservation>();
	}
        
        public void addReservation(Reservation res){
            this.listeRes.add(res);
        }
        
        public void deleteReservation(Reservation res){
            this.listeRes.remove(res);
        }

    public List<Reservation> getListeRes() {
        return listeRes;
    }

    public void setListeRes(List<Reservation> listeRes) {
        this.listeRes = listeRes;
    }
           

}
