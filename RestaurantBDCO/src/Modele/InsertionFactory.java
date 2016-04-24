/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author michecle
 */
public abstract class InsertionFactory {
    
    // Insertion d'une carte
    public abstract void creerCarte();
    
    // Insertion d'un article
    public abstract void ajoutArticleCarte(String nomArticle, int codeCarte, float prix);
    
    // Insertion d'un client
    public abstract void creerClient(String nomClient, String numTel);
    
    // Insertion d'une commande
    public abstract void creerReservation(ArrayList<Table> tablesOcc, int nbPersonnes, int heure, int minutes, int codeClient, Date jour, Service nomService);
    
    // Insertion de tables occupees 
    public abstract void creerOccTable(ArrayList<Table> tablesOcc, int codeReservation);
    
    // Insert dans la base de donnée une commande ( attention a update et pas insert ) si une commande de la meme reservation contient des articles similaires
    public abstract void creeCommande(Commande commande);
        
    public abstract void addCommande(Commande commande);
    
    // Supprime dans la base de donnée une commande faire attention de update et non delete si la quantité d'article commandé de la reservation ( d'ou vient la commande ) est > 0 (voir google drive)
    public abstract void supprimeCommande(Commande commande);
    
    // Ajoute le prix à une reservation qui vient d'être facturée
    public abstract void ajoutePrix(int CodeReservation, int Prix);
    
}
