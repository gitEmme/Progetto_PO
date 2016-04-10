package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.List;
import java.util.ArrayList;

public class Reflexologist implements Card{
    
    private class ReflexologistEffect extends AbstractCreatureCardEffect {
        public ReflexologistEffect(Player p, Card c) { super(p,c); }
        
        protected Creature create_creature() { return new ReflexologistCreature(owner); }

        @Override
        public void setTarget() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    public Effect get_effect(Player p) { return new ReflexologistEffect(p,this); }
    
    
    private class ReflexologistCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        ReflexologistCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    public boolean play() { 
                                        CardGame.instance.get_stack().add(this);
                                        return tap(); 
                                    }
                                    public void resolve() {}
                                    public String toString() 
                                        { return "tap: Reflexology does nothing"; }

                @Override
                public void setTarget() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Player getTarget() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                                }
                ); 
        }
        
        public String name() { return "Reflexologist"; }
        public int get_power() { return 0; }
        public int get_toughness() { return 1; }

        // METODI NUOVI O MODIFICATI DA IMPLEMENTARE PER OGNI MOSTRO
        public void attack_creature(Creature c, int dmg) {super.attack_creature(c, dmg);}
        public void attack_player(Player avversario, int dmg){super.attack_player(avversario, dmg);}
        public void defend(Creature c, int dmg) {super.defend(c, dmg);}
        
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
        
        // FINE

        public List<Effect> effects() { return all_effects; }
        public List<Effect> avaliable_effects() { return (is_tapped)?tap_effects:all_effects; }
    }
    
    
    public String name() { return "Reflexologist"; }
    public String type() { return "Creature"; }
    public String rule_text() { return "Put in play a creature Reflexologist(0/1) with tap: Reflexology does nothing"; }
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return false; }

}