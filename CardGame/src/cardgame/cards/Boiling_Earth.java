/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Effect;
import cardgame.Player;
import cardgame.Player;

/**
 *
 * @author azanolla
 */
public class Boiling_Earth implements Card{
    private class Boiling_EarthEffect extends AbstractCardEffect {
        public Boiling_EarthEffect (Player p, Card c) {super (p,c);}
        public void resolve(){}
    }
    public Effect get_effect(Player owner) { 
        return new Boiling_EarthEffect(owner, this);
    }
    
    public String name() { return "Boiling Hearth"; }
    public String type() { return "Sorcery"; }
    public String rule_text() { return "Boiling Earth deals 1 damage to each creature."; }
    public String toString() { return "Boiling Hearth [Boiling Hearth deals 1 damage to each creature]";}
    public boolean isInstant() { return false; }
}
