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
     private lateinit var comandoCostruito: AbstractComando
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
    private fun processaIstruzione(comando:AbstractComando, console:IOConsole):Boolean {
        var stampa = comando.esegui(partita)
        when (stampa) {
            "Che attrezzo vuoi posare?" -> {
                stampa=cambioParametro(console, stampa)
            }

            "Che attrezzo vuoi prendere?" -> {
                stampa=cambioParametro(console, stampa)
            }

            "Dove vuoi andare?" -> {
                stampa=cambioParametro(console, stampa)
            }
            "Su cosa vuoi avere le informazioni?"->{
                stampa=cambioParametro(console, stampa)
            }
            "Grazie Di Aver Giocato" -> return true
        }
        if (partita.isFinita()) {
            console.mostraMessaggio("WOW HAI VINTO!")
            return true
        }
        console.mostraMessaggio(stampa)
            return false

    }
    companion object {
        val config= leggiDaFileJson()
        val MessaggioDiBenvenuto= File(getAbsolutePath(config!!.diaDiaConfig.messaggioDiBenvenuto)).readText()
    }

    private fun cambioParametro(console: IOConsole, stampa:String): String {
        var parametro: String?
        do {
            console.mostraMessaggio(stampa)
            parametro = console.leggiRiga()
        } while (parametro.isNullOrEmpty())
        comandoCostruito.setParametro(parametro)
        return comandoCostruito.esegui(partita)
    }
}

