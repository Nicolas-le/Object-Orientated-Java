/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;

/**
 * Die Klasse Mensch bestimmt die Versuchsteilnehmer.
 * @author Nicolas & Martin
 */
public class Mensch {
    
    
    private boolean heirat;
    private boolean dementi;
    private long id;
    
    /**
     * Konstruktor welcher, einen intitialen Wert zur Meinung uebergeben bekommt und eine eindeutige ID.
     * @param heirat    Von Heirat ueberzeugt?
     * @param dementi   Vom Gegenteil ueberzeugt?
     * @param id        eindeutige incrementiert erstellte id
     */
    public Mensch(boolean heirat, boolean dementi,long id){
        
        this.heirat = heirat;
        this.dementi = dementi;
        this.id = id;
    }

    /**
     * Getter zu Heirat
     * @return  heirat ja oder nein?
     */
    public boolean isHeirat() {
        return heirat;
    }

    /**
     * Getter zum Dementi
     * @return  dementi ja oder nein?
     */
    public boolean isDementi() {
        return dementi;
    }

    /**
     * Setter zur Heirat.
     * @param heirat
     */
    public void setHeirat(boolean heirat) {
        this.heirat = heirat;
    }

    /**
     * Setter zum Dementi.
     * @param dementi
     */
    public void setDementi(boolean dementi) {
        this.dementi = dementi;
    }

    /**
     * Getter fuer die ID.
     * Genutzt fuer abgleich ob schon bewegt.
     * @return id
     */
    public long getId() {
        return id;
    }
    
      
    
}

