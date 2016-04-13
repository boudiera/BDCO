package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.sql.*;

/** Une reservation */
public abstract class Reservation {
    private ArrayList<Integer> codeTable;
    private int nbPersonnes;
    private int nomClient;
    private Date jour;
    private float heure;

    public Reservation(ArrayList<Integer> codeTable, int nbPersonnes, int nomClient, Date jour, float heure) {
        this.codeTable = codeTable;
        this.nbPersonnes = nbPersonnes;
        this.nomClient = nomClient;
        this.jour = jour;
        this.heure = heure;
    }

    
}
