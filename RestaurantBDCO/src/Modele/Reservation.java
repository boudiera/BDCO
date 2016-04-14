package Modele;

import java.util.ArrayList;

/** Une reservation */
public class Reservation {
    private ListTables codeTable;
    private int nbPersonnes;
    private String nomClient;
    private ReservationDate date;
    private String phone;
    private Service service;

    public Reservation(ListTables codeTable, int nbPersonnes, String nomClient, String phone, ReservationDate date, Service service) {
        this.codeTable = codeTable;
        this.nbPersonnes = nbPersonnes;
        this.nomClient = nomClient;
        this.date = date;
        this.phone = phone;
        this.service = service;
    }

    public ArrayList<Integer> getCodeTable() {
        return codeTable;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public ReservationDate getDate() {
        return date;
    }    

    public String getNomClient() {
        return nomClient;
    }

    public String getPhone() {
        return phone;
    }

    public void setCodeTable(ListTables codeTable) {
        this.codeTable = codeTable;
    }

    public void setDate(ReservationDate jour) {
        this.date = jour;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

}
