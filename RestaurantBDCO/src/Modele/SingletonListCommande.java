/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author trentini
 */
public class SingletonListCommande extends Observable{
    final private static SingletonListCommande SINGLETON_LIST_COMMANDE = new SingletonListCommande();
    
    // Liste de commandes avec le "Identificateur" comme clé
    private HashMap<Integer, HashMap<String, Commande>> listCommande = new HashMap<>();
    
    private SingletonListCommande(){
    }
    
    public static SingletonListCommande singletonListCommande(){
        return SingletonListCommande.SINGLETON_LIST_COMMANDE;
    }
    
    // Says if the list of commandes is empty or not (if not empty, it can be used to prevent the closing of the application
    public boolean isEmpty(){
        return this.listCommande.isEmpty();
    }
    
    // Add a commande in the list (not in the database)
    public void addCommand(int codeReservation, Commande commande){
        if(this.listCommande.containsKey(codeReservation)){
            this.listCommande.get(codeReservation).put(commande.getIdentifier(), commande);
        }else{
            this.listCommande.put(codeReservation, new HashMap<String, Commande>());
            this.listCommande.get(codeReservation).put(commande.getIdentifier(), commande);
        }

        this.setChanged();
        this.notifyObservers();
    }
    
    // Remove a commande from the list (not from the database)
    public void removeCommand(int codeReservation, String identifier){
        this.listCommande.get(codeReservation).remove(identifier);
        if(this.listCommande.get(codeReservation).isEmpty()){
            this.listCommande.remove(codeReservation);
        }
        
        this.setChanged();
        this.notifyObservers();
    }
    
    // Returns a list of Commandes inside a Reservation
    public ArrayList<Commande> getListCommandByReservationCode(int codeReservation){
        HashMap<String, Commande> hash = this.listCommande.get(codeReservation);
                
        if(hash == null){
            return new ArrayList<Commande>();
        }else{
            ArrayList<Commande> listCommande = new ArrayList<Commande>(hash.values());
            return  listCommande;
        }
    }
    
    // Returns the list of articles inside a Commande (that is, of course, inside a Reservation)
    public ArrayList<Article> getListArticlesByReservationCodeAndCommandeIdentifier(int codeReservation, String identifierCommande){
        
        ArrayList<Commande> array = this.getListCommandByReservationCode(codeReservation);
        
        for (Commande c : array) {
            if (c.getIdentifier().equals(identifierCommande)) {
                return c.getListArticles();
            }
        }
        return new ArrayList<Article>();
    }
}
