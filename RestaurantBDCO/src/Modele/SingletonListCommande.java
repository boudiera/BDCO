/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author trentini
 */
public class SingletonListCommande extends Observable{
    final private static SingletonListCommande SINGLETON_LIST_COMMANDE = new SingletonListCommande();
    
    private HashMap<Integer, HashMap<String, Commande>> listCommande = new HashMap<>();
    
    private SingletonListCommande(){
    }
    
    public static SingletonListCommande singletonListCommande(){
        return SingletonListCommande.SINGLETON_LIST_COMMANDE;
    }
    
    public boolean isEmpty(){
        return this.listCommande.isEmpty();
    }
    
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
    
    public void removeCommand(int codeReservation, String identifier){
        this.listCommande.get(codeReservation).remove(identifier);
        if(this.listCommande.get(codeReservation).isEmpty()){
            this.listCommande.remove(codeReservation);
        }
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public ArrayList<Commande> getListCommandByReservationCode(int codeReservation){
        HashMap<String, Commande> hash = this.listCommande.get(codeReservation);
                
        if(hash == null){
            return new ArrayList<Commande>();
        }else{
            ArrayList<Commande> listCommande = new ArrayList<Commande>(hash.values());
            return  listCommande;
        }
    }
    
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
