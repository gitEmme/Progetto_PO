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
public class VolcanicHammer implements Card {

    public Effect get_effect(Player owner){return new VolcanicHammerEffect(owner,this);}
    
    public String name(){return "Volcanic Hammer";}
    
    public String type(){return "Sorcery";}
    
    public String rule_text(){return "Volcanic Hammer deals 3 damage to any one creature or player";}
    
    public boolean isInstant(){return false;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class VolcanicHammerEffect extends AbstractCardEffect
    {
         VolcanicHammerEffect(Player p,Card c){super(p,c);}
         
         public void resolve(){}/*da fare*/
    }     
        
        
}
