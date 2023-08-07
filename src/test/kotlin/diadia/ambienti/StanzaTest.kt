package diadia.ambienti

import diadia.ambienti.stanze.Stanza
import diadia.Attrezzi.Attrezzo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test



class StanzaTest {
    private lateinit var StanzaSenzaNomeVuota: Stanza
    private lateinit var StanzaConNome: Stanza
    private lateinit var StanzaPiena: Stanza
    private val attrezzi =
        arrayOf(Attrezzo("",0), Attrezzo("", 5), Attrezzo("Attrezzo", 0), Attrezzo("Attrezzo", 5), Attrezzo("Banana", 200))

    @BeforeEach
    fun setUp() {
        StanzaSenzaNomeVuota = Stanza("")
        StanzaPiena = Stanza("")
        for (i in 0..9) {
            if (i < 5) {
                StanzaPiena.addAttrezzo(attrezzi.get(i))
            } else {
                StanzaPiena.addAttrezzo(attrezzi.get(i - 5))
            }
        }
        StanzaConNome = Stanza("Bagno")
        StanzaConNome.impostaStanzaAdiacente("sud", StanzaPiena)

    }

    @Test
    fun testGetNomeStanzaSenzaNomeVuota() {
        assertEquals("", StanzaSenzaNomeVuota.getNome())
    }

    @Test
    fun testGetNomeStanzaConNome() {
        assertEquals("Bagno", StanzaConNome.getNome())
    }

    //Test del mettodo Get Array Di Attrezzi

    //Test del mettodo Get Array Di Attrezzi


    //Test del mettodo Get Stanza Adiacente

    //Test del mettodo Get Stanza Adiacente
    @Test
    fun testGetStanzaNordStanzaSenzaNomeVuota() {
        assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("nord"))
    }

    @Test
    fun testGetStanzaSudStanzaSenzaNomeVuota() {
        assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("sud"))
    }

    @Test
    fun testGetStanzaEstStanzaSenzaNomeVuota() {
        assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("est"))
    }

    @Test
    fun testGetStanzaOvestStanzaSenzaNomeVuota() {
        assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("ovest"))
    }

    @Test
    fun testImpostaStanzaAdiacente() {
        assertEquals(StanzaPiena, StanzaConNome.getStanzaAdiacente("sud"))
    }

    //Test del mettodo Add Attrezzo

    //Test del mettodo Add Attrezzo
    @Test
    fun testAddAttrezzoStanzaVuota() {
        assertTrue(StanzaConNome.addAttrezzo(attrezzi.get(0)))
    }

  

    //Test del mettodo Remove Attrezzo

    //Test del mettodo Remove Attrezzo
    @Test
    fun testRemoveAttrezzoStanzaPiena() {
        assertEquals(attrezzi.get(3), StanzaPiena.removeAttrezzo(attrezzi.get(3).getNome()))
    }

    @Test
    fun testRemoveAttrezzoStanzaVuota() {
        assertNull(StanzaSenzaNomeVuota.removeAttrezzo(attrezzi.get(1).getNome()))
    }

    @Test
    fun testRemoveAttrezzoStanzaPienaConMultiAttrezzo() {
        for (i in 0..4) assertEquals(attrezzi.get(i), StanzaPiena.removeAttrezzo(attrezzi.get(i).getNome()))
    }

    //Test del mettodo Get Numero di Attrezzo

    //Test del mettodo Get Numero di Attrezzo
    @Test
    fun testGetNumeroAttrezzi() {
        assertEquals(10, StanzaPiena.getNumeroAttrezzi())
    }

    @Test
    fun testGetNumeroAttrezziStanzaVuota() {
        assertEquals(0, StanzaSenzaNomeVuota.getNumeroAttrezzi())
    }

    @Test
    fun testHasAttrezzo() {
        assertTrue(StanzaPiena.hasAttrezzo("Attrezzo"))
    }
}