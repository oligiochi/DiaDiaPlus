package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.direzioni.Direzioni

class StanzaBloccata(private var nome:String):Stanza(nome) {

    constructor(nome: String,attrezziBloccanti: List<Attrezzo>,direzioniBloccate: List<Direzioni>) : this(nome) {
        oggettisbloccanti.addAll(attrezziBloccanti)
        direzzioniBloccate.addAll(direzioniBloccate)
    }
    override fun addoggettisbloccante(attrezzo: Attrezzo){
        oggettisbloccanti.add(attrezzo)
    }
    override fun addDirezzionibloccante(string: String){
        direzzioniBloccate.add(Direzioni.valueOf(string.uppercase()))
    }

    override fun getDirezioni(): MutableSet<Direzioni> {
        if(getListaDiAttrezzi().any { ele->ele in oggettisbloccanti })
        return super.getDirezioni().filterNot { direzzioniBloccate.any { ele->ele in super.getDirezioni()} }.toMutableSet()
        return super.getDirezioni()
    }
}