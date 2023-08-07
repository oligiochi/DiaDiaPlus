package diadia.ambienti.labirinto
import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni
import diadia.ambienti.stanze.Stanza
import diadia.util.getAbsolutePath
import diadia.util.getClassesInPackage
import java.io.File
import java.io.IOException
import java.io.LineNumberReader
import kotlin.reflect.full.primaryConstructor

class CaricatoreLabirinto(nomeFile:String) {
    private val mappaDiStanze= mutableMapOf<String,Stanza>()
    private val reader=LineNumberReader(File(getAbsolutePath(nomeFile)).reader())
    private var inizale:Stanza?=null
    private var finale:Stanza?=null
    companion object {
        const val STANZE_MARKER = "Stanze:"
        const val ATTREZZI_MARKER = "Attrezzi:"
        const val COLLEGAMENTI_MARKER = "Collegamenti:"
        const val OGGETTI_BLOCCANTI="OggettiSbloccanti:"
        const val DIREZZIONI_BLOCCATE="DirezzioniBloccate:"
        const val PERSONAGGI = "Personaggi:"
		val tipi= getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/ambienti/stanze"))
    }
    fun leggiEcreaStanze(){
        val stanze=reader.readLine().substringAfter(STANZE_MARKER).split(",")
        for(stanza in stanze){
            var s=stanza.split(" ")
            s=s.filter { a -> !a.equals("") }
            var className="StanzaNormale"
            when {
                tipi.any { ele -> ele in s } -> {
                    className = tipi.find { ele -> ele in s }.toString()
                }
            }
                try {
                    //className=className.plus(".kt")
                    val clazz="diadia.ambienti.stanze.$className"
                    val classKotlin = Class.forName(clazz).kotlin.primaryConstructor
                    if(classKotlin!=null) {
                        val instance = classKotlin.call(s.first())
                        mappaDiStanze[s.first()] = instance as Stanza
                    }
                    // Ora hai l'istanza della classe dinamica e puoi usarla come desideri
                } catch (e: ClassNotFoundException) {
                    println("Classe non trovata: ${e.message}")
                } catch (e: InstantiationException) {
                    println("Errore nell' istanziare la classe: ${e.message}")
                } catch (e: IllegalAccessException) {
                    println("Accesso illegale alla classe: ${e.message}")
                }
            if(s.contains("Inizio")){
                inizale=mappaDiStanze[s.first()]
            }
            if(s.contains("Fine")){
                finale=mappaDiStanze[s.first()]
            }
            analizzaOggettiSbloccanti(s,mappaDiStanze[s.first()])
            analizzaDirezioniBloccate(s,mappaDiStanze[s.first()])
         }
    }

    private fun analizzaDirezioniBloccate(s: List<String>, stanza: Stanza?) {
        if (s.contains("direzzioniBloccate:") && s.contains("StanzaBloccata")) {
            var i = s.indexOf("direzzioniBloccate:")
            while (i<s.size && s[i].first().isUpperCase()) {
                stanza?.addDirezzionibloccante(s[i])
                i++
            }
        }
    }

    private fun analizzaOggettiSbloccanti(s: List<String>, stanza: Stanza?) {
        if (s.contains("OggettiSbloccanti:") && (s.contains("StanzaBloccata") || s.contains("StanzaBuia"))) {
            var i = s.indexOf("OggettiSbloccanti:")
            while (i<s.size && s[i].first().isUpperCase()) {
                stanza?.addoggettisbloccante(Attrezzo(s[i], 0))
                i++
            }
        }
    }
    fun leggiEaggiungiAttrezzi(){
        val attrezzi=reader.readLine().substringAfter(ATTREZZI_MARKER).split(",")
        for(attrezzo in attrezzi){
            var a=attrezzo.split(" ")
            a=a.filter { at -> !at.equals("") }
            val stanza= mappaDiStanze[a.first()]
            var i=1
            var p=0
            while(i<a.size){
                val z=i+1
                if(isNumeric(a[z])){
                    p=a[z].toInt()
                }
                stanza?.addAttrezzo(Attrezzo(a[i],p))
                i += 2
            }
        }
    }
    private fun isNumeric(input: String): Boolean {
        return input.toLongOrNull() != null || input.toDoubleOrNull() != null
    }
    fun leggiEcreaCollegamenti(){
        val collegamenti=reader.readLine().substringAfter(COLLEGAMENTI_MARKER).split(",")
        for(collegamento in collegamenti){
            var c=collegamento.split(" ")
            c=c.filter { a -> !a.equals("") }
            doppiocollegamento(mappaDiStanze[c.first()],mappaDiStanze[c.last()],Direzioni.valueOf(c[1].uppercase()))
        }
    }
    private fun doppiocollegamento(prima:Stanza?, seconda:Stanza?, direzione: Direzioni){
        if (seconda != null && prima != null) {
            prima.impostaStanzaAdiacente(direzione.toString(),seconda)
            seconda.impostaStanzaAdiacente(direzione.getopposta().toString(),prima)
        }
    }
    fun carica(){
        try {
            leggiEcreaStanze()
            leggiEaggiungiAttrezzi()
            leggiEcreaCollegamenti()
        }finally {
            try {
                reader.close()
            }catch (e: IOException){
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }
    }
    fun getIniziale()=inizale
    fun getFinale()=finale
}