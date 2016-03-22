/*
 * Sorcery/Stregoneria
 * Il giocatore avversario salta la prossima draw phase
 * 
 * 
 */
package cardgame.cards;

import cardgame.Card;
import cardgame.AbstractCardEffect;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author Francesco Benetello
 * 
 */
public class Fatigue implements Card {
    
    public class FatigueEffect extends AbstractCardEffect {
        
        public FatigueEffect(Player p, Card c) {
            super(p,c);
        }
        public void resolve() {}
    }
    
    public Effect get_effect(Player owner) {
        return FatigueEffect(owner, this);
    }
    
    
    
    
    
    public String name() { 
        return "Fatigue"; 
    }
    public String type() { 
        return "Sorcery"; 
    }
    public String rule_text() { 
        return "Target player skips his or her next draw step."; 
    }
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    public boolean isInstant() {  // Non è istantanea!
        return false; 
    }

    
    
}
