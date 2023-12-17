package notes_2_

open class Arac (var renk: String = "",
                 var tekerlekSayisi: Int = 0) {

    open fun araciCalistir () {
        println("Arac calisti.")
    }
}

open class Araba (renk: String = "",
                  tekerlekSayisi: Int = 0,
                  var direksiyon: Boolean = false): Arac(renk, tekerlekSayisi) {}

class Tesla (renk: String = "",
              tekerlekSayisi: Int = 0,
              direksiyon: Boolean = false,
              var teslaKasa: Boolean = false): Araba(renk, tekerlekSayisi, direksiyon) {
    override fun araciCalistir() {
        println("Tesla calisti.")
    }
}

open class Motorsiklet (renk: String = "",
                        tekerlekSayisi: Int = 0,
                        var kaskRengi: String = ""): Arac(renk, tekerlekSayisi) {}

class Kawasaki (renk: String = "",
                tekerlekSayisi: Int = 0,
                kaskRengi: String = "",
                var kawasakiKasa: Boolean = false): Motorsiklet(renk, tekerlekSayisi, kaskRengi) {
    override fun araciCalistir() {
        println("Kawasaki calisti.")
    }
}

fun main () {
    ///////////////////////////
    // inheritence (kalitim)
    // bir class baska bir classa miras yoluyla eklenebilir.
    // ust class'a superclass, alt class'a subclass adi verilir.

    // yukardaki classlar kalitim icin bir ornektir.
    // Araba ve Motosiklet birer Arac'tir. Tesla bir Araba'dir. Kawasaki bir Motorsiklet'tir.
    // subclasslarda superclasstan gelen parametreler tekrar parametre olarak girilmelidir ancak
    // var/val ile member yapmayiz. yalnizca ekleriz. bunlarin yanina istersek subclass'a ozel
    // yeni parametreler ekleyebiliriz. ancak bunlari member yapmak istersek var/val kullaniriz.
    // bunun disinda, superclasslardan uretilen nesneler subclasslarin ozelliklerine erisemezler.
    // not: parametrelere default degerleri bilerek atadim kolaylik olsun diye. zorunlu degil.

    var teslaRoadster2025 = Tesla("Kirmizi", 4, true, true)

    var kawasakiNinja250R = Kawasaki("Siyah", 2, "Beyaz", true)

    //////////////////////////
    // polymorphism (cok bicimlilik) ve overriding
    /* Polimorfizm, bir nesnenin farklı şekillerde davranabilme yeteneğini ifade eder.
    Kotlin gibi nesne yönelimli programlama dillerinde, polimorfizm, bir üst sınıfın metotlarının
    alt sınıflar tarafından farklı şekillerde uygulanabilmesini sağlar.

    Overriding ise bir alt sınıfın, üst sınıfın metotlarını yeniden tanımlayarak kendi
    ihtiyaçlarına göre uyarlamasını ifade eder. Yani, alt sınıf, üst sınıfın metotlarını
    aynı isimle ve aynı parametrelerle yeniden tanımlayarak, farklı bir davranış sergileyebilir. */

    // bir superclasstaki functionu subclassta kullanabilmek icin kullaniriz.
    // superclasstaki functionu open ettikten sonra subclassta override fun ile olusturabiliriz.
    // function open ile olusturulduysa tum subclasslarda o fonksiyona her turlu erisilir.

    val arac = Arac()
    arac.araciCalistir()
    ///Arac calisti.

    // func bunlarin icinde tekrar yazilmamis olmasina ragmen erisilebildi.
    // arac classindaki functionu calistirdilar.
    val araba = Araba()
    araba.araciCalistir()
    val motorsiklet = Motorsiklet()
    motorsiklet.araciCalistir()
    ///Arac calisti.
    ///Arac calisti.

    // ayni isimle classa gore farkli is yapan override edilmis funclar calisti.
    val tesla = Tesla()
    tesla.araciCalistir()
    val kawasaki = Kawasaki()
    kawasaki.araciCalistir()
    ///Tesla calisti.
    ///Kawasaki calisti.

    // fonksiyonu override etmeye calistigimda default olarak "super.araciCalistir()" kodu gelmisti.
    // bu da superclasstaki fonksiyonu calistir anlamina geliyor. kontrol amacli var. zaten fonksiyonu
    // olusturmasan da subclass ile fonksiyonu cagirabildigin icin onu kullanmanin geregi yok.

    // superclasslarin subclass ozelligi tasiyan nesnelerinin yaratilmasi:
    val arac2: Arac = Tesla()
    val arac3: Arac = Kawasaki()
    arac2.araciCalistir()
    arac3.araciCalistir()
    ///Tesla calisti.
    ///Kawasaki calisti.
    // normalde teslaya ya da kawasakiye ozel olarak override edilmis fonksiyonu superclass ile yaratilmis
    // bir nesne ile kullandik. bunu superclass ile yaratilmis nesneyi subclass'a esitleyerek yapabildik.
}