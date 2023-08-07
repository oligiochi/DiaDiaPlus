package diadia.ambienti.labirinto

import diadia.Attrezzi.Attrezzo
import diadia.ambienti.stanze.Stanza
import diadia.config.leggiDaFileJson
import diadia.util.FormatoFileNonValidoException
import java.io.FileNotFoundException


class Labirinto(private var entrata: Stanza, private var uscita: Stanza) {
    companion object{
        val config= leggiDaFileJson()
        val File= config!!.labirintoConfig.labDef
        lateinit var fileLab:CaricatoreLabirinto

    }
    constructor():this(File)
  constructor(file:String) : this(Stanza(),Stanza()){carica(file)}
    fun getEntrata()=this.entrata
    fun getUscita()=this.uscita
    private fun carica(file: String){
        try {
            fileLab = CaricatoreLabirinto(file)
            fileLab.carica()
            entrata= fileLab.getIniziale()!!
            uscita= fileLab.getFinale()!!
        } catch (e: FileNotFoundException) {
            println("controlla che nel tuo pc esista un file chiamato: $file\nVerrà eseguita una partita standard\n")
        } catch (e: FormatoFileNonValidoException) {
            println("PROBLEMI CON IL CARICAMENTO DEL FILE\n${e.message}")
        }
    }
    fun inserisci_attrezzi(stanza: Stanza, attrezzo: Attrezzo) { stanza.addAttrezzo(attrezzo) }
}