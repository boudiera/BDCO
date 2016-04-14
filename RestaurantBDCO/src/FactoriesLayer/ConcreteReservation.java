package FactoriesLayer;

import Modele.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(ListTables codeTable, int nbPersonnes, String nomClient, String tel, java.util.Date jour, Service service) {
        super(codeTable, nbPersonnes, nomClient, tel, jour, service);
    }
 
}
