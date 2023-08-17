package diadia.controlli.comandi.comandiDaInfo

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoInfoGiocatore: AbstractComando() {
    override fun esegui(partita: Partita) = partita.getPlayer().getDescrizione()
    override fun getNome() = "ComandoInfoGiocatore"
}