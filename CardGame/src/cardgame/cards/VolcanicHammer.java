

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
    
    private class VolcanicHammerEffect extends AbstractCardEffect
    {
         VolcanicHammerEffect(Player p,Card c){super(p,c);}
         
         public void resolve(){
         
             int target=0;
             int creatureSelected=0;
             int creatures=0;
             Scanner s=CardGame.instance.get_scanner();
             
             
             do
             {    
                System.out.println("Seleziona il bersaglio: 0 per l'avversario 1 per la creatura");
                target=s.nextInt();
             }while(target!=0 && target !=1);   
             
             if(target==0)
             {
                 CardGame.instance.get_current_adversary().inflict_damage(3);/*get_current_adversary e get_player public*/
             }
             
             else
             {
                 creatures=CardGame.instance.get_current_adversary().get_creatures().size();
                 
                 try
                 {
                     do
                     {
                         System.out.println("Select one creature for which apply the effect: 0 - " + (creatures -1));
                         creatureSelected=s.nextInt();
                     }while(creatureSelected<0);
                     
                      CardGame.instance.get_current_adversary().get_creatures().get(creatureSelected).inflict_damage(3);
                 }catch(IndexOutOfBoundsException e){System.out.println("Non ci sono carte in campo!");};    
         }
    }     
    
    }
    
        
}
