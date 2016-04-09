package cardgame;

// utility clas implementing common defaul behavior and fields for creatures
// creatures with differenf behavior from the default nee not extend it
public abstract class AbstractCreature implements Creature {
    protected Player owner;
    protected boolean is_tapped=false;
    protected int damage_left = getCurrent_toughness();
        
        protected AbstractCreature(Player owner) { this.owner=owner; }
        
        public boolean tap() { 
            if (is_tapped) {
                System.out.println("creature " + name() + " already tapped");
                return false;
            }
            
            System.out.println("tapping creature " + name());
            is_tapped=true; 
            return true; 
        }
        
        public boolean untap() { 
            if (!is_tapped) {
                System.out.println("creature " + name() + " not tapped");
                return false;
            }
            
            System.out.println("untapping creature " + name());
            is_tapped=false; 
            return true; 
        }
        
        public boolean isTapped() { return is_tapped; }
        
        public void attack_creature(Creature c, int dmg) {
            c.defend(this, dmg);
        } // to do in assignment 2
        
        public void attack_player(Player avversario, int dmg){
            avversario.inflict_damage(dmg);
        }
        
        public void defend(Creature c, int dmg) {
            int real_dmg = 0;
            if(dmg > this.getCurrent_shield()){
                real_dmg = dmg - this.getCurrent_shield();
                if(real_dmg <= this.getCurrent_toughness())
                    System.out.println(c.name() + " attaccando ha inflitto " + real_dmg + " danni al mostro " + this.name());
                else{
                    real_dmg = this.getCurrent_toughness();
                    System.out.println(c.name() + " attaccando ha inflitto " + real_dmg + " danni al mostro " + this.name());
                
                }
            }
            else
                System.out.println(c.name() + " attaccando ha inflitto " + real_dmg + " danni al mostro " + this.name());
            inflict_damage(dmg);
        } // to do in assignment 2
        
        public void inflict_damage(int dmg) {
            if(getCurrent_shield() > dmg)
                setCurrent_shield(-dmg);
            else
                dmg = dmg - getCurrent_shield();
                reset_shield();
                damage_left = getCurrent_toughness();
                damage_left -= dmg;
                if (damage_left<=0)
                    owner.destroy(this);       
        }
        
        
        
        public void reset_damage() { damage_left = get_toughness(); }
        public void reset_shield() { damage_left = get_shield(); }
    
}
