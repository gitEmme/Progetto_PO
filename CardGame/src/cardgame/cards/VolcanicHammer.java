

package cardgame.cards;
import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.Scanner;



public class VolcanicHammer implements Card {

    public Effect get_effect(Player owner){return new VolcanicHammerEffect(owner,this);}
    
    public String name(){return "Volcanic Hammer";}
    
    public String type(){return "Sorcery";}
    
    public String rule_text(){return "Volcanic Hammer deals 3 damage to any one creature or player";}
    
    public boolean isInstant(){return false;}
    
    public String toString() { return name() + "[" + rule_text() +"]";}
    
    private class VolcanicHammerEffect extends AbstractCardEffect {
        VolcanicHammerEffect(Player p,Card c) {
             super(p,c);
        }
         
        public void resolve(){
         
            int target=0;
            int creatureSelected=0;
            int creatures=0;
            Scanner s=CardGame.instance.get_scanner();
             
            do {    
                System.out.println("Seleziona il bersaglio: 0 per l'avversario 1 per la creatura");
                target=s.nextInt();
            }while(target!=0 && target !=1);   
             
            if(target==0) {
                int danno = 3 - CardGame.instance.get_current_adversary().getCurrent_shield();
                if (danno < 0) {
                    CardGame.instance.get_current_adversary().inflict_damage(0);
                    System.out.println("Your creature has: " +CardGame.instance.get_current_adversary().get_life());
                }
                else {
                    CardGame.instance.get_current_adversary().inflict_damage(danno);
                    System.out.println("Your creature has: " +CardGame.instance.get_current_adversary().get_life());
                    
                }

            }
            else {
                creatures=CardGame.instance.get_current_adversary().get_creatures().size();
                 
    for(int i=0; i<creatures; i++) {
        System.out.println("Creature in the opponent's field:" + " " + i + CardGame.instance.get_current_adversary().get_creatures().get(i).name() + "Creature attack= " + " " +CardGame.instance.get_current_adversary().get_creatures().get(i).get_power() + "Creature toughness= " + " " +CardGame.instance.get_current_adversary().get_creatures().get(i).get_toughness());
    }


    try {
        do {
            System.out.println("Select one creature for which apply the effect: 0 - " + (creatures -1));
            creatureSelected=s.nextInt();
            }while(creatureSelected<0);
        int danno = 0;
        danno = 3 - CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).getCurrent_shield();
        if (danno < 0) {
            CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).inflict_damage(0);
            System.out.println("Your creature has: " +CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).get_toughness());
        }
        else {
            CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).inflict_damage(danno);
            System.out.println("Your creature has: " +CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).get_toughness());
        }
    CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).inflict_damage(3);
    }catch(IndexOutOfBoundsException e){System.out.println("Non ci sono carte in campo!");};    
            }
        }        

        @Override
        public void setTarget() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}