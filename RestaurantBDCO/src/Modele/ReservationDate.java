/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Date;

/**
 *
 * @author Iago Felipe Trentin
 */
public class ReservationDate extends Date{
    
    public ReservationDate(int year, int month, int day){
        this.setYear(year);
        this.setMonth(month);
        this.setDate(day);
    }
    
    public ReservationDate(int year, int month, int day, int hour, int min){
        this.setYear(year);
        this.setMonth(month);
        this.setDate(day);
        
        this.setHours(hour);
        this.setMinutes(min);
    }
    
    public String writeDateSortable(){
        String s = "";
        
        s += this.getYear();
        s += " / ";
        s += this.writeMonth();
        s += " / ";
        s += this.writeDay();
        
        s += " : ";
        
        s += this.writeHour();
        s += ":";
        s += this.writeMin();
        
        return s;
    }
    
    public String writeDateComplete(){
        String s = "";
        
        s += this.writeDayMonthName();
        s += " / ";
        s += this.getYear();
        s += ", ";
        s += this.writeHourMin();
        
        return s;
    }
    
    public String writeDayMonthNameHourMin(){
        String s = "";
        
        s += this.writeDayMonthName();
        s += ", ";
        s+= this.writeHourMin();
        
        return s;
    }
    
    public String writeHourMin(){
        String s = "";
        
        s += this.writeHour();
        s += " : ";
        s += this.writeMin();
        
        return s;
    }
    
    public String writeMin(){
        String s = "";
        
        if(this.getMinutes()>= 10){
            s = Integer.toString(this.getMinutes());
        }else{
            s = "0" + Integer.toString(this.getMinutes());
        }

        return s;
    }

    public String writeHour(){
        String s = "";
        
        if(this.getHours() >= 10){
            s = Integer.toString(this.getHours());
        }else{
            s = "0" + Integer.toString(this.getHours());
        }

        return s;
    }
    
    public String writeDayMonthName(){
        String s = "";
        
        s += this.writeDay();
        s += " / ";
        s += this.writeMonthName();
        
        return s;
    }
    
    public String writeDayMonth(){
        String s = "";
        
        s += this.writeDay();
        s += " / ";
        s += this.writeMonth();
        
        return s;
    }
    
    public String writeDay(){
        String s = "";
        
        if(this.getDate() >= 10){
            s = Integer.toString(this.getDate());
        }else{
            s = "0" + Integer.toString(this.getDate());
        }

        return s;
    }
    
    public String writeMonth(){
        String s = "";
        
        if((this.getMonth()+1) >= 10){
            s = Integer.toString(this.getMonth()+1);
        }else{
            s = "0" + Integer.toString(this.getMonth()+1);
        }
        
        return s;
    }
    
    public String writeMonthName(){
        String s = "";
        
        switch (this.getMonth()){
            case 0 : s = "Jan"; break;
            case 1 : s = "Fev"; break;
            case 2 : s = "Mar"; break;
            case 3 : s = "Avr"; break;
            case 4 : s = "Mai"; break;
            case 5 : s = "Jun"; break;
            case 6 : s = "Jul"; break;
            case 7 : s = "Aou"; break;
            case 8 : s = "Sep"; break;
            case 9 : s = "Oct"; break;
            case 10: s = "Nov"; break;
            case 11: s = "Dec"; break;
            default: s = "Unknown"; break;
        }
        
        return s;
    }
    
    @Override
    public String toString(){
        return this.writeDateSortable();
    }
}
