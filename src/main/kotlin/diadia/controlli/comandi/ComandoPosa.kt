package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoPosa: AbstractComando() {
    override fun setParametro(p: String) {
        parametro =p
    }

    override fun esegui(partita: Partita): String {
        val output = partita.getPlayer().prendiAttrezzo(getParametro(), partita.getStanzaCorrente())
        val bool = output === "${getParametro()} non esiste nella stanza"
        val bool2 = output === "Che attrezzo vuoi prendere?"
        val bool3= output==="La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra"
        return if (!(bool || bool2 || bool3)) {
            partita.getPlayer().setCFU(partita.getPlayer().getCFU() - 1)
            output.plus(partita.getPlayer().getDescrizione().trimIndent())
        } else output
    }
    override fun getNome()="ComandoPosa"
}