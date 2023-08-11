package diadia.controlli.gestore

interface FabbricaDiComandi {
    fun costruisciComando(istruzione:String): AbstractComando
}