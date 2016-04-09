package cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class DefaultCombatPhase implements Phase {
    private final ArrayList<Integer> attaccanti = new ArrayList<>();
    private final ArrayList<Integer> difensori = new ArrayList<>();
    
    public void execute() {
        Player current_player = CardGame.instance.get_current_player();
        Player current_adversary = CardGame.instance.get_current_adversary();
        int response_player_idx = (CardGame.instance.get_player(0) == current_player)?1:0;
        int current_player_idx = (CardGame.instance.get_player(0) == current_adversary)?1:0;
        
        System.out.println(current_player.get_name() + ": combat phase");
        
        CardGame.instance.get_triggers().trigger(Phases.COMBAT_FILTER);
        // TODO combat
        
        combat_phase(response_player_idx, current_player_idx);
    }
    
    // GIOCA ISTANTANEE, PRIMA DIFENSORE POI ATTACCANTE
    public void giocaIstanaeaCombatPhase_Atk(int response_player_idx){
        int number_passes=0;
        while (number_passes<2) {
            if (play_available_effect_CombatPhase(CardGame.instance.get_player(response_player_idx)))
                number_passes=0;
            else ++number_passes;
            
            response_player_idx = (response_player_idx+1)%2;
        }
    }
    
    // GIOCA INSTANTANEE, PRIMA ATTACCANTE POI DIFENSORE
    public void giocaIstanaeaCombatPhase_Def(int current_player_idx){
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
    private void combat_phase(int response_player_idx, int current_player_idx){

        //collect and display available monster in the field
        if (CardGame.instance.get_current_player().get_creatures().isEmpty()){
            System.out.println("no creatures on the field, " + CardGame.instance.get_current_player().get_name() + " can't attack");
        }
        else{
            dichiara_Atk(CardGame.instance.get_current_player(), attaccanti);
        }
        giocaIstanaeaCombatPhase_Atk(response_player_idx);
        if(CardGame.instance.get_current_adversary().get_creatures().isEmpty())    
            System.out.println("no creatures on the field, " + CardGame.instance.get_current_adversary().get_name() + " can't defend");
        else{
            dichiara_Def(CardGame.instance.get_current_adversary(), difensori);
        }
        giocaIstanaeaCombatPhase_Def(current_player_idx);
        
        if(!attaccanti.isEmpty()){
            for(int i=attaccanti.size()-1; i>=0; i--){
                int current_power = CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).get_power();
                int initial_currentpower = current_power;
                int danno_difensori = 0;
                while(current_power > 0){
                    if(difensori.isEmpty() && initial_currentpower == current_power){
                        int real_dmg = 0;
                        if(current_power > CardGame.instance.get_current_adversary().getCurrent_shield())
                            real_dmg = current_power - CardGame.instance.get_current_adversary().getCurrent_shield();
                        System.out.println(CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).name() + " attaccando ha inflitto " + real_dmg + " danni al giocatore " + CardGame.instance.get_current_adversary().get_name());
                        CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).attack_player(CardGame.instance.get_current_adversary(), current_power);
                        current_power = 0;
                    }
                    else{
                        danno_difensori = danno_difensori + CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)).get_power();
                        int danno = current_power;
                        current_power = current_power - (CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)).getCurrent_toughness() + CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)).getCurrent_shield());
                        if(danno >= (CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)).getCurrent_toughness() + CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)).getCurrent_shield())){
                            int pos = difensori.get(0);
                            difensori.remove(0);
                            for(int aggiusta=0; aggiusta<difensori.size(); aggiusta++){
                                if(difensori.get(aggiusta) > pos)
                                    difensori.set(aggiusta, difensori.get(aggiusta)-1);
                            }
                            if(difensori.isEmpty())
                                current_power = 0;
                            CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).attack_creature(CardGame.instance.get_current_adversary().get_creatures().get(pos), danno);
                        }
                        else
                            CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).attack_creature(CardGame.instance.get_current_adversary().get_creatures().get(difensori.get(0)), danno);
                    }
                    if(current_power <= 0 && danno_difensori > 0){
                        int real_def_dmg = 0;
                        if(danno_difensori > CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_shield()){
                            real_def_dmg = danno_difensori - CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_shield();
                            if(real_def_dmg <= CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_toughness())
                                System.out.println("i mostri difensori difendendo hanno inflitto " + real_def_dmg + " danni al mostro " + CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).name());
                            else{
                                real_def_dmg = CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_toughness();
                                System.out.println("i mostri difensori difendendo hanno inflitto " + real_def_dmg + " danni al mostro " + CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).name());
                            }
                        }
                        else
                            System.out.println("i mostri difensori difendendo hanno inflitto " + real_def_dmg + " danni al mostro " + CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).name());
                        if(danno_difensori >= (CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_toughness() + CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).getCurrent_shield())){
                            int pos = attaccanti.get(i);
                            for(int aggiusta_atk=0; aggiusta_atk<attaccanti.size(); aggiusta_atk++){
                                if(attaccanti.get(aggiusta_atk) > pos)
                                    attaccanti.set(aggiusta_atk, attaccanti.get(aggiusta_atk)-1);
                            }
                        }
                        CardGame.instance.get_current_player().get_creatures().get(attaccanti.get(i)).inflict_damage(danno_difensori);
                    }
                }
            }
        }
        attaccanti.clear();
        difensori.clear();
    }

    // DICHIARAZIONE ATTACCANTI
    private boolean dichiara_Atk(Player current_player, ArrayList<Integer> attaccanti){
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
                    if(CardGame.instance.get_current_player().get_creatures().get(idx).getCurrent_power() <= 0)
                        System.out.println("il mostro " + current_player.get_creatures().get(idx).name() + " ha 0 o meno punti attacco, non può attaccare!");
                    else{
                        System.out.println("il mostro " + current_player.get_creatures().get(idx).name() + " ha dichiarato un'azione di attacco");
                        current_player.get_creatures().get(idx).tap();
                        attaccanti.add(0, idx);
                    }
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
    private boolean dichiara_Def(Player current_adversary, ArrayList<Integer> difensori){
        Scanner reader = CardGame.instance.get_scanner();
        
        if(!CardGame.instance.get_current_adversary().get_creatures().isEmpty()){
            boolean alltapped = true;
            boolean pass = false;
            
            for(int x = 0; x < CardGame.instance.get_current_adversary().get_creatures().size(); x++){
                if(!CardGame.instance.get_current_adversary().get_creatures().get(x).isTapped()){
                    alltapped = false;
                    if(!alltapped)
                        break;
                }
            }
            if(alltapped)
                System.out.println(current_adversary.get_name() + " tutti i tuoi mostri sono tappati non puoi difenderti");
            else{
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
                        difensori.add(idx);
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
            }
            return true;
        }
        return false;
    }
}
