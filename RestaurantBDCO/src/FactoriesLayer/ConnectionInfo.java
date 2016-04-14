package FactoriesLayer;



public class ConnectionInfo {
    final String CONN_URL;
    final String USER;
    final String PASSWD;

    public ConnectionInfo(String CONN_URL, String USER, String PASSWD) {
        this.CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
        this.USER = "morieuxf";
        this.PASSWD = "morieuxf";
    }
    
}
