/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Phases;
import cardgame.TriggerAction;

/**
 *
 * @author azanolla
 */
public class Boiling_Earth implements Card{
    private class Boiling_EarthEffect extends AbstractCardEffect implements TriggerAction  {
        public Boiling_EarthEffect (Player p, Card c) {super (p,c);}
        public void resolve(){  //per ogni carta dei due player tolgo 1 punto di toughness
            
            
            for (Creature c: CardGame.instance.get_current_player().get_creatures()){ 
                c.setCurrent_toughness(c.getCurrent_toughness()-1);
                if (c.getCurrent_toughness()==0)
                    owner.destroy(c);
            }    
            for (Creature c: CardGame.instance.get_current_adversary().get_creatures()){
                c.setCurrent_toughness(c.getCurrent_toughness()-1);
                if (c.getCurrent_toughness()==0)
                    owner.destroy(c);
            }
            CardGame.instance.get_triggers().register(Phases.END_FILTER, this);
        }
        
        
        @Override
        public void execute() { //resetta il valore toughness delle creature ancora vive al valore iniziale
            for (Creature c: CardGame.instance.get_current_player().get_creatures()){
                c.setCurrent_toughness(c.get_toughness());
            }
            for (Creature c: CardGame.instance.get_current_adversary().get_creatures()){
                c.setCurrent_toughness(c.get_toughness());
            }
        }
        
    }
    public Effect get_effect(Player owner) { 
        return new Boiling_EarthEffect(owner, this);
    }
    
    public String name() { return "Boiling Hearth"; }
    public String type() { return "Sorcery"; }
    public String rule_text() { return "Boiling Earth deals 1 damage to each creature."; }
    public String toString() { return "Boiling Hearth [Boiling Hearth deals 1 damage to each creature]";}
    public boolean isInstant() { return false; }
}
