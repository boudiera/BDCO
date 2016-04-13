package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.sql.*;

/** Une reservation */
public abstract class Reservation {
    private ArrayList<Integer> codeTable;
    private int nbPersonnes;
    private String nomClient;
    private Date jour;
    private float heure;
    private int tel;

    public Reservation(ArrayList<Integer> codeTable, int nbPersonnes, String nomClient, Date jour, float heure) {
        this.codeTable = codeTable;
        this.nbPersonnes = nbPersonnes;
        this.nomClient = nomClient;
        this.jour = jour;
        this.heure = heure;
    }

    public ArrayList<Integer> getCodeTable() {
        return codeTable;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public float getHeure() {
        return heure;
    }

    public Date getJour() {
        return jour;
    }

    public String getNomClient() {
        return nomClient;
    }

    public int getTel() {
        return tel;
    }

    public void setCodeTable(ArrayList<Integer> codeTable) {
        this.codeTable = codeTable;
    }

    public void setHeure(float heure) {
        this.heure = heure;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
 
   }
