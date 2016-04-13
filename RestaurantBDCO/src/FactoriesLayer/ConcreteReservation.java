package FactoriesLayer;

import Modele.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(ArrayList<Integer> codeTable, int nbPersonnes, String nomClient, Date jour, int heure, String tel) {
        super(codeTable, nbPersonnes, nomClient, jour, heure, tel);
    }
 
}
