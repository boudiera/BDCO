package InterfaceBaseDeDonnees;

import Modele.*;
import java.util.ArrayList;

public class ConcreteReservation extends Reservation {

    public ConcreteReservation(int codeReservation, ArrayList<Table> listCodeTables, int nbPersonnes, String nomClient, String phone, ReservationDate date, Service service) {
        super(codeReservation, listCodeTables, nbPersonnes, nomClient, phone, date, service);
    }
 
}
