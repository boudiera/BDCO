package Modele;

import java.util.ArrayList;
import java.util.HashMap;

/** Une liste de reservations */
public abstract class RequeteFactory{
    // Renvoie TOUTES les réservations
    public abstract ArrayList<Reservation> getReservationsList();
    
    // Renvoie les tables libres pour un service donné
    public abstract ArrayList<Table> tablesLibres(int year, int month, int day, Service service, int heure, int minutes);
  
    // Renvoie tous les articles d'une carte en fonction d'un type
    public abstract ArrayList<Article> getArticlesCarte(int codeCarte, TypeArticle typeArticle);
    
    // Permet d'avoir tous les articles d'un menu
    public abstract ArrayList<Article> getArticlesMenu(TypeArticle typeArticle, String nomMenu);
    
    // Permet d'avoir tous les articles d'un menu
    public abstract ArrayList<TypeArticle> getTypesMenu(String nomMenu);
      
    // Fonction qui renvoit les menus d'une carte
    public abstract ArrayList<Article> getMenu(int codeCarte);
        
    // Fonction qui renvoit le codecarte d'une reservation
    public abstract int getCodeCarte(int codeReservation);    
            
    // Return codeClient si le client est deja présent dans la base de donnée, sinon le crée
    public abstract int clientConnu(String nomClient, String numTel);

    // Renvoie la liste des tables voisines
    public abstract HashMap<Integer, ArrayList<Integer>> tablesVoisines();
    
    // Renvoie une liste d'articles d'une reservation pour avoir la facture
    public abstract ArrayList<Article> getFacture(int codeReservation);
    
   
    
}