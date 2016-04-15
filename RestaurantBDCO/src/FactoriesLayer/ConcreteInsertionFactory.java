/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoriesLayer;

import Modele.InsertionFactory;

/**
 *
 * @author michecle
 */
public class ConcreteInsertionFactory extends InsertionFactory {
    private TheConnection connexion;

    public ConcreteInsertionFactory(TheConnection connexion) {
        this.connexion = connexion;
    }
    
    @Override
    public void createReservation(String NomClient, String NumTelClient) {
        connexion.open();
        
        String STMT_1 = "insert into Client"
                + "values (SeqClient.nextval, ?, ?)";
                
        
        String STMT = "insert into Reservation"
                + "values (A FINIR)";
    }
    
    
}
