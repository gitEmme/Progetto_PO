/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame.cards;

import cardgame.*;
import cardgame.Creature;
import cardgame.cards.CreatureDecorator;

/**
 *
 * @author fecarrar
 */
public class AggressiveUrgeDecorator extends CreatureDecorator {

    public AggressiveUrgeDecorator (Creature DecoratedCard)
    {
        super(DecoratedCard);
    }    
    
    public int get_power()
    {
        return DecoratedCreature.get_power()+1;
        
    }
    
    public int get_toughness()
    {
        
        return DecoratedCreature.get_power()+1;
        
    }
    
    public void inflict_damage(int dmg)
    {
        this.get_power();
        DecoratedCreature.inflict_damage(dmg);
        
        
    }
    
 
    
    
}
