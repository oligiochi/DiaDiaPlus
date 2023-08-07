package diadia

import diadia.console.IO

class DiaDia(io: IO) {
    /*private val partita: Partita
    private val console: IOConsole
    //private val fabbrica: FabbricaDiComandi

    init {
        partita = Partita()
        console = io as IOConsole
        //fabbrica = FabbricaDiComandiRiflessiva()
    }
    fun gioca(){
       // console.mostraMessaggio(DiaDia.MessaggioDiBenvenuto)
        var comandoCostruito: AbstractComando?=null
        var istruzione:String?
        do {
            istruzione=console.leggiRiga()
            //comandoCostruito = fabbrica.costruisciComando(istruzione)
        }while (!processaIstruzione(comandoCostruito, console) && !partita.isFinita())
        if (partita.getPlayer().getCFU() == 0) {
            console.mostraMessaggio("\nOh no hai perso mi dispiace")
        }
        console.mostraMessaggio("Grazie di aver giocato!")
    }
    private fun processaIstruzione(Comando:AbstractComando?,console:IOConsole):Boolean {
        when (val stampa = Comando?.esegui(partita)) {
            "Che attrezzo vuoi posare?" -> {
                return CambioParametro(console, stampa)
            }

            "Che attrezzo vuoi prendere?" -> {
                return CambioParametro(console, stampa)
            }

            "Dove vuoi andare?" -> {
                return CambioParametro(console, stampa)
            }
            "Su cosa vuoi avere le informazioni?"->{
                return CambioParametro(console, stampa)
            }
            "Grazie di aver giocato!" -> return true
        }
        if (partita.vinta()) {
            console.mostraMessaggio("WOW HAI VINTO!")
            return true
        }
            return false

    }
    companion object {
       // private const val MessaggioDiBenvenuto=
    }

    private fun CambioParametro(console: IOConsole,stampa:String): Boolean {
        var parametro: String?
        do {
            console.mostraMessaggio(stampa)
            parametro = console.leggiRiga()
        } while (parametro.isNullOrEmpty())
       // comandoCostruito.setParametro(parametro)
        return false
    }*/
}

