

package cardgame.cards;

import cardgame.Creature;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Effect;
import cardgame.Player;
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
    
    public void inflict_damage(int dmg) {DecoratedCreature.inflict_damage(dmg);}
    
    public void reset_damage() {DecoratedCreature.reset_damage();}
    
    public int get_power() {return DecoratedCreature.get_power();}
    
    public int get_toughness() {return DecoratedCreature.get_toughness();}

    public int get_shield(){return DecoratedCreature.get_shield();}

    public int getCurrent_power() {return DecoratedCreature.getCurrent_power();}

    public int getCurrent_toughness() {return DecoratedCreature.getCurrent_toughness();}

    public int getCurrent_shield() {return DecoratedCreature.getCurrent_shield();}

    public void setCurrent_power(int dmg) {DecoratedCreature.setCurrent_power(dmg);}

    public void setCurrent_toughness(int dmg){DecoratedCreature.setCurrent_toughness(dmg);}

    public void setCurrent_shield(int dmg){DecoratedCreature.setCurrent_shield(dmg);}
    
    @Override
    public void attack_creature(Creature c, int dmg) {
        DecoratedCreature.attack_creature(c, dmg);
    }

    @Override
    public void attack_player(Player avversario, int dmg) {
        DecoratedCreature.attack_player(avversario, dmg);
    }

    @Override
    public void defend(Creature c, int dmg) {
        DecoratedCreature.defend(c, dmg);
    }

    
    public List<Effect> effects() {return DecoratedCreature.effects();}
    
    public List<Effect> avaliable_effects() {return DecoratedCreature.avaliable_effects();}
    
       
    

    
}