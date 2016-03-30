/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author fecarrar
 */
public class SavorTheMoment implements Card{
    
    public Effect get_effect(Player owner) { return new SavorTheMomentEffect(owner,this);}
    
    public String name() { return "Savor the moment";}
    
    public String type() { return "Sorcery";}
    
    public String rule_text() { return "The merrow took a moment for herself.\n She never gave it back";}
    
    public boolean isInstant() { return false;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class SavorTheMomentEffect extends AbstractCardEffect
    {
        public SavorTheMomentEffect(Player p,Card c) { super(p,c);}
        public void resolve() {} /*da fare*/
    }    
}
