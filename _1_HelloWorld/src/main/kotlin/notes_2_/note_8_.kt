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

    fun elektrikliSarj () {
        println("Sarj ediliyor.")
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
    // polymorphism (cok bicimlilik)
    // Polimorfizm, bir nesnenin farklı şekillerde davranabilme yeteneğini ifade eder.
    // Kotlin gibi nesne yönelimli programlama dillerinde, polimorfizm, bir üst sınıfın metotlarının
    // alt sınıflar tarafından farklı şekillerde uygulanabilmesini sağlar.

    // overriding

    // Overriding bir alt sınıfın, üst sınıfın metotlarını yeniden tanımlayarak kendi
    // ihtiyaçlarına göre uyarlamasını ifade eder. Yani, alt sınıf, üst sınıfın metotlarını
    // aynı isimle ve aynı parametrelerle yeniden tanımlayarak, farklı bir davranış sergileyebilir.

    // bir superclasstaki functionu subclassta ayni ya da farkli islemler icin kullanabilmek mumkundur.
    // superclasstaki functionu open ettikten sonra subclassta override fun ile olusturabiliriz.
    // function open ile olusturulduysa tum subclasslarda o fonksiyona her turlu erisilir.

    val arac = Arac()
    arac.araciCalistir()
    ///Arac calisti.

    // func bunlarin icinde tekrar yazilmamis olmasina ragmen erisilebildi.
    // arac classindaki functionu calistirdilar. fonksiyonu override etmis olduk.
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

    //////////////////////////
    // tip kontrolu (is keywordu)
    // bir degiskenin hangi siniftan turetildigini ogrenmemize yarar.
    var degisken = 10
    if (degisken is Int) { println("Integer.") } else { println("Not integer.") } ///Integer.

    val teslaX = Tesla()
    println(teslaX is Tesla) ///true

    //////////////////////
    // upcasting (yukseltme)
    // subclasstan olusmus bir referansi ya da nesneyi, superclasstan olusan bir referansa atamaya denir.
    // burada Tesla ve Kawasaki alt classindan olan nesneleri, Arac ust classindan olusmus referanslara atiyoruz.
    val arac2: Arac = Tesla("Mor", 4, true, true)
    val arac3: Arac = Kawasaki()
    // alt siniftan olan nesneyi ust sinifa donusturduk!
    arac2.araciCalistir()
    arac3.araciCalistir()
    ///Tesla calisti.
    ///Kawasaki calisti.
    // normalde teslaya ya da kawasakiye ozel olarak override edilmis fonksiyonu superclass ile yaratilmis
    // bir nesne ile kullandik.

    /////////////////////////
    // downcasting (alcaltma) (as keyword)
    // üst sınıfın referansını alt sınıfın referansına dönüştürerek,
    // alt sınıfa özgü özelliklere ve işlevlere erişmemizi sağlar.
    // note_9_ da terfiEttir fonksiyonu icinde ornegi var.

    val yeniArac: Arac = Tesla()

    if (yeniArac is Tesla) {
        val yeniTesla: Tesla = yeniArac as Tesla
        yeniTesla.elektrikliSarj()
    }
    // normalde burada bunun ornegi yoktu. ama internetten bakarak buna da bi ornek uyarladim ama
    // ben buradakini anlamadim. ilerde belki anlamli olur diye bu ornegi burada birakiyorum.
    // note_9_daki ornek biraz daha anlamli geldi. ama onu da tam olarak anladigim soylenemez su an.

}