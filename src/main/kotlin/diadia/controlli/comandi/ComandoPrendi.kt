package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoPrendi: AbstractComando() {
    override fun setParametro(p: String) {
        parametro =p
    }

    override fun esegui(partita: Partita): String {
        val output = partita.getPlayer().rimuoviAttrezzo(getParametro(), partita.getStanzaCorrente())
        val bool = output === "Ho toccato il fondo, ma ${getParametro()} non l'ho trovato"
        val bool2 = output === "Che attrezzo vuoi posare?"
        return if (!(bool || bool2)) {
            partita.getPlayer().setCFU(partita.getPlayer().getCFU() - 1)
            output.plus(partita.getPlayer().getDescrizione().trimIndent())
        } else output
    }
    override fun getNome()="ComandoPrendi"
}