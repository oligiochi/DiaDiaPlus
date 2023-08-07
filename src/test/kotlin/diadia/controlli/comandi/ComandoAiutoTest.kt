package diadia.controlli.comandi

import diadia.Partita
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class ComandoAiutoTest {
val aiuto=ComandoAiuto()
val partita=Partita()
    @BeforeEach
    fun setUp() {
        aiuto.setParametro("")
    }

    @Test
    fun esegui() {
        assertEquals("[Aiuto, Fine, Info, Iteragisci, Posa, Prendi, Regala, Saluta, Vai]",aiuto.esegui(partita))
    }
}