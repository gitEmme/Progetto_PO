
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;



public class FalsePeace implements Card{
    
    private class FalsePeaceEffect extends AbstractCardEffect {
        private Player owner;
        public FalsePeaceEffect(Player p, Card c) { super(p,c); 
        owner = p;
        }
        public void resolve() {
        SkipPhase skipCombat = new SkipPhase(Phases.COMBAT);
        owner.set_phase(Phases.COMBAT, skipCombat);
        }
    }

    public Effect get_effect(Player owner) { 
        return new FalsePeaceEffect(owner, this); 
    }
    
    public String name() { return "False peace"; }
    public String type() { return "Sorcery"; }
    public String rule_text() { return name() + "mutual consent in not required for war"; }
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return false; }
    
}