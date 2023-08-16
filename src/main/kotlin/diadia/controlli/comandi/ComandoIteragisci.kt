package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoIteragisci: AbstractComando() {
    override fun esegui(partita: Partita) = partita.getStanzaCorrente().getPersonaggio()!!.agisci(partita)
    override fun getNome() = "ComandoIteragisci"
}