package diadia.Attrezzi

class ComparatorPerPeso():Comparator<Attrezzo> {
    override fun compare(o1: Attrezzo?, o2: Attrezzo?): Int {
        if (o2 != null && o1 != null) {
            if (o1.getPeso() - o2.getPeso() != 0){
                return o1.getPeso()-o2.getPeso()
            }else{
                return o1.compareTo(o2)
            }
        }
        return 0
    }
}