package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni

open class Stanza(private var nome: String) {

    private val attrezzi : MutableList<Attrezzo> = mutableListOf()
    private val stanzeAdiacenti : MutableMap<Direzioni, Stanza> = mutableMapOf()
    companion object {
        var oggettisbloccanti = mutableListOf<Attrezzo>()
        var direzzioniBloccate = mutableListOf<Direzioni>()
    }
    //private val personaggio: Personaggio?=null
    constructor() : this("")

    fun impostaStanzaAdiacente(direzione : String , stanza : Stanza){
            try{
                if(!stanzeAdiacenti.containsKey(Direzioni.valueOf(direzione))) {
                    stanzeAdiacenti.put(Direzioni.valueOf(direzione),stanza)
                }
            }catch(_: IllegalArgumentException){println("non esiste nessuna direzione di nome $direzione")}
    }
    fun getStanzaAdiacente(direzione : String) : Stanza? {
        try{        if(stanzeAdiacenti.containsKey(Direzioni.valueOf(direzione)))

            return stanzeAdiacenti.get(Direzioni.valueOf(direzione))
        }catch(_: IllegalArgumentException){println("non esiste nessuna direzione di nome $direzione")}
        return null
    }

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
        costruttore.append("\nUscite:\n")
        costruttore.append(this.getDirezioni().toString())
        costruttore.append("\nAttrezzi nella stanza:\n")
        costruttore.append(this.getListaDiAttrezzi().toString())
        costruttore.filter {it.isLetterOrDigit() || it.isWhitespace()}
        return costruttore.toString()
    }

}