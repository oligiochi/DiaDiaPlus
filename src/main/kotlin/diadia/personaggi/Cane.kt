package diadia.personaggi

import diadia.Attrezzi.Attrezzo
import diadia.Partita
import kotlin.random.Random


class Cane(nome: String, presentazione: String): AbstractPersonaggio(nome, presentazione) {
    constructor(nome: String, presentazione: String, oggettiRegalare:List<Attrezzo>, oggettiPreferiti:List<Attrezzo>) : this(nome,presentazione){
        getRegali().addAll(oggettiRegalare)
        getOggettiPreferiti().addAll(oggettiPreferiti)
    }

    override fun agisci(partita: Partita): String {
        return run {
            partita.getPlayer().setCFU(partita.getPlayer().getCFU() - 1)
            "Il cane ti ha morso"
        }
    }

    override fun riceviRegalo(attrezzo: Attrezzo, partita: Partita): String {
        return if (getOggettiPreferiti().contains(attrezzo)) {
                partita.getStanzaCorrente().addAttrezzo(getRegali().shuffled(Random(attrezzo.hashCode())).first())
                "WOOOOOOF"
            } else {
                agisci(partita)
            }
    }
    override fun addRegalo(attrezzo: Attrezzo) {
        getRegali().add(attrezzo)
    }

    override fun addOggettoPreferito(attrezzo: Attrezzo) {
        getOggettiPreferiti().add(attrezzo)
    }
}