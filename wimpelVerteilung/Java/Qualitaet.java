package oop4;

import java.util.*;

/**
 * Klasse, welche zur Bearbeitung der Qualitaetssteigerung der Aufgabenstellung zustaendig ist.
 * @author Nicolas & Martin
 */
public class Qualitaet {

    private int besteFrequenz;

    public Qualitaet() {

    }

    /**
     * Berechnet die minimal Vorhandene Distanz in einem char Array zwischen gleichen Charactern.
     * In der Funktion wird ueber alle in der Hasmap gespeicherten Farben interiert.
     * Fuer jede Farbe wird der minimalste Abstand zwischen einander berechnet. (minimumDistance)
     * Wenn der Abstand kleiner ist, als der davor in min_dis gespeicherte, wird min_dis mit diesem belegt.
     * So wird der geringste Abstand zwischen gleichen Farben im berechnet.
     * @param str   Char Array
     * @param hm    Hashmap mit gespeicherten Farben
     * @return      Gibt die minimalste Distanz zurueck.
     */
    public int berechneMinDis(char[] str, HashMap<String, Integer> hm) {

        char[] a = str;
        int min_dis = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {

            int min_dis_tmp = minimaleDistanz(str, entry.getKey().toCharArray()[0]);

            if (min_dis_tmp < min_dis) {

                min_dis = min_dis_tmp;
            }

        }

        return min_dis;
    }

    /**
     * Berechnet die minimalste Distanz gleicher Character. Wird fuer jede im char Array vorhandene Farbe
     * aufgerufen.
     * @param array Char Array
     * @param x     aktuell zu betrachtender Character
     * @return      minimalste Distanz zwischen dem Auftreten von x
     */
    public int minimaleDistanz(char[] array, char x) {

        int prev, i = 0;
        prev = 0;
        int dist = Integer.MAX_VALUE;

        for (i = 0; i < array.length; i++) {

            if (array[i] == x) {

                prev = i;
                break;
            }
        }
        for (i = prev + 1; i < array.length; i++) {

            if (array[i] == x) {
                if ((array[i] == (array[prev])) && (i - prev) <= dist) {

                    dist = i - prev - 1;
                    prev = i;

                } else {

                    prev = i;

                }
            }
        }
        return dist;
    }

    /**
     * Findet die beste Frequenz (Hauefigkeiten) des Auftretens der minimalsten Distanz herraus
     * und loescht alle Strings mit schlechteren Frequenzen aus der Eingabe Liste.
     * @param besteDistanzen        List der Strings mit bester Distanz
     * @param bestMoeglicheDistanz  besteDistanz zwischen gleichen Farben
     * @param hm                    Hashmap mit gespeicherten Farben
     * @return                      gefilterte List mit besten Abstand und bester Frequenz
     */
    public List<String> frequenz(List<String> besteDistanzen, int bestMoeglicheDistanz, HashMap<String, Integer> hm) {

        //Erstellung eine Kopie der Eingabe Lsite zur Filterung.
        List<String> gefiltert = new ArrayList<>();
        for(String s: besteDistanzen){

            gefiltert.add(s);
        }

        // Beste Frequenz wird maximal initialisiert.
        int besteF = Integer.MAX_VALUE;

        /**
         * Fuer jeden String in beste Distanzen wird mit Hilfe der berechneFrequenz Methode
         * die aktuelle Frequenz des Auftretens der minimalsten Distanz (bestMoeglicheDistanz)
         * berechnet.
         * Wenn diese aktuelle Frequenz (aktF) kleiner ist als die Beste, wird die Beste auf
         * die Aktuelle gesetzt.
         */
        for (String s : besteDistanzen) {

            int aktF = berechneFrequenz(s.toCharArray(),hm,bestMoeglicheDistanz);
            if(aktF < besteF){
                besteF = aktF;
            }

        }
        /**
         * Fuer jeden String in beste Distanzen wird mit Hilfe der berechne Frequenz Methode
         * die aktuelle Frequenz des Auftretens der minimalsten Distanz (bestMoeglicheDistanz)
         * berechnet. Wenn diese größer ist als die beste Frequenz wir der String aus
         * der gefiltert Liste geloescht.
         */
        for (String s: besteDistanzen) {

            int stringFrequenz = berechneFrequenz(s.toCharArray(),hm,bestMoeglicheDistanz);
            if(stringFrequenz > besteF){
                gefiltert.remove(s);
            }

        }

        besteFrequenz = besteF;
        return gefiltert;
    }

    /**
     * Berechnet die Frequenz des Auftretens der besteDistanz.
     * Fuer jede Farbe wird das Auftreten der bestenMoeglichen Distanz gezaehlt und aufsummiert.
     * Diese Summe wird am ende als Frequenz zurueck gegeben.
     * @param str                   zu betrachtendes Char Array
     * @param hm                    die Hashmap mit den Farben
     * @param bestmoeglicheDistanz  die besteMoeglicheDistanz
     * @return                      Die Frequenz dieser Distanz in dem Char Array
     */
    public int berechneFrequenz(char[] str, HashMap<String, Integer> hm, int bestmoeglicheDistanz) {

        char[] a = str;
        int frequenzBestMoeglicheDistanz = 0;

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {

            int frequenzBestMoeglicheDistanzEntry = berechneFrequenzHelper(str, entry.getKey().toCharArray()[0],bestmoeglicheDistanz);
            frequenzBestMoeglicheDistanz += frequenzBestMoeglicheDistanzEntry;

        }

        return frequenzBestMoeglicheDistanz;
    }

    /**
     * Diue Funktion funktioniert genau wie minimaleDistanz nur, dass wenn die Distanz gleich
     * der bestMoeglichen ist der Counter Frequenz um 1 erhoeht wird.
     * @param array                 zu betrachtendes char array
     * @param x                     aktuell zu betrachtende Farbe
     * @param bestMoeglicheDistanz  bestMoegliche Distanz
     * @return                      Frequenz des Auftertens der Distanz bei char x
     */
    public int berechneFrequenzHelper(char[] array, char x, int bestMoeglicheDistanz) {

        int prev, i, frequenz = 0;
        prev = 0;
        int dist = bestMoeglicheDistanz;


        for (i = 0; i < array.length; i++) {

            if (array[i] == x) {

                prev = i;

                break;

            }

        }

        for (i = prev + 1; i < array.length; i++) {

            if (array[i] == x) {
                //Ist die Distanz zwischen gleichen Characterb gleich der bestMoeglichenDistanz?
                if ((array[i] == (array[prev])) && i - prev - 1 == dist) {

                    frequenz++;
                    prev = i;
                } else {
                    prev = i;
                }

            }

        }

        return frequenz;

    }

    /**
     * Getter fuer die beste Frequenz.
     * @return besteFrequenz
     */
    public int getBesteFrequenz() {
        return besteFrequenz;
    }
}