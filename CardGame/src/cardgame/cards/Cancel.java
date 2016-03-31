/*
 * @Author Alessandro Zanolla
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;


public class Cancel implements Card{
    
    private class CancelEffect extends AbstractCardEffect {
        public CancelEffect(Player p, Card c) { super(p,c); }
        public void resolve() {}
    }

    public Effect get_effect(Player owner) { 
        return new CancelEffect(owner, this); 
    }
    
    public String name() { return "Cancel"; }
    public String type() { return "Instant"; }
    public String rule_text() { return "Counter target spell."; }
    public String toString() { return "Cancel [Counter target spell]";}
    public boolean isInstant() { return true; }
    
    
}
