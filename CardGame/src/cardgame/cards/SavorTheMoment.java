package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;


public class SavorTheMoment implements Card{
    
    private class SavorTheMomentEffect extends AbstractCardEffect {
        
        Player own;        
        
        public SavorTheMomentEffect(Player p, Card c) { 
            super(p,c);
            own = p;
            
        }
        @Override
        public void resolve() {
            
            
            SkipPhase skipNull = new SkipPhase(Phases.NULL);
            SkipPhase skipUntap = new SkipPhase(Phases.UNTAP);
            
            own.set_phase(Phases.NULL, skipNull);
            own.set_phase(Phases.UNTAP, skipUntap);
        }

        @Override
        public void setTarget() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Effect get_effect(Player owner) { 
        return new SavorTheMomentEffect(owner, this); 
    }
    
    @Override
    public String name() { return "SavorTheMoment"; }
    @Override
    public String type() { return "Sorcery"; }
    @Override
    public String rule_text() { return name() + " Take an eztra turn after this one. Skip the untap step of that turn. "; }
    @Override
    public String toString() { return name() + "[" + rule_text() +"]";}
    @Override
    public boolean isInstant() { return false; }
    
}
