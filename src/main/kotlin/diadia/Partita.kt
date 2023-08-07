package diadia

import diadia.ambienti.labirinto.Labirinto
import diadia.ambienti.stanze.Stanza
import diadia.giocatore.Giocatore


class Partita() {
    private val lab= Labirinto()
    private var player=Giocatore()
    private var finita=false
    private var stanzaCorrente=lab.getEntrata()


    /**
     * Restituisce la stanza vincente
     * @return stanza di uscita dal labirinto
     */
    fun getUscita()= lab.getUscita()


    /**
     * imposta la stanza in cui ci troviamo dopo ogni spostamento
     * @param stanzaCorrente
     */
    fun setStanzaCorrente(stanzaCorrente: Stanza) {
        this.stanzaCorrente = stanzaCorrente
    }

    /**
     * mi restiruisce la stanza in cui si trova il giocatore
     * @return stanza in cui si trova il giocatore
     */
    fun getStanzaCorrente()=stanzaCorrente


    /**
     * Restituisce vero se e solo se la partita e' stata vinta
     * @return vero se partita vinta
     */
    fun vinta()=getStanzaCorrente() === this.lab.getUscita()

    /**
     * Restituisce vero se e solo se la partita e' finita
     * @return vero se partita finita
     */
    fun isFinita()=finita || vinta() || player.getCFU() == 0


    /**
     * Imposta la partita come finita
     */
    fun setFinita() {
        this.finita = true
    }

    fun getLabirinto()=this.lab

    /**
     * Restituisce il giocatore
     * @return Giocatore della partita
     */
    fun getPlayer()=this.player

    fun setPlayer(Player: Giocatore) {
        this.player = Player
    }
}