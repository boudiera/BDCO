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
import InterfaceMVC.Permutation.SepaPnkIterator;
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
import java.util.Iterator;

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
    
    //Appel dans FrameCreation bouton trouver
    public ArrayList<Table> findCombinaison(ArrayList<Table> liste, int nbPlaces){
        Iterator<int[]> i = null;
        HashMap<Integer, ArrayList<Integer>> hash = Factory.singletonFactory().getRequeteFactory().tablesVoisines();
        for(int j=1; j<liste.size(); j++){
            i = new SepaPnkIterator(liste.size(), j);
                while(i.hasNext()){
                    int[] tab=i.next();
                    if(verifycombi(tab, hash)){
                        if (calculNbPlaces(tab, liste) > nbPlaces){
                            ArrayList<Table> res = new ArrayList<>();
                            for(int k=0; k<tab.length; k++){
                                res.add(liste.get(tab[k]));
                            }
                            return res;
                        }
                    }
                }    
        }
        return null;
    }
    
    private int calculNbPlaces(int[] tab, ArrayList<Table> liste){
        int nbPlaces=0;
        if (tab.length==1)
            return liste.get(tab[0]).getNbPlace0();
        nbPlaces += liste.get(tab[0]).getNbPlace1();
        for(int i=1; i<tab.length-1; i++){
            nbPlaces += liste.get(tab[i]).getNbPlace2();
        }
        nbPlaces += liste.get(tab[tab.length-1]).getNbPlace1();
        return nbPlaces;
    }
    
    private boolean verifycombi(int[] tab, HashMap<Integer, ArrayList<Integer>> hash){
        if (tab.length==1)
            return true;
        if (!hash.get(tab[0]+1).contains(tab[1]+1))
            return false;
        for(int i=1; i<tab.length-1; i++){
            if(!hash.get(tab[i]+1).contains(tab[i-1]+1) && 
                    !hash.get(tab[i]+1).contains(tab[i+1]+1))
                return false;
        }
        if ((!hash.get(tab[tab.length-1]+1).contains(tab[tab.length-2]+1)))
            return false;
        return true;
    }
    
    public void addCommande(Commande commande){
        
        // Ajout dans la mémoire de l'application
        SingletonListCommande.singletonListCommande().addCommand(commande.getCodeReservation(),commande);
    }
    
    // Ajoute un article dans une commande
    public void addArticleCommande(Article a, Commande c){
        c.addArticle(a);
    }

    // Ajoute un article dans un menu
    public void addArticleMenu(Article a, Menu m){
        m.ajoutArticle(a);
    }
    // Permet de verifier si un menu est valide ( ici qu'il contient au moin un plat et au moins un autre type d'article)
    public boolean verifyMenu(Menu m){
        return m.contientPlat()&& (m.contientAutreQuePlat());
    }
    
    public ArrayList<Article> getArticles (int codeCarte, TypeArticle typeArticle){
        return Factory.singletonFactory().getRequeteFactory().getArticlesCarte(codeCarte, typeArticle);
    }
    
    public HashMap<String, Article> getArticlesByName (int codeCarte, TypeArticle typeArticle){
        HashMap<String, Article> list = new HashMap<>();
        for(Article a : Factory.singletonFactory().getRequeteFactory().getArticlesCarte(codeCarte, typeArticle)){
            list.put(a.getName(), a);
        }
        return list;
    }
    
    public HashMap<String, Article> getMenuArticles(TypeArticle typeArticle, String nomMenu){
        HashMap<String, Article> list = new HashMap<>();
        for(Article a : Factory.singletonFactory().getRequeteFactory().getArticlesMenu(typeArticle, nomMenu)){
            list.put(a.getName(), a);
        }
        return list;
    }
    
    public ArrayList<TypeArticle> getTypeArticleMenu(String nomMenu){
        ArrayList<TypeArticle> list=new ArrayList<>();
        list=Factory.singletonFactory().getRequeteFactory().getTypesMenu(nomMenu);
        return list;
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
    
    public boolean containsAtLeastOneInteger(String chaine) {
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
        if( containsAtLeastOneInteger(nomClient)){
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
