/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceMVC;

import InterfaceMVC.Exceptions.*;
import Modele.Article;
import Modele.Commande;
import Modele.Factory;
import Modele.Menu;
import Modele.Reservation;
import Modele.Service;
import Modele.SingletonListCommande;
import Modele.Table;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static Modele.TypeArticle.MENU;
import java.util.HashMap;

/**
 *
 * @author Iago Felipe Trentin
 */
public class Controller {
    
    private AbstractView view;
    private EnumView viewType;
    
    public AbstractView getView(){
        return this.view;
    }
    
    public EnumView getViewType() {
        return viewType;
    }
    
    public void setView(EnumView vt){
        this.viewType = vt;
        this.view.showView(true);
    }
    
    public void setView(AbstractView v){
        this.view = v;
        this.view.showView(true);
    }
    
    public ArrayList<Reservation> getReservationList(){
        return Factory.singletonFactory().getRequeteFactory().getReservationsList();
    }
    
    public void addCommande(Commande commande){
        
        // Ajout dans la mémoire de l'application
        SingletonListCommande.singletonListCommande().addCommand(commande.getCodeReservation(),commande);
    }
    
    // Ajoute un article dans une commande
    public void addArticleCommande( Article a, Commande c){
        c.ajoutArticle(a);
    }

    // Ajoute un article dans un menu
    public void addArticleMenu( Article a, Menu m){
        m.ajoutArticle(a);
    }
    // Permet de verifier si un menu est valide ( ici qu'il contient au moin un plat et au moins un autre type d'article)
    public boolean menuValid(Menu m){
        return m.contientPlat()&& (m.contientAutreQuePlat());
    }
    
    public ArrayList<Article> getArticles (int codeCarte, TypeArticle typeArticle){
        return Factory.singletonFactory().getRequeteFactory().getArticlesCarte(codeCarte, typeArticle);
    }
    
    public HashMap<String, Article> getArticlesByName (int codeCarte, TypeArticle typeArticle){
        return Factory.singletonFactory().getRequeteFactory().getArticlesCarteByName(codeCarte, typeArticle);
    }
    
    
    // Permet d'obtenir toutes les commandes d'une reservation
    public ArrayList<Commande> getCommande (int codeReservation){
        return SingletonListCommande.singletonListCommande().getListCommandByReservationCode(codeReservation);
    }
    
    
    // Permet de supprimer une commande d'une reservation
    public void deleteCommande( int codeReservation,Commande commande){
        SingletonListCommande.singletonListCommande().removeCommand(codeReservation,commande.getIdentifier());
        // Actualisation de la vue
        this.setView(this.getView());
    }
    
    public boolean ContientAuMoinUnEntier(String chaine) {
                int index =0;
                while(index < chaine.length()) {
                    try { 
                        Integer.parseInt(chaine.substring(index,index+1));
                             return true;
                        }    
                    catch (NumberFormatException e){
                            index++;
                    }
                }    
		return false;
	}
    
    // Recupère les tables libres, renvoit une exception si il n'y en pas pas
    public ArrayList<Table> getTablesLibres (int year, int month, int day, Service service) throws RestaurantCompletException{
        
        ArrayList<Table> tablesLibres = Factory.singletonFactory().getRequeteFactory().tablesLibres(year, month, day, service);
        if (tablesLibres == null)
            throw new RestaurantCompletException();
        return tablesLibres;
    }
    
    
    
    // Verification du bon forma d'entré des données
    public void verifyAddReservation(String year, String month, String day, String hour, String minute, String nbPeople, String phone, String service, String nomClient) throws ReservationException {
       
        int yearIn;
        int monthIn;
        int dayIn;
        int hourIn;
        int minuteIn;
        int nbPeopleIn;
        Service serv;
        // Verifications du parseur pour la date
        try {
            yearIn=Integer.parseInt(year);
            monthIn=Integer.parseInt(month);
            dayIn=Integer.parseInt(day);
        }    
                
        catch (Exception e){
                throw new ParseDateException();
        } 
        
        
         //gestion date antérieure à celle du jour
        if (Calendar.getInstance().getTime().getYear() > (yearIn-1900))
            throw new WrongDateException();
        else if((Calendar.getInstance().getTime().getYear()== (yearIn-1900)) && (Calendar.getInstance().getTime().getMonth()+1>monthIn))
            throw new WrongDateException();
        else if ((Calendar.getInstance().getTime().getYear()== (yearIn-1900)) && (Calendar.getInstance().getTime().getMonth()+1==monthIn) && (Calendar.getInstance().getTime().getDate()>dayIn))
            throw new WrongDateException();
        
             //gestion format date 
        //pour les mois:
        if (monthIn>12)
            throw new MonthException();
        if (dayIn>31)
            throw  new JourException();
        if (monthIn==2){ // gestion du mois de février
            if (yearIn%4==0){ //annee bissextile
                if(dayIn>29)
                    throw new JourException();
            }
            else if(dayIn>28)
                throw new JourException();
        }
        if( (monthIn== 4 || monthIn== 6 || monthIn==9 || monthIn==11) && ( dayIn>30)){
            throw new JourException();
        }
        
        
        
          // Verifications du parseur pour les heures
        try{
            hourIn=Integer.parseInt(hour);
            minuteIn=Integer.parseInt(minute);
        }
        catch (Exception e){
            throw new ParseHeureException();
        }
         //gestion d'heure:
        
        if(hourIn<0 || hourIn>24)
            throw new HeureException();
        
        //gestion minutes
        if(minuteIn<0 || minuteIn>59)
            throw new MinuteException();
        
        
        
        // Verifications du parseur pour le nombre de personne
        try{
            nbPeopleIn= Integer.parseInt(nbPeople);
        }
        catch (Exception e){
            throw new ParseNbPersonneException();
        }
        
           //gestion nombre client négatif ou nul
        if (nbPeopleIn<=0)
            throw new NbPersonneException();
        
         // Verifications du parseur pour le service
        try {
            serv = Service.valueOf(service);
        }
         catch (Exception e){
                throw new ParseServiceException();
        }  
      
        
        // Verification du bon format pour le client
        if( ContientAuMoinUnEntier(nomClient)){
            throw new ParseNomClientException();
        }
        
        try {
            Integer.parseInt(phone);
        }
        catch (Exception e){
            throw new ParseTelephoneException();
        }
        //gestion téléphone
        if(phone.length()!=10)
            throw new TelephoneException();  
    }
}
