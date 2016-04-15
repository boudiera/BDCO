/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import Modele.Factory;
import Modele.Reservation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Iago Felipe Trentin
 */
public class Controller {
    
    private AbstractView View;
    
    public AbstractView getView(){
        return this.View;
    }
    
    public void setView(AbstractView v){
        this.View = v;
        this.View.showView(true);
    }
    
    public ArrayList<Reservation> getReservationList(){
        return Factory.reservations.getReservationsList();
    }

    public void VerifyAddReservation(String annee,String mois, String jour, String heure, String minute, String nbPersonnes, String tel) throws AddReservationException{
        int anneeEnt=Integer.parseInt(annee);
        int moisEnt=Integer.parseInt(mois);
        int jourEnt=Integer.parseInt(jour);
        int heureEnt=Integer.parseInt(heure);
        int minuteEnt=Integer.parseInt(minute);
        int nbPersonnesEnt= Integer.parseInt(nbPersonnes);
       
        //gestion date antérieure à celle du jour
        if (Calendar.getInstance().getTime().getYear()> (anneeEnt-1900))
            throw new MauvaiseDateException();
        else if((Calendar.getInstance().getTime().getYear()== (anneeEnt-1900)) && (Calendar.getInstance().getTime().getMonth()+1>moisEnt))
            throw new MauvaiseDateException();
        else if ((Calendar.getInstance().getTime().getYear()== (anneeEnt-1900)) && (Calendar.getInstance().getTime().getMonth()+1==moisEnt) && (Calendar.getInstance().getTime().getDate()>jourEnt))
            throw new MauvaiseDateException();
        
        //gestion format date 
        //pour les mois:
        if (moisEnt>12)
            throw new MoisException();
        if (jourEnt>31)
            throw  new JourException();
        if (moisEnt==2){ // gestion du mois de février
            if (anneeEnt%4==0){ //annee bissextile
                if(jourEnt>29)
                    throw new JourException();
            }
            else if(jourEnt>28)
                throw new JourException();
        }
        if( (moisEnt== 4 || moisEnt== 6 || moisEnt==9 || moisEnt==11) && ( jourEnt>30)){
            throw new JourException();
        }
        
        //gestion nombre client négatif
        
        if (nbPersonnesEnt<0)
            throw new NbPersonneException();
         
        //gestion d'heure:
        
        if(heureEnt<0 || heureEnt>24)
            throw new HeureException();
        
        //gestion minutes
        if(minuteEnt<0 || minuteEnt>59)
            throw new MinuteException();
        
        //gestion téléphone
        if(tel.length()!=10)
            throw new TelephoneException();  
    }
}
