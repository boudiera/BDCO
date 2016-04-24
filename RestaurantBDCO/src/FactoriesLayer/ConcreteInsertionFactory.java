package FactoriesLayer;

import Modele.Article;
import Modele.Commande;
import Modele.Service;
import java.sql.Date;

import Modele.InsertionFactory;
import Modele.Table;
import Modele.TypeArticle;
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
    public void creerClient(String nomClient, String numTel) {
        //connexion.open();
        String STMT_1 = " insert into Client "
                + "values (seqClient.nextval,?,?)";
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setString(1, nomClient);
            stmt.setString(2, numTel);
            stmt.executeUpdate();
            stmt.close();
            // connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void creerCarte() {
        connexion.open();
        String STMT_1 = " insert into Carte "
                + "values (seqCarte.nextval)";
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.executeQuery();
            stmt.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void ajoutArticleCarte(String nomArticle, int codeCarte, float prix) {
        connexion.open();
        String STMT_1 = " insert into EstElement "
                + "values (?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setString(1, nomArticle);
            stmt.setInt(2, codeCarte);
            stmt.setFloat(3, prix);
            stmt.executeQuery();
            stmt.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void creerReservation(ArrayList<Table> tablesOcc, int nbPersonnes, int heure, int minutes, int codeClient, Date jour, Service nomService) {
        connexion.open();
        int codeReservation;

        String STMT_1 = "insert into Reservation "
                + "values (seqReservation.nextval, ?, ?, ?, ?, ?, ?, 0)";
        String STMT_2 = "select seqReservation.currval from Dual";

        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, nbPersonnes);
            stmt.setInt(2, heure);
            stmt.setInt(3, minutes);
            stmt.setInt(4, codeClient);
            stmt.setDate(5, jour);
            stmt.setString(6, nomService.name());
            stmt.executeQuery();
            stmt = connexion.getConnection().prepareStatement(STMT_2);
            ResultSet tab;
            tab = stmt.executeQuery();
            tab.next();
            codeReservation = tab.getInt(1);
            this.creerOccTable(tablesOcc, codeReservation);
            System.out.println("Code Reservation" + codeReservation);
            stmt.close();
            tab.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void creerOccTable(ArrayList<Table> tablesOcc, int codeReservation) {
        //connexion.open();
        String STMT_1 = "insert into Occupe "
                + "values (?, ?)";
        try {
            PreparedStatement stmt = null;
            for (Table t : tablesOcc) {
                stmt = connexion.getConnection().prepareStatement(STMT_1);
                stmt.setInt(1, codeReservation);
                stmt.setInt(2, t.getCodeTable());
                stmt.executeQuery();

            }
            stmt.close();
            //   connexion.close();

        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void creeCommande(Commande commande) {
        connexion.open();
        String STMT_1 = "insert into Commande "
                + "values (?,?,?)";
        PreparedStatement stmt = null;
        try {
            for (String nomArt : commande.getRegroupeArticle().keySet()) {

                stmt = connexion.getConnection().prepareStatement(STMT_1);
                stmt.setInt(1, commande.getCodeReservation());
                stmt.setString(2, nomArt);
                stmt.setInt(3, commande.getRegroupeArticle().get(nomArt));
                stmt.executeQuery();

            }
            stmt.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void addCommande(Commande commande) {
        connexion.open();

        try {

            String STMT_1 = "select C.Quantite "
                    + " from Commande C "
                    + " where C.CodeReservation = ? "
                    + " and C.NomArticle = ? ";
            PreparedStatement stmt1 = connexion.getConnection().prepareStatement(STMT_1);

            String STMT_2 = "insert into Commande "
                    + "values (?,?,?)";
            PreparedStatement stmt2 = connexion.getConnection().prepareStatement(STMT_2);

            String STMT_3 = "update Commande "
                    + "set Commande.Quantite = ? "
                    + "where Commande.CodeReservation = ? "
                    + "and Commande.NomArticle = ? ";
            PreparedStatement stmt3 = connexion.getConnection().prepareStatement(STMT_3);

            for (String nomArt : commande.getRegroupeArticle().keySet()) {

                stmt1.setInt(1, commande.getCodeReservation());
                stmt1.setString(2, nomArt);
                ResultSet res1 = stmt1.executeQuery();

                if (res1.next()) {

                    stmt3.setInt(1, res1.getInt(1) + commande.getRegroupeArticle().get(nomArt));
                    stmt3.setInt(2, commande.getCodeReservation());
                    stmt3.setString(3, nomArt);
                    stmt3.executeQuery();

                } else {

                    stmt2.setInt(1, commande.getCodeReservation());
                    stmt2.setString(2, nomArt);
                    stmt2.setInt(3, commande.getRegroupeArticle().get(nomArt));
                    stmt2.executeQuery();

                }
                res1.close();
            }

            stmt1.close();
            stmt2.close();
            stmt3.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void supprimeCommande(Commande commande) {
        connexion.open();
        String STMT_1 = "delete from Commande"
                + " where CodeReservation = ?";
        PreparedStatement stmt;
        try {
            stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, commande.getCodeReservation());
            stmt.executeQuery();
            stmt.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void ajoutPrix(int codeReservation, float prix) {

        connexion.open();
        String STMT_1 = "update Reservation "
                + "set Reservation.Prix = ? "
                + "where Reservation.CodeReservation = ? ";
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setFloat(1, prix);
            stmt.setInt(2, codeReservation);
            stmt.executeQuery();
            stmt.close();
            connexion.close();
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }

    }

}
