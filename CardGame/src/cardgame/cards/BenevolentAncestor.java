/*
 * Creature/Creatura Attacco: 0, Difesa: 4
 * Questa creatura non può attaccare!
 * Previeni il prossimo punto danno che verrebbe inflitto a una creatura o a un giocatore bersaglio in questo turno.
 */

package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.TriggerAction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francesco Benetello
 * 
 */

public class BenevolentAncestor implements Card {
    
    
    @Override
    public String name() { 
        return "Benevolent Ancestor"; 
    }
    @Override
    public String type() { 
        return "Creature"; 
    }
    @Override
    public String rule_text() { 
        return "This card can't attack!\n Prevent the next one damage that would be dealt to target creature or player this turn."; 
    }
    @Override
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    @Override
    public boolean isInstant() { // E' una creatura!
        return false; 
    }

    @Override
    public Effect get_effect(Player owner) {
        return new BenevolentAncestor.BenevolentAncestorEffect(owner,this);
    }

    
    
    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c) { 
            super(p,c); 
        }
        protected Creature create_creature() { 
            return new BenevolentAncestorCreature(owner); 
        }
       
    }
    
    
    public class BenevolentAncestorCreature extends AbstractCreature implements TriggerAction {
        ArrayList<Effect> all_effects= new ArrayList<>(); //Denota tutti gli effetti
        ArrayList<Effect> tap_effects= new ArrayList<>(); //Effetti che possono essere usati in quel turno
        
        BenevolentAncestorCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    public boolean play() { 
                                        CardGame.instance.get_stack().add(this);
                                        return tap(); 
                                    }
                                    public void resolve() {
                                        int monster;
                                        int choose;
                                        do {
                                            System.out.println("Do you want increase the shield at a monster o at Player?");
                                            System.out.println("Increase the shield at Player press 0 else press 1: ");
                                            choose = CardGame.instance.get_scanner().nextInt();
                                        } while(choose != 0 && choose != 1);
                                        
                                        if (choose == 0) {
                                            CardGame.instance.get_current_player().setCurrent_shield(CardGame.instance.get_current_player().getCurrent_shield()+1);
                                            System.out.println("Now your shield has as value: " +CardGame.instance.get_current_player().getCurrent_shield());
                                        }
                                        if (choose == 1) {
                                            System.out.println("Creatures in your field, select a monster: ");
                                            for(int i=0; i<CardGame.instance.get_current_player().get_creatures().size(); i++) {
                                                System.out.println(i+ " " + CardGame.instance.get_current_player().get_creatures().get(i).name());                                            
                                            }
                                            try {
                                                monster = CardGame.instance.get_scanner().nextInt();
                                                CardGame.instance.get_current_player().get_creatures().get(monster).setCurrent_shield(CardGame.instance.get_current_player().get_creatures().get(monster).getCurrent_shield()+1);
                                                System.out.println("The creature " + CardGame.instance.get_current_player().get_creatures().get(monster).name()+ " has as shield value: " + CardGame.instance.get_current_player().get_creatures().get(monster).getCurrent_shield());
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("There aren't monster in the field");
                                            }
                                        }
                                        //CardGame.instance.get_triggers().register(Phases.END_FILTER, this);
                                    }
                                    
                                    public String toString() { 
                                        return "tap: Benevolent Ancestor fa qualcosa, ma non so cosa! Con questo messaggio sto tappando!"; 
                                    }
                                }
                ); 
        }
        public void execute() {
            
        }
        
        @Override
        public String name() { 
            return "Benevolent Ancestor"; 
        }
        
        public void attack_creature(Creature c, int dmg) {
            super.attack_creature(c, dmg);
        }
        public void attack_player(Player avversario, int dmg){
            super.attack_player(avversario, dmg);
        }
        public void defend(Creature c, int dmg) {
            super.defend(c, dmg);
        }
        
        @Override
        public int get_power() { 
            return 0; 
        }
        @Override
        public int get_toughness() { 
            return 4; 
        }

        @Override
        public List<Effect> effects() { 
            return all_effects; 
        }
        @Override
        public List<Effect> avaliable_effects() { 
            return (is_tapped)?tap_effects:all_effects; 
        }
        
        private int current_power = 0;
        
        private int current_toughness = 4;
        
        private int current_shield = 0;
        
        public int get_shield() { 
            return 0; 
        }
        
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
    
}
