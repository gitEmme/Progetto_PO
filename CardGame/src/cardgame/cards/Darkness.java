package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Phases;
import cardgame.TriggerAction;

// author Alberto
public class Darkness implements Card{
    
    private class DarknessEffect extends AbstractCardEffect implements TriggerAction{
        
        public DarknessEffect(Player p, Card c) { 
            super(p,c);
        }
        
        @Override
        public void resolve() {
            if(!CardGame.instance.get_current_player().get_creatures().isEmpty()){
                for(int i=0; i<CardGame.instance.get_current_player().get_creatures().size();i++){
                    CardGame.instance.get_current_player().get_creatures().get(i).setCurrent_power(0);
                }
            }
            if(!CardGame.instance.get_current_adversary().get_creatures().isEmpty()){
                for(int y=0; y<CardGame.instance.get_current_adversary().get_creatures().size(); y++){
                    CardGame.instance.get_current_adversary().get_creatures().get(y).setCurrent_power(0);
                }
            }
        }
        
        @Override
        public void execute(){
            if(!CardGame.instance.get_current_player().get_creatures().isEmpty()){
                for(int i=0; i<CardGame.instance.get_current_player().get_creatures().size();i++){
                    CardGame.instance.get_current_player().get_creatures().get(i).setCurrent_power(CardGame.instance.get_current_player().get_creatures().get(i).get_power());
                }
            }
            if(!CardGame.instance.get_current_adversary().get_creatures().isEmpty()){
                for(int y=0; y<CardGame.instance.get_current_adversary().get_creatures().size(); y++){
                    CardGame.instance.get_current_adversary().get_creatures().get(y).setCurrent_power(CardGame.instance.get_current_player().get_creatures().get(y).get_power());
                }
            }
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
