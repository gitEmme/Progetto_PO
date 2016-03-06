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
public class Giocatore {
    String nome;
    int energia = 10;
    int turno = 0;
    Mazzo mazzoGiocatore;
    Mano manoGiocatore;
    Field campoGiocatore;
    /* Inserimento al nome utente */
    public Giocatore(String nome, int energia, Mazzo mazzoGiocatore, Mano manoGiocatore, Field campoGiocatore) {
        this.nome = nome;
        this.energia = energia;
        this.mazzoGiocatore = mazzoGiocatore;
        this.manoGiocatore = manoGiocatore;
        this.campoGiocatore = campoGiocatore;
    }
}
