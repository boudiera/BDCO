package FactoriesLayer;

import Modele.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(ArrayList<Integer> codeTable, int nbPersonnes, int nomClient, Date jour, float heure) {
        super(codeTable, nbPersonnes, nomClient, jour, heure);
    }
    
}
