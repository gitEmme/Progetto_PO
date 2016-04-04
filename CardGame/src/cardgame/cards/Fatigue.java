/*
 * Sorcery/Stregoneria
 * Il giocatore avversario salta la prossima draw phase
 * 
 * 
 */

package cardgame.cards;


import cardgame.Card;
import cardgame.AbstractCardEffect;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.SkipPhase;

/**
 *
 * @author Francesco Benetello
 * 
 */
public class Fatigue implements Card {
    
    @Override
    public String name() { 
        return "Fatigue"; 
    }
    @Override
    public String type() { 
        return "Sorcery"; 
    }
    @Override
    public String rule_text() { 
        return "Target player skips his or her next draw step."; 
    }
    @Override
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    @Override
    public boolean isInstant() {  // Non è istantanea!
        return false; 
    }
    
    
    private class FatigueEffect extends AbstractCardEffect {
        public FatigueEffect(Player p, Card c) { 
            super(p,c); 
        }
       
        
        public void resolve() {
            //L'avversario salta la draw phase
            CardGame.instance.get_current_adversary().set_phase(Phases.DRAW,new SkipPhase(Phases.DRAW));
            
        }  
    }
    
    
    public Effect get_effect(Player owner) { 
        return new Fatigue.FatigueEffect(owner, this); 
    }
    

}
