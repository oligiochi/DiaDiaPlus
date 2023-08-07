package diadia.personaggi

import diadia.Attrezzi.Attrezzo
import diadia.Partita
import kotlin.random.Random

class Mago(nome: String, presentazione: String): AbstractPersonaggio(nome, presentazione) {
    constructor(nome: String,presentazione: String,vararg regali:Attrezzo) : this(nome,presentazione) {
        getRegali().addAll(regali)
    }
    override fun agisci(partita: Partita): String {
        return if(partita.getPlayer().getBorsa().addAttrezzo(getRegali().shuffled().first()))
            "Da un'occhiata alla tua borsa giocatore ho fatto una magia!"
        else "Torna da me quando sarai più leggero e verrai ricompensato"
    }

    override fun riceviRegalo(attrezzo: Attrezzo, partita: Partita): String {
        return if(partita.getPlayer().getBorsa().removeAttrezzo(attrezzo.getNome())!=null){
            attrezzo.setPeso(attrezzo.getPeso()/ Random(attrezzo.hashCode()).nextInt(2))
            partita.getStanzaCorrente().addAttrezzo(attrezzo)
            "Cerca nella stanza, fidati ;)"
        }else "Purtroppo non posso aiutarti non ho trovato il tuo attrezzo neanche con l'aiuto della mia magia"
    }

    override fun addRegalo(attrezzo: Attrezzo) {
        getRegali().add(attrezzo)
    }
}