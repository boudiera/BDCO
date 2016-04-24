/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import FactoriesLayer.ConcreteInsertionFactory;
import FactoriesLayer.ConcreteRequeteFactory;
import FactoriesLayer.ConnectionInfo;
import FactoriesLayer.TheConnection;
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
    
    private RequeteFactory requeteFactory = new ConcreteRequeteFactory(new TheConnection(new ConnectionInfo()));
    private InsertionFactory insertionFactory = new ConcreteInsertionFactory(new TheConnection(new ConnectionInfo()));
    
    public RequeteFactory getRequeteFactory(){
        this.setChanged();
        return this.requeteFactory;
    }

    public InsertionFactory getInsertionFactory() {
        this.setChanged();
        return this.insertionFactory;
    }

    public void setInsertionFactory(InsertionFactory insertionFactory) {
        this.setChanged();
        this.insertionFactory = insertionFactory;
    }
   
    public void setRequeteFactory(RequeteFactory requeteFactory) {
        this.setChanged();
        this.requeteFactory = requeteFactory;
    }
}
