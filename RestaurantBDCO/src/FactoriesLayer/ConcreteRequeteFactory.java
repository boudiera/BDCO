package FactoriesLayer;

import java.sql.*;
import Modele.Reservation;
import Modele.ReservationDate;
import Modele.RequeteFactory;
import Modele.Service;
import Modele.Table;
import java.util.ArrayList;
import java.util.Calendar;

public class ConcreteRequeteFactory extends RequeteFactory{
    final private static ConcreteRequeteFactory CONCRETE_REQUETE_FACTORY = new ConcreteRequeteFactory();
    
    private static TheConnection connexion;

    private ConcreteRequeteFactory() {
    }
    
    public static ConcreteRequeteFactory singletonConcreteRequeteFactory(TheConnection connexion){
        ConcreteRequeteFactory.connexion = connexion;
        return ConcreteRequeteFactory.CONCRETE_REQUETE_FACTORY;
    }
    
    public static ConcreteRequeteFactory singletonConcreteRequeteFactory(){
        connexion = new TheConnection(new ConnectionInfo());
        return ConcreteRequeteFactory.CONCRETE_REQUETE_FACTORY;
    }

    @Override // REnvoie TOUTES les réservations
    public ArrayList<Reservation> getReservationsList() {
        connexion.open();
        try {
            String STMT_1 = "select R.CodeReservation, R.NbPersonnes, R.Jour, "
                    + "R.Heure, R.Minutes, R.NomService, C.NomClient, C.NumTel "
                    + "from Reservation R, Client C "
                    + "where R.CodeClient = C.CodeClient";
            
            //  Creation de la requete
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            //  Execution  de la  requete
            ResultSet rsetReservation = stmt.executeQuery();
          
            //  Conversion  du  resultat  en ArrayList <Reservation>
            ArrayList<Reservation> resReservation = new ArrayList<Reservation> ();
            while (rsetReservation.next()) {
                // Requete de recherche des tables
                String STMT_2 = "select T.CodeTable, T.NbPlace0, T.NbPlace1, "
                        + "T.NbPlace2, T.Localisation "
                        + "from Occupe O, TableRepas T "
                        + "where CodeReservation = ? "
                        + "and O.CodeTable = T.CodeTable";
                stmt = connexion.getConnection().prepareStatement(STMT_2);
                stmt.setInt(1, rsetReservation.getInt("CodeReservation")); 
                ResultSet rsetTable = stmt.executeQuery();
                // Conversion du résultat en Arraylist <codeTable>
                ArrayList<Table> resTable = new ArrayList<Table>();
                while (rsetTable.next()) {
                    resTable.add(new Table(rsetTable.getInt("CodeTable"),
                            rsetTable.getString("Localisation"),
                            rsetTable.getInt("NbPlace0"),
                            rsetTable.getInt("NbPlace1"),
                            rsetTable.getInt("NbPlace2")));
                }
                
                // Conversion de date et heure en ReservationDate
                ReservationDate date = new ReservationDate(
                    rsetReservation.getDate("Jour").getYear(),
                    rsetReservation.getDate("Jour").getMonth(),
                    rsetReservation.getDate("Jour").getDay(),
                    rsetReservation.getInt("Heure"),
                    rsetReservation.getInt("Minutes"));
                
                
                resReservation.add(new ConcreteReservation(
                        rsetReservation.getInt("CodeReservation"),
                        resTable,
                        rsetReservation.getInt("NbPersonnes"),
                        rsetReservation.getString("NomClient"),
                        rsetReservation.getString("NumTel"),
                        date,
                        Service.valueOf(rsetReservation.getString("NomService"))));
                
                //Fermeture
                rsetTable.close();
            }
            //  Fermeture
            rsetReservation.close();
            stmt.close();
            connexion.close();
            
            this.setChanged();
            this.notifyObservers(resReservation);
            
            return resReservation;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }
    
    @Override // Renvoie les tables libres pour un certain service d'un certain jour
    public ArrayList<Table> tablesLibres(int year, int month, int day, Service service){
        try {
            String STMT = "select T.CodeTable, T.NbPlace0, T.NbPlace1, "
                    + "T.NbPlace2, T.Localisation "
                    + "from TableRepas T "
                    + "minus"
                    + "( select T2.CodeTable, T2.NbPlace0, T2.NbPlace1, "
                    + "T2.NbPlace2, T2.Localisation"
                    + "from TableRepas T2, Occupe O, CodeReservation C "
                    + "where T2.CodeTable = O.CodeTable"
                    + "and O.CodeReservation = C.CodeReservation"
                    + "and C.Jour = ?"
                    + "and C.Service = ?";
            Calendar cal = new java.util.GregorianCalendar(year, month, day);
            Date d = new Date(cal.getTime().getTime());
            //  Creation de la requete
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT);
            stmt.setDate(1, d);
            //  Execution  de la  requete
            ResultSet rsetTable = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <Table> resTable = new ArrayList<Table> ();
            while (rsetTable.next()) {
                resTable.add(new Table(
                        rsetTable.getInt("CodeTable"),
                        rsetTable.getString("Localisation"),
                        rsetTable.getInt("NbPlace0"),
                        rsetTable.getInt("NbPlace1"),
                        rsetTable.getInt("NbPlace2")));
            }
            
            //  Fermeture
            rsetTable.close();
            stmt.close();
            return resTable;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }
}
