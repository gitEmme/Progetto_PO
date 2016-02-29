# Progetto_PO
Progetto PO realizzazione gioco carte magic


Progetto di programmazione a Oggetti
Docente: Andrea Torsello

Assignment 1

Gruppo 15:
-	Alberto Galvan
-	Francesco Benetello
-	Rachele Marin
-	Jacopo Favaro
-	Alessandro Zanolla

Per la realizzazione del progetto del corso di Programmazione a Oggetti, il quale prevede la creazione di un gioco di carte simile a Magic, il nostro gruppo ha definito la seguente serie di oggetti(classi):

•	Main: Questa classe gestirà il programma(gioco) e si occuperà del funzionamento, esecuzione e chiusura di quest’ultimo.

•	Giocatore: E’ la classe che si occuperà degli attributi dei Giocatori quali Nome, Energia(e del suo variare nel corso della partita), Mazzo e Mano.

•	Turno: Per la realizzazione del turno abbiamo pensato di suddividere tutto in ulteriori sottofasi:
1.	Draw Phase: Durante questa fase il Giocatore dovrà pescare una carta dal mazzo, tuttavia, nel caso in cui questa condizione venisse a mancare(in quanto il mazzo è terminato), la partita terminerà immediatamente concedendo la vittoria al giocatore avversario. Sarà presente inoltre un controllo sul numero di carte nella mano del giocatore che esegue la draw phase, in quanto, nel caso in cui la mano di questo superasse il numero di carte previste dal regolamento (ovvero 7), la carta pescata dovrà essere eliminata.
2.	Untap Phase: Innanzitutto introduciamo stabilendo che vi saranno due possibili stati per le carte, “tapped” e “untapped”, nel caso in cui una carta venga considerata “untapped” questa potrà eseguire un’azione per questo turno (come per esempio attaccare l’avversario o spostarsi in difesa), mentre una carta sarà considerata “tapped” in seguito all’esecuzione di una delle sue possibili azioni. All’inizio di questa fase vi sarà quindi un “untapping” per tutte le carte del giocatore già presenti nel campo in modo da permettere loro di eseguire un’azione in questo turno. Successivamente le carte saranno spostate nello stato di “tapped” dalla fase all’avvenire delle seguenti condizioni:
a.	La carte esegue un’azione.
b.	Termina la untap phase.
3.	Combat Phase: Sarà sviluppata nel prossimo assignment.
4.	Main Phase: E’ la fase in cui il giocatore può giocare le sue magie(Stregonerie) e le sue creature(che verranno immediatamente “tappate” per questo turno).
5.	End Phase: Tramite questa fase avremmo la fine del turno del giocatore corrente e l’inizio del turno del giocatore avversario.

•	Carta: Questo oggetto definirà le 3 tipologie di carta presenti nel gioco:
1.	Creatura: Una creatura giocata e posizionata sul campo potrà assumere due posizioni, quella di attacco o quella di difesa. Quando una creatura viene giocata nella “Main Phase” non potrà eseguire nessun altra azione per questo turno in quanto “tappata”, ma, nel turno successivo durante la “Untap Phase” questa potrà eseguire un’azione come attaccare o spostarsi in difesa.
2.	Stregoneria: Queste carte possono avere molteplici effetti, una carta stregoneria può infatti per esempio fare danno all’Avversario e/o alle sue creature e in alcuni casi possono anche arrivare a cambiare le regole del gioco.
3.	Istantanea: Hanno un effetto istantaneo, può quindi venire giocata in qualsiasi momento anche se non è il proprio turno.
Includeremo inoltre vari metodi per la rimozione delle carte all’avvenire di determinate condizioni, quali per esempio: la morte di una creatura, cessazione dell’effetto di una carta stregoneria e/o istantanea, mano del giocatore piena, ecc.

•	Mazzo: Prevediamo per il “mazzo” una serie di responsabilità:
1.	Un metodo per il mescolamento delle carte ad inizio partita.
2.	Varie metodologie per la gestione dello stack delle carte una volta che il mazzo è stato mescolato.

•	Campo di Gioco(Field): Questa classe gestirà il collocamento delle carte all’interno dell’area di gioco e se queste possono essere giocate al suo interno o meno.

•	Mano: Come per il mazzo anche la mano avrà delle Metodologie per la gestione dello stack.

•	Strategia: Questa varierà da giocatore a giocatore e sarà condizionata essenzialmente dalla mano di questi ultimi e dalle possibili azioni che potranno intraprendere.
