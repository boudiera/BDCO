package Modele;

import java.util.ArrayList;

/**
 * Une reservation
 */
public abstract class Reservation {

    private final int codeReservation;
    private ArrayList<Table> listCodeTables;
    private int nbPersonnes;
    private String clientName;
    private String phone;
    private ReservationDate date;
    private Service service;

    public Reservation(int codeReservation, ArrayList<Table> listCodeTables, int nbPersonnes, String clientName, String phone, ReservationDate date, Service service) {
        this.codeReservation = codeReservation;
        this.listCodeTables = listCodeTables;
        this.nbPersonnes = nbPersonnes;
        this.clientName = clientName;
        this.date = date;
        this.phone = phone;
        this.service = service;
    }
public String PrintReservation(){
    String service;
    if (this.service.equals(Service.MIDI))
        service="midi";
    else 
       service="soir";
    String S="Le "+ this.getDate().toString()+ "service du "+ service;
    return S;
}
    public String printNbPeople() {
        int tailleNombre = String.valueOf(this.nbPersonnes).length();
        String affNbPersonne = "";
        int nbpers = this.nbPersonnes;

        for (int j = 1; j <= Math.floor(15 - tailleNombre) / 2; j++) {
            affNbPersonne = affNbPersonne.concat(" ");
        }
        affNbPersonne = affNbPersonne + nbpers;
        for (int j = 1; j < 15 - tailleNombre - Math.floor(15 - tailleNombre) / 2; j++) {
            affNbPersonne = affNbPersonne.concat(" ");
        }
        affNbPersonne = affNbPersonne.concat("|");
        return affNbPersonne;
    }

    public String printClientName() {
        String S = "";
        int taille = this.clientName.length();
        if (this.clientName.length() < 13) {
            for (int i = 1; i <= Math.floor((13 - taille) / 2); i++) {
                S = S.concat(" ");
            }
            S = S + this.clientName;
            for (int i = 1; i <= 13 - taille - Math.floor((13 - taille) / 2); i++) {
                S = S.concat(" ");
            }
            return S;
        }
        return clientName;
    }

    public ArrayList<Table> getCodeTable() {
        return listCodeTables;
    }

    //retourn vrai si la date de la reservation est plus recente que celle passée en paramettre
    public boolean EstPlusRecente (Reservation res){
        if(this.date.getYear()<res.getDate().getYear())
            return true;
        else if(this.date.getYear()==res.getDate().getYear() && this.date.getMonth()<res.getDate().getMonth())
            return true;
        else if(this.date.getYear()==res.getDate().getYear() && this.date.getMonth()==res.getDate().getMonth() && this.date.getDay()<res.getDate().getDay())
            return true;
        else if(this.date.getYear()==res.getDate().getYear() && this.date.getMonth()==res.getDate().getMonth() && this.date.getDay()==res.getDate().getDay()&& this.date.getHour()<res.getDate().getHour())
            return true;
        else if(this.date.getYear()==res.getDate().getYear() && this.date.getMonth()==res.getDate().getMonth() && this.date.getDay()==res.getDate().getDay()&& this.date.getHour()==res.getDate().getHour()&& this.date.getMin()<res.getDate().getMin())
            return true;
        else
            return false;                 
    }
    
    
    
    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public ReservationDate getDate() {
        return date;
    }

    public int getCodeReservation() {
        return codeReservation;
    }

    public ArrayList<Table> getListCodeTables() {
        return listCodeTables;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setListCodeTables(ArrayList<Table> listCodeTables) {
        this.listCodeTables = listCodeTables;
    }

    public void setDate(ReservationDate jour) {
        this.date = jour;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
