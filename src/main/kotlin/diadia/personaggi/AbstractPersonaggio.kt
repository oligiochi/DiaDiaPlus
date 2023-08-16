package diadia.personaggi

import diadia.Attrezzi.Attrezzo
import diadia.Partita


abstract class AbstractPersonaggio(private var nome:String, private var presentazione:String) {
    private val attrezziCheIlPersonaggioPossiede= mutableListOf<Attrezzo>()
    private val regaloPreferito=mutableListOf<Attrezzo>()
    private var salutato=false
    fun saluta(): String {
        val saluto = "Ciao, io sono $nome "
        return if(!haSalutato()){
            salutato=true
            saluto.plus(presentazione)
        }
        else
            saluto.plus("Ci siamo gia' presentati!")
    }
    fun getNome()= nome
    fun getPresentazione()=presentazione
    fun setNome(nome: String?) { if (nome != null) this.nome = nome }
    fun getRegali()=attrezziCheIlPersonaggioPossiede
    fun getOggettiPreferiti()=regaloPreferito
    fun haSalutato()=salutato
    fun setPresentazione(pres: String?) { if (pres != null) presentazione = pres }
    open fun addRegalo(attrezzo: Attrezzo){}
    open fun addOggettoPreferito(attrezzo: Attrezzo){}
    abstract fun agisci(partita: Partita): String
    abstract fun riceviRegalo(attrezzo: Attrezzo, partita: Partita): String

}