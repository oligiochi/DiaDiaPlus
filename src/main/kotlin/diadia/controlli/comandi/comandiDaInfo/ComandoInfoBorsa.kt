package diadia.controlli.comandi.comandiDaInfo

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoInfoBorsa: AbstractComando() {
    override fun esegui(partita: Partita) = partita.getPlayer().getBorsa().toString()
    override fun getNome() = "ComandoInfoBorsa"
}