package notes_2_

fun main () {
    /////////////////////
    // arrayList
    // daha once sirasiyla listOf, setOf, mapOf ve arrayOf ogrenmistik. bunu yeni ogreniyorum.


    val meyveler = ArrayList<String> ()
    // bu sekilde de yaratilabilir:
    //val meyveler: ArrayList<String> = ArrayList ()

    meyveler.add("Cilek")
    meyveler.add("Karpuz")
    meyveler.add("Muz")
    meyveler.add("Elma")
    meyveler.add("Portakal")

    println(meyveler.get(3)) ///Elma
    println(meyveler[3]) ///Elma

    // bi indexe deger eklemek. o indexteki degerin yerine eklemek:
    meyveler[3] = "Ananas"
    println(meyveler.toString()) ///[Cilek, Karpuz, Muz, Ananas, Portakal]
    // bi indexe yeni eleman eklemek. o index ve sonraki degerleri saga kaydirmak:
    meyveler.add(3, "Kivi")
    println(meyveler.toString()) ///[Cilek, Karpuz, Muz, Kivi, Ananas, Portakal]


}