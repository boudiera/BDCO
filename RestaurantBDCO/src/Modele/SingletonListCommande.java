/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author trentini
 */
public class SingletonListCommande {
    final private static SingletonListCommande singleton = new SingletonListCommande();
    
    private HashMap<Integer, HashMap<String, Commande>> listCommande = new HashMap<>();
    
    private SingletonListCommande(){
        
    }
    
    public static SingletonListCommande singletonListCommande(){
        return SingletonListCommande.singleton;
    }
    
    public void addCommand(int codeReservation, Commande commande){
        if(this.listCommande.containsKey(codeReservation)){
            this.listCommande.get(codeReservation).put(commande.getIdentifier(), commande);
        }else{
            this.listCommande.put(codeReservation, new HashMap<String, Commande>());
            this.listCommande.get(codeReservation).put(commande.getIdentifier(), commande);
        }
    }
    
    public void removeCommand(int codeReservation, String identifier){
        this.listCommande.get(codeReservation).remove(identifier);
    }
    
    public ArrayList<Commande> getListCommandByReservationCode(int codeReservation){
        HashMap<String, Commande> hash = this.listCommande.get(codeReservation);
                
        if(hash == null){
            return new ArrayList<Commande>();
        }else{
            return (ArrayList<Commande>) hash.values();
        }
    }
}
