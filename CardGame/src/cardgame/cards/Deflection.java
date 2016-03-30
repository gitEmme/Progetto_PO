/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author azanolla
 */
public class Deflection implements Card{
    private class DeflectionEffect extends AbstractCardEffect {
        public DeflectionEffect (Player p, Card c) {super(p,c);}
        public void resolve() {}
    }
    
    public Effect get_effect(Player owner) {
        return new DeflectionEffect(owner,this);
    }
    
    public String name() {return "Deflection";}
    public String type() {return "Instant";}
    public String rule_text() {return name() + "changes the target of target "
                                             + " spell with a single target.";}
    public String toString() {return name() + "[" + rule_text() + "]";}
    public boolean isInstant() {return true;}
}
    

