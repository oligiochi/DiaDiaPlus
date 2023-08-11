package diadia.controlli.comandi

import diadia.Partita
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ComandoFineTest{
    private var partita: Partita? = null
    private var Fine: ComandoFine? = null

    @BeforeEach
    fun setUp() {
        partita = Partita()
        Fine = ComandoFine()
    }

    @Test
    fun fineTest() {
        assertEquals("Grazie Di Aver Giocato", Fine!!.esegui(partita!!))
    }
}