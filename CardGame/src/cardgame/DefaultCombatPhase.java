package cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class DefaultCombatPhase implements Phase {
    private final ArrayList<Creature> attaccanti = new ArrayList<>();
    private final ArrayList<Creature> difensori = new ArrayList<>();
    
    public List<Creature> get_Atk_creatures() {
        return attaccanti;
    }
    
    public void add_attaccante(Creature e) {
        attaccanti.add(e);
    }
    
    public void remove_Atk(Creature e) {
        attaccanti.remove(e);
    }
    
    public List<Creature> get_Def_creatures() {
        return difensori;
    }
    
    public void add_difensore(Creature e) { 
        difensori.add(e); 
    }
    
    public void remove_Def(Creature e) {
        difensori.remove(e);
    }
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        int response_player_idx = (CardGame.instance.get_player(0) == current_player)?1:0;
        
        System.out.println(current_player.get_name() + ": combat phase");
        
        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);
        // TODO combat
        
        
        
        combat_phase(current_player, response_player_idx);
    }
    
    public void giocaIstanaeaCombatPhase(Player current_player, int response_player_idx){
        int number_passes=0;
        while (number_passes<2) {
            if (play_available_effect_CombatPhase(CardGame.instance.get_player(response_player_idx)))
                number_passes=0;
            else ++number_passes;
            
            response_player_idx = (response_player_idx+1)%2;
        }
        
        CardGame.instance.get_stack().resolve();
    }
    
    // looks for all playable effects from cards in hand and creatures in play
    // and asks player for which one to play
    // includes creatures and sorceries only if is_main is true
    private boolean play_available_effect_CombatPhase(Player active_player) {
        //collect and display available effects...
        ArrayList<Effect> available_effects = new ArrayList<>();
        Scanner reader = CardGame.instance.get_scanner();

        //...cards first
        System.out.println(active_player.get_name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:active_player.get_hand() ) {
            if (c.isInstant() ) {
                available_effects.add( c.get_effect(active_player) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:active_player.get_creatures()) {
            for (Effect e:c.avaliable_effects()) {
                available_effects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=available_effects.size()) return false;

        available_effects.get(idx).play();
        return true;
    }
    
    private boolean combat_phase(Player active_player, int response_player_idx) {
        
        
        //collect and display available monster in the field
        if (active_player.get_creatures().isEmpty()){
            System.out.println("no creatures on the field, " + active_player.get_name() + " can't attack or defend");
            return false;
        }
        else{
            Scanner reader = CardGame.instance.get_scanner();
            boolean pass = false;
            
            while(!pass){
                System.out.println(active_player.get_name() + " select a monster for attack, 0 to pass");
                
                int i=0;
                //print monster in the field
                for ( Creature c:active_player.get_creatures()) {
                    if(!c.isTapped())
                        System.out.println(Integer.toString(i+1)+") " + c.name() +
                                " ["+ c.get_power() + "/" + c.get_toughness() + "]" );
                    ++i;
                }
                
                //user choice monster for attack
                int idx= reader.nextInt()-1;
                if (idx<0 || idx>=active_player.get_creatures().size()) return false;
                
                System.out.println("il mostro " + active_player.get_creatures().get(idx).name() + " ha dichiarato un'azione di attacco");
                active_player.get_creatures().get(idx).tap();
                attaccanti.add(active_player.get_creatures().get(idx));
            }
            
            
            //giocaIstanaeaCombatPhase(active_player, response_player_idx);
            //active_player.get_creatures().get(idx).attack();
            return true;
        }
    }  
}
