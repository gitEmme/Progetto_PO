

package cardgame.cards;

import cardgame.Creature;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Effect;
import cardgame.Player;
import java.util.List;


public class CreatureDecorator implements Creature {

    protected Creature DecoratedCreature;
    
    public CreatureDecorator(Creature DecoratedCreature) {
        this.DecoratedCreature=DecoratedCreature;
    }    
    
    public String name() {return DecoratedCreature.name();}
    
    public boolean tap() {return DecoratedCreature.tap();}
    
    public boolean untap() {return DecoratedCreature.untap();}
    
    public boolean isTapped() {return DecoratedCreature.isTapped();}
    
// METODI NUOVI O MODIFICATI DA IMPLEMENTARE PER OGNI MOSTRO
        public void attack_creature(Creature c, int dmg) {
            /*super.*/attack_creature(c, dmg);
        }
        public void attack_player(Player avversario, int dmg){
            /*super.*/attack_player(avversario, dmg);
        }
        public void defend(Creature c, int dmg) {
            /*super.*/defend(c, dmg);
        }
        
        public int get_shield() { return 0; }
        
        private int current_power = 0;
        private int current_toughness = 1;
        private int current_shield = 0;
        
        
        public int getCurrent_power(){
            return current_power;
        }

        public void setCurrent_power(int dmg) {
            this.current_power += dmg;
        }
        
        public int getCurrent_toughness() {
            return current_toughness;
        }

        public void setCurrent_toughness(int dmg) {
            this.current_toughness += dmg;
        }

        public int getCurrent_shield() {
            return current_shield;
        }
        
        public void setCurrent_shield(int dmg) {
            this.current_shield += dmg;
        }
    
    public void inflict_damage(int dmg) {DecoratedCreature.inflict_damage(dmg);}
    
    public void reset_damage() {DecoratedCreature.reset_damage();}
    
    public int get_power() {return DecoratedCreature.get_power();}
    
    public int get_toughness() {return DecoratedCreature.get_toughness();}
    
    public List<Effect> effects() {return DecoratedCreature.effects();}
    
    public List<Effect> avaliable_effects() {return DecoratedCreature.avaliable_effects();}
    
       
    

    
}
