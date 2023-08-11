package diadia

import diadia.config.leggiDaFileJson
import diadia.console.IO
import diadia.console.IOConsole
import diadia.controlli.gestore.AbstractComando
import diadia.controlli.gestore.FabbricaDiComandi
import diadia.controlli.gestore.FabbricaDiComandiRiflessiva
import diadia.util.getAbsolutePath
import java.io.File

class DiaDia(io: IO) {
    private val partita: Partita = Partita()
    private val console: IOConsole= io as IOConsole
    private val fabbrica: FabbricaDiComandi= FabbricaDiComandiRiflessiva()
     lateinit var comandoCostruito: AbstractComando
    fun gioca(){
        console.mostraMessaggio(MessaggioDiBenvenuto)
        var istruzione:String?
        do {
            istruzione=console.leggiRiga()
            comandoCostruito = fabbrica.costruisciComando(istruzione)
        }while (!processaIstruzione(comandoCostruito, console) && !partita.isFinita())
        if (partita.getPlayer().getCFU() == 0) {
            console.mostraMessaggio("\nOh no hai perso mi dispiace")
        }
        console.mostraMessaggio("Grazie di aver giocato!")
    }
    private fun processaIstruzione(Comando:AbstractComando?,console:IOConsole):Boolean {
        val stampa = Comando?.esegui(partita)
        when (stampa) {
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
        if (stampa != null) {
            console.mostraMessaggio(stampa)
        }
            return false

    }
    companion object {
        val config= leggiDaFileJson()
        val MessaggioDiBenvenuto= File(getAbsolutePath(config!!.diaDiaConfig.messaggioDiBenvenuto)).readText()
    }

    private fun CambioParametro(console: IOConsole,stampa:String): Boolean {
        var parametro: String?
        do {
            console.mostraMessaggio(stampa)
            parametro = console.leggiRiga()
        } while (parametro.isNullOrEmpty())
        comandoCostruito.setParametro(parametro)
        return false
    }
}

