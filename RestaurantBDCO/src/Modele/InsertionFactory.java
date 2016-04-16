/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author michecle
 */
public abstract class InsertionFactory {
    
    public abstract void creerCarte();
    public abstract void ajoutArticleCarte(String nomArticle, int codeCarte, int prix);
    public abstract void createClient(String nomClient, String numTel);
    
    // Insertion d'une commande
    public abstract void creerReservation(ArrayList<Table> tablesOcc, int nbPersonnes, int heure, int minutes, int codeClient, Date jour, Service nomService);
    public abstract void creerOccTable(ArrayList<Table> tablesOcc, int codeReservation);
}
