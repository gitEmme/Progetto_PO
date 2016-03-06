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
        boolean cartaValida = false;
        while(!cartaValida){
            Scanner console = new Scanner(System.in);
            int scelta = console.nextInt();
            if(scelta >= g.campoGiocatore.campo.size() || scelta < 0) // MANCA IL CONFRONTO SE LA CARTA E MOSTRO
                System.out.println("Selezionare una carta mostro con una posizione valida da potenziare");
            if(scelta >= 0 && scelta < g.campoGiocatore.campo.size()){
                g.campoGiocatore.campo.get(scelta).attacco++;
                g.campoGiocatore.campo.get(scelta).difesa++;
                cartaValida = true;
                System.out.println("Il mostro " + g.campoGiocatore.campo.get(scelta).nome + " ha ora: " + g.campoGiocatore.campo.get(scelta).attacco + " punti attacco e " + g.campoGiocatore.campo.get(scelta).difesa + " punti difesa" );
            }
        }
    }
        
}
