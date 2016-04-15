package Modele;

import java.util.ArrayList;
import java.util.Observable;

/** Une liste de reservations */
public abstract class RequeteFactory extends Observable{
    // Renvoie TOUTES les réservations
    public abstract ArrayList<Reservation> getReservationsList();
    
    // Renvoie les tables libres pour un service donné
    public abstract ArrayList<Table> tablesLibres(int year, int month, int day, Service service);
  
}
