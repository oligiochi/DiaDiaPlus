package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoInfo: AbstractComando() {
    override fun setParametro(p: String) {
        parametro =p
    }

    override fun esegui(partita: Partita): String {
        return when (getParametro()) {
            "" -> "Su cosa vuoi avere le informazioni?"
            "borsa" -> partita.getPlayer().getBorsa().toString()
            "stanza" -> partita.getStanzaCorrente().getDescrizione()
            "giocatore" -> partita.getPlayer().getDescrizione()
            else -> "non esiste il comando:\n${getParametro()}"
        }
    }
    override fun getNome()="ComandoInfo"
}