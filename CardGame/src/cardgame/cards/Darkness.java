/*  Instant/Istantanea
 *
 *  Evita tutto il danno da combattimento che verrebbe
 *  inflitto in questo turno.
 *
 */
package cardgame.cards;

import cardgame.Card;
import cardgame.AbstractCardEffect;
import cardgame.Player;
import cardgame.Effect;

/**
 *
 * @author Francesco Benetello
 */
public class Darkness implements Card {
    
    public String name() {
        return "Darkness";
    }
    
    public String type() {
        return "Instant";
    }
    
    public String rule_text() {
        return "Prevent all combat damage that would be dealt this turn.";
    }
    
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    
    public boolean isInstant() {
        return true;
    }
    
    
    
    
    private class DarknessEffect extends AbstractCardEffect {
        public DarknessEffect(Player p, Card c) {
            super(p, c);
        }
        public void resolve() {}
    }
    
    
    
    
    public Effect get_effect(Player owner) {
        return new DarknessEffect(owner, this);
    }
    
    
}
