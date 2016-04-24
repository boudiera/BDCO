package FactoriesLayer;

import Modele.Article;
import java.sql.*;
import Modele.Reservation;
import Modele.ReservationDate;
import Modele.RequeteFactory;
import Modele.Service;
import Modele.Table;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteRequeteFactory extends RequeteFactory{
    
    private static TheConnection connexion;

    public ConcreteRequeteFactory(TheConnection connexion) { 
        this.connexion = connexion;
    }

    @Override // Renvoie TOUTES les réservations
    public ArrayList<Reservation> getReservationsList() {
        connexion.open();
        try {
            String STMT_1 = "select R.CodeReservation, R.NbPersonnes, R.Jour, "
                    + "R.Heure, R.Minutes, R.NomService, C.NomClient, C.NumTel "
                    + "from Reservation R, Client C "
                    + "where R.CodeClient = C.CodeClient and "
                    + "R.prix = 0 "
                   + "order by R.Jour, R.Heure, R.Minutes";
            
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
                    rsetReservation.getDate("Jour").getYear()+1900,
                    rsetReservation.getDate("Jour").getMonth()+1,
                    rsetReservation.getDate("Jour").getDate(),
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
            //Fermeture
            rsetReservation.close();
            stmt.close();
            connexion.close();
            return resReservation;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }
    
    @Override
    public ArrayList<Article> getArticlesCarte(int codeCarte, TypeArticle typeArticle){
        connexion.open();
        ArrayList<Article> articles= new ArrayList<>();
        String STMT_1 = "select E.NomArticle, A.TypeArticle, E.PrixActuel, A.NomSpecialite "
                + " from Article A, EstElement E "
                + " where A.NomArticle = E.NomArticle"
                + " and E.CodeCarte = ?"
                + " and A.TypeArticle = ?";
        try{
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1,codeCarte);
            stmt.setString(2, typeArticle.toString());
            ResultSet resCarte = stmt.executeQuery();
            while(resCarte.next()){
                if(TypeArticle.valueOf(resCarte.getString(2)).equals(TypeArticle.MENU)){
                    articles.add(new ConcreteMenu(resCarte.getString(1), 
                            TypeArticle.valueOf(resCarte.getString(2)), 
                            resCarte.getFloat(3),resCarte.getString(4)));
                }else{
                    articles.add(new ConcreteArticle(resCarte.getString(1), 
                            TypeArticle.valueOf(resCarte.getString(2)), 
                            resCarte.getFloat(3),resCarte.getString(4)));
                }
            }
            resCarte.close();
            stmt.close();
            connexion.close();
            return articles;
        }
        catch(SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }
    
    @Override // Renvoie les tables libres pour un certain service d'un certain jour
    public ArrayList<Table> tablesLibres(int year, int month, int day, Service service){
        connexion.open();
        try {
            String STMT = "select T.CodeTable, T.NbPlace0, T.NbPlace1, "
                    + "T.NbPlace2, T.Localisation "
                    + "from TableRepas T "
                    + "minus"
                    + "( select T2.CodeTable, T2.NbPlace0, T2.NbPlace1, "
                    + "T2.NbPlace2, T2.Localisation "
                    + "from TableRepas T2, Occupe O, Reservation R "
                    + "where T2.CodeTable = O.CodeTable "
                    + "and O.CodeReservation = R.CodeReservation "
                    + "and R.Jour = ? "
                    + "and R.NomService = ? )";
            java.sql.Date d = new java.sql.Date(year-1900, month-1, day);
            
            //  Creation de la requete
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT);
            stmt.setDate(1, d);
            stmt.setString(2, service.name());
            //  Execution  de la  requete
            ResultSet rsetTable = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <Table> resTable = new ArrayList<> ();
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
            connexion.close();
            return resTable;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }
    
    @Override
    public HashMap<Integer, ArrayList<Integer>> tablesVoisines(){
        connexion.open();
        HashMap<Integer, ArrayList<Integer>> tablesVoisines = new HashMap<>();
        
        String STMT_1 = "select *"
                + " from EstVoisine ";
        try{
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            ResultSet resVoisines = stmt.executeQuery();
            
            while(resVoisines.next()){
                if (tablesVoisines.containsKey(resVoisines.getInt(1))){
                    if (!tablesVoisines.get(resVoisines.getInt(1)).contains(resVoisines.getInt(2)))
                        tablesVoisines.get(resVoisines.getInt(1)).add(resVoisines.getInt(2));
                }
                else{
                    tablesVoisines.put(resVoisines.getInt(1), new ArrayList<Integer>());
                    tablesVoisines.get(resVoisines.getInt(1)).add(resVoisines.getInt(2));
                }
                if (tablesVoisines.containsKey(resVoisines.getInt(2))){
                    if (!tablesVoisines.get(resVoisines.getInt(2)).contains(resVoisines.getInt(1)))
                        tablesVoisines.get(resVoisines.getInt(2)).add(resVoisines.getInt(1));
                }
                else{
                    tablesVoisines.put(resVoisines.getInt(2), new ArrayList<Integer>());
                    tablesVoisines.get(resVoisines.getInt(2)).add(resVoisines.getInt(1));
                }
            }
            resVoisines.close();
            stmt.close();
            connexion.close();
            return tablesVoisines;
        }
        catch(SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }

    @Override
    public ArrayList<Article> getArticlesMenu(TypeArticle typeArticle, String nomMenu) {
        connexion.open();
        PreparedStatement stmt;
        try {
            if (typeArticle.name().equalsIgnoreCase("PLAT")) {
                String STMT_1 = "select A.NomArticle, A.NomSpecialite "
                        + "from Article A, ContientPlat Pl "
                        + "where A.NomArticle = Pl.NomArticlePlat "
                        + "and A.TypeArticle = ? "
                        + "and Pl.NomArticleMenu = ?";
                stmt = connexion.getConnection().prepareStatement(STMT_1);
            } else {    
                String STMT_2 = "select A.NomArticle, A.NomSpecialite "
                        + "from Article A, ContientAutreArticle Au "
                        + "where A.NomArticle = Au.NomArticleAutre "
                        + "and A.TypeArticle = ? "
                        + "and Au.NomArticleMenu = ?";
                stmt = connexion.getConnection().prepareStatement(STMT_2);
            }
            
            stmt.setString(1, typeArticle.toString());
            stmt.setString(2, nomMenu);
            
            //  Execution  de la  requete
            ResultSet rsetArticleMenu = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <Article> resArticleMenu = new ArrayList<> ();
            while (rsetArticleMenu.next()) {
                resArticleMenu.add(new ConcreteArticle(
                        rsetArticleMenu.getString("NomArticle"),
                        typeArticle,
                        0,
                        rsetArticleMenu.getString("NomSpecialite")));
            }
            
            //  Fermeture
            rsetArticleMenu.close();
            stmt.close();
            connexion.close();
            return resArticleMenu;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }
    }

    @Override
    public int clientConnu(String nomClient, String numTel) {
        connexion.open();
                
        String STMT_1 = "select *"
                + " from Client C"
                + " where C.NomClient = ?"
                + " and C.NumTel = ? ";
        try{
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setString(1, nomClient);
            stmt.setString(2, numTel);
            ResultSet rsetClient = stmt.executeQuery();
            
            int code = 0;
            if (rsetClient.next()) {
                code = rsetClient.getInt("CodeClient");
            } else {
                String STMT_2 = "select SeqClient.currVal from Dual";
                ConcreteInsertionFactory insertFactory = new ConcreteInsertionFactory(connexion);
                insertFactory.creerClient(nomClient, numTel);
                stmt = connexion.getConnection().prepareStatement(STMT_2);
                ResultSet rsetCode = stmt.executeQuery();
                rsetCode.next();
                code = rsetCode.getInt(1);
                rsetCode.close();
            }
            rsetClient.close();
            stmt.close();
            connexion.close();
            return code;
        }
        catch(SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return -1;
        }
    }

    @Override
    public ArrayList<TypeArticle> getTypesMenu(String nomMenu) {
        connexion.open();
        String STMT_1 = "select distinct A.TypeArticle "
                        + "from Article A, ContientAutreArticle Au "
                        + "where A.NomArticle = Au.NomArticleAutre "
                        + "and Au.NomArticleMenu = ?";
        
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setString(1, nomMenu);
            
            //  Execution  de la  requete
            ResultSet rsetTypesMenu = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <TypeArticle> resTypesMenu = new ArrayList<> ();
            while (rsetTypesMenu.next()) {
                resTypesMenu.add(TypeArticle.valueOf(rsetTypesMenu.getString("TypeArticle")));
            }
            
            //  Fermeture
            rsetTypesMenu.close();
            stmt.close();
            connexion.close();
            return resTypesMenu;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }    
    }

    @Override
    public ArrayList<Article> getMenu(int codeCarte) {
        connexion.open();
        String STMT_1 = "select A.NomArticle, A.TypeArticle, A.NomSpecialite, El.PrixActuel " +
                "from Article A, EstElement El " +
                "where A.NomArticle = El.NomArticle " + 
                "and A.TypeArticle = 'MENU' " +
                "and El.CodeCarte = ?";
        
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, codeCarte);
            
            //  Execution  de la  requete
            ResultSet rsetMenu = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <Article> resMenu = new ArrayList<> ();
            while (rsetMenu.next()) {
                resMenu.add(new ConcreteMenu(
                        rsetMenu.getString("NomArticle"),
                        TypeArticle.valueOf(rsetMenu.getString("TypeArticle")),
                        rsetMenu.findColumn("PrixActuel"),
                        rsetMenu.getString("NomSpecialite")));
            }
            
            //  Fermeture
            rsetMenu.close();
            stmt.close();
            connexion.close();
            return resMenu;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }       
    }

    @Override
    public int getCodeCarte(int codeReservation) {
connexion.open();
        String STMT_1 = "select S.CodeCarte " +
                "from Reservation R, Service S " +
                "where R.Jour = S.Jour " +
                "and R.NomService = S.NomService " + 
                "and R.CodeReservation = ?";
        
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, codeReservation);
            
            //  Execution  de la  requete
            ResultSet rsetCodeCarte = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            int resCodeCarte = -1;
            if (rsetCodeCarte.next()) {
                resCodeCarte = rsetCodeCarte.getInt("CodeCarte");
            }
            
            //  Fermeture
            rsetCodeCarte.close();
            stmt.close();
            connexion.close();
            return resCodeCarte;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return -1;
        }       
       }
    
    @Override
    public ArrayList<Article> getFacture(int codeReservation) {
        connexion.open();
        String STMT_1 = "select distinct A.NomArticle, A.TypeArticle, P.PrixActuel, A.NomSpecialite, C.Quantite "
                        + "from Reservation R, Article A, EstElement P, Service S, Commande C "
                        + "where R.Jour = S.Jour "
                        + "and R.NomService = S.NomService "
                        + "and S.CodeCarte = P.CodeCarte "
                        + "and R.CodeReservation = ? "
                        + "and A.NomArticle = P.NomArticle "
                        + "and C.CodeReservation = R.CodeReservation "
                        + "and C.NomArticle = A.NomArticle";
                
        float prix = 0;
        try {
            PreparedStatement stmt = connexion.getConnection().prepareStatement(STMT_1);
            stmt.setInt(1, codeReservation);
            
            //  Execution  de la  requete
            ResultSet rsetFacture = stmt.executeQuery ();
            
            //  Conversion  du  resultat  en ArrayList <Table>
            ArrayList <Article> resListArt = new ArrayList<> ();
            while (rsetFacture.next()) {
                Article a;
                a = new ConcreteArticle(rsetFacture.getString(1), TypeArticle.valueOf(rsetFacture.getString(2)), rsetFacture.getFloat(3), rsetFacture.getString(4));
                a.setQuantity(rsetFacture.getInt(5));
                resListArt.add(a);
                prix = prix + rsetFacture.getInt(5)*rsetFacture.getFloat(3);
            }
            
            String STMT_2 = "update Reservation "
                + "set Reservation.Prix = ? "
                + "where Reservation.CodeReservation = ? ";
            PreparedStatement stmt2 = connexion.getConnection().prepareStatement(STMT_2);
            stmt2.setFloat(1, prix);
            stmt2.setInt(2, codeReservation);
            stmt2.executeQuery();
            stmt2.close();
            
            //  Fermeture
            rsetFacture.close();
            stmt.close();
            
            connexion.close();
            return resListArt;
        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace (System.err);
            return null;
        }    
    }
}
