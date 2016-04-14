package FactoriesLayer;

import java.sql.*;
import Modele.Reservation;
import Modele.ReservationFactory;
import java.util.ArrayList;

public class ConcreteReservationFactory extends ReservationFactory{
    private TheConnection  connexion;

    public ConcreteReservationFactory(TheConnection connexion) {
        this.connexion = connexion;
    }

    @Override
    public ArrayList<Reservation> reservations() {
        try {
            String  STMT_1 = "select * from pilots";
            String STMT_2;
            //  Creation de la requete
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            //  Execution  de la  requete
            ResultSet  rsetReservation = stmt.executeQuery ();
            ResultSet rsetTable;
            //  Conversion  du  resultat  en ArrayList <Pilot >
            ArrayList <Reservation> res = new ArrayList<Reservation> ();
            while (rsetReservation.next()) {
                STMT_2="select CodeTable from Occupe where CodeReservation =";
                STMT_2+=rsetReservation.getInt("CodeReservation");
                stmt=connexion.getConnection().prepareStatement(STMT_2);
                rsetTable = stmt.executeQuery();
                
                res.add(new ConcreteReservation(rset.getInt(), rset.getString(2)));
            }
            //  Fermeture
            rset.close();
            stmt.close();
            return res;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        } 
    }
    
}
