package diadia.controlli.gestore

import diadia.controlli.comandi.ComandoNonValido
import kotlin.reflect.full.primaryConstructor

class FabbricaDiComandiRiflessiva:FabbricaDiComandi {
    override fun costruisciComando(istruzione: String): AbstractComando {
        lateinit var comd:AbstractComando
        val istruzzioni=istruzione.lowercase().split("[\\W]+").toMutableList()
        val base="diadia.controlli.comandi.Comando"
        if(istruzzioni.isNotEmpty()) {
            if (istruzzioni.size < 2){
                istruzzioni.add("")
            }
        }else{
                istruzzioni.addAll(arrayOf("",""))
        }
        try {
            istruzzioni[1].replace(istruzzioni[1].first(),istruzzioni[1].first().uppercaseChar())
            comd=Class.forName("$base${istruzzioni[1]}").kotlin.primaryConstructor?.call() as AbstractComando
            comd.setParametro(istruzzioni[2])
        }catch (e:Exception){
            when(e){
                is IllegalAccessException ->println("CONTROLLA CHE ESISTA UN COSTRUTTORE NO-ARGS IN TUTTE LE CLASSI DI COMANDO E CHE SIA PUBLIC")
                is InstantiationException -> println("CONTROLLA CHE ESISTA UN COSTRUTTORE NO-ARGS IN TUTTE LE CLASSI DI COMANDO E CHE SIA PUBLIC")
                else->comd=ComandoNonValido()
            }
        }
        return comd
    }

}