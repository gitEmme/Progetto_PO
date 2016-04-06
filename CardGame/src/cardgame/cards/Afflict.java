package cardgame.cards;



import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.TriggerAction;
import java.util.Scanner;


public class Afflict implements Card{
    
    public Effect get_effect(Player p) {return new AfflictEffect(p, this);}
    public String name() {return "Afflict";}
    public String type() {return "Instant";}
    public String rule_text() {return "One rarely notices a heartbeat, save when it is stolen";}
    public boolean isInstant() {return true;}
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    public void resolve() {}
    
    private class AfflictEffect extends AbstractCardEffect implements TriggerAction
    {
        private Player owner;
        private Creature old,decorated;
        public AfflictEffect(Player p,Card c) {
            super(p,c);
            owner = p;
        }
                
        public void resolve(){
        int nCreatures = owner.get_creatures().size();
        System.out.println("Creature in campo:" + nCreatures);
        if(nCreatures > 0){
            System.out.println("Select one creature for which apply the effect: 0-" + (nCreatures-1));
            Scanner s = CardGame.instance.get_scanner();
            int creatureSelected = s.nextInt();
            System.out.println("Creature attack=" + decorated.get_power());
            System.out.println("Creature toughness=" + decorated.get_toughness());
            
            old = owner.get_creatures().remove(creatureSelected);
            decorated = new AfflictDecorator(old);
            owner.get_creatures().add(decorated);
        
            CardGame.instance.get_triggers().register(Phases.END_FILTER, this);
        
        }
        } 

        @Override
        public void execute() {
            if(owner.get_creatures().contains(decorated)){
                owner.get_creatures().remove(decorated);
                owner.get_creatures().add(old);
            }
        }
    }
}   