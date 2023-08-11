package diadia.controlli.comandi

import diadia.Attrezzi.Attrezzo
import diadia.Partita
import diadia.ambienti.direzioni.Direzioni
import diadia.ambienti.stanze.Stanza
import diadia.ambienti.stanze.StanzaBloccata
import diadia.ambienti.stanze.StanzaBuia
import diadia.ambienti.stanze.StanzaMagica
import diadia.controlli.gestore.AbstractComando
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ComandoInfoTest{
    private var comando: AbstractComando? = null
    private var comandoStanza: AbstractComando? = null
    private var StanzaNormale: Stanza? = null
    private var StanzaBloccata: StanzaBloccata? = null
    private var StanzaBuia: StanzaBuia? = null
    private var StanzaMagica: StanzaMagica? = null
    private var P: Partita? = null
    @BeforeEach
    fun setUp() {
        comando = ComandoInfo()
        comandoStanza = ComandoInfo()
        StanzaBloccata = StanzaBloccata("cucina", listOf(Attrezzo("chiave",5)),listOf(Direzioni.NORD) )
        StanzaBuia = StanzaBuia("cantina", Attrezzo("torcia",5))
        StanzaMagica = StanzaMagica("Una comunissima Stanza", 1)
        P = Partita()
        StanzaNormale = Stanza("bibliotteca")
        StanzaBloccata!!.impostaStanzaAdiacente("Sud", StanzaBuia!!)
        StanzaBloccata!!.impostaStanzaAdiacente("NORD", StanzaMagica!!)
        (comandoStanza as ComandoInfo).setParametro("stanza")
    }

    //Test ComandoInfo con Parametro Borsa
    @Test
    fun testComandoInfoConParametroBorsaVuota() {
        comando!!.setParametro("borsa")
        assertEquals("Borsa Vuota", comando!!.esegui(P!!))
    }

    @Test
    fun testComandoInfoConParametroBorsaPiena() {
        P!!.getPlayer().getBorsa().addAttrezzo(Attrezzo("libro", 10))
        comando!!.setParametro("borsa")
        assertEquals("Contenuto borsa (10 Kg/50 Kg): attrezzi=[libro (10 Kg)]", comando!!.esegui(P!!))
    }

    @Test
    fun testComandoInfoConParametroBorsaConPiùOggetti() {
        P!!.getPlayer().getBorsa().addAttrezzo(Attrezzo("libro", 2))
        P!!.getPlayer().getBorsa().addAttrezzo(Attrezzo("manganello", 5))
        P!!.getPlayer().getBorsa().addAttrezzo(Attrezzo("banana", 1))
        comando!!.setParametro("borsa")
        assertEquals("Contenuto borsa (8 Kg/50 Kg): attrezzi=[libro (2 Kg), manganello (5 Kg), banana (1 Kg)]", comando!!.esegui(P!!))
    }

    //Test ComandoInfo con Parametro Stanza normale
    @Test
    fun testComandoInfoConParametroStanzaNormaleSenzaOggetti() {
        P!!.setStanzaCorrente(StanzaNormale!!)
        assertEquals(
            """
            bibliotteca
            Uscite: 
            Attrezzi nella stanza: 
            """.trimIndent(), comandoStanza!!.esegui(P!!)
        )
    }

    @Test
    fun testComandoInfoConParametroStanzaNormaleConOggetti() {
        StanzaNormale!!.addAttrezzo(Attrezzo("libro", 5))
        P!!.setStanzaCorrente(StanzaNormale!!)
        assertEquals("" , comandoStanza!!.esegui(P!!))
    }

    //Test ComandoInfo con Parametro Stanza Magica
    @Test
    fun testComandoInfoConParametroStanzaMagicaSenzaOggetti() {
        P!!.setStanzaCorrente(StanzaMagica!!)
        assertEquals(
            """
            Una comunissima Stanza
            Uscite: 
            Attrezzi nella stanza: 
            """.trimIndent(), comandoStanza!!.esegui(
                P!!
            )
        )
    }

    @Test
    fun testComandoInfoConParametroStanzaMagicaConOggetti() {
        StanzaMagica!!.addAttrezzo(Attrezzo("libro", 5))
        StanzaMagica!!.addAttrezzo(Attrezzo("libro", 5))
        P!!.setStanzaCorrente(StanzaMagica!!)
        assertEquals(
            """
            Una comunissima Stanza
            Uscite: 
            Attrezzi nella stanza: libro (5 kg), orbil (10 kg)
            """.trimIndent(), comandoStanza!!.esegui(
                P!!
            )
        )
    }

    //Test ComandoInfo con Parametro Stanza Bloccata
    @Test
    fun testComandoInfoConParametroStanzaBloccataSenzaOggetti() {
        P!!.setStanzaCorrente(StanzaBloccata!!)
        assertEquals(
            """
            cucina
            Uscite: SUD
            Attrezzi nella stanza: 
            """.trimIndent(), comandoStanza!!.esegui(
                P!!
            )
        )
    }

    @Test
    fun testComandoInfoConParametroStanzaBloccataConOggetti() {
        P!!.setStanzaCorrente(StanzaBloccata!!)
        StanzaBloccata!!.addAttrezzo(Attrezzo("libro", 5))
        assertEquals(
            """
            cucina
            Uscite: sud
            Attrezzi nella stanza: libro (5kg)
            Dai segni sul pavimento deduco che ci sono altri passaggi oltre a questi portei usare
            [chiave]
            per cercare di riaprire tutti i passaggi
            """.trimIndent(), comandoStanza!!.esegui(
                P!!
            )
        )
    }

    @Test
    fun testComandoInfoConParametroStanzaBloccataConOggettiSbloccante() {
        P!!.setStanzaCorrente(StanzaBloccata!!)
        StanzaBloccata!!.addAttrezzo(Attrezzo("chiave", 5))
        assertEquals(
            """
            cucina
            Uscite: nord sud
            Attrezzi nella stanza: chiave (5kg)
            """.trimIndent(), comandoStanza!!.esegui(
                P!!
            )
        )
    }

    //Test ComandoInfo con Parametro Stanza Buia
    @Test
    fun testComandoInfoConParametroStanzaBuiaSenzaOggetti() {
        P!!.setStanzaCorrente(StanzaBuia!!)
        assertEquals("qui c'è un buio pesto", comandoStanza!!.esegui(P!!))
    }

    @Test
    fun testComandoInfoConParametroStanzaBuiaConOggetti() {
        P!!.setStanzaCorrente(StanzaBuia!!)
        StanzaBuia!!.addAttrezzo(Attrezzo("libro", 5))
        assertEquals("qui c'è un buio pesto", comandoStanza!!.esegui(P!!))
    }

    @Test
    fun testComandoInfoConParametroStanzaBuiaConOggettiLuminoso() {
        P!!.setStanzaCorrente(StanzaBuia!!)
        StanzaBuia!!.addAttrezzo(Attrezzo("torcia", 5))
        assertEquals(
            """
            cantina
            Uscite: 
            Attrezzi nella stanza: torcia (5kg)
            """.trimIndent(), comandoStanza!!.esegui(P!!)
        )
    }

    //Test ComandoInfo con Parametro fuori dal ordinario
   @Test
    fun testComandoInfoConParametroStringaVuota() {
        comando!!.setParametro("")
        assertEquals("Su cosa vuoi avere le informazioni?", comando!!.esegui(P!!))
    }

    @Test
    fun testComandoInfoConParametroRandom() {
        comando!!.setParametro("sasso")
        assertEquals("non esiste il comando:\nsasso", comando!!.esegui(P!!))
    }
}