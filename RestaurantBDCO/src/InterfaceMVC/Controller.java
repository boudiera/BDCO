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
import InterfaceMVC.Permutation.TypedSepaPnkIterator;
import Modele.Reservation;
import Modele.Service;
import Modele.SingletonListCommande;
import Modele.Table;
import Modele.TypeArticle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static Modele.TypeArticle.MENU;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        Iterator<Integer[]> i = null;
        HashMap<Integer, ArrayList<Integer>> tablesVoisines = Factory.singletonFactory().getRequeteFactory().tablesVoisines();
        Integer[] tabNum= new Integer[liste.size()];
        for(int n=0; n<liste.size(); n++){
            tabNum[n] = liste.get(n).getCodeTable();
            System.out.println(
                    liste.get(n).getCodeTable() + " => " + liste.get(n).getNbPlace0() + " , " + liste.get(n).getNbPlace1() + " , " +
                            liste.get(n).getNbPlace2());
        }
        for(int j=1; j<liste.size(); j++){
            i = new TypedSepaPnkIterator<>(tabNum, j);
                while(i.hasNext()){
                    Integer[] tab=i.next();
                    if(verifycombi(tab, tablesVoisines)){
                        HashMap<Integer, Table> hash = new HashMap<>();
                        for(Table t : liste){
                            hash.put(t.getCodeTable(), t);
                        }
                        if (calculNbPlaces(tab, hash) >= nbPlaces){
                            ArrayList<Table> res = new ArrayList<>();
                            for (Integer tab1 : tab) {
                                res.add(hash.get(tab1));
                            }
                            return res;
                        }
                    }
                }    
        }
        return null;
    }
    
    private int calculNbPlaces(Integer[] tab, HashMap<Integer, Table> hash ){
        int nbPlaces=0;
        if (tab.length==1)
            return hash.get(tab[0]).getNbPlace0();
        nbPlaces += hash.get(tab[0]).getNbPlace1();
        for(int i=1; i<tab.length-1; i++){
            nbPlaces += hash.get(tab[i]).getNbPlace2();
        }
        nbPlaces += hash.get(tab[tab.length-1]).getNbPlace1();
        return nbPlaces;
    }
    
    private boolean verifycombi(Integer[] tab, HashMap<Integer, ArrayList<Integer>> hash){
        if (tab.length==1)
            return true;
        if (!hash.get(tab[0]).contains(tab[1]))
            return false;
        for(int i=1; i<tab.length-1; i++){
            if(!hash.get(tab[i]).contains(tab[i-1]) && 
                    !hash.get(tab[i]).contains(tab[i+1]))
                return false;
        }
        if ((!hash.get(tab[tab.length-1]).contains(tab[tab.length-2])))
            return false;
        return true;
    }
    
    // Fonction qui renvoit une hashmap indexe sur le nom des localisation, pour chaque localisation -> on obtient une lise de tables pouvants être occupées en tenant compte du nombre de personne
    public HashMap<String,ArrayList<Table>> getTablesLibresByLocalisation (String annee, String mois, String jour, String service,String nbPersonnes) throws ReservationException{
        
                // On récupère toutes les tables libres
                ArrayList<Table> tablesLibres = getTablesLibres(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour), Service.valueOf(service));
               
                // On les tries par localisation
                HashMap<String, ArrayList<Table> > tableZones = new HashMap<>();
                for(Table t : tablesLibres){
                        System.out.println(t.getCodeTable() + " " + t.getLocation());
                        if (tableZones.containsKey(t.getLocation()))
                         tableZones.get(t.getLocation()).add(t);
                        else {
                            ArrayList<Table> listTablesZone = new ArrayList<>();
                            listTablesZone.add(t);
                            tableZones.put(t.getLocation(),listTablesZone);
                        }     
                }
                
                Set<String> s = tableZones.keySet();
                Iterator<String> iterator = s.iterator();
                
                HashMap<String,ArrayList<Table>> listTablesOccupeesParLocalisation = new HashMap<>();
                ArrayList<Table> resultCombinaison;
                // On itère sur la localisation en cherchant une combinaison de tables libres
                while(iterator.hasNext()){
                    String nomLocalisation = iterator.next();
                    resultCombinaison = findCombinaison(tableZones.get(nomLocalisation),Integer.parseInt(nbPersonnes));
                    // Si il existe une combinaison pour une localisation on l'ajoute dans la hashmap
                    if (resultCombinaison != null)
                             listTablesOccupeesParLocalisation.put(nomLocalisation,resultCombinaison);
                }
                
                return listTablesOccupeesParLocalisation;
        
    }
    
    // Fonction qui permet d'obetnir le codeClient d'un client 
    public int getClient(String nomClient, String numTel){
        return Factory.singletonFactory().getRequeteFactory().clientConnu(nomClient, numTel);
    }
    
    public void addCommande(Commande commande){
        
        // Ajout dans la mémoire de l'application
        SingletonListCommande.singletonListCommande().addCommand(commande.getCodeReservation(),commande);
    }
    
    // Ajoute un article dans une commande
    public void addArticleCommande(Article a, Commande c){
        c.addArticle(a);
    }
    
    public void removeArticleCommande(Article a, Commande c){
        c.removeArticle(a);
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
    
    public HashMap<String, Article> getArticlesMenuByName(TypeArticle typeArticle, String nomMenu){
        HashMap<String, Article> list = new HashMap<>();
        for(Article a : Factory.singletonFactory().getRequeteFactory().getArticlesMenu(typeArticle, nomMenu)){
            list.put(a.getName(), a);
        }
        return list;
    }
    
    public ArrayList<Article> getArticlesMenu(TypeArticle typeArticle, String nomMenu){
        ArrayList<Article> listArticles=new ArrayList<>();
        listArticles=Factory.singletonFactory().getRequeteFactory().getArticlesMenu(typeArticle, nomMenu);
        return listArticles;
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
    
    
    public void creerReservation (ArrayList<Table> tablesOcc, int nbPersonnes, int heure, int minutes,String nomClient, String tel, Date jour, Service nomService) {
        
        // recuperation du codeClient 
        int codeClient = Factory.singletonFactory().getRequeteFactory().clientConnu(nomClient, tel);
        
        // Ajout de la reservation
        Factory.singletonFactory().getInsertionFactory().creerReservation(tablesOcc, nbPersonnes, heure, minutes, codeClient, jour, nomService);   
        
        
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
