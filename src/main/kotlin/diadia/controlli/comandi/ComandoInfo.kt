package diadia.controlli.comandi

import diadia.Partita
import diadia.controlli.gestore.AbstractComando
import diadia.util.getAbsolutePath
import diadia.util.getClassesInPackage
import kotlin.reflect.full.primaryConstructor

class ComandoInfo: AbstractComando() {
    companion object{
        fun listaDiInfo(): Set<String> {
            val inizio=getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/controlli/comandi/comandiDaInfo"))
            val fine=mutableSetOf<String>()
            for(a in inizio){
                    fine.add(a.substringAfter("ComandoInfo"))
            }
            return fine
        }
        }

    override fun setParametro(p: String) {
        parametro =p
    }

    override fun esegui(partita: Partita): String {
        if(getParametro()=="") return "Su cosa vuoi avere le informazioni?"
        return try{
            val base="diadia.controlli.comandi.comandiDaInfo.${getNome()}"
            val nome="${getParametro().first().uppercase()}${getParametro().substring(1).lowercase()}"
            val comando=Class.forName("$base$nome").kotlin.primaryConstructor?.call() as AbstractComando
            comando.esegui(partita)
        }catch (e:Exception){
            "non alcuna info su:\n${getParametro()}"
        }

    }
    override fun getNome()="ComandoInfo"
}