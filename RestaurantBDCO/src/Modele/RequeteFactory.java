package Modele;

import java.util.ArrayList;

/** Une liste de reservations */
public abstract class RequeteFactory {
    // Renvoie TOUTES les réservations
    public abstract ArrayList<Reservation> getReservationsList();
    
    // Renvoie les tables libres pour un service donné
    public abstract ArrayList<Table> tablesLibres(int year, int month, int day, Service service);
    
}
