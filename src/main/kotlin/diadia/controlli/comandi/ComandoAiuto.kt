package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando
import diadia.util.getAbsolutePath
import diadia.util.getClassesInPackage

class ComandoAiuto(): AbstractComando() {
    override fun setParametro(p: String) {
        parametro =p
    }

    override fun esegui(partita: Partita): String {
        return when (getParametro()) {
            "aiuto" -> "Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni ${setDiComandi()}"
            "" -> setDiComandi()
            "vai" -> "Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]"
            "fine" -> "Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine"
            "prendi" -> "Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere"
            "posa" -> "Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere"
            "info" -> "Il comando info ti permette di ricevere informazioni sulla stanza in qui ti trovi o sulla tua borsa con tutti gli oggetti con i relativi pesi\nsi scriva info e una di queste opzioni ${ComandoInfo.listaDiInfo()}"
            else -> "non esiste il comando:\n${getParametro()}"
        }
    }
    override fun getNome()="ComandoAiuto"
    private fun setDiComandi():String{
        val fine=mutableSetOf<String>()
        val inizio=getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/controlli/comandi"))
        for(a in inizio){
            if(a != "ComandoNonValido")
            fine.add(a.substringAfter("Comando"))
        }
        return fine.toString()
    }
}