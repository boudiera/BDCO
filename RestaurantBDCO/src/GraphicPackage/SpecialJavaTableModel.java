/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicPackage;

import Modele.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * <p>
 * A TableModel designed to stock the following
 * objects: Reservation, Commande and Article
 * </p>
 * <p>
 * -- Use with others objets is undefined
 * </p>
 * 
 * @author Iago Felipe Trentin
 */
public class SpecialJavaTableModel extends AbstractTableModel {
    private LinkedHashMap<String, Object> objects;
    private Class type;

    public SpecialJavaTableModel(LinkedHashMap<String, Object> objects, Class type) {
        this.type = type;
        
        if(     type.equals(Reservation.class) ||       // TableModel designed to receive Reservations, Commandes and Articles
                type.equals(Commande.class)    ||
                type.equals(Article.class)     ){
            this.objects = new LinkedHashMap<>(objects);
        }else{
            try {
                throw new Exception("Not a valid type of objects");
            } catch (Exception ex) {
                Logger.getLogger(SpecialJavaTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;   //all cells false = not editable
    }
    
    @Override
    public int getRowCount() {
        return objects.size();
    }

    @Override
    public int getColumnCount() {
        if(type.equals(Reservation.class)){
            return 6;   // Code, Date, Nom, Téléphone, No Personnes, Table(s)
        }else if(type.equals(Commande.class)){
            return 2;   // Identifier, Sub-Total
        }else if(type.equals(Article.class)){
            return 5;   // Nom, Type, Prix, Spécialité, Qte
        }else{
            return 0;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(type.equals(Reservation.class)){
            switch (columnIndex) {
                case 0: return "Code";
                case 1: return "Date";
                case 2: return "Nom";
                case 3: return "Téléphone";
                case 4: return "No Personnes";
                case 5: return "Table(s)";
            }
        }else if(type.equals(Commande.class)){
            switch (columnIndex) {
                case 0: return "Identificateur";
                case 1: return "Sub-Total";
            }
        }else if(type.equals(Article.class)){
            switch (columnIndex) {
                case 0: return "Nom";
                case 1: return "Type";
                case 2: return "Prix";
                case 3: return "Spécialité";
                case 4: return "Qte";
            }
        }
        
        return "-error-";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Object obj = (objects.values().toArray())[rowIndex];

        if(type.equals(Reservation.class)){
            switch (columnIndex) {
                case 0:
                    String code = Integer.toString(((Reservation) obj).getCodeReservation());
                    String parse = ".....";
                    value = parse.substring(code.length()) + code;
                    break;
                case 1:
                    value = ((Reservation) obj).getDate().writeDateSortable();
                    break;
                case 2:
                    value = ((Reservation) obj).getClientName();
                    break;
                case 3:
                    value = ((Reservation) obj).getPhone();
                    break;
                case 4:
                    value = ((Reservation) obj).getNbPersonnes();
                    break;
                case 5:
                    value = ((Reservation) obj).getListCodeTables();
                    break;
            }
        }else if(type.equals(Commande.class)){
            switch (columnIndex) {
                case 0:
                    value = ((Commande) obj).getIdentifier();
                    break;
                case 1:
                    value = ((Commande) obj).getPrice();
                    break;
            }
        }else if(type.equals(Article.class)){
            switch (columnIndex) {
                case 0:
                    value = ((Article) obj).getName();
                    break;
                case 1:
                    value = ((Article) obj).getType();
                    break;
                case 2:
                    value = ((Article) obj).getPrice();
                    break;
                case 3:
                    value = ((Article) obj).getSpeciality();
                    break;
                case 4:
                    value = ((Article) obj).getQuantity();
                    break;
            }
        }

        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(type.equals(Reservation.class)){
            switch (columnIndex) {
                case 0: return int.class;
                case 1: return String.class;
                case 2: return String.class;
                case 3: return int.class;
                case 4: return int.class;
                case 5: return String.class;
            }
        }else if(type.equals(Commande.class)){
            switch (columnIndex) {
                case 0: return String.class;
                case 1: return float.class;
            }
        }else if(type.equals(Article.class)){
            switch (columnIndex) {
                case 0: return String.class;
                case 1: return TypeArticle.class;
                case 2: return float.class;
                case 3: return String.class;
                case 4: return int.class;
            }
        }
        
        return null;
    }


    // It returns the object stored in the row
    public Object getObjectAt(int row) {
        return (objects.values().toArray())[row];
    }
    
    // It returns the object by its key
    public Object getObjectByKey(String key) {
        return objects.get(key);
    }
    
    // It returns all the objects stored in the table
    public LinkedHashMap<String, Object> getAll(){
        return this.objects;
    }
}
