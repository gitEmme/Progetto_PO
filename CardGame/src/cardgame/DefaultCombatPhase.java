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
        Player current_adversary = CardGame.instance.get_current_adversary();
        int response_player_idx = (CardGame.instance.get_player(0) == current_player)?1:0;
        int current_player_idx = (CardGame.instance.get_player(1) == current_adversary)?1:0;
        
        System.out.println(current_player.get_name() + ": combat phase");
        
        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);
        // TODO combat
        
        combat_phase(current_player, current_adversary, response_player_idx, current_player_idx);
    }
    
    // GIOCA ISTANTANEE, PRIMA DIFENSORE POI ATTACCANTE
    public void giocaIstanaeaCombatPhase_Atk(Player current_player, int response_player_idx){
        int number_passes=0;
        while (number_passes<2) {
            if (play_available_effect_CombatPhase(CardGame.instance.get_player(response_player_idx)))
                number_passes=0;
            else ++number_passes;
            
            response_player_idx = (response_player_idx+1)%2;
        }
    }
    
    // GIOCA INSTANTANEE, PRIMA ATTACCANTE POI DIFENSORE
    public void giocaIstanaeaCombatPhase_Def(Player current_adversary, int current_player_idx){
        int number_passes=0;
        while (number_passes<2) {
            if (play_available_effect_CombatPhase(CardGame.instance.get_player(current_player_idx)))
                number_passes=0;
            else ++number_passes;
            
            current_player_idx = (current_player_idx+1)%2;
        }
        
        CardGame.instance.get_stack().resolve();
    }   
    
    // VISUALIZZA EFFETTI GIOCABILI PER LA COMBAT PHASE
    private boolean play_available_effect_CombatPhase(Player active_player) {
        ArrayList<Effect> available_effects = new ArrayList<>();
        Scanner reader = CardGame.instance.get_scanner();

        System.out.println(active_player.get_name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:active_player.get_hand() ) {
            if (c.isInstant() ) {
                available_effects.add( c.get_effect(active_player) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        for ( Creature c:active_player.get_creatures()) {
            for (Effect e:c.avaliable_effects()) {
                available_effects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=available_effects.size()) return false;

        available_effects.get(idx).play();
        return true;
    }
    
    // COMBAT PHASE
    private void combat_phase(Player current_player, Player current_adversary, int response_player_idx, int current_player_idx){
        
        
        //collect and display available monster in the field
        if (current_player.get_creatures().isEmpty()){
            System.out.println("no creatures on the field, " + current_player.get_name() + " can't attack");
        }
        else{
            dichiara_Atk(current_player);
        }
        giocaIstanaeaCombatPhase_Atk(current_player, response_player_idx);
        if( current_adversary.get_creatures().isEmpty())    
            System.out.println("no creatures on the field, " + current_player.get_name() + " can't defend");
        else{
            dichiara_Def(current_adversary);
        }
        giocaIstanaeaCombatPhase_Def(current_adversary, current_player_idx);
        
        if(!attaccanti.isEmpty()){
            for(int i=attaccanti.size()-1; i>=0; i--){
                int current_power = attaccanti.get(i).getCurrent_power();
                int initial_currentpower = current_power;
                int danno_difensori = 0;
                while(current_power > 0){
                    if(difensori.isEmpty() && initial_currentpower == current_power){
                        int real_dmg = 0;
                        if(current_power > current_adversary.getCurrent_shield())
                            real_dmg = current_power - current_adversary.getCurrent_shield();
                        System.out.println(attaccanti.get(i).name() + " attaccando ha inflitto " + real_dmg + " danni al giocatore " + current_adversary.get_name());
                        attaccanti.get(i).attack_player(current_adversary, current_power);
                        current_power = 0;
                    }
                    else{
                        danno_difensori = difensori.get(difensori.size()-1).getCurrent_power();
                        attaccanti.get(i).attack_creature(difensori.get(difensori.size()-1), current_power);
                        current_power = current_power - (difensori.get(difensori.size()-1).getCurrent_toughness() + difensori.get(difensori.size()-1).getCurrent_shield());
                    }
                    if(current_power <= 0){
                        System.out.println("i mostri difensori difendendo hanno inflitto " + danno_difensori + " danni al mostro " + attaccanti.get(i).name());
                        attaccanti.get(i).inflict_damage(danno_difensori);
                    }
                }
            }
        }
        for(int i=0; i<attaccanti.size(); i++){
            attaccanti.remove(i);
        }
        for(int i=0; i<difensori.size(); i++){
            difensori.remove(i);
        }
    }

    // DICHIARAZIONE ATTACCANTI
    private boolean dichiara_Atk(Player current_player){
        Scanner reader = CardGame.instance.get_scanner();
            
            if(!CardGame.instance.get_current_player().get_creatures().isEmpty()){
                boolean alltapped = false;
                boolean pass = false;
                
                while(!pass && !alltapped){
                    System.out.println(current_player.get_name() + " select a monster for attack, 0 to pass");
                    
                    int i=0;
                    //print monster in the field
                    for (Creature c:current_player.get_creatures()) {
                        if(!c.isTapped())
                            System.out.println(Integer.toString(i+1)+") " + c.name() +
                                    " ["+ c.get_power() + "/" + c.get_toughness() + "]" );
                        ++i;
                    }
                    
                    //user choice monster for attack
                    int idx= reader.nextInt()-1;
                    if (idx<0 || idx>=current_player.get_creatures().size()) return false;
                    
                    if(CardGame.instance.get_current_player().get_creatures().get(idx).isTapped())
                        System.out.println("il mostro " + current_player.get_creatures().get(idx).name() + " ha già dichiarato la sua azione di attacco");
                    else{
                        System.out.println("il mostro " + current_player.get_creatures().get(idx).name() + " ha dichiarato un'azione di attacco");
                        current_player.get_creatures().get(idx).tap();
                        attaccanti.add(current_player.get_creatures().get(idx));
                    }
                    
                    alltapped = true;
                    for(int z = 0; z < CardGame.instance.get_current_player().get_creatures().size(); z++){
                        if(!CardGame.instance.get_current_player().get_creatures().get(z).isTapped()){
                            alltapped = false;
                            if(!alltapped)
                                break;
                        }
                    }
                
                }
                return true;
            }
            return false;
    }
    
    // DICHIARAZIONE DIFENSORI
    private boolean dichiara_Def(Player current_adversary){
        Scanner reader = CardGame.instance.get_scanner();
            
            if(!CardGame.instance.get_current_adversary().get_creatures().isEmpty()){
                boolean alltapped = false;
                boolean pass = false;
                
                while(!pass && !alltapped){
                    System.out.println(current_adversary.get_name() + " select a monster for defend, 0 to pass");
                    
                    int i=0;
                    //print monster in the field
                    for (Creature c:current_adversary.get_creatures()) {
                        if(!c.isTapped())
                            System.out.println(Integer.toString(i+1)+") " + c.name() +
                                    " ["+ c.get_power() + "/" + c.get_toughness() + "]" );
                        ++i;
                    }
                    
                    //user choice monster for defend
                    int idx= reader.nextInt()-1;
                    if (idx<0 || idx>=current_adversary.get_creatures().size()) return false;
                    
                    if(CardGame.instance.get_current_adversary().get_creatures().get(idx).isTapped())
                        System.out.println("il mostro " + current_adversary.get_creatures().get(idx).name() + " ha già dichiarato la sua azione di difesa");
                    else{
                        System.out.println("il mostro " + current_adversary.get_creatures().get(idx).name() + " ha dichiarato un'azione di difesa");
                        current_adversary.get_creatures().get(idx).tap();
                        attaccanti.add(current_adversary.get_creatures().get(idx));
                    }
                    
                    alltapped = true;
                    for(int z = 0; z < CardGame.instance.get_current_adversary().get_creatures().size(); z++){
                        if(!CardGame.instance.get_current_adversary().get_creatures().get(z).isTapped()){
                            alltapped = false;
                            if(!alltapped)
                                break;
                        }
                    }
                
                }
                return true;
            }
            return false;
    }
}
