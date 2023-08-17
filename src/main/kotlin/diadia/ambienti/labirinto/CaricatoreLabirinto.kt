package diadia.ambienti.labirinto
import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni
import diadia.ambienti.stanze.Stanza
import diadia.config.leggiDaFileJson
import diadia.util.getAbsolutePath
import diadia.util.getClassesInPackage
import java.io.File
import java.io.IOException
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

class CaricatoreLabirinto(nomeFile:String) {
    private val mappaDiStanze= mutableMapOf<String,Stanza>()
    private val reader=File(getAbsolutePath(nomeFile)).readLines()
    private val config= leggiDaFileJson()
    private var inizale:Stanza?=null
    private var finale:Stanza?=null
    companion object {
        const val STANZE_MARKER = "Stanze:"
        const val ATTREZZI_MARKER = "Attrezzi:"
        const val COLLEGAMENTI_MARKER = "Collegamenti:"
        const val OGGETTI_BLOCCANTI="OggettiSbloccanti:"
        const val DIREZZIONI_BLOCCATE="DirezioniBloccate:"
        const val PERSONAGGI_MARKER = "Personaggi:"
        const val PERSONAGGI_OGGETTI_MARKER = "OggettiPossedutiPersonaggi:"
        const val PERSONAGGI_OGGETTI_PREFERITI_MARKER="OggettiPreferitiPersonaggi:"
		val tipi= getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/ambienti/stanze"))
    }
    private fun leggiEcreaStanze(){
        val stanze= reader.find { it.startsWith(STANZE_MARKER) }?.substringAfter(STANZE_MARKER)?.split(",")
        if (stanze != null) {
            for(stanza in stanze){
                var s=stanza.split(" ")
                s=s.filter { a -> a != "" }
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
                analizzaOggettiSbloccanti(mappaDiStanze[s.first()])
                analizzaDirezioniBloccate(mappaDiStanze[s.first()])
            }
        }
    }

    private fun analizzaDirezioniBloccate(stanza: Stanza?) {
        val funAddDir=stanza!!.javaClass.kotlin.functions.find { it.name=="addDirezzionibloccante" }
        if(funAddDir!=null){
            val listOggettiSbloc=reader.find { it.startsWith(DIREZZIONI_BLOCCATE) }?.substringAfter(DIREZZIONI_BLOCCATE)?.split(",")
            var direzioniBlock=listOggettiSbloc?.find { it.contains(stanza.getNome())}?.substringAfter(stanza.getNome())?.split(" ")?: emptyList()
            direzioniBlock=direzioniBlock.filterNot { it=="" }
            for(dk in direzioniBlock){
                funAddDir.call(stanza,dk)
            }
        }
    }

    private fun analizzaOggettiSbloccanti(stanza: Stanza?) {
        val funAddDir=stanza!!.javaClass.kotlin.functions.find { it.name=="addOggettiSbloccante" }
        if(funAddDir!=null){
            val nameStanza=stanza.javaClass.simpleName
            val namePerConf=nameStanza.replace(nameStanza.first(),nameStanza.first().lowercaseChar())
            val configStanza= config?.let { config.stanzaConfig.javaClass.kotlin.memberProperties.find { it.name=="${namePerConf}Config" } }?.get(config.stanzaConfig)
            val oggettiDef = configStanza?.let { sz ->
                val prope = sz.javaClass.kotlin.memberProperties.find { prop ->
                    prop.name.startsWith("oggettiSbloccantiDef")
                }
                @Suppress("UNCHECKED_CAST")
                prope?.get(sz) as List<String>?
            }?: listOf("Tipo Non Trovato")
            val listOggettiSbloc=reader.find { it.startsWith(OGGETTI_BLOCCANTI) }?.substringAfter(OGGETTI_BLOCCANTI)?.split(",")
            var oggettiSbloc= listOggettiSbloc?.find { it.contains(stanza.getNome()) }?.substringAfter(stanza.getNome())?.split(" ") ?: oggettiDef
            oggettiSbloc= oggettiSbloc.filterNot { it=="" }
            for(oS in oggettiSbloc){
                funAddDir.call(stanza,Attrezzo(oS,0))
            }
        }

    }
    private fun leggiEaggiungiAttrezzi(){
        val attrezzi=reader.find { it.startsWith(ATTREZZI_MARKER) }?.substringAfter(ATTREZZI_MARKER)?.split(",")
        if (attrezzi != null) {
            for(attrezzo in attrezzi){
                var a=attrezzo.split(" ")
                a=a.filter { at -> at != "" }
                val stanza= mappaDiStanze[a.first()]
                a=a.subList(1,a.size)
                stanza?.addAllAttrezzi(creaListaAttrezziDaListaStringa(a))
            }
        }
    }
    private fun creaListaAttrezziDaListaStringa(stringhe:List<String>): List<Attrezzo> {
        val a= mutableListOf<Attrezzo>()
        val n= mutableListOf<String>()
        val p= mutableListOf<Int>()
        for(s in stringhe){
            if(isNumeric(s)){
                p.add(s.toInt())
            }else{
                n.add(s)
            }
            if(n.size>p.size+1){
                p.add(0)
            }
        }
        if(n.size>p.size){
            var i=n.size-p.size
           while(i>0){
               p.add(0)
               i--
            }
        }
            var i=0
            while (i<n.size){
                a.add(Attrezzo(n[i],p[i]))
                i++
        }
        return a
    }
    private fun leggiEcreaPersonaggi(){
        val personaggi=reader.find { it.startsWith(PERSONAGGI_MARKER) }?.substringAfter(PERSONAGGI_MARKER)?.split(",")
        if (personaggi != null) {
            for (persone in personaggi){
                var pers=persone
                var presentazione=""
                if(persone.contains('"')){
                    presentazione=persone.substring(persone.indexOfFirst { it== '"' }+1,persone.indexOfLast { it=='"' })
                    pers=persone.removeRange(persone.indexOfFirst { it== '"' }-1,persone.indexOfLast { it=='"' }+1)
                }
                var p=pers.split(" ")
                p=p.filter { pt->pt!="" }
                val stanza=mappaDiStanze[p.first()]
                if(p.any { it in getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/personaggi")) }){
                    val tipo=p.find { it in getClassesInPackage(getAbsolutePath("src/main/kotlin/diadia/personaggi")) }
                    val ciao="${tipo?.lowercase()}Config"
                    val personaggio=
                        config?.let {
                            config.personaggiConfig.javaClass.kotlin.memberProperties.find { it.name==ciao }?.get(config.personaggiConfig)
                        }
                    val nomeConf=personaggio?.let { val prope=it.javaClass.kotlin.memberProperties.find { prop->prop.name.startsWith("nome") }
                        prope?.get(it) as?String}?:"Tipo non trovato"
                    val presentazioneConf=personaggio?.let { val prope=it.javaClass.kotlin.memberProperties.find { prop->prop.name.startsWith("presentazione") }
                        prope?.get(it) as?String}?:"Tipo non trovato"
                    if(presentazione.isBlank()){
                        stanza!!.creaPersonaggio(tipo.toString(),nomeConf, presentazioneConf)
                    }else{
                        if(p.indexOf(tipo)+1>=p.size)
                            stanza!!.creaPersonaggio(tipo.toString(),nomeConf, presentazione)
                        else
                            stanza!!.creaPersonaggio(tipo.toString(),p[p.indexOf(tipo)+1], presentazione)

                    }

                }
                aggiungiOggettiPossedutiPersonaggio(stanza)
                aggiungiOggettiPreferitiPersonaggio(stanza)
            }
        }
    }
    private fun aggiungiOggettiPreferitiPersonaggio(stanza: Stanza?){
        val funAddDir=stanza!!.getPersonaggio()!!.javaClass.kotlin.functions.find { it.name=="addOggettoPreferito" }
        if(funAddDir!=null){
            val nameStanza=stanza.getPersonaggio()!!.javaClass.simpleName
            val namePerConf=nameStanza.lowercase()
            val configPersonaggio= config?.let { config.personaggiConfig.javaClass.kotlin.memberProperties.find { it.name=="${namePerConf}Config" } }?.get(config.personaggiConfig)
            val oggettiDef = configPersonaggio?.let { sz ->
                val prope = sz.javaClass.kotlin.memberProperties.find { prop ->
                    prop.name.startsWith("oggettiPreferitiDef")
                }
                @Suppress("UNCHECKED_CAST")
                prope?.get(sz) as List<String>?
            }?: listOf("Tipo Non Trovato")
            val listOggettiSbloc=reader.find { it.startsWith(PERSONAGGI_OGGETTI_PREFERITI_MARKER) }?.substringAfter(PERSONAGGI_OGGETTI_PREFERITI_MARKER)?.split(",")
            var oggettiPref= listOggettiSbloc?.find { it.contains(stanza.getNome()) }?.substringAfter(stanza.getNome())?.split(" ") ?: oggettiDef
            oggettiPref= oggettiPref.filterNot { it=="" }
            for(oP in oggettiPref){
                funAddDir.call(stanza.getPersonaggio(),Attrezzo(oP,0))
            }
        }
    }
    private fun aggiungiOggettiPossedutiPersonaggio(stanza: Stanza?) {
        val funAddDir=stanza!!.getPersonaggio()!!.javaClass.kotlin.functions.find { it.name=="addOggettoPosseduto" }
        if(funAddDir!=null){
            val listOggettiPoss=reader.find { it.startsWith(PERSONAGGI_OGGETTI_MARKER) }?.substringAfter(PERSONAGGI_OGGETTI_MARKER)?.split(",")
            var oggPoss=listOggettiPoss?.find { it.contains(stanza.getNome())}?.substringAfter(stanza.getNome())?.split(" ")?: emptyList()
            oggPoss=oggPoss.filterNot { it=="" }
            val oggettiPoss=creaListaAttrezziDaListaStringa(oggPoss)
            for(oP in oggettiPoss){
                funAddDir.call(stanza.getPersonaggio(),oP)
            }
        }
    }
    private fun isNumeric(input: String): Boolean {
        return input.toLongOrNull() != null || input.toDoubleOrNull() != null
    }
    private fun leggiEcreaCollegamenti(){
        val collegamenti=reader.find { it.startsWith(COLLEGAMENTI_MARKER) }?.substringAfter(COLLEGAMENTI_MARKER)?.split(",")
        if (collegamenti != null) {
            for(collegamento in collegamenti){
                var c=collegamento.split(" ")
                c=c.filter { a -> a != "" }
                doppioCollegamento(mappaDiStanze[c.first()],mappaDiStanze[c.last()],Direzioni.valueOf(c[1].uppercase()))
            }
        }
    }
    private fun doppioCollegamento(prima:Stanza?, seconda:Stanza?, direzione: Direzioni){
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
            leggiEcreaPersonaggi()
        }catch (e: IOException){
                e.printStackTrace()
                throw RuntimeException(e)
            }
    }
    fun getIniziale()=inizale
    fun getFinale()=finale
}