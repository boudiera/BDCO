/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import Modele.Reservation;
import Modele.ReservationFactory;
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
        //return this.reservFactory.reservations();
        return null;
    }

    public void VerifyAddReservation(Date date, String nbPersonnes, String tel) throws MauvaiseDateException,NbPersonneException, MoisException,JourException, HeureException, MinuteException, TelephoneException{

        int nbPersonnesEnt= Integer.parseInt(nbPersonnes);
        
        //gestion date antérieure à celle du jour
        if (Calendar.getInstance().getTime().getYear()> date.getYear())
            throw new MauvaiseDateException();
        else if((Calendar.getInstance().getTime().getYear()== date.getYear()) && (Calendar.getInstance().getTime().getMonth()>date.getMonth()))
            throw new MauvaiseDateException();
        else if ((Calendar.getInstance().getTime().getYear()== date.getYear()) && (Calendar.getInstance().getTime().getMonth()==date.getMonth()) && (Calendar.getInstance().getTime().getDay()==date.getDay()))
            throw new MauvaiseDateException();
        
        //gestion format date 
        //pour les mois:
        if (date.getMonth()>12)
            throw new MoisException();
        if (date.getDay()>31)
            throw  new JourException();
        if (date.getMonth()==2){ // gestion du mois de février
            if (date.getYear()%4==0){ //annee bissextile
                if(date.getDay()>29)
                    throw new JourException();
            }
            else if(date.getDay()>28)
                throw new JourException();
        }
        if( (date.getMonth()== 4 || date.getMonth()== 6 || date.getMonth()==9 || date.getMonth()==11) && ( date.getDay()>30)){
            throw new JourException();
        }
        
        //gestion nombre client négatif
        
        if (nbPersonnesEnt<0)
            throw new NbPersonneException();
         
        //gestion d'heure:
        
        if(date.getHours()<0 || date.getHours()>24)
            throw new HeureException();
        
        //gestion minutes
        if(date.getMinutes()<0 || date.getMinutes()>59)
            throw new MinuteException();
        
        //gestion téléphone
        if(tel.length()!=10)
            throw new TelephoneException();  
    }
}
