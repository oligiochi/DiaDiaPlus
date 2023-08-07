package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando

class ComandoNonValido : AbstractComando() {
    override fun esegui(partita: Partita) = "Comando sconosciuto"
    override fun getNome() = "ComandoNonValido"
}