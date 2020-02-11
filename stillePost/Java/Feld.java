/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;
import java.util.*;

/**
 * Die Klasse bildet in einem Objekt Array in der Klasse Welt das Spielfeld.
 * In ihrer ArrayList sind die Menschen, welche sich auf dem Feld befinden gespeichert.
 * @author Nicolas & Martin
 */
public class Feld {
    
    /** Attribute.
     *  menschen --> ArrayList mit auf Feld stehenden Menschen
     *  spezialFeld --> Wert welcher Information ueber spezielle Lage in 
     *  zweidimensionalem Array speichert.
     */
    private ArrayList<Mensch> menschen;
    private String spezialFeld;


    /**
     * 
     * @param spezialFeld Information ueber spezielle Lage
     */
    public Feld(String spezialFeld){
        
        this.menschen = new ArrayList<Mensch>();
        this.spezialFeld = spezialFeld;

    }

    /**
     * Getter fuer die ArrayList
     * @return  menschen ArrayList vom Typ Mensch
     */
    public ArrayList<Mensch> getMenschen() {
        return menschen;
    }


    /**
     * Checkt das Attribut Spezial und gibt einen der moeglichen Wege zufaellig zurueck.
     * Schritte:
     * 1: keiner ; 2: unten; 3: oben; 4: rechts; 5: links
     * links oben   --> 1,2 o. 4
     * rechts oben  --> 1,2 o. 5
     * rechts unten --> 1,3 o. 5
     * links unten  --> 1,3 o. 4
     * links        --> 1,2,3 o. 4
     * rechts       --> 1,2,3 o. 5
     * oben         --> 1,2,4 o. 5
     * unten        --> 1,3,4 o. 5
     * @return  Zufaellig gewaehlter moeglicher Weg
     */
    public int moeglichWege(){
        
          int random = Welt.random(1, 5);
          int[] tmp3 = new int[3];
          int[] tmp4 = new int[4];
        
        switch(this.spezialFeld){
            
            case "links oben":
                tmp3[0] = 1;
                tmp3[1] = 2;
                tmp3[2] = 4;
                random = arrayZufall(tmp3);
                break;
            case "rechts oben":
                tmp3[0] = 1;
                tmp3[1] = 2;
                tmp3[2] = 5;
                random = arrayZufall(tmp3);
                break;

            case "rechts unten":
                tmp3[0] = 1;
                tmp3[1] = 3;
                tmp3[2] = 5;
                random = arrayZufall(tmp3);
                break;
            case "links unten":
                tmp3[0] = 1;
                tmp3[1] = 3;
                tmp3[2] = 4;
                random = arrayZufall(tmp3);
                break;
            case "links":
                tmp4[0] = 1;
                tmp4[1] = 2;
                tmp4[2] = 3;
                tmp4[3] = 4;
                random = arrayZufall(tmp4);
                break;
            case "rechts":
                tmp4[0] = 1;
                tmp4[1] = 2;
                tmp4[2] = 3;
                tmp4[3] = 5;
                random = arrayZufall(tmp4);
                break;
            case "oben":
                tmp4[0] = 1;
                tmp4[1] = 2;
                tmp4[2] = 4;
                tmp4[3] = 5;
                random = arrayZufall(tmp4);
                break;
            case "unten":
                tmp4[0] = 1;
                tmp4[1] = 3;
                tmp4[2] = 4;
                tmp4[3] = 5;
                random = arrayZufall(tmp4);
                break;
            default:
                break;

        }
        return random;
    }
    
    /**
     * Gibt aus einem Array mit ints (moeglichen Wegen) einen zufaelligen Wert zurueck.
     * @param zahlen - Array, welches die moeglichen Wege enthaelt.
     * @return int - ausgeawehlter Weg
     */
    public int arrayZufall(int[] zahlen){

        switch(Welt.random(1,zahlen.length)){
            case 1:
                return zahlen[0];
            case 2:
                return zahlen[1];
            case 3:
                return zahlen[2];
            case 4:
                return zahlen[3];
            case 5:
                return zahlen[4];
            default:
                return 0;
         }
    } 


    
}

