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
public abstract class Carta {
	String nome;
        boolean tapped = true;
        public int attacco;
	public int difesa;
	public abstract void effettoCarta(Giocatore g);
}
