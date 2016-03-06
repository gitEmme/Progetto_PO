/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgc;

import java.util.Scanner;

/**
 *
 * @author Alberto
 */
public class Turno{
    
    
    public void drawPhase(Giocatore g) {
        System.out.println("Draw Phase");
        if (g.turno == 0){ // se è il primo turno del giocatore, il giocatore pesca 5 carte
            for(int i=0; i<5; i++){
                g.manoGiocatore.mano.add(g.mazzoGiocatore.mazzo.get(0));
                g.mazzoGiocatore.mazzo.remove(0);
            }
        } else {  // altrimenti il giocatore pesca una sola carta
            if(g.mazzoGiocatore.mazzo.isEmpty()){  // prima di pescare controllo se vi sono carte nel mazzo
                g.energia = 0;  // il giocatore con il mazzo vuoto perde
            }
            else{
                g.manoGiocatore.mano.add(g.mazzoGiocatore.mazzo.get(0));
                g.mazzoGiocatore.mazzo.remove(0);
                if (g.manoGiocatore.mano.size() > 6) { // controllo della quantità di carte nella mano del giocatore
                    System.out.println("Selezionare la carta da scartare");
                    while(g.manoGiocatore.mano.size()> 6) {
                        Scanner console = new Scanner(System.in); // standard input della posizione della carta da rimuovere
                        int cartaScartata = console.nextInt();
                        if(cartaScartata >= g.manoGiocatore.mano.size() || cartaScartata < 0)
                            System.out.println("Scartare una carta con una posizione valida");
                        else{
                            g.campoGiocatore.campo.add(g.manoGiocatore.mano.get(cartaScartata));
                            g.manoGiocatore.mano.remove(cartaScartata);
                        }
                    }              
                }
            }
        }
    }
    
    public void untapPhase(Giocatore g) {
        System.out.println("Untap Phase");
        
        for(int i=0; i<g.campoGiocatore.campo.size(); i++){
            if(g.campoGiocatore.campo.get(i).tapped == true)
                g.campoGiocatore.campo.get(i).tapped = false;
        }
    }
    
    public void combatPhase(Giocatore g){
        System.out.println("Combat Phase!");
        //Combat Phase (Successivamente)
    }
    
    public void mainPhase(Giocatore g) {
        System.out.println("Main Phase");
        boolean fine = false;
        System.out.println("Seleziona la carta da giocare");
        if(g.manoGiocatore.mano.size()>0){
            while(!fine) {
                Scanner console = new Scanner(System.in); // standard input della posizione della carta da rimuovere
                int cartaGiocata = console.nextInt();
                if(cartaGiocata < 0)
                    fine = true;
                else{
                    if(cartaGiocata >= g.manoGiocatore.mano.size()) // MANCA ISTANCE OF MOSTRO || STREGONERIA
                                System.out.println("Giocare una carta con una posizione valida");
                    if(cartaGiocata >= 0 && cartaGiocata < g.manoGiocatore.mano.size()){
                        g.campoGiocatore.campo.add(g.manoGiocatore.mano.get(cartaGiocata));
                        g.manoGiocatore.mano.remove(cartaGiocata);
                        fine = true;  // MANCA SE UNA CARTA VIENE GIOCATA L'AVVERSARIO Può USARE UNA ISTANTANEA???
                    }
                }        
            }
        }
        else{
            System.out.println("Il giocatore" + g.nome + "non ha carte in mano");
        }
    }
    // MANCA CHE SE IL CAMPO E VUOTO E IO GIOCO UNA CARTA PER POTENZIARE NON LA POSSO GIOCARE PERCHE NON HO NULLA DA POTENZIARE
    
    public void endPhase(Giocatore g) {
        g.turno++;
    }
    
    public boolean lifeCheck(Giocatore g){
        if(g.energia > 0 ){
            return true;
        }
        return false;
    }
    
    public void giocaIstantanea(Giocatore G1, Giocatore G2) {
        System.out.println(G1.nome + "vuoi giocare una carta istantanea?");
        Scanner istant = new Scanner(System.in);
        int decisione = istant.nextInt();
        if(decisione > 0){
            boolean cartagiocata = false;
            boolean fine = false;
            System.out.println("Seleziona la carta istantanea da giocare");  // MANCA ISTANCEOF ISTANTANEA
            while(G1.manoGiocatore.mano.size()>0 && !fine) {
                Scanner console = new Scanner(System.in);
                int cartaGiocata = console.nextInt();
                if(cartaGiocata < 0)
                    fine = true;
                else{
                    if(cartaGiocata >= G1.manoGiocatore.mano.size())
                        System.out.println("Giocare una carta istantanea con una posizione valida");
                    if(cartaGiocata >= 0 && cartaGiocata < G1.manoGiocatore.mano.size()){
                        G1.campoGiocatore.campo.add(G1.manoGiocatore.mano.get(cartaGiocata));
                        G1.manoGiocatore.mano.remove(cartaGiocata);
                        cartagiocata = true;
                        System.out.println("Seleziona la carta istantanea da giocare");
                    }
                }
            }
            if(cartagiocata)
                giocaIstantanea(G2,G1);
        }
    }
}