package FactoriesLayer;

import Modele.Article;
import Modele.Commande;
import Modele.Service;
import java.sql.Date;


import Modele.InsertionFactory;
import Modele.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
   public void creerClient(String nomClient, String numTel){
       //connexion.open();
       String STMT_1 = " insert into Client "
               + "values (seqClient.nextval,?,?)";
       try{
           PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
           stmt.setString(1,nomClient);
           stmt.setString(2,numTel);
           stmt.executeUpdate();
           stmt.close();
          // connexion.close();
       }
       catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
   }
    
   @Override
   public void creerCarte(){
       connexion.open();
       String STMT_1 = " insert into Carte "
               + "values (seqCarte.nextval)";
       try{
           PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
           stmt.executeQuery();
           stmt.close();
           connexion.close();
       }
       catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
   }
   
    
   @Override
   public void ajoutArticleCarte(String nomArticle, int codeCarte, float prix){
       connexion.open();
       String STMT_1 = " insert into EstElement "
               + "values (?, ?, ?)";
       try{
           PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
           stmt.setString(1,nomArticle);
           stmt.setInt(2,codeCarte);
           stmt.setFloat(3, prix);
           stmt.executeQuery();
           stmt.close();
           connexion.close();
       }
       catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
   }
  

    @Override
    public void creerReservation(ArrayList<Table> tablesOcc, int nbPersonnes, int heure, int minutes, int codeClient, Date jour, Service nomService) {
          connexion.open();
          int codeReservation;
        
        String STMT_1 = "insert into Reservation "
                + "values (seqReservation.nextval, ?, ?, ?, ?, ?, ?)";
        String STMT_2 = "select seqReservation.currval from Dual";
        
        
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1,nbPersonnes);
            stmt.setInt(2,heure);
            stmt.setInt(3,minutes);
            stmt.setInt(4, codeClient);
            stmt.setDate(5, jour);
            stmt.setString(6,nomService.name());
            stmt.executeQuery();
            stmt = connexion.getConnection().prepareStatement(STMT_2);
            ResultSet tab;
            tab = stmt.executeQuery();
            tab.next();
            codeReservation = tab.getInt(1);
            this.creerOccTable(tablesOcc, codeReservation);
            stmt.close();
            tab.close();
            connexion.close();
        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
    }

    @Override
    public void creerOccTable(ArrayList<Table> tablesOcc, int codeReservation) {
        //connexion.open();
        String STMT_1 = "insert into Occupe "
                + "values (?, ?)";
        try{
            PreparedStatement stmt=null;
            for(Table t : tablesOcc) {
                 stmt = connexion.getConnection().prepareStatement(STMT_1);
                 stmt.setInt(1,t.getCodeTable());
                 stmt.setInt(2,codeReservation);
                 stmt.executeQuery();
                
            }
            stmt.close();
         //   connexion.close();
            
        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
    }

    @Override
    public void creeCommande(Commande commande) {
        connexion.open();
        String STMT_1 = "insert into Commande "
                + "values (?,?,?)";
        PreparedStatement stmt = null;
        try{
            for(String nomArt : commande.getRegroupeArticle().keySet()){
                
                stmt = connexion.getConnection().prepareStatement(STMT_1);
                stmt.setInt(1, commande.getCodeReservation());
                stmt.setString(2,nomArt);
                stmt.setInt(3, commande.getRegroupeArticle().get(nomArt));
                stmt.executeQuery();
                
            }
            stmt.close();
            connexion.close();
        


        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }    
        
    }

    @Override
    public void supprimeCommande(Commande commande) {
        connexion.open();
        String STMT_1 = "delete from Commande"
                + " where CodeReservation = ?";
        PreparedStatement stmt;
        try{   
            stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, commande.getCodeReservation());
            stmt.executeQuery();
            stmt.close();
            connexion.close();
        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
    }
    
}
