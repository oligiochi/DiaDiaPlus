package diadia.giocatore

import diadia.ambienti.stanze.Stanza
import diadia.Attrezzi.Attrezzo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GiocatoreTest {
    private lateinit var stanzaNonVuota: Stanza
    private lateinit var stanzaVuota: Stanza
    private lateinit var StanzaPiena: Stanza
    private lateinit var attrezzoDiProva: Attrezzo
    private lateinit var StanzaPienaConRipetizione: Stanza
    private lateinit var StanzaConRipetizione: Stanza
    private val attrezzi = arrayOf(
        Attrezzo("",0),
        Attrezzo("", 5),
        Attrezzo("Attrezzo", 5),
        Attrezzo("Attrezzo", 5),
        Attrezzo("Banana", 2),
        Attrezzo("osso", 6),
        Attrezzo("spada", 1),
        Attrezzo("scudo", 1),
        Attrezzo("cassa", 2),
        Attrezzo("sasso", 1)
    )
    private lateinit var borsaNonVuota: Borsa
    private lateinit var borsaVuota: Borsa
    private lateinit var GiocatoreConBorsaNonVuota: Giocatore
    private lateinit var GiocatoreConBorsaVuota: Giocatore
    private lateinit var GiocatoreDef: Giocatore

    @BeforeEach
    fun setup() {
        stanzaNonVuota = Stanza("Bagno")
        for (i in 0..4) {
            stanzaNonVuota.addAttrezzo(attrezzi[i])
        }
        stanzaVuota = Stanza("Docccia")
        StanzaPiena = Stanza("Mensa")
        for (i in 0..9) {
            StanzaPiena.addAttrezzo(attrezzi[i])
        }
        StanzaPienaConRipetizione = Stanza("N10")
        for (i in 0..9) {
            StanzaPienaConRipetizione.addAttrezzo(attrezzi[8])
        }
        StanzaConRipetizione = Stanza("N11")
        for (i in 0..0) {
            StanzaConRipetizione.addAttrezzo(attrezzi[8])
        }
        borsaNonVuota = Borsa(10)
        borsaVuota = Borsa()
        attrezzoDiProva = attrezzi[3]
        borsaNonVuota.addAttrezzo(attrezzoDiProva)
        GiocatoreConBorsaNonVuota = Giocatore(borsaNonVuota,20)
        GiocatoreConBorsaVuota = Giocatore(borsaVuota,20)
        GiocatoreDef= Giocatore()
    }

    //Test del metodo prendiAttrezzo
    @Test
    fun testPrendiAttrezzoInesistente() {
        assertEquals("NonEsisto non esiste nella stanza", GiocatoreConBorsaNonVuota.prendiAttrezzo("NonEsisto", StanzaPiena))
    }
    @Test
    fun testPrendiAttrezzoNull() {
        assertEquals("Che attrezzo vuoi prendere?", GiocatoreConBorsaNonVuota.prendiAttrezzo(null, StanzaPiena))
    }

    @Test
    fun testPrendiAttrezzo() {
        assertEquals(
            GiocatoreConBorsaNonVuota.prendiAttrezzo(attrezzi[3].getNome(), StanzaPiena),
            "Ho preso ${attrezzi[3]}! Lo troverai nella tua Borsa ${GiocatoreConBorsaNonVuota.getBorsa().pesoInRelazione()}"
        )
    }

    @Test
    fun testPrendiAttrezzoBorsaTroppoPesante() {
        assertEquals(
            GiocatoreConBorsaNonVuota.prendiAttrezzo(attrezzi[5].getNome(), StanzaPiena),
            "La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra"
        )
    }

    @Test
    fun testPrendiAttrezzoStanzaVuota() {
        assertEquals("ciao non esiste nella stanza", GiocatoreConBorsaNonVuota.prendiAttrezzo("ciao", stanzaVuota))
    }

    //Test Metodi Con CFU
    @Test
    fun testGetCFU() {
        assertEquals(20, GiocatoreConBorsaNonVuota.getCFU())
        assertEquals(30, GiocatoreDef.getCFU())
    }

    @Test
    fun testGetCFUDopoAverSettatoiCFUconUnNumeroPositivo() {
        GiocatoreConBorsaNonVuota.setCFU(5)
        assertEquals(5, GiocatoreConBorsaNonVuota.getCFU())
    }

    @Test
    fun testGetCFUDopoAverSettatoiCFUconUnNumeroNegativo() {
        GiocatoreConBorsaNonVuota.setCFU(-5)
        assertEquals(-5, GiocatoreConBorsaNonVuota.getCFU())
    }

    @Test
    fun testGetCFUDopoAverSettatoCFUconZero() {
        GiocatoreConBorsaNonVuota.setCFU(0)
        assertEquals(0, GiocatoreConBorsaNonVuota.getCFU())
    }

    //Test del metodo removeAttrezzo
    @Test
    fun testRemoveAttrezzoInesistente() {
        assertEquals("Che attrezzo vuoi posare?", GiocatoreConBorsaNonVuota.rimuoviAttrezzo(null, StanzaConRipetizione))
    }

    @Test
    fun testRemoveAttrezzoConAttrezzoNonTrovato() {
        assertEquals(
            "Ho toccato il fondo, ma " + attrezzi[4].getNome() + " non l'ho trovato",
            GiocatoreConBorsaNonVuota.rimuoviAttrezzo(
                attrezzi[4].getNome(), stanzaNonVuota
            )
        )
    }

    @Test
    fun testRemoveAttrezzoConBorsaVuota() {
        assertEquals(
            "Ho toccato il fondo, ma " + attrezzi[4].getNome() + " non l'ho trovato",
            GiocatoreConBorsaVuota.rimuoviAttrezzo(
                attrezzi[4].getNome(), stanzaNonVuota
            )
        )
    }

    @Test
    fun testRemoveAttrezzoTrovato() {
        assertEquals(
            GiocatoreConBorsaNonVuota.rimuoviAttrezzo(attrezzoDiProva.getNome(), stanzaNonVuota),
            "Non mi è mai servito a nulla $attrezzoDiProva quindi me ne sono sbarazzato, ora la mia borsa è più leggera! ${GiocatoreConBorsaNonVuota.getBorsa().pesoInRelazione()}"
        )
    }
}