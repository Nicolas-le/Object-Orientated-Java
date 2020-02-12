package oop1;


/**
 * Das Nim-Spiel
 * @author Nicolas & Martin 
 */
public class Spiel {
    
    /**
     * Die Klasse Spiel realisiert den Spielablauf und verwaltet die Spieleigenschaften.
     */
    private Mensch mensch;
    private Computer computer;
    private int steine;
    private int aktuellerSpieler;
    private int rundenAnzahl;
    private int gewinner;
    private String regeln;

    /**
     *  Der Konstruktor hat keine Parameter und initialisiert die wesentlichen Spieleigenschaften.    
     */
    public Spiel() {
         
        this.mensch = setup();                  // Das Objekt Mensch wird über setup() initialisiert.
        this.computer = new Computer();         // Das Objekt Computer wird erstellt.
        this.steine = random(20,30);            // Die Spielsteine werden ueber die Funktion random() auf eine Zahl zwischen 20 und 30 gesetzt.
        this.aktuellerSpieler = random(0,1);    // Es wird der aktuelle und damit der Startspieler gewaehlt. 0 steht für Mensch, 1 für den Computer.
        this.rundenAnzahl = 0;                  
        this.regeln = "Sie spielen gegen den Computer, nimm 1-3 Steine. Wer den letzten Stein nimmt, verliert.";
        
    }
    
    /**
     * Die Funktion gibt eine Instanz von Mensch zurueck.
     * Hier wird der Name, welchen der Mensch haben soll abgefragt und das Objekt dann damit initialisiert.
     * @return mensch
     */
    private Mensch setup(){
        
        String name = Eingabe.eingabeString("Herzlich Willkommen bei Nim-Spiel. Welchen Namen waehlen Sie?");
        Mensch mensch = new Mensch(name);
        System.out.println("Alles klar, Ihr Spieler mit den Namen '" + name+ "' wurde erstellt.");
        
        return mensch;
        
    }
    
    /** 
     * Die Funktion regelt den Spielablauf.
     * Als erstes werden die Regel erklaert, die Anzahl der Steine angegeben und bekannt gegeben wer
     * startet. Darauf hin wird eine while-Schleife benutzt um die Spieler spielen zu lassen, bis 
     * 0 Steine mehr vorhanden sind. Dann bricht das Spiel ab und die Funktionen ausgabe() und
     * neuesSpiel() werden ausgeloest.
     * 
     */
    public void run(){
        
             
        boolean ende = false;
        
        System.out.println(regeln + "\nWir starten mit " + steine + " Steinen.");
        
        switch(aktuellerSpieler){
            case 1:
                System.out.println("Der Computer beginnt.");
                break;
            case 0:
                System.out.println("Sie starten!");
                break;
       }
        
        
        while(!ende){
            
            if(steine == 0){
                
                ende = true;
                break;
            }
            
            if(aktuellerSpieler == 1){
                
                steine = computer.optimaleSteine(steine);
                aktuellerSpieler = 0;
                rundenAnzahl++;
                gewinner = 0;
                
            }else if(aktuellerSpieler == 0){
                

                steine = mensch.steineNehmen(steine);
                aktuellerSpieler = 1;
                rundenAnzahl++;
                gewinner = 1;
                
                 
            }
        }
    
        ausgabe();
        neuesSpiel();
   
    }
             
    /**
     * Es wird ein 'zufaelliger' int zwischen den (inklusiv) Parametern zurückgegeben.
     * @param min
     * @param max
     * @return Zufallswert
     */
    public static int random(int min, int max){
    
        int a = min + (int)(Math.random() * ((max - min) + 1));
        return a;
    }

    
    /**
     * Hier wird die Ausgabe geregelt. 
     * Es werden je nach Gewinner ein Output mit der Rundenzahl des Spiels ausgegeben.
     */
    private void ausgabe(){
        
        if(gewinner == 1){

            System.out.println("Gewinner ist der Computer. Es gab " + rundenAnzahl + " Runden.");
        }else{
            System.out.println(mensch.getName()+ " hat gewonnen! Es gab " + rundenAnzahl + " Runden.");
        }
        
    }
    /**
     * Die Funktion fragt ab, ob ein neues Spiel gespielt werden soll.
     * Wenn ja (Y oder y), dann werden die Steine neu gesetzt, der Starspieler gewaehlt
     * und die Rundenanzahl zurueckgesetzt.
     */
    private void neuesSpiel(){
    
        String eingabe = Eingabe.eingabeString("Neues Spiel? Y/N");
        
        switch(eingabe.toLowerCase()){
            
            case "y":
               steine = random(20,30);
               aktuellerSpieler = random(0,1);
               rundenAnzahl = 0;
               run();
            case "n":
                System.out.println("Bye Bye.");
                break;
            default:
                neuesSpiel();
                
        }
    
}
}
