package diadia.personaggi

import diadia.Attrezzi.Attrezzo
import diadia.Partita
import diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi
import java.util.*
import kotlin.random.Random

class Strega(nome: String, presentazione: String): AbstractPersonaggio(nome, presentazione) {
    override fun agisci(partita: Partita): String {
        val stanzead=partita.getStanzaCorrente().getMapStanzeAdiacenti().values.toList()
        return if(stanzead.isNotEmpty()){
           Collections.sort(stanzead,ComparatoreStanzePerNumeroAttrezzi())
           if(haSalutato()){
               partita.setStanzaCorrente(stanzead.first())
               "Sei stato bravo ora divertiti ti ho trasportato in un posto pieno di attrezzi"
           }else{
               partita.setStanzaCorrente(stanzead.last())
               "Impara l'educazione e saluta la prossima volta! ora ti spedisco in una stanza desolata"
           }
       }
        else "Non ci sono uscite da questa Stanza non posso trasportarti da nessuna parte"

    }

    override fun riceviRegalo(attrezzo: Attrezzo, partita: Partita): String {
        return when (Random(attrezzo.hashCode()).nextInt(4)) {
            0 -> "ah ah ah ah ah"
            1 -> "hi hi hi hi"
            2 -> "ma fammi il piacere a cosa mi serve ${attrezzo.getNome()}"
            3 -> "non sono tanto idiota come il mago"
            4 -> "AHAHAHAAHAHHAHAHAHAHAHAHAHAHAAHAHAHAHAHAHAH!!!!!!!!!"
            else -> "Eeeeeeeh Volevi!\nguarda che faccia non se l'aspettava"
        }
    }
}