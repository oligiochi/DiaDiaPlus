package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoRegala: AbstractComando() {
    override fun esegui(partita: Partita) =
        partita.getPlayer().getBorsa().getAttrezzo(getParametro())?.let { partita.getStanzaCorrente().getPersonaggio().riceviRegalo(it,partita) }.toString()
    override fun getNome() = "ComandoRegala"
    override fun setParametro(p: String) {
        parametro =p
    }
}