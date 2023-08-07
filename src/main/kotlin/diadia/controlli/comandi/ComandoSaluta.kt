package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoSaluta: AbstractComando() {
    override fun esegui(partita: Partita) = partita.getStanzaCorrente().getPersonaggio().saluta()
    override fun getNome() = "ComandoSaluta"
}