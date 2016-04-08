

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

public class AggressiveUrge implements Card {
    
    public Effect get_effect(Player p) {return new AggressiveUrgeEffect(p, this);}
    
    public String name() {return "Aggressive Urge";}
    
    public String type() {return "Instant";}
    
    public String rule_text() {return "The power of the wild,concentrated in a single charge";}
    
    public boolean isInstant() {return true;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class AggressiveUrgeEffect extends AbstractCardEffect implements TriggerAction {
        
        private Player owner;/* creo la variabile giocatore per poter accedere ai metodi*/
        private Creature old,decorated;/*creo le variabili old e decorated di tipo creatura per indicare rispettivamente la vecchia e la nuova (decorata) creatura*/
        public AggressiveUrgeEffect(Player p,Card c) {
            super(p,c);
            owner = p;
        }
                
        public void resolve(){
        int nCreatures = owner.get_creatures().size();/*memorizzo il numero di creature nel campo*/
        int creatureSelected=0;
        int i;

    for(i=0;i<nCreatures;i++) {

        System.out.println("Creature in the player field:" + " " + i + owner.get_creatures().get(i).name() + "Creature attack= " + " " + owner.get_creatures().get(i).get_power() + "Creature toughness= " + " " + owner.get_creatures().get(i).get_toughness());
    }


    try {    
        /*System.out.println("nCreatures = " +nCreatures);*/
        do {    
            System.out.println("Select one creature for which apply the effect: 0 - " + (nCreatures -1));
            Scanner s = CardGame.instance.get_scanner();/*seleziono quale creatura applicare l'effetto impostare public get_scanner*/
            creatureSelected = s.nextInt();
            } while(creatureSelected<0);    
        
        old = owner.get_creatures().remove(creatureSelected);/*salvo la vecchia creatura e la rimuovo dal campo*/
        decorated = new AggressiveUrgeDecorator(old);/*creo la nuova creatura da modificare(decorare)*/
        owner.get_creatures().add(decorated);/*aggiungo la creatura modificata al campo*/
        System.out.println("Creature name" + decorated + "Power and toughness: " + decorated.get_power()  + " " +  decorated.get_toughness() );
        CardGame.instance.get_triggers().register(Phases.END_FILTER, this);/*seleziono il turno e se finisce eseguo il trigger (execute), end_filter impostato a public*/
    }
    catch(IndexOutOfBoundsException e) {    
        System.out.println("Non ci sono carte in campo!");
    }  
        
        
        } 

        @Override
        public void execute() {/*viene eseguito se e solo se sono alla fine del turno*/
            if(owner.get_creatures().contains(decorated)){/*controllo se la creatura decorata è ancora presente nel campo(non è stata ancora distrutta da altre creature)*/
                owner.get_creatures().remove(decorated);/*la rimuovo*/
                owner.get_creatures().add(old);/*rimetto quella originale*/
            }
        }
    }
}
