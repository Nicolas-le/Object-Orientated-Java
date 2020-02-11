
package oop4;
import java.util.*;


/**
 * Verwaltungsklasse. Regelt den groben Versuchsablauf.
 * hm: Aggregiert die hashmap-Klasse
 * zuSortieren: String der zu sortierenden Farben/Zeichen, welche permutiert werden
 * gesamteWimpelAnzahl: gesamte Anzahl der zu sortierenden Wimpel
 * bestMoeglicheDistanz: die beste auftretende Distanz zwischen gleichen Farben in der Permutation.
 * ergebnis: Liste von best sortierten Strings (groeßte Distanz und deren niedrigste Frequenz)
 * @author Nicolas & Martin
 */
public class Verwaltung {

    private Hashmap hm;
    private String zuSortieren;
    private int gesamteWimpelAnzahl;
    private int bestMoeglicheDistanz;
    private List<String> ergebnis;

    /**
     * Konstruktor der Verwaltung.
     * Die Hashmap erstellt ein neues Objekt der HashmapKlasse.
     * Setzt die zu sortierenden Farben in einen String, mit Hilfe der zuSortierenSetUp() Methode.
     * Die gesamteWimpelAnzahl entspricht der Laenge von zuSortieren
     */
    public Verwaltung(){

        this.hm = new Hashmap();
        this.zuSortieren = zuSortierenSetUp();
        this.gesamteWimpelAnzahl = zuSortieren.length();
    }

    /**
     * Erstellt den zu sortierenden String.
     * Benutzt einen StringBuilder um nacheinander die Farben, je nach Haeufigkeit, mit ihrem Anfangsbuchstaben in den Stirng zu sortieren.
     * @return sb.toString als String aller zu sortierenden Zeichen.
     */
    public String zuSortierenSetUp(){

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : hm.getHm().entrySet()) {
            for(int i = 0; i<entry.getValue();i++){

                char value = entry.getKey().toCharArray()[0];
                sb.append(value);
            }
        }
        return sb.toString();
    }

    /**
     * Regelt den gesamten Versuchsaufbau.
     * Eine Instsanz von Qualitaet, sowie der Permutation wird erstellt.
     * Anschließende wird in einem Durchlauf der kompletten Permutationen die bestmoegliche Distanz errechnet.
     * Daraufhin werde alle Permutationen in denen dies die niedrigste Distanz zwischen gleichen Farben ist zu einer List hinzugefuegt. (In der Permuationsklasse)
     * Anschließende werden durhc die FrequenzMethode der Qualitaets-Klasse alle Permutationen hinzugefuegt, bei denen diese Distanz am seltensten Vorkommt. (z.B. rwrgrt vs. rwrgtr)
     */

    public void run(){

        Qualitaet q = new Qualitaet();
        Permutation pm = new Permutation(q,hm);

        pm.setBesteDistanz(zuSortieren.toCharArray(),0,zuSortieren.length());
        System.out.println("50% geschafft.");
        pm.findePermutationen(zuSortieren.toCharArray(),0,zuSortieren.length());

        ergebnis = new ArrayList<>();
        ergebnis = q.frequenz(pm.getBesteDistanzen(),pm.getBestMoeglicheDistanz(),hm.getHm());

        ausgabe(q,pm.getBestMoeglicheDistanz());

    }

    /**
     * Gibt das Ergebnis aus.
     * @param q
     */

    public void ausgabe(Qualitaet q, int bestMoeglicheDistanz){

        System.out.print("\nDie bestmoegliche Distanz ist: ");
        System.out.println(bestMoeglicheDistanz);

        System.out.print("Die bestmoegliche Frequenz dieser Distanz ist: ");
        System.out.println(q.getBesteFrequenz());

        System.out.println("\nBeste Anordnungen:");
        ergebnis.forEach(System.out::println);

    }

}