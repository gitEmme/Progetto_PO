
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;



public class DayOfJudgment implements Card{
    
    private class DayOfJudgmentEffect extends AbstractCardEffect {
        private Player owner;
        public DayOfJudgmentEffect(Player p, Card c) { 
            super(p,c); 
            owner = p;
        }
        public void resolve() {
            int n_creature = owner.get_creatures().size();
            while(n_creature>0){
                owner.destroy(owner.get_creatures().get(n_creature-1));
                n_creature--;
            }
        }
    }

    public Effect get_effect(Player owner) { 
        return new DayOfJudgmentEffect(owner, this); 
    }
    
    public String name() { return "Day of judgment"; }
    public String type() { return "Sorcery"; }
    public String rule_text() { return name() + " destroy all creatures"; }
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return false; }
    
}
