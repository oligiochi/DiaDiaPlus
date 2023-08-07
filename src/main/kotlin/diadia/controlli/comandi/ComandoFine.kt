package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoFine():AbstractComando() {
    override fun esegui(partita: Partita) = "Grazie Di Aver Giocato"
    override fun getNome() = "ComandoFine"
}