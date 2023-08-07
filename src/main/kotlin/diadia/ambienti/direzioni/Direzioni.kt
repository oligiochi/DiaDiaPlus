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
        fun upperValuteOf(direzione:String):Direzioni?{
            return try {
                valueOf(direzione.uppercase())
            }catch (e:IllegalArgumentException){
                null
            }
        }
    }
}