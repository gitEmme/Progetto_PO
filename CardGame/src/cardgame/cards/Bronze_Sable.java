/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author azanolla
 */
public class Bronze_Sable implements Card{
    private class Bronze_SableEffect extends AbstractCreatureCardEffect {
        public Bronze_SableEffect(Player p, Card c) { super(p,c); }
        
        protected Creature create_creature() { return new Bronze_SableCreature(owner); }
    }
    public Effect get_effect(Player p) { return new Bronze_SableEffect(p,this); }
    
    private class Bronze_SableCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        Bronze_SableCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    public boolean play() { 
                                        CardGame.instance.get_stack().add(this);
                                        return tap(); 
                                    }
                                    public void resolve() {}
                                    public String toString() 
                                        { return "tap: The Champion stood alone "
                                                + "between the horde of the Returned "
                                                + "and the shrine to Karametra, cutting"
                                                + " down scores among hundreds. She "
                                                + "would have been overcome if not f"
                                                + "or the aid of the temple guardians "
                                                + "whom Karametra awakened."; }
                                }
                ); 
        }
    
    
    public String name(){ return "Bronze Sable";}
    public int get_power() { return 2; }
    public int get_toughness() { return 1; }
    public void attack() {}
    public void defend (Creature C) {}
    
    public List<Effect> effects() { return all_effects; }
    public List<Effect> avaliable_effects() { return (is_tapped)?tap_effects:all_effects;}
    
    // METODI NUOVI O MODIFICATI DA IMPLEMENTARE PER OGNI MOSTRO
    public void attack_creature(Creature c, int dmg) {
        super.attack_creature(c, dmg);
    }
    public void attack_player(Player avversario, int dmg){
        super.attack_player(avversario, dmg);
    }
    public void defend(Creature c, int dmg) {
        super.defend(c, dmg);
    }
        
    public int get_shield() { return 0; }
        
    private int current_power = 2;
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
 }
    
    public String name() {return "Bronze Sable";}
    public String type() {return "Creature";}
    public String rule_text() {return "Put in play a creature Bronze Sable (2,1) with tap: "
                                    + "The Champion stood alone "
                                    + "between the horde of the Returned "
                                    + "and the shrine to Karametra, cutting"
                                    + " down scores among hundreds. She "
                                    + "would have been overcome if not f"
                                    + "or the aid of the temple guardians "
                                    + "whom Karametra awakened.";}
    public String toString() { return name() + "[" + rule_text() +"]";}
    public boolean isInstant() { return false; }
        
        
       
}
