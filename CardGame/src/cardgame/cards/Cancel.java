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

/**
 *
 * @author Giacomo
 */
public class Cancel implements Card{

    private class CancelEffect extends AbstractCardEffect{

        private CancelEffect(Player owner, Card card){
            super(owner, card);
        }
        
        @Override
        public void resolve() {
            if(CardGame.instance.get_stack().isEmpty()){
                System.out.println("There no effects to cancel");
            }else{
                CardGame.instance.get_stack().removeLast();
            }
            
        }
        
    }
    @Override
    public Effect get_effect(Player owner) {
        return new CancelEffect(owner, this);
    }

    @Override
    public String name() {
        return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String rule_text() {
        return "Counter target spell";
    }
    
    @Override
    public String toString(){
        return name() + "[" + rule_text() + "]";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
    
}
