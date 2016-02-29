import java.util.*;



public class Giocatore {
    String nome;
    int energia = 10;
    int turno = 0;
    Mazzo mazzoGiocatore;
    Mano manoGiocatore;
    Field campoGiocatore;
    /* Inserimento al nome utente */
    public Giocatore(String nome, int energia, Mazzo mazzoGiocatore, Mano manoGiocatore, Field campoGiocatore) {
        this.nome = nome;
        this.energia = energia;
        this.mazzoGiocatore = mazzoGiocatore;
        this.manoGiocatore = manoGiocatore;
        this.campoGiocatore = campoGiocatore;

    }

}

public class Turno(Giocatore g) {
     //Draw phase(controlla mazzo con isEmpty)
       public void drawPhase() {
           if (g.turno == 0) {
               int i = 0;
               for(i; i<5; i++) {
                Carta temp = new Carta();
                temp = g.mazzo; /* assegnamento errato */
                g.mazzo.remove();
                g.mano.add(temp);
                temp.remove(); /* metodo ancora da definire */
                }
           } else {
               Carta temp = new Carta();
               temp = g.mazzo; /* assegnamento errato */
               g.mazzo.remove();
               g.mano.add(temp);
               temp.remove(); /* metodo ancora da definire */
           }
           if (g.mano.size() > 7) {
               Scanner console = new Scanner(System.in); // standard input della posizione della carta da rimuovere
               int scelta = console.nextInt;
               mano.remove(scelta); //rimuovo carta alla posizione scelta
           }
       }
       public void untapPhase() {
           for(int i=0; i < g.campoGiocatore.size(); i++)
            g.campoGiocatore(i).tapped = false;
       }

       //Combat Phase (Successivamente)
       public void mainPhase() {
           int numeroGiocata = 0;
           int finegiocata = 0;
           while(numeroGiocata < g.mano.size() || finegiocata == 0) {
               Scanner console = new Scanner(System.in); // standard input della posizione della carta da rimuovere
               int cartaGiocata = console.nextInt;
               g.campoGiocatore.add(g.mano(cartaGiocata));
               g.mano.remove(CartaGiocata);
               /* puo essere necessario l'utilizzo di temp */
           }

       }
       public void endPhase() {
       		turno.g++;


       }
}

public class Carta {

	String nome; //Nome carta
	public void effettoCarta() {

	}
}

public class Mostro extends Carta  {			// da fare get e set per attacco e difesa
	boolean tapped = true;
	private int attacco;
	private int difesa;
	public Mostro(String nome, int attacco, int difesa) {
		this.nome = nome;
		this.attacco = attacco;
		this.difesa = difesa;
	}
	public void effettoCarta() {

	}

}

public class Stregoneria extends Carta  {
	boolean tapped = true;
	public Stregoneria(String nome) {
		this.nome = nome;
	}
	public void effettoCarta() {

	}

}

public class Istantanea extends Carta  {
	public Istantanea(String nome) {
		this.nome = nome;
	}
	public void effettoCarta() {

	}
}

public class Effetti {
	private void buffMonster() {

	// FARE METODO EQUALS E SELEZIONARE MOSTRO DAL CAMPO E PASSARLO COME PARAMETRO AL METODO BUFFMOSTRO
	// eseguire metodo per aumentare attacco e difesa tramite get e set dell'oggetto mostro
	}
}

// Possibile classe STACK che contiene tutti gli effetti che devono essere risolti in un ordine, da stabilire e in determinate
// fasi del gioco a seconda della carta

public class Mazzo {

       ArrayList<Carta> mazzo = new ArrayList<>(); //organizziamo mazzo in arraylist
       Collections.shuffle(mazzo); //mescola il mazzo(arraylist)
       public addCard(Carta carta){
           mazzo.add(carta);
       }
       public removeCard(Carta carta){
           mazzo.remove(carta);
       }
}

public class Field {
    ArrayList<Carta> campo = new ArrayList<>();

}

public class Mano {
    ArrayList<Carta> mano = new ArrayList<>(); //organizziamo mano in arraylist
    public addCard(Carta carta){
        mano.add(carta);
    }
    public removeCard(Carta carta){
        mano.remove(carta);
    }
}

public class Strategia {
    /* Gestione scelte giocatore */

}


public static void main {
    String nome1 = "Giocatore1";
    String nome2 = "Giocatore2";
    Mazzo mazzo1 = new Mazzo();
    Mazzo mazzo2 = new Mazzo();
    Mano mano1 = new Mano();
    Mano mano2 = new Mano();

     //Field campo1 = new Field(); da definire i campi
    //Field campo2 = new Field();  da definire i campi

    Giocatore giocatore1 = new Giocatore1(nome1, 10, mazzo1, mano1, campo1);
    Giocatore giocatore2 = new Giocatore2(nome2, 10, mazzo2, mano2, campo2);
    Mostro mostro1 = new Mostro("Alberto", 5, 3);
    Stregoneria stregoneria1 = new Stregoneria("Potenziamento");




    // PER LA PROSSIMA VOLTA: CREARE CICLAGGIO TURNI GIOCATORI
}



public interface command e {
    void invoke();

}
