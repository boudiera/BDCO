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
            //  Creation  de la  requete
            Statement stmt = connexion.getConnection().createStatement();
            //  Execution  de la  requete
            String  STMT = "select * from pilots";
            //...
            ResultSet  rset = stmt.executeQuery (STMT);
            //  Conversion  du  resultat  en ArrayList <Pilot >
            ArrayList <Reservation> res = new ArrayList<Reservation> ();
            while (rset.next()) {
                res.add(new ConcreteReservation(new ArrayList<Integer>(),
                            rset.getInt(2), rset.getDate));
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
