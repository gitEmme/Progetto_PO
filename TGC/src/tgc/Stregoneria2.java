/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgc;
import java.util.Scanner;

/**
 *  Questa classe gestisce la Stregoneria "Terra Bollente"
 *  Con effetto: infligge 1 danno a ogni creatura
 * 
 * @author Francesco Benetello
 */
public class Stregoneria2 extends Stregoneria  {
    int durata;
    int i=0;
    public Stregoneria2 (String nome, int durata) {
        this.nome = nome;
        this.durata = durata;
    }
    
    public void effettoCarta(Giocatore g) {
        System.out.println("Inserisci la posizione del campo dove inserire la carta?");
        Scanner console = new Scanner(System.in);
        int scelta;
        do {
            scelta = console.nextInt();
            if(scelta < g.campoGiocatore.campo.size()) // MANCA IL CONFRONTO SE LA CARTA E MOSTRO
                System.out.println("Selezionare una posizione valida!");
        }while(scelta < g.campoGiocatore.campo.size()); // MANCA IL CONFRONTO SE LA CARTA E MOSTRO)
        
        while( 1 < g.campoGicatore.campo.size) {   //Controlla se dentro all'arraylist vi è situato un mostro o una stregoneria
            if (g.campoGiocatore.campo.istanceof(mostro)) { //Se dentro alla cella c'è un mostro allora togli un punto danno
                g.mostro.attacco = g.mostro.attacco - 1;
                System.out.print("Il mostro " + g.mostro.nome + " ha un unità in meno di danno!");
            }
            i++;
        }
    }
}

