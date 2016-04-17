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
    
    private RequeteFactory requeteFactory;
    private InsertionFactory insertionFactory;
    public RequeteFactory getReservations(){
        return FACTORY.requeteFactory;
    }

    public InsertionFactory getInsertionFactory() {
        return insertionFactory;
    }

    public void setInsertionFactory(InsertionFactory insertionFactory) {
        this.insertionFactory = insertionFactory;
        
    }
   
    public void setRequeteFactory(RequeteFactory requeteFactory){
        FACTORY.requeteFactory = requeteFactory;
        
        FACTORY.setChanged();
        FACTORY.notifyObservers(this.requeteFactory);
    }
}
