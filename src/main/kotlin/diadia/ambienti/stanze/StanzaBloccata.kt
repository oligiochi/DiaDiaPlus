package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni

class StanzaBloccata(private var nome:String):Stanza(nome) {

    constructor(nome: String,attrezziBloccanti: List<Attrezzo>,direzioniBloccate: List<Direzioni>) : this(nome) {
        oggettisbloccanti.addAll(attrezziBloccanti)
        direzzioniBloccate.addAll(direzioniBloccate)
    }
    fun addOggettiSbloccante(attrezzo: Attrezzo){
        oggettisbloccanti.add(attrezzo)
    }
    fun addDirezzionibloccante(string: String){
        direzzioniBloccate.add(Direzioni.valueOf(string.uppercase()))
    }

    override fun getDirezioni(): MutableSet<Direzioni> {
        return if(getListaDiAttrezzi().none { it in oggettisbloccanti })
        super.getDirezioni().filterNot { it in direzzioniBloccate }.toMutableSet()
        else super.getDirezioni()
    }
}