package diadia.Attrezzi

class Attrezzo(private var nome:String, private var peso:Int) : Comparable<Attrezzo> {
    override fun compareTo(other: Attrezzo): Int {
        return this.nome.compareTo(other.nome)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val that=other as Attrezzo
        return this.nome.lowercase().equals(that.nome.lowercase())
    }

    override fun hashCode(): Int {
        return this.nome.hashCode()+this.peso
    }

    override fun toString(): String {
        return "$nome ($peso Kg)"
    }
    fun getNome()=nome
    fun getPeso()=peso
    fun setNome(nom:String){nome=nom}
    fun setPeso(pes:Int){peso=pes}
}