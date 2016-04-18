package Modele;

import java.util.ArrayList;
import java.util.Observable;

/** Une liste de reservations */
public abstract class RequeteFactory{
    // Renvoie TOUTES les réservations
    public abstract ArrayList<Reservation> getReservationsList();
    
    // Renvoie les tables libres pour un service donné
    public abstract ArrayList<Table> tablesLibres(int year, int month, int day, Service service);
  
    // Renvoie tous les articles d'une carte en fonction d'un type
    public abstract ArrayList<Article> getArticlesCarte(int codeCarte, TypeArticle typeArticle);
    
    // Permet d'avoir tous les articles, d'un menu pour une carte donnée
    public abstract ArrayList<Article> getArticlesCarteMenu(int codeCarte, TypeArticle typeArticle,Menu menu);
     
    // Return vrai si le client est deja présent dans la base de donnée
    public abstract boolean clientConnu(String nomClient, String numTel);

    // Renvoie la liste des tables voisines
    public abstract ArrayList<Integer[]> tablesVoisines();
}