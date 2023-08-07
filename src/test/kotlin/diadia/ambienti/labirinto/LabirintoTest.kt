package diadia.ambienti.labirinto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LabirintoTest {
    private lateinit var Lab:Labirinto
    @BeforeEach
    fun setUp() {
        Lab= Labirinto()
    }
    @Test
    fun getEntrata() {
        assertEquals("Bagno",Lab.getEntrata().getNome())
        assertEquals("[fiamma (5 Kg), lanterna (1 Kg)]",Lab.getEntrata().getListaDiAttrezzi().toString())
        assertEquals("Bagno\nUscite:\n[NORD]\nAttrezzi nella stanza:\n[fiamma (5 Kg), lanterna (1 Kg)]",Lab.getEntrata().getDescrizione())
    }

    @Test
    fun getUscita() {
        assertEquals("Atrio",Lab.getUscita().getNome())
        assertEquals("[lubrificante (10 Kg)]",Lab.getUscita().getListaDiAttrezzi().toString())
        assertEquals("Atrio\nUscite:\n[SUD]\nAttrezzi nella stanza:\n[lubrificante (10 Kg)]",Lab.getUscita().getDescrizione())
    }
}