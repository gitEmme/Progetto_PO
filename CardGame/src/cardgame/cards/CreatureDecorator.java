

package cardgame.cards;

import cardgame.Creature;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Effect;
import java.util.List;


public class CreatureDecorator implements Creature {

    protected Creature DecoratedCreature;
    
    public CreatureDecorator(Creature DecoratedCreature)
    {
        this.DecoratedCreature=DecoratedCreature;
    }    
    
    public String name() {return DecoratedCreature.name();}
    
    public boolean tap() {return DecoratedCreature.tap();}
    
    public boolean untap() {return DecoratedCreature.untap();}
    
    public boolean isTapped() {return DecoratedCreature.isTapped();}
    
    public void attack() {DecoratedCreature.attack();}
    
    public void defend(Creature c){DecoratedCreature.defend(c);}
    
    public void inflict_damage(int dmg) {DecoratedCreature.inflict_damage(dmg);}
    
    public void reset_damage() {DecoratedCreature.reset_damage();}
    
    public int get_power() {return DecoratedCreature.get_power();}
    
    public int get_toughness() {return DecoratedCreature.get_toughness();}
    
    public List<Effect> effects() {return DecoratedCreature.effects();}
    
    public List<Effect> avaliable_effects() {return DecoratedCreature.avaliable_effects();}
    
       
    

    
}
