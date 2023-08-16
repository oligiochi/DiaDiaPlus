package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando


class ComandoVai: AbstractComando() {
    override fun esegui(partita: Partita): String {
        if(getParametro()==="") return "Dove vuoi andare?"
        val prossimaStanza= partita.getStanzaCorrente().getStanzaAdiacente(getParametro())
        return if (prossimaStanza == null)
            "Direzione inesistente\n${partita.getStanzaCorrente().getDescrizione()}"
        else {
            partita.setStanzaCorrente(prossimaStanza)
            partita.getPlayer().setCFU(partita.getPlayer().getCFU() - 1)
            "${partita.getPlayer().getDescrizione()}\n${partita.getStanzaCorrente().getDescrizione()}"
        }
    }


    override fun getNome()="ComandoVai"
    override fun setParametro(p: String) {
        parametro =p
    }
}