/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tgc;

import java.util.Scanner;

/**
 *
 * @author agalvan
 */
public class Stregoneria1 extends Stregoneria{
    int durata;
    public Stregoneria1(String nome, int durata){
		this.nome = nome;
                this.durata = durata;
    }
    public void effettoCarta(Giocatore g) {
        System.out.println("Selezionare la carta da potenziare");
        int scelta;
        do{
            Scanner console = new Scanner(System.in); // scegliamo tramite standard input la carta da rimuovere dalla mano
            scelta = console.nextInt();
            if(scelta < g.campoGiocatore.campo.size()) // MANCA IL CONFRONTO SE LA CARTA E MOSTRO
                System.out.println("Selezionare una carta mostro con una posizione valida da potenziare");
        }while(scelta < g.campoGiocatore.campo.size()); // MANCA IL CONFRONTO SE LA CARTA E MOSTRO)
        g.campoGiocatore.campo.get(scelta).attacco++;
        g.campoGiocatore.campo.get(scelta).difesa++;
    }
}
