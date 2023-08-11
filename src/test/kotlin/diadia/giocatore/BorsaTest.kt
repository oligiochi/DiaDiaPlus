package diadia.giocatore

import diadia.Attrezzi.Attrezzo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BorsaTest {
    private lateinit var BorsaVuota : Borsa
    private lateinit var BorsaNonRiempibile : Borsa
    private lateinit var BorsaPiena : Borsa
    @BeforeEach
    fun setUp() {
        BorsaVuota=Borsa()
        BorsaNonRiempibile=Borsa(0)
        BorsaPiena=Borsa()
        BorsaPiena.addAllAttrezzi(listOf(Attrezzo("spugna",2),Attrezzo("bastone",1),Attrezzo("piombo",5),Attrezzo("anello",3),Attrezzo("incudine",9)))
    }

    @Test
    fun getPeso() {
        assertEquals(0,BorsaVuota.getPeso())
        assertEquals(0,BorsaNonRiempibile.getPeso())
        assertEquals(20,BorsaPiena.getPeso())
    }
    @Test
    fun getPesoMax() {
        assertEquals(0,BorsaVuota.getpesoMax())
        assertEquals(0,BorsaNonRiempibile.getpesoMax())
        assertEquals(0,BorsaPiena.getpesoMax())
    }
    @Test
    fun ifEmpty() {
        assertTrue(BorsaVuota.ifEmpty())
        assertTrue(BorsaNonRiempibile.ifEmpty())
        assertFalse(BorsaPiena.ifEmpty())
    }

    @Test
    fun getContenutoOrdinatoPerNome() {
        assertEquals(BorsaPiena.getContenutoOrdinatoPerNome().first(),Attrezzo("anello",3))
        assertEquals(BorsaPiena.getContenutoOrdinatoPerNome().last(),Attrezzo("spugna",2))
    }

    @Test
    fun getContenutoOrdinatoPerPeso() {
        assertEquals(BorsaPiena.getContenutoOrdinatoPerPeso().first(),Attrezzo("bastone",1))
        assertEquals(BorsaPiena.getContenutoOrdinatoPerPeso().last(),Attrezzo("incudine",9))
    }

    @Test
    fun hasAttrezzi() {
        assertTrue(BorsaPiena.hasAttrezzi("bastone"))
        assertFalse(BorsaVuota.hasAttrezzi("bastone"))
    }

    @Test
    fun addAttrezzo() {
        assertTrue(BorsaVuota.addAttrezzo(Attrezzo("spugna",2)))
        assertFalse(BorsaNonRiempibile.addAttrezzo(Attrezzo("spugna",2)))
    }

    @Test
    fun removeAttrezzo() {
        assertEquals(Attrezzo("spugna",2),BorsaPiena.removeAttrezzo("spugna"))
        assertNull(BorsaNonRiempibile.removeAttrezzo("spugna"))
    }

    @Test
    fun testToString() {
        assertEquals("Borsa Vuota",BorsaVuota.toString())
        assertEquals("Contenuto borsa (20 Kg/20 Kg): attrezzi=[spugna (2 Kg), bastone (1 Kg), piombo (5 Kg), anello (3 Kg), incudine (9 Kg)]",BorsaPiena.toString())
    }
}