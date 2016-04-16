/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;

/**
 *
 * @author boudiera
 */
public class Factory extends Observable {
    //SINGLETON: Start Code
    final private static Factory FACTORY = new Factory();
    
    private Factory(){
    }
    
    public static Factory singletonFactory(){
        return Factory.FACTORY;
    }
    //SINGLETON: End Code
    //////////////////////////////////////////////////////
    
    private RequeteFactory reservations;
    
    public RequeteFactory getReservations(){
        return FACTORY.reservations;
    }
    
    public void setReservations(RequeteFactory requeteFactory){
        FACTORY.reservations = requeteFactory;
        
        FACTORY.setChanged();
        FACTORY.notifyObservers(reservations);
    }
}
