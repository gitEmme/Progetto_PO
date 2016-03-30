/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author fecarrar
 */
public class AggressiveUrge implements Card {
    
    public Effect get_effect(Player p) {return new AggressiveUrgeEffect(p, this);}
    
    public String name() {return "Aggressive Urge";}
    
    public String type() {return "Instant";}
    
    public String rule_text() {return "The power of the wild,concentrated in a single charge";}
    
    public boolean isInstant() {return true;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class AggressiveUrgeEffect extends AbstractCardEffect
    {
        public AggressiveUrgeEffect(Player p,Card c) {super(p,c);}
                
        public void resolve(){} /* da fare l'effetto*/
    }
    
    
}
