/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Die Klasse Welt regelt den gesamten Spielablauf.
 * @author Martin & Nicolas
 */
public class Welt {
    
    
    private Feld[][] spielfeld;
    private int anzahlRunden;
    private int anzahlMenschen;
    private int anzahlDementi;
    private int anzahlHeirat;
    private int x;
    private int y;
    private static final AtomicLong ID = new AtomicLong(0);
    
    /**
     *  Der Konstruktor der Welt initialisiert die Welt mit ihren Attributen.
     *  
     */
    public Welt(){
        
        this.x = Eingabe.eingabeInt("Welche Laenge soll das Spielfeld haben?");
        this.y = Eingabe.eingabeInt("Welche Breite soll das Spielfeld haben?");
        this.anzahlMenschen = Eingabe.eingabeInt("Wie viele Menschen soll es geben?");
        this.spielfeld = setupSpielfeld();
        this.anzahlRunden = Eingabe.eingabeInt("Wie viele Runden soll es geben?");
        this.anzahlDementi = 0;
        this.anzahlHeirat = 0;

  
    }
    
    /**
     * Erzeugt das Spielfeld, durch ein zweidimensionales Feld Array.
     * Dabei werden den Feldern der String spezial gesondert zugewiesen.
     * So gibts es Eckfelder, Randfelder und Mittlere Felder. Dies dient spaeter
     * der "Orientiertung" im Array.
     * @return spielfeld - Gibt das zweidimensionale Array vom Typ Feld zurueck.
     */
    public Feld[][] setupSpielfeld(){
 
     Feld[][] spielfeld = new Feld[x][y];
     spielfeld[0][0] = new Feld("links oben");                              //1 = Ecke links oben
     spielfeld[0][y-1] = new Feld("rechts oben");                           //2 = Ecke rechts oben
     spielfeld[x-1][y-1] = new Feld("rechts unten");                        //3 = Ecke rechts unten
     spielfeld[x-1][0] = new Feld("links unten");                           //4 = Ecke links unten
    
        for (int i = 0; i < spielfeld.length; i++) {
            for(int j = 0; j < spielfeld[i].length; j++) {
                
                if(spielfeld[i][j] == null){
                   
                    if(j == 0){
                        spielfeld[i][j] = new Feld("links");                //Kante links
                        
                    }else if(j == y-1){
                       spielfeld[i][j] = new Feld("rechts");                //Kante rechts
                    
                    }else if(i == 0){
                        spielfeld[i][j] = new Feld("oben");                 //Kante oben
                        
                    }else if(i == x-1){
                        spielfeld[i][j] = new Feld("unten");                //Kante unten
                   
                    }else{
                        spielfeld[i][j] = new Feld("mitte");                //rest
                    }
                }   
            }
        }
        return spielfeld;

    }
    
    /**
     * Regelt einen kompletten Versuchsablauf.
     * Als erstes werden die Menschen zufaellig in den Array Listen der Felder
     * verteilt.
     * Fuer jede Runde werden einmal alle Menschen zufaellig bewegt (oder nicht)
     * und ihre Meinungen werden angepasst und gezaehlt.
     * Am Ende werden die auszugebenden Daten zurueckgegeben.
     *  
     */
    public void leben(){
        
        menschenVerteilung();
        ausgabe(0);

        for(int i = 0; i<=anzahlRunden;i++){
           
            bewegen();
            meinungAendern();
            meinungZaehlen();
            ausgabe(i+1);
            anzahlHeirat = 0;
            anzahlDementi = 0;

        }


        
    }
    
    /**
     *  Verteilt die Objekte der Klasse Mensch zufaellig in die Array Listen der Felder.
     *  Dabei werden Anton und Berta gesondert an ihren Startpunkten intitialisiert.
     *  Die globale Variable ID wird dabei von 1 an erhoeht und so erhaelt jeder Mensche
     *  eine eigene Identifikationsnummer.
     *  Am Beginn werden keine Menschen auf die selben Felder verteilt.
     */
    public void menschenVerteilung(){
        
     spielfeld[0][0].getMenschen().add(new Anton(ID.incrementAndGet()));
     spielfeld[x-1][y-1].getMenschen().add(new Berta(ID.incrementAndGet()));

        for(int i = 0; i<anzahlMenschen;i++){
         
            int a = random(0,x-1);
            int b = random(0,y-1);
         
            while(!(spielfeld[a][b].getMenschen().isEmpty())){                       //sichert, dass Menschen nicht auf selbes Feld verteilt werden
             
                a = random(0,x-1);
                b = random(0,y-1);
            }
         
        spielfeld[a][b].getMenschen().add(new Mensch(false,false, ID.incrementAndGet()));
         
     }
   }
    
    /**
     *  Die Funktion bewegt einmal alle Menschen. (auch keine Bewegung moeglich)
     *  Die Menschen werden sequentiell nacheinander bewegt. Um zu verhindern, dass
     *  eine Mensch nach seiner Bewegung, in der Betrachtung eines folgenden Feldes
     *  nocheinmal bewegt wird, wird seine ID nach einer Bewegung einer Liste hinzugefuegt.
     *  Anton und Berta werden nicht bewegt, oder bewegt (Frage).
     */
    public void bewegen(){
        
        List<Long> id = new ArrayList<Long>();
        
        for (int i = 0; i < spielfeld.length; i++) {
            for(int j = 0; j < spielfeld[i].length; j++) {
                if(spielfeld[i][j].getMenschen().isEmpty())                          //Wenn ArrayList des Feldes leer --> naechstes Feld
                    continue;
                for(int k = 0; k<spielfeld[i][j].getMenschen().size();k++){          //Iteration ueber ArrayList des Feldes
                    Mensch m = spielfeld[i][j].getMenschen().get(k);                 
                    if(id.contains(m.getId()))                                  //Wenn schon einmal bewegt in der Runde --> naechster Mensch
                            continue;
                    /**if(m instanceof Anton || m instanceof Berta)             //Sollen sie sich bewegen?      
                            continue;*/
                    id.add(m.getId());
                    int[] nextPos = bewegenHelper(i,j);                         //bewegenHelper gibt neue Koordinaten zurueck
                    spielfeld[nextPos[0]][nextPos[1]].getMenschen().add(m);          //Mensch wird ArrayList in neuem Feld (Koordinaten) hinzugefuegt
                    spielfeld[i][j].getMenschen().remove(m);                         //Mensch wird entfernt
                }
                
                
            }
        }
        id.clear();
    }
    
    /**
     * Gibt neue Koordinaten (neue Position den Menschen) zurueck.
     * Bekommt die Koordinaten eines Feldes im Array. 
     * Einen zufaelliger moeglicher Schritt von diesem Feld aus wird per feld.moeglicheSchritte zurueckgegeben.
     * Die Schritte sind mit folgenden Zahlen kodiert:
     * 1: keiner ; 2: unten; 3: oben; 4: rechts; 5: links
     * 
     * @param x x-Position des Feldes
     * @param y y-Position des Feldes
     * @return int[] mit x auf 0, y auf 1
     */
    public int[] bewegenHelper(int x, int y){
        
        int[] xy = new int[2];
        xy[0] = x;
        xy[1] = y;
        int schritt = spielfeld[x][y].moeglichWege();

        switch(schritt){
            
            case 1:
                break;
            case 2:
                xy[0] = x+1;
                xy[1] = y;
                break;
            case 3:
                xy[0] = x-1;
                xy[1] = y;
                break;
            case 4:
                xy[0] = x;
                xy[1] = y+1;
                break;
            case 5:
                xy[0] = x;
                xy[1] = y-1;
                break;
        }
        return xy;
        
    }
    
    /**
     * Veraendert die Meinungen nach jedem Rundendurchlauf.
     * Es wird ueber die Felder iterriert.
     * Im ersten Teil der Funktion wird gezaehlt ob es mehr Dementis oder Heirats in einer 
     * ArrayList gibt. Wenn sich nur ein oder Mensch in einer ArrayList befindet wird das
     * naechste Feld betrachtet. 
     * Wenn es eine Mehrheit gibt, werden alle Menschen in der zweiten Interation ueber
     * die ArrayList in ihrer Meinung angepasst.
     * 
     */
    public void meinungAendern(){
        
        int dementiTmp = 0;
        int heiratTmp = 0;

        for (int i = 0; i < spielfeld.length; i++) {                            //Iteration ueber Felder
            for(int j = 0; j < spielfeld[i].length; j++) {

                for(int k = 0; k<spielfeld[i][j].getMenschen().size();k++){          //Itertation ueber ArrayList
                    Mensch m = spielfeld[i][j].getMenschen().get(k);                 //Objekt Mensch an Index
                    if(m.isDementi() && m.isHeirat() == false){                 //Wenn Mensch dementi = true und heirat = false--> dementitmp++
                        dementiTmp++;
                        
                    }else if(m.isHeirat()&& m.isDementi() == false){            //analog zu demento             
                        heiratTmp++;
                        
                    }else{                                                      //wenn beides false oder beides true --> naechster Mensch
                        continue;
                    }

                }
                
                for(int k = 0; k<spielfeld[i][j].getMenschen().size();k++){          //Aenderung der Meinungen
                    Mensch m = spielfeld[i][j].getMenschen().get(k);
                    if(m instanceof Anton || m instanceof Berta)
                            continue;
                    if(dementiTmp > heiratTmp){                                 //Wenn mehr dementis --> andere ueberzeugt
                        m.setDementi(true);
                        m.setHeirat(false);
                        

                    }else if(dementiTmp < heiratTmp){
                        m.setDementi(false);
                        m.setHeirat(true);

                    }else{
                        m.setDementi(false);
                        m.setHeirat(false);
                    }
                    
                }

                dementiTmp = 0;
                heiratTmp = 0;
        
            }
        }

    }
    
    /**
     * Zaehlt die Meinungen und wird nach jeder Runde aufgerufen.
     * Anton und Berta werden nicht mitgzaehlt.
     */
    public void meinungZaehlen(){
        
        for (int i = 0; i < spielfeld.length; i++) {                            
            for(int j = 0; j < spielfeld[i].length; j++) {
                for(int k = 0; k<spielfeld[i][j].getMenschen().size();k++){          
                    if(spielfeld[i][j].getMenschen().isEmpty())
                        continue;
                    Mensch m = spielfeld[i][j].getMenschen().get(k); 
                    if(m instanceof Anton || m instanceof Berta)
                        continue;
                    if(m.isDementi() && m.isHeirat() == false){                 
                        anzahlDementi++;
                    }else if(m.isHeirat()&& m.isDementi() == false){            
                        anzahlHeirat++;
                    }else{                                                      
                        continue;
                    }
                }
        
            }
        }
    }
    
    
    /**
     * Gibt einen zufaelligen Wert zurueck.
     * @param min minimaler Wert (inklusiv)
     * @param max maximaler Wert (inklusiv)
     * @return  int zufaelliger int Wert
     */
    public static int random(int min, int max){
    
        int a = min + (int)(Math.random() * ((max - min) + 1));
        return a;
    }
    
    /**
     * Rechnet den prozentualen Wert aus.
     * Prozentsatz = (vorhanden*100)/Gesamtwert
     * @param vorhanden  vorhandene Menschen
     * @param gesamt     anzahlMenschen
     * @return           Prozentsatz in double
     */
    public double prozent(int vorhanden, int gesamt){
        
        return (vorhanden*100)/gesamt;

    }

    /**
     * Gibt die Runden und die jeweiligen Ergebnisse aus.
     * @param runde aktuelle Runde
     */
    public void ausgabe(int runde){
        
        System.out.println("Runde: "+runde);
        System.out.print("Heirat/Anton: "+ prozent(anzahlHeirat,anzahlMenschen) + "%    ");
        System.out.print("Dementi/Berta: "+prozent(anzahlDementi,anzahlMenschen) + "%   ");
        System.out.print("Unentschlossen: "+prozent((anzahlMenschen - anzahlDementi - anzahlHeirat),anzahlMenschen) + "%    "); 
        System.out.println("\n");
        //printSpielfeld();
    }

}
