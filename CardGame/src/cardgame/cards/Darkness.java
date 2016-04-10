package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;


public class Darkness implements Card{
    
    private class DarknessEffect extends AbstractCardEffect {
        
        Player own;        
        
        public DarknessEffect(Player p, Card c) { 
            super(p,c);
            own = p;
            
        }
        @Override
        public void resolve() {
            Player target = CardGame.instance.get_current_player();
            setTarget(target);
            SkipPhase skipCombat = new SkipPhase(Phases.COMBAT);
            getTarget().set_phase(Phases.COMBAT, skipCombat);

        }

        @Override
        public void setTarget() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

    @Override
    public Effect get_effect(Player owner){ 
        return new DarknessEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Darkness"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String rule_text() { return name() + " Prevent all combat demage that would be dealt this turn "; }
    @Override
    public String toString() { return name() + "[" + rule_text() +"]";}
    @Override
    public boolean isInstant() { return true; }
    
}
