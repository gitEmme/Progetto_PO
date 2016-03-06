/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgc;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alberto
 */

public class Mazzo {

       ArrayList<Carta> mazzo = new ArrayList<>(); //organizziamo mazzo in arraylist
       // medoto per mescolare il mazzo qui ???
       public void addCard(Carta carta){
           mazzo.add(carta);
       }
       public void removeCard(Carta carta){
           mazzo.remove(carta);
       }
}
