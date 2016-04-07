
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;
import cardgame.TriggerAction;


public class SavorTheMoment implements Card{
    
    public Effect get_effect(Player owner) { return new SavorTheMomentEffect(owner,this);}
    
    public String name() { return "Savor the moment";}
    
    public String type() { return "Sorcery";}
    
    public String rule_text() { return "The merrow took a moment for herself.\n She never gave it back";}
    
    public boolean isInstant() { return false;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class SavorTheMomentEffect extends AbstractCardEffect implements TriggerAction
    {
        int i=0;
    
        public SavorTheMomentEffect(Player p,Card c) { super(p,c);}
        public void resolve() {
        
            owner.set_phase(Phases.UNTAP,new SkipPhase(Phases.UNTAP));
            CardGame.instance.get_triggers().register(Phases.END_FILTER, this);
            
        } 

        @Override
        public void execute() {
           
            if(i>0)
            {
                
                /*applicare il metodo next_Phase di DefaultPhaseManager e fare i--*/
            }
            
            /*else if(i==0)
            {
                ripristinare il turno con set_phase di player
            }*/    
        }
    }    
}
