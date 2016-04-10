package cardgame;

// utility class to implementing behavior common to all effects, 
// namely placing it in the stack when played
public abstract class AbstractEffect implements Effect {
    
    private Player owner;
    
    public AbstractEffect (Player owner) {
        this.owner = owner;
    }
    
    public Player getTarget() {
        return owner;
    }
    
    public void setTarget (Player t) {
        owner = t;
    }
    
    public boolean play() { 
        CardGame.instance.get_stack().add(this);
        return true;
    }
    
}
