package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo

class StanzaBuia(private var nome:String):Stanza(nome) {
    constructor(nome: String,vararg attrezzo: Attrezzo):this(nome){
        oggettisbloccanti.addAll(attrezzo)
    }
    fun addOggettiSbloccante(attrezzo: Attrezzo){
        oggettisbloccanti.add(attrezzo)
    }
    override fun getDescrizione(): String {
        if(oggettisbloccanti.none { it in this.getListaDiAttrezzi()}){
            return "qui c'è un buio pesto"
        }
        return toString()
    }
}