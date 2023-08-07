package diadia.giocatore

import diadia.Attrezzi.Attrezzo
import diadia.Attrezzi.ComparatorPerPeso
import diadia.config.leggiDaFileJson


class Borsa(private val pesoMax: Int){
    private val attrezzi : MutableList<Attrezzo> = mutableListOf()
    private var peso=0
    companion object {
        val config= leggiDaFileJson()
    }
    constructor() : this(config!!.borsaConfig.pesoDef)
    fun getPeso()=this.peso
    fun ifEmpty()=this.attrezzi.isEmpty()
    fun getContenutoOrdinatoPerNome()=this.attrezzi.apply { sort() }
    fun getAttrezzo(attrezzo:String)=this.attrezzi.getOrNull(attrezzi.indexOf(Attrezzo(attrezzo,0)))
    fun getContenutoOrdinatoPerPeso()=this.attrezzi.apply{sortWith(ComparatorPerPeso())}
    fun hasAttrezzi(attrezzo:String)=this.attrezzi.contains(this.getAttrezzo(attrezzo))
    fun getpesoMax()=pesoMax
    fun addAttrezzo(attrezzo: Attrezzo):Boolean{
        if(this.getPeso()+attrezzo.getPeso()>this.pesoMax) {
            return false
        }
        this.peso=this.getPeso()+attrezzo.getPeso()
        return this.attrezzi.add(attrezzo)
    }
    fun addAllAttrezzi(attrezzi: List<Attrezzo>){
        val i=attrezzi.iterator()
        var c =true
        while(i.hasNext() && c){
            c=this.addAttrezzo(i.next())
        }
    }
    fun removeAttrezzo(attrezzo:String): Attrezzo? {
        if(getAttrezzo(attrezzo)!=null)
            return this.attrezzi.removeAt(this.attrezzi.indexOf(getAttrezzo(attrezzo)))
        return null
    }
    fun pesoInRelazione()="($peso Kg/$pesoMax Kg)"
    override fun toString(): String {
        if(!ifEmpty())
        return "Contenuto borsa ${this.pesoInRelazione()}: attrezzi=$attrezzi"
        return "Borsa Vuota"
    }

}