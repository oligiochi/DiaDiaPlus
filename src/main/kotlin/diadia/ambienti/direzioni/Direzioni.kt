package diadia.ambienti.direzioni

enum class Direzioni {
    NORD, EST,SUD, OVEST;

    fun getopposta(): Direzioni {
       return getopposta(this)
    }

    companion object {
        fun getopposta(a : Direzioni): Direzioni{
            return Direzioni.values()[(a.ordinal+2)%4]
        }
    }
}