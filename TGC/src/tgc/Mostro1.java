/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tgc;

/**
 *
 * @author agalvan
 */
public class Mostro1 extends Mostro {
    public Mostro1(String nome, int attacco, int difesa) {
		this.nome = nome;
		this.attacco = attacco;
		this.difesa = difesa;
    }
    public void effettoCarta(Giocatore g) {}
}
