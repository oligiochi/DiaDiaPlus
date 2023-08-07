package diadia.Attrezzi

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AttrezzoTest {
    private lateinit var attrezzotest: Attrezzo
    private lateinit var ononimo: Attrezzo
    private lateinit var stessopeso: Attrezzo
    private lateinit var completamenteDiverso: Attrezzo
    @BeforeEach
    fun setUp() {
        attrezzotest=Attrezzo("pigna",1)
        ononimo=Attrezzo("pigna",5)
        stessopeso=Attrezzo("faggiano",1)
        completamenteDiverso=Attrezzo("ala",15)
    }
    @Test
    fun compareTo() {
        assertTrue(attrezzotest.compareTo(completamenteDiverso)>0)
        assertTrue(completamenteDiverso.compareTo(attrezzotest)<0)
        assertTrue(ononimo.compareTo(attrezzotest)==0)
    }

    @Test
    fun testEquals() {
        assertTrue(attrezzotest.equals(ononimo))
        assertFalse(attrezzotest.equals(stessopeso))
    }

    @Test
    fun testToString() {
        assertEquals("pigna (1 Kg)",attrezzotest.toString())
    }

    @Test
    fun get() {
        assertEquals(15,completamenteDiverso.getPeso())
        assertEquals("ala",completamenteDiverso.getNome())
    }
}