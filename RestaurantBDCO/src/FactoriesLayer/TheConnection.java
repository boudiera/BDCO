package FactoriesLayer;

import java.sql.*;

public class TheConnection {
    private ConnectionInfo info;
    private Connection conn;
    
    public TheConnection(ConnectionInfo info) {
        this.info = info;
    }
    
    public void open () {
        try {
            //  Enregistrement  du  driver  Oracle
          //  System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            //System.out.println("loaded");
            //  Etablissement  de la  connexion
           // System.out.print("Connecting  to the database ... ");
            conn = DriverManager.getConnection(info.CONN_URL, info.USER, info.PASSWD);
            //System.out.println("connected");
        } catch (SQLException  e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
        }
    }
    
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }   
    }
    
    public Connection getConnection() {
        return conn;
    }
       
}
