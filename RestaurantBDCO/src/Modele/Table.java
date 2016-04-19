/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author trentini
 */
public class Table implements Comparable<Table>{
    private final int codeTable;
    private final String location;

    private int NbPlace0;
    private int NbPlace1;
    private int NbPlace2;

    public Table(int codeTable, String location, int NbPlace0, int NbPlace1, int NbPlace2) {
        this.codeTable = codeTable;
        this.location = location;
        
        this.NbPlace0 = NbPlace0;
        this.NbPlace1 = NbPlace1;
        this.NbPlace2 = NbPlace2;
    }

    public int getCodeTable() {
        return codeTable;
    }

    public String getLocation() {
        return location;
    }

    public int getNbPlace0() {
        return NbPlace0;
    }

    public void setNbPlace0(int NbPlace0) {
        this.NbPlace0 = NbPlace0;
    }

    public int getNbPlace1() {
        return NbPlace1;
    }

    public void setNbPlace1(int NbPlace1) {
        this.NbPlace1 = NbPlace1;
    }

    public int getNbPlace2() {
        return NbPlace2;
    }

    public void setNbPlace2(int NbPlace2) {
        this.NbPlace2 = NbPlace2;
    }

    @Override
    public int compareTo(Table t) {
        if (this.codeTable>t.getCodeTable())
            return 1;
        if (this.codeTable<t.getCodeTable())
            return -1;
        return 0;       
    }
    
}
