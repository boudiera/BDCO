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
    
    @Override // Creation et insertion d'une reservation
    public void createReservation() {
        connexion.open();
        
        String STMT_2 = "insert into Reservation"
                + "values (A FINIR)";
        String STMT_3 = "insert into Occupe"
                + "values (A FINIR)";
    }    
    
}
