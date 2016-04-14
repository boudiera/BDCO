package FactoriesLayer;

import Modele.*;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(ListTables codeTable, int nbPersonnes, String nomClient, String tel, ReservationDate jour, Service service) {
        super(codeTable, nbPersonnes, nomClient, tel, jour, service);
    }
 
}
