/*
 * Creature/Creatura Attacco: 0, Difesa: 4
 * Questa creatura non può attaccare!
 * 
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
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
        return "This card can't attack!"; 
    }
    @Override
    public String toString() { 
        return name() + "[" + rule_text() +"]";
    }
    @Override
    public boolean isInstant() { // E' una creatura!
        return false; 
    }

    
    
    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c) { 
            super(p,c); 
        }
        protected Creature create_creature() { 
            return new BenevolentAncestorCreature(owner); 
        }
    }
    
    public Effect get_effect(Player p) { 
        return new BenevolentAncestorEffect(p,this); 
    }
    
    
    private class BenevolentAncestorCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        BenevolentAncestorCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    public boolean play() { 
                                        CardGame.instance.get_stack().add(this);
                                        return tap(); 
                                    }
                                    public void resolve() {}
                                    public String toString() { 
                                        return "tap: Benevolent Ancestor fa qualcosa, ma non so cosa"; 
                                    }
                                }
                ); 
        }
        
        public String name() { 
            return "Benevolent Ancestor"; 
        }
        
        public void attack() {}
        public void defend(Creature c) {}
        public int get_power() { 
            return 0; 
        }
        public int get_toughness() { 
            return 4; 
        }

        public List<Effect> effects() { 
            return all_effects; 
        }
        public List<Effect> avaliable_effects() { 
            return (is_tapped)?tap_effects:all_effects; 
        }
    }
    
}
