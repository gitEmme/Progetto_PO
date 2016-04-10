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
import cardgame.DefaultCombatPhase;

/**
 *
 * @author azanolla
 */
public class Boiling_Earth implements Card{
    private class Boiling_EarthEffect extends AbstractCardEffect implements TriggerAction  {
        public Boiling_EarthEffect (Player p, Card c) {super (p,c);}
        public void resolve(){  //per ogni carta dei due player tolgo 1 punto di toughness
            
            if(!CardGame.instance.get_current_player().get_creatures().isEmpty()){
                for (Creature c: CardGame.instance.get_current_player().get_creatures()){
                    int pos = 0;
                    boolean trovato = false;
                    if((c.getCurrent_toughness()+c.getCurrent_shield()) <= 1){
                        for(int i=0; i<CardGame.instance.get_current_player().get_creatures().size(); i++){
                            if(c == CardGame.instance.get_current_player().get_creatures().get(i)){
                                pos = i;
                            }
                        }
                        int pos_atk = 0;
                        for(int w=0; w<CardGame.instance.get_current_player().getAttaccantiCombat().size(); w++){
                            if(CardGame.instance.get_current_player().getAttaccantiCombat().get(w) == pos){
                                pos_atk = w;
                                trovato = true;
                            }
                        }
                        if(trovato){
                            for(int y=0; y<CardGame.instance.get_current_player().getAttaccantiCombat().size(); y++){
                                if(CardGame.instance.get_current_player().getAttaccantiCombat().get(y) > pos)
                                    CardGame.instance.get_current_player().getAttaccantiCombat().set(y, CardGame.instance.get_current_player().getAttaccantiCombat().get(y)-1);
                            }

                            CardGame.instance.get_current_player().getAttaccantiCombat().remove(pos_atk);
                        }
                    }
                    c.inflict_damage(1);
                }
            }
            
            if(!CardGame.instance.get_current_adversary().get_creatures().isEmpty()){
                for (Creature c: CardGame.instance.get_current_adversary().get_creatures()){
                    int pos = 0;
                    boolean trovato = false;
                    if((c.getCurrent_toughness()+c.getCurrent_shield()) <= 1 ){
                        for(int x=0; x<CardGame.instance.get_current_adversary().get_creatures().size(); x++){
                            if(c == CardGame.instance.get_current_adversary().get_creatures().get(x)){
                                pos = x;
                            }
                        }
                        int pos_def = 0;
                        for(int q=0; q<CardGame.instance.get_current_adversary().getDifensoriCombat().size(); q++){
                            if(CardGame.instance.get_current_adversary().getDifensoriCombat().get(q) == pos){
                                pos_def = q;
                                trovato = true;
                            }
                        }
                        if(trovato){
                            for(int z=0; z<CardGame.instance.get_current_adversary().getDifensoriCombat().size(); z++){
                                if(CardGame.instance.get_current_adversary().getDifensoriCombat().get(z) > pos)
                                    CardGame.instance.get_current_adversary().getDifensoriCombat().set(z, CardGame.instance.get_current_adversary().getDifensoriCombat().get(z)-1);
                            }
                            CardGame.instance.get_current_adversary().getDifensoriCombat().remove(pos_def);
                        }
                    }
                    c.inflict_damage(1);
                }
            }
        }
        
        
        @Override
        public void execute() { //resetta il valore toughness delle creature ancora vive al valore iniziale
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
