/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlModele;

import Modele.Service;
import java.util.Date;

/**
 *
 * @author leclairn
 */
public class CreateReservation implements Command {
    private final String clientName;
    private final String clientPhone;
    private final int nbPeople;
    private final Date date;
    private final Service service;
    
    public CreateReservation(String clientName, String clientPhone, int nbPeople, Date date, Service service){
        this.clientName=clientName;
        this.clientPhone=clientPhone;
        this.nbPeople=nbPeople;
        this.date=date;
        this.service=service;
    }
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
