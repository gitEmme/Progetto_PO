
package cardgame.cards;

import cardgame.Creature;


public class AfflictDecorator extends CreatureDecorator {

    public AfflictDecorator (Creature DecoratedCard)
    {
        super(DecoratedCard);
    }    
    
    public int get_power()
    {
        return DecoratedCreature.get_power()-1;
        
    }
    
    public int get_toughness()
    {
        
        return DecoratedCreature.get_toughness()-1;
        
    }
    
    public void inflict_damage(int dmg)
    {
        this.get_power();
        DecoratedCreature.inflict_damage(dmg);
        
        
    }
    
 
    
    
}
