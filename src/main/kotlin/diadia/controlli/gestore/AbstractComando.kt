package diadia.controlli.gestore

import diadia.Partita

abstract class AbstractComando {
    companion object {
        var parametro: String = ""
    }
    open fun setParametro(p: String) {
    }
    fun getParametro() = parametro

    abstract fun esegui(partita: Partita): String
    abstract fun getNome(): String
}