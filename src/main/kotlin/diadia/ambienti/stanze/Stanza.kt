package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni
import diadia.personaggi.AbstractPersonaggio

open class Stanza(private var nome: String) {

    private val attrezzi : MutableList<Attrezzo> = mutableListOf()
    private val stanzeAdiacenti : MutableMap<Direzioni, Stanza> = mutableMapOf()
    var oggettisbloccanti = mutableListOf<Attrezzo>()
    var direzzioniBloccate = mutableListOf<Direzioni>()
    companion object {
        private lateinit var personaggio: AbstractPersonaggio
    }

    constructor() : this("")

    fun impostaStanzaAdiacente(direzione : String , stanza : Stanza){
        val direzioni=direzione.uppercase()
            try{
                if(!stanzeAdiacenti.containsKey(Direzioni.valueOf(direzioni))) {
                    stanzeAdiacenti[Direzioni.valueOf(direzioni)] = stanza
                }
            }catch(_: IllegalArgumentException){println("non esiste nessuna direzione di nome $direzioni")}
    }
    fun getStanzaAdiacente(direzione : String) : Stanza? {
        val direzioni=direzione.uppercase()
        try{
            if(stanzeAdiacenti.containsKey(Direzioni.valueOf(direzioni)))
            return stanzeAdiacenti[Direzioni.valueOf(direzioni)]
        }catch(_: IllegalArgumentException){println("non esiste nessuna direzione di nome $direzioni")}
        return null
    }
    fun getPersonaggio()= personaggio
    fun setPersonaggio(p:AbstractPersonaggio){ personaggio=p}
    fun getNumeroAttrezzi()=this.attrezzi.size
    fun getNome()=this.nome
    fun getNumeroStanzeAdiacenti()=this.stanzeAdiacenti.size
    fun getListaDiAttrezzi()=this.attrezzi
    fun hasAttrezzo(attrezzo:String)=this.attrezzi.contains(this.getAttrezzo(attrezzo))
    open fun getDirezioni()=this.stanzeAdiacenti.keys
    fun getMapStanzeAdiacenti()=this.stanzeAdiacenti
    open fun addAttrezzo(attrezzo: Attrezzo)=this.attrezzi.add(attrezzo)
    open fun addoggettisbloccante(attrezzo: Attrezzo){}
    open fun addDirezzionibloccante(string: String){}
    open fun setSoglia(int:Int){}
    fun getAttrezzo(attrezzo:String)=this.attrezzi.getOrNull(attrezzi.indexOf(Attrezzo(attrezzo,0)))
    fun removeAttrezzo(attrezzo:String): Attrezzo? {
        if(this.getAttrezzo(attrezzo)!=null)
        return this.attrezzi.removeAt(attrezzi.indexOf(this.getAttrezzo(attrezzo)))
        return null
    }
    open fun getDescrizione()=toString()
    override fun toString(): String {
        val costruttore = StringBuilder()
        costruttore.append(this.nome)
        costruttore.append("\nUscite: ")
        costruttore.append(this.getDirezioni().toString().filterNot { it == '[' || it == ']'})
        costruttore.append("\nAttrezzi nella stanza: ")
        costruttore.append(this.getListaDiAttrezzi().toString().filterNot { it == '[' || it == ']'})
        costruttore.filter {it.isLetterOrDigit() || it.isWhitespace()}
        return costruttore.toString()
    }

}