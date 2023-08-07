package diadia.personaggi

import diadia.Attrezzi.Attrezzo
import diadia.Partita


abstract class AbstractPersonaggio(private var nome:String, private var presentazione:String) {
    private val attrezziRegalo= mutableListOf<Attrezzo>()
    private val regaloPreferito=mutableListOf<Attrezzo>()
    private var salutato=false
    fun saluta(): String {
        val saluto = "Ciao, io sono $nome"
        if(!haSalutato())
            saluto.plus(presentazione)
        else
            saluto.plus("Ci siamo gia' presentati!")
        return saluto
    }
    fun getNome()= nome
    fun getPresentazione()=presentazione
    fun setNome(nome: String?) { if (nome != null) this.nome = nome }
    fun getRegali()=attrezziRegalo
    fun getOggettiPreferiti()=regaloPreferito
    fun haSalutato()=salutato
    fun setPresentazione(pres: String?) { if (pres != null) presentazione = pres }
    open fun addRegalo(attrezzo: Attrezzo){}
    open fun addOggettoPreferito(attrezzo: Attrezzo){}
    abstract fun agisci(partita: Partita): String
    abstract fun riceviRegalo(attrezzo: Attrezzo, partita: Partita): String

}