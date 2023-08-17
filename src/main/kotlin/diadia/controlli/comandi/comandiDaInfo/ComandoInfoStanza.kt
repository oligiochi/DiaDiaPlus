package diadia.controlli.comandi.comandiDaInfo

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoInfoStanza: AbstractComando() {
    override fun esegui(partita: Partita) = partita.getStanzaCorrente().getDescrizione()
    override fun getNome() = "ComandoInfoStanza"
}