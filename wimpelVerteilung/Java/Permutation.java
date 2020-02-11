package oop4;

import java.util.*;

/**
 * Die Klasse Permutation fuehrt die gesamten Permutationen durch.
 * besteDistanzen: Liste von Strings, welche die bestMoeglicheDistanz enthalten.
 * bestmoeglicheDistanz: wird durch die Funktion setBesteDistanz errechnet.
 * Die Klassen Qualiatet und Hashmap werden aggregiert.
 * @author Martin & Nicolas
 */
public class Permutation {

    private List<String> besteDisanzen;
    private int bestMoeglicheDistanz;
    private Qualitaet q;
    private Hashmap hm;

    /**
     * Der Konstruktor der Permutation. Die Klassen Qualiatet und Hashmap werden uebergeben.
     * @param q     Eine Instanz der Klasse Qualitaet.
     * @param hm    Eine Instanz der Klasse Hashmap.
     */
    public Permutation(Qualitaet q, Hashmap hm){

        this.besteDisanzen = new ArrayList<String>();
        this.q = q;
        this.hm = hm;

    }

    /**
     * Berechnet rekursiv alle Permutationen ohne Duplikate (distinct) und speichert, wenn eine groeßere Distanz
     * gefunden wird, diese als int in dem Attribut bestMoeglicheDistanz.
     * Der in der aktuell betrachteten Permutation, minimal vorhandene, Abstand gleicher Farben wird ueber eine Funktion
     * der Klasse Qualitaet berechnet. (calculateMinDis)
     * @param str   char Array, welches aus dem String generiert wurde, welcher zu permutieren ist.
     * @param index Index startet bei 0 und laeuft rekursiv ueber das charArray.
     * @param n     ist die Laenge des zu sortierenden Strings
     */
    public void setBesteDistanz(char[] str, int index, int n) {
        if (index >= n) {
            int bestMoeglicherAbstand = q.berechneMinDis(str,hm.getHm());

            if(bestMoeglicherAbstand > bestMoeglicheDistanz) {
                bestMoeglicheDistanz = bestMoeglicherAbstand;
            }

            return;
        }

        for (int i = index; i < n; i++) {

            /** Stelle, welche die Permutation distinkt werden laesst.
             * Es wird mit str[i] nur weitergearbeiten, wenn er mit keinem char
             * nach str[index] uebereinstimmt
             * */
            boolean check = shouldSwap(str, index, i);
            if (check) {
                swap(str, index, i);
                setBesteDistanz(str, index + 1, n);
                swap(str, index, i);
            }
        }
    }

    /**
     * Berechnet rekursiv alle distinkten Permutationen.
     * Permutationen welche als minimalen Abstand zwischen gleichen Farben
     * den Wert von bestMoeglicheDistanz besitzen, werden der List<String> besteDistanzen
     * hinzugefuegt.
     * @param str   char Array, welches aus dem String generiert wurde, welcher zu permutieren ist.
     * @param index Index startet bei 0 und laeuft rekursiv ueber das charArray.
     * @param n     ist die Laenge des zu sortierenden Strings
     */
    public void findePermutationen(char[] str, int index, int n) {
        if (index >= n) {
            //System.out.println(str);
            int min_dis_str = q.berechneMinDis(str,hm.getHm());

            if(min_dis_str == bestMoeglicheDistanz) {
                String string = new String(str);
                besteDisanzen.add(string);
            }

            return;
        }

        for (int i = index; i < n; i++) {

            /** Stelle, welche die Permutation distinkt werden laesst.
             * Es wird mit str[i] nur weitergearbeiten, wenn er mit keinem char
             * nach str[index] uebereinstimmt
             * */
            boolean check = shouldSwap(str, index, i);
            if (check) {
                swap(str, index, i);
                findePermutationen(str, index + 1, n);
                swap(str, index, i);
            }
        }
    }

    /**
     * Helper fuer die Erstellung von Permutationen. Entscheidet ob Chars getauscht werden sollen.
     * Checkt ob str(curr) mit Character nach dem Index Start uebereinstimmt.
     * Geht iterativ ueber das Char Array in dem Intervall zwischen start und curr.
     * Wenn der Character an der Stelle i (von start incrementierend) gleich dem an Stelle
     * curr ist, wird false zurueckgegeben. So werden unnoetige Tauschoperationen
     * verhindert, da keine gleichen Farben getauscht werden.
     * @param str   char Array, welches aus dem String generiert wurde, welcher zu permutieren ist.
     * @param start Aktueller Index, Start des zu betrachteten Intervalls
     * @param curr  Wert von Index in HauptMethode incrementiert. Ende des Intervalls.
     * @return Soll getauscht werden true oder false
     */
    private static boolean shouldSwap(char[]str, int start, int curr) {
        for (int i = start; i < curr; i++) {
            if (str[i] == str[curr]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Simple Tausch Methode.
     * @param str das char Array
     * @param i   Index des zu vertauschenden Characters
     * @param j   Index des zu vertauschenden Characters
     */
    private void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

    /**
     * Getter fuer besteDistanzen
     * @return  List der Permutationen mit der besten Distanz
     */
    public List<String> getBesteDistanzen() {
        return besteDisanzen;
    }

    /**
     * Getter fuer die besteMoeglicheDistanz des Strings
     * @return bestMoeglicheDistanz
     */
    public int getBestMoeglicheDistanz() {
        return bestMoeglicheDistanz;
    }
}
