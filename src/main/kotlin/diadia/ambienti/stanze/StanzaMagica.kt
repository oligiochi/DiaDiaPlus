package diadia.ambienti.stanze

import diadia.Attrezzi.Attrezzo
import diadia.config.leggiDaFileJson


class StanzaMagica(private var nome:String):Stanza(nome){
    val config= leggiDaFileJson()
    var contatoreAttrezziPosati=0
    var soglie=config!!.StanzaConfig.SogliaMagicaStandar
    constructor(nome:String,soglia:Int):this(nome){
        soglie=soglia
    }
    override fun setSoglia(int:Int){soglie=int}
    private fun modificaAttrezzo(attrezzo: Attrezzo) {
        val reverse = StringBuilder(attrezzo.getNome())
        attrezzo.setNome(reverse.reverse().toString())
        attrezzo.setPeso(attrezzo.getPeso() * 2)
    }
       override fun addAttrezzo(attrezzo: Attrezzo): Boolean {
        if(super.addAttrezzo(attrezzo)){
            if(contatoreAttrezziPosati== soglie){
                contatoreAttrezziPosati=0
                modificaAttrezzo(attrezzo)
            }
            contatoreAttrezziPosati++
            return true
        }
        return false
    }
}