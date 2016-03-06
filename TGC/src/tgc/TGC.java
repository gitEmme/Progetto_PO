/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgc;

/**
 *
 * @author Alberto
 */
public class TGC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // inserimento nomi giocatori
        String nome1 = "Giocatore1";
        String nome2 = "Giocatore2";

        //creazione mazzi giocatori
        Mazzo mazzo1 = new Mazzo();
        Mazzo mazzo2 = new Mazzo();

        // creazione mani giocatori
        Mano mano1 = new Mano();
        Mano mano2 = new Mano();

        // creazione campi giocatori
        Field campo1 = new Field();
        Field campo2 = new Field();

        // creazione giocatori
        Giocatore giocatore1 = new Giocatore(nome1, 10, mazzo1, mano1, campo1);
        Giocatore giocatore2 = new Giocatore(nome2, 10, mazzo2, mano2, campo2);

        // creazione carte per il giocatore1
        Mostro1 mostro0 = new Mostro1("Sergio", 99, 99);
        Mostro1 mostro1 = new Mostro1("Alberto", 5, 3);
        Mostro1 mostro2 = new Mostro1("Francesco", 0, 0);
        Mostro1 mostro3 = new Mostro1("Gesu", 3, 3);
        Mostro1 mostro4 = new Mostro1("Allah", 6, 6);
        Mostro1 mostro5 = new Mostro1("Alberto", 5, 3);
        Mostro1 mostro6 = new Mostro1("Francesco", 0, 0);
        Stregoneria1 stregoneria1 = new Stregoneria1("Potenziamento", 2);


        // creazione carte per il giocatore 2
        Mostro1 mostro7 = new Mostro1("Alberto", 5, 3);
        Mostro1 mostro8 = new Mostro1("Francesco", 0, 0);
        Mostro1 mostro9 = new Mostro1("Gesu", 3, 3);
        Mostro1 mostro10 = new Mostro1("Allah", 6, 6);
        Mostro1 mostro11 = new Mostro1("Francesco", 0, 0);
        Mostro1 mostro12 = new Mostro1("Gesu", 3, 3);
        Stregoneria1 stregoneria2 = new Stregoneria1("Potenziamento", 2);

        // inserimento carte nel mazzo del giocatore1
        giocatore1.mazzoGiocatore.addCard(mostro0);
        giocatore1.mazzoGiocatore.addCard(mostro1);
        giocatore1.mazzoGiocatore.addCard(stregoneria1);
        giocatore1.mazzoGiocatore.addCard(mostro2);
        giocatore1.mazzoGiocatore.addCard(mostro3);
        giocatore1.mazzoGiocatore.addCard(mostro4);
        giocatore1.mazzoGiocatore.addCard(mostro5);
        giocatore1.mazzoGiocatore.addCard(mostro6);



        // inserimento carte nel mazzo del giocatore2
        giocatore2.mazzoGiocatore.addCard(mostro7);
        giocatore2.mazzoGiocatore.addCard(mostro8);
        giocatore2.mazzoGiocatore.addCard(mostro9);
        giocatore2.mazzoGiocatore.addCard(mostro10);
        giocatore2.mazzoGiocatore.addCard(mostro11);
        giocatore2.mazzoGiocatore.addCard(mostro12);
        giocatore2.mazzoGiocatore.addCard(stregoneria2);


        //creazione partita
        Turno partita = new Turno();

        //svolgimento partita
        while(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2)){
            System.out.println("TURNO GIOCATORE 1");
            partita.drawPhase(giocatore1);
            if(partita.lifeCheck(giocatore1)){
                partita.untapPhase(giocatore1);
                partita.combatPhase(giocatore1);
                if(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2)){
                    partita.mainPhase(giocatore1);
                }
                    if(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2))
                        partita.endPhase(giocatore1);
            }
            if(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2)){
                System.out.println("TURNO GIOCATORE 2");
                partita.drawPhase(giocatore2);
                if(giocatore2.energia > 0){
                    partita.untapPhase(giocatore2);
                    partita.combatPhase(giocatore2);
                    if(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2)){
                        partita.mainPhase(giocatore2);
                    }
                    if(partita.lifeCheck(giocatore1) && partita.lifeCheck(giocatore2))
                        partita.endPhase(giocatore2);
                }
            }
        }
        if(partita.lifeCheck(giocatore1))
            System.out.println("HA VINTO IL GIOCATORE 1");
        else
            System.out.println("HA VINTO IL GIOCATORE 2");
    }
}
