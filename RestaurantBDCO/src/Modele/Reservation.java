package Modele;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	private List<Command> listeCom;
	private final int[] codeTable;
	private final int nbPeople;
	private float price;
	private final int codeReservation;
        
        private Reservation(int codeReservation, int nbPeople, int [] codeTable){
            this.codeReservation=codeReservation;
            this.nbPeople=nbPeople;
            this.codeTable=codeTable;
            this.price=0;
            this.listeCom = new ArrayList<Command>();
        }
        
        public void addCommand(Command com){
            this.listeCom.add(com);
            this.price+=com.getPrice();
        }
        

}
