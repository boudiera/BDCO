package FactoriesLayer;

import Modele.Article;
import Modele.InsertionFactory;
import Modele.Service;
import java.sql.Date;


import Modele.InsertionFactory;
import Modele.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
   public void createClient(String nomClient, String numTel){
       connexion.open();
       String STMT_1 = " insert into Client"
               + "values (seqClient.nextval,?,?)";
       try{
           PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
           stmt.setString(1,nomClient);
           stmt.setString(2,numTel);
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
   public void creerCarte(){
       connexion.open();
       String STMT_1 = " insert into Carte"
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
   public void ajoutArticleCarte(String nomArticle, int codeCarte, int prix){
       connexion.open();
       String STMT_1 = " insert into EstElement"
               + "values (?,?,?)";
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
        
        String STMT_2 = "insert into Reservation"
                + "values (seqReservation.nextval,?,?,?,?,?,?)";
        String STMT_3 = "select seqReservation.currval from Dual";
        
        
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_2);
            stmt.setInt(1,nbPersonnes);
            stmt.setInt(2,heure);
            stmt.setInt(3,minutes);
            stmt.setInt(4, codeClient);
            stmt.setDate(5, jour);
            stmt.setString(6,nomService.toString());
            stmt.executeQuery();
            stmt = connexion.getConnection().prepareStatement(STMT_3);
            ResultSet tab;
            tab = stmt.executeQuery();
            codeReservation=tab.getInt(1);
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
        connexion.open();
        String STMT_1 = "insert into Occupe"
                + "values (?,?)";
        try{
            PreparedStatement stmt=null;
            for(Table t : tablesOcc) {
                 stmt = connexion.getConnection().prepareStatement(STMT_1);
                 stmt.setInt(1,t.getCodeTable());
                 stmt.setInt(2,codeReservation);
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
    
}
