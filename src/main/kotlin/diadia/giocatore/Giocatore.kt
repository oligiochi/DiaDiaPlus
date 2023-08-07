package diadia.giocatore

import diadia.ambienti.stanze.Stanza
import diadia.config.leggiDaFileJson


class Giocatore(private var borsa : Borsa , private var CFU : Int) {
    companion object{
        val config= leggiDaFileJson()
    }
    constructor() : this(Borsa(), config!!.giocatoreConfig.cFUDef)

    fun getCFU() = this.CFU
    fun getBorsa() = this.borsa
    fun setCFU(c: Int) {
        this.CFU = c
    }

    fun prendiAttrezzo(attrezzo: String?, stanzaAttuale: Stanza): String {
        if (attrezzo == null) return "Che attrezzo vuoi prendere?"
        val prendere = stanzaAttuale.getAttrezzo(attrezzo)
        if (prendere != null) {
            if (this.getBorsa().addAttrezzo(prendere)) {
                stanzaAttuale.removeAttrezzo(attrezzo)
                return "Ho preso $prendere! Lo troverai nella tua Borsa ${this.getBorsa().pesoInRelazione()}"
            } else return "La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra"
        } else return "$attrezzo non esiste nella stanza"
    }

    fun rimuoviAttrezzo(attrezzo: String?, stanzaAttuale: Stanza): String {
        if (attrezzo == null) return "Che attrezzo vuoi posare?"
        val prendere = this.getBorsa().getAttrezzo(attrezzo)
        if (prendere != null) {
            if (stanzaAttuale.addAttrezzo(prendere)) {
                this.getBorsa().removeAttrezzo(attrezzo)
                return "Non mi è mai servito a nulla $prendere quindi me ne sono sbarazzato, ora la mia borsa è più leggera! ${this.getBorsa().pesoInRelazione()}"
            }
        }
        return "Ho toccato il fondo, ma $attrezzo non l'ho trovato"
        }
}