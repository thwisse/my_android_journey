package notes_2_

open class Arac (var renk: String,
                 var tekerlekSayisi: Int) {}

open class Araba (renk: String,
                  tekerlekSayisi: Int,
                  var direksiyon: Boolean): Arac(renk, tekerlekSayisi) {}

class Tesla (renk: String,
              tekerlekSayisi: Int,
              direksiyon: Boolean,
              var teslaKasa: Boolean): Araba(renk, tekerlekSayisi, direksiyon) {}

open class Motorsiklet (renk: String,
                        tekerlekSayisi: Int,
                        var kaskRengi: String): Arac(renk, tekerlekSayisi) {}

class Kawasaki (renk: String,
                tekerlekSayisi: Int,
                kaskRengi: String,
                var kawasakiKasa: Boolean): Motorsiklet(renk, tekerlekSayisi, kaskRengi) {}

fun main () {
    // inheritence (kalitim)
    // bir class baska bir classa miras yoluyla eklenebilir.
    // ust class'a superclass, alt class'a subclass adi verilir.

    // yukardaki classlar kalitim icin bir ornektir.
    // Araba ve Motosiklet birer Arac'tir. Tesla bir Araba'dir. Kawasaki bir Motorsiklet'tir.
    // subclasslarda superclasstan gelen parametreler tekrar parametre olarak girilmelidir ancak
    // var/val ile member yapmayiz. yalnizca ekleriz. bunlarin yanina istersek subclass'a ozel
    // yeni parametreler ekleyebiliriz. ancak bunlari member yapmak istersek var/val kullaniriz.
    // bunun disinda, superclasslardan uretilen nesneler subclasslarin ozelliklerine erisemezler.

    var teslaRoadster2025 = Tesla("Kirmizi", 4, true, true)

    var kawasakiNinja250R = Kawasaki("Siyah", 2, "Beyaz", true)


}