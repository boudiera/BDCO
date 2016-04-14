package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;

/** Une reservation */
public class Reservation {
    private ArrayList<Integer> codeTable;
    private int nbPersonnes;
    private String nomClient;
    private Date date;
    private String tel;
    private Service service;

    public Reservation(ArrayList<Integer> codeTable, int nbPersonnes, String nomClient, Date date, String tel, Service service) {
        this.codeTable = codeTable;
        this.nbPersonnes = nbPersonnes;
        this.nomClient = nomClient;
        this.date = date;
        this.tel = tel;
        this.service = service;
    }


    public ArrayList<Integer> getCodeTable() {
        return codeTable;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Date getDate() {
        return date;
    }

    

    public String getNomClient() {
        return nomClient;
    }

    public String getTel() {
        return tel;
    }

    public void setCodeTable(ArrayList<Integer> codeTable) {
        this.codeTable = codeTable;
    }

 

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    
    
    
    
}
