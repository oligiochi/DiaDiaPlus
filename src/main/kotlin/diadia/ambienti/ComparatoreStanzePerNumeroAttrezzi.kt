package diadia.ambienti

import diadia.ambienti.stanze.Stanza


class ComparatoreStanzePerNumeroAttrezzi : Comparator<Stanza?> {
    override fun compare(s1: Stanza?, s2: Stanza?): Int {
        return if (s1 == null && s2 == null) 0 else {
            if (s1 == null) return -1
            if (s2 == null) 1 else s1.getListaDiAttrezzi().size - s2.getListaDiAttrezzi().size
        }
    }
}

