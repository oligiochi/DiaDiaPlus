package diadia.ambienti.direzioni

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DirezioniTest {

    @Test
    fun values() {
        assertArrayEquals(Direzioni.values(), arrayOf(Direzioni.NORD,Direzioni.EST,Direzioni.SUD,Direzioni.OVEST))
    }

    @Test
    fun valueOf() {
        assertEquals(Direzioni.NORD,Direzioni.upperValuteOf("nord"))
        assertEquals(Direzioni.SUD,Direzioni.upperValuteOf("sud"))
        assertEquals(Direzioni.EST,Direzioni.upperValuteOf("est"))
        assertEquals(Direzioni.OVEST,Direzioni.upperValuteOf("ovest"))
    }
    @Test
    fun GetOpposta(){
        assertEquals(Direzioni.NORD,Direzioni.getopposta(Direzioni.SUD))
        assertEquals(Direzioni.SUD,Direzioni.getopposta(Direzioni.NORD))
        assertEquals(Direzioni.EST,Direzioni.getopposta(Direzioni.OVEST))
        assertEquals(Direzioni.OVEST,Direzioni.getopposta(Direzioni.EST))
    }
}