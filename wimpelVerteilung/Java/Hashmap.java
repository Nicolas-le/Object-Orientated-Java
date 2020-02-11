package oop4;

import java.util.HashMap;

/**
 * Die Klasse Hashmap verwaltet als Datenstruktur die Hashmap in der die Farben und ihre
 * Hauefigkeit abgespeichert werden.
 */
public class Hashmap {

    private HashMap<String, Integer> hm;            //key: farbe als String, value: Anzahl der Farbe
    private int farbAnzahl;

    public Hashmap() {

        this.hm = examples();
    }

    /**
     * Regelt die Auswahl der Belegung. (Manuell oder preset)
     * @return liefert Beispiele oder Custom zurueck
     */
    public HashMap<String,Integer> examples(){

        String decision = Eingabe.eingabeString("Wollen Sie eine vorgefertigte Eingabe? Y/N");
        HashMap<String, Integer> hmTMP = new HashMap<String, Integer>();

        //Soll eine vorangelegte Eingabe bentutzt werden?
        if(decision.toLowerCase().equals("y")){
            System.out.println("Die Voreinstellungen sind: A:(rot: 3, blau: 2, weiss: 2) oder \nB:(rot: 4, blau: 3, gelb: 2, weiss: 4, schwarz: 2)");
            String decision2 = Eingabe.eingabeString("Welche Voreinstellung wollen Sie? A/B");

            if(decision2.toLowerCase().equals("a")){

                hmTMP.put("r",3); hmTMP.put("b",2); hmTMP.put("w",2);
                farbAnzahl = 3;

            }else if (decision2.toLowerCase().equals("b")){

                hmTMP.put("r",4); hmTMP.put("b",3); hmTMP.put("w",4); hmTMP.put("g",2); hmTMP.put("s",2);
                farbAnzahl = 5;
            }else{

                System.out.println("Entscheiden Sie sich korrekt");
                examples();
            }

        //Wenn nein wird die customHashMap() Funktion fuer eine manuelle Eingabe genutzt.
        }else if(decision.toLowerCase().equals("n")){

            hmTMP = customHashMap();

        }else{

            System.out.println("Entscheiden Sie sich korrekt");
            examples();

        }
        return hmTMP;

    }

    /**
     * Regelt die manuelle Eingabe von Farben und deren Haeufigkeit.
     * @return Die manuell erstellt hashmap
     */
    public HashMap<String, Integer> customHashMap() {

        farbAnzahl = Eingabe.eingabeInt("Wie viele Farben wollen Sie benutzen?");

        HashMap<String, Integer> hmTMP = new HashMap<String, Integer>();

        for (int i = 0; i < farbAnzahl; i++) {
            String farbe = Eingabe.eingabeString("Geben Sie die Farbe ein!");
            int anzahl = Eingabe.eingabeInt("Geben Sie die Haeufigkeit der Farbe '" + farbe + "' ein.");
            hmTMP.put(farbe, anzahl);

        }

        return hmTMP;

    }

    /**
     * Getter fuer die HashMap
     * @return erstellte Hashmap
     */
    public HashMap<String, Integer> getHm() {
        return hm;
    }

}
