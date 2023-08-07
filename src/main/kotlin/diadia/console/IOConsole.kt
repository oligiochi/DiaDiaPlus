package diadia.console

import java.util.*

class IOConsole : IO {
    override fun mostraMessaggio(msg: String) {
        println(msg)
    }

    override fun leggiRiga(): String {
        val scannerDiLinee = Scanner(System.`in`)
        val riga = scannerDiLinee.nextLine()
        return riga
    }
}