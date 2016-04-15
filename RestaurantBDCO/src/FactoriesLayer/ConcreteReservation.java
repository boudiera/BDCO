package FactoriesLayer;

import Modele.*;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(int codeReservation, ListTables listCodeTables, int nbPersonnes, String nomClient, String phone, ReservationDate date, Service service) {
        super(codeReservation, listCodeTables, nbPersonnes, nomClient, phone, date, service);
    }
 
}
