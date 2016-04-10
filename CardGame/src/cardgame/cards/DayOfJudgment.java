
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Phases;
import cardgame.TriggerAction;


//autor Alberto
public class DayOfJudgment implements Card{
    
    private class DayOfJudgmentEffect extends AbstractCardEffect {
        
        public DayOfJudgmentEffect(Player p, Card c) { 
            super(p,c); 
        }
        public void resolve() {
            CardGame.instance.get_current_player().get_creatures().clear();
            CardGame.instance.get_current_player().getAttaccantiCombat().clear();
            CardGame.instance.get_current_adversary().get_creatures().clear();
            CardGame.instance.get_current_adversary().getDifensoriCombat().clear();
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
