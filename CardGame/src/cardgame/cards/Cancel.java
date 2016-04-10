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
import java.util.Scanner;

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
        public void resolve(){
            if(CardGame.instance.get_stack().isEmpty()){
                System.out.println("There no effects to cancel");
            }
            else{
                Scanner reader = CardGame.instance.get_scanner();
                System.out.println("effect on the field");
                int i=0;
                for( Effect e:CardGame.instance.get_stack()) {
                    System.out.println(Integer.toString(i+1)+") " + e );
                    ++i;
                }
                int idx =0;
                do{
                    System.out.println(owner.get_name() + " select effect to counter");
                    idx= reader.nextInt()-1;
                }while(idx<0 || idx>=i);
                
                int y=0;
                for( Effect e:CardGame.instance.get_stack()){
                    if(y == idx)
                        CardGame.instance.get_stack().remove(e);
                }
                
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
