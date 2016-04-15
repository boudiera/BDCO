package Modele;

import java.util.ArrayList;

/** Une reservation */
public class Reservation {
    private final int codeReservation;
    private ArrayList<Table> listCodeTables;
    private int nbPersonnes;
    private String nomClient;
    private ReservationDate date;
    private String phone;
    private Service service;

    public Reservation(int codeReservation, ArrayList<Table> listCodeTables, int nbPersonnes, String nomClient, String phone, ReservationDate date, Service service) {
        this.codeReservation = codeReservation;
        this.listCodeTables = listCodeTables;
        this.nbPersonnes = nbPersonnes;
        this.nomClient = nomClient;
        this.date = date;
        this.phone = phone;
        this.service = service;
    }

    
        public String AfficheNombrePersonnes(){
        int tailleNombre= 1;
        String affNbPersonne="";
        int nbpers= this.nbPersonnes;
        
            while (nbpers/10>0){
                    nbpers=nbpers/10;
                    tailleNombre++;
            }
            for (int j=1; j<=Math.floor(13-tailleNombre)/2;j++){
                affNbPersonne=affNbPersonne.concat(" ");
            }
           affNbPersonne=affNbPersonne+nbpers;
           for (int j=1; j<13-tailleNombre-Math.floor(13-tailleNombre)/2;j++){
                affNbPersonne=affNbPersonne.concat(" ");
           }
           affNbPersonne=affNbPersonne.concat("|");
           return affNbPersonne;
    }
           
    public String AffichageNomClient() {
        String S="";
        int taille=this.nomClient.length();
        if (this.nomClient.length()<13){
            for( int i=1; i<=Math.floor((13-taille)/2); i++){
                S=S.concat(" ");
            }
            S=S+this.nomClient;
            for( int i=1; i<=13-taille-Math.floor((13-taille)/2); i++){
                S=S.concat(" ");
            }
            return S;
        }
        return nomClient;
    }
    
   
    
    public ArrayList<Table> getCodeTable() {
        return listCodeTables;
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

    
    public String getNomClient() {
        return nomClient;
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
