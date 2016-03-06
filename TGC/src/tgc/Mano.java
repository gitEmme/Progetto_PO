/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgc;

import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class Mano {
    ArrayList<Carta> mano = new ArrayList<>(); //organizziamo mano in arraylist
    public void addCard(Carta carta){
        mano.add(carta);
    }
    public void removeCard(Carta carta){
        mano.remove(carta);
    }
}
