package cardgame.cards;

/* World At War - Mondo in guerra - Stregoneria
 *
 * Dopo la prima fase principale post-combattimento in questo turno, 
 * c’è una fase di combattimento addizionale seguita da una fase di principale addizionale. 
 * All’inizio di quel combattimento, 
 * STAPpa tutte le creature che hanno attaccato in questo turno.
 *  
 */

import cardgame.Card;
import cardgame.AbstractCardEffect;
import cardgame.Effect;
import cardgame.Player;
import cardgame.DefaultCombatPhase;
import cardgame.DefaultMainPhase;
import cardgame.DefaultEndPhase;
import cardgame.CardGame;
import cardgame.SkipPhase;
import cardgame.Phases;

/**
 *
 * @author Francesco Benetello
 * 
 */
public class WorldAtWar implements Card {
    
    @Override
    public String name() { 
        return "WorldAtWar"; 
    }
    @Override
    public String type() { 
        return "Sorcery"; 
    }
    @Override
    public String rule_text() { 
        return "After the firts postcombat phase this turn, there's an additional combat phase followed by an additional main phase.\n"
                + "At the beginning of that combat, untap all creatures that attacked this turn."; 
    }
    @Override
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    @Override
    public boolean isInstant() {  // Non è istantanea!
        return false; 
    }
    
    
    private class WorldAtWarEffect extends AbstractCardEffect {
        
        public WorldAtWarEffect(Player p, Card c) {
            super(p, c);
        }
        
        public void resolve() {
           System.out.println("Starting CUSTOM COMBAT PHASE");
           CardGame.instance.get_current_player().remove_phase(Phases.END, new SkipPhase(Phases.END));
           CardGame.instance.get_current_player().set_phase(Phases.COMBAT, new DefaultCombatPhase());
           CardGame.instance.get_current_player().next_phase().execute();
           System.out.println("Starting CUSTOM MAIN PHASE");
           CardGame.instance.get_current_player().set_phase(Phases.MAIN, new DefaultMainPhase());
           CardGame.instance.get_current_player().next_phase();
           CardGame.instance.get_current_player().set_phase(Phases.END, new DefaultEndPhase());
        }   
    
    }
    

    public Effect get_effect(Player owner) { 
        return new WorldAtWarEffect(owner, this); 
    }
    
}
