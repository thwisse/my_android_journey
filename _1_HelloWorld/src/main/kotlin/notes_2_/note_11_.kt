package notes_2_

// class 1
class Urun (var urunNo: Int, var urunAd: String, var urunFiyat: Double) {}

fun main () {
    /////////////////////
    // arrayList
    // daha once sirasiyla listOf, setOf, mapOf ve arrayOf ogrenmistik. bunu yeni ogreniyorum.


    val meyveler = ArrayList<String>()
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

    // ikisi de dizinin boyutunu veriyor.
    println(meyveler.count()) ///5
    println(meyveler.size)    ///5
    // indexi belirterek eleman silme
    meyveler.removeAt(4)
    println(meyveler.toString()) // Ananas silindi
    // elemani belirterek eleman silme
    meyveler.remove("Karpuz")
    println(meyveler.toString()) // Karpuz silindi

    meyveler.clear()
    println(meyveler.toString()) ///[]  // dizi icerigi temizlendi.

    ////////////////
    // nesnelerden olusan diziler

    val urun1 = Urun(1, "Asus TUF FX506LI", 26999.0)
    val urun2 = Urun(2,"Logitech MX Keys Eng Q Keyboard", 4427.72)
    val urun3 = Urun(3, "Logitech G305 Lightspeed Mouse", 1049.0)
    val urun4 = Urun(4, "OneOdio A70 Headset", 1827.90)
    val urun5 = Urun(5, "Samsung Galaxy S20 128 GB", 23999.0)

    val urunler = ArrayList<Urun>()

    urunler.add(urun3)
    urunler.add(urun1)
    urunler.add(urun5)
    urunler.add(urun4)
    urunler.add(urun2)

    println("\nUrunlerin kayit sirasina gore default ilkel siralama:\n")
    var i = 1

    for (urun in urunler) {
        println("------- $i -------")
        println("Urun Numarasi: ${urun.urunNo}")
        println("Urun Adi: ${urun.urunAd}")
        println("Urun Fiyati: ${urun.urunFiyat}")
        i += 1
    }

    ////////////////////
    // nesnelerden olusan dizileri farkli sekilde siralamak (sortedWith func)

    // urunNo'ya gore kucukten buyuge sirala ve siralama1 List'ine bu listeyi aktar.
    val siralama1 = urunler.sortedWith(compareBy{ it.urunNo })
    // buradaki it keywordu Urun nesnelerinin atandigi urun1, urun2 gibi degiskenleri ifade eder.
    // compareBy kucukten buyuge siraliyor.
    // buyukten kucuge siralamak icin compareByDescending kullanabilirdik.

    println("\nUrun numarasina gore siralama:\n")
    i = 1

    for (n in siralama1) {
        println("------- $i -------")
        println("Urun Numarasi: ${n.urunNo}")
        println("Urun Adi: ${n.urunAd}")
        println("Urun Fiyati: ${n.urunFiyat}")
        i += 1
    }

    // simdi de urunFiyat'ina gore kucukten buyuge siralayalim.
    val siralama2 = urunler.sortedWith(compareBy{ it.urunFiyat })

    println("\nUrun fiyatina gore siralama:\n")
    i = 1

    for (f in siralama2) {
        println("------- $i -------")
        println("Urun Numarasi: ${f.urunNo}")
        println("Urun Adi: ${f.urunAd}")
        println("Urun Fiyati: ${f.urunFiyat}")
        i += 1
    }

    ////////////////////////////
    //

}