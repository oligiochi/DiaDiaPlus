package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni

class StanzaBuia(private var nome:String):Stanza(nome) {
    constructor(nome: String,vararg attrezzo: Attrezzo):this(nome){
        oggettisbloccanti.addAll(attrezzo)
    }
    fun addOggettiSbloccante(attrezzo: Attrezzo){
        oggettisbloccanti.add(attrezzo)
    }

    override fun getDirezioni(): MutableSet<Direzioni> {
        if(this.getListaDiAttrezzi().none { it in oggettisbloccanti }){
           val map= this.getMapStanzeAdiacenti().filterValues { it.getVisitata() }
            return map.keys.toMutableSet()
        }

        return super.getDirezioni()
    }
    override fun getDescrizione(): String {
        if(oggettisbloccanti.none { it in this.getListaDiAttrezzi()}){
            return "qui c'è un buio pesto"
        }
        return toString()
    }
}