package notes_2_

import java.util.Scanner
import kotlin.math.sin

// class 1
class Car3 {
    var color = "Red"
    val brand = "Mercedes"
}

/*
// class 2
class Car4 (var color:String, val brand:String, var running:Boolean) {

    fun ozellikleriYazdir() {
        var durum:String

        println("Renk: $color")
        println("Marka: $brand")
        if (running == true) durum = "Calisiyor" else durum = "Calismiyor"
        print("Durum: $durum")
    }
}
*/

// class 3
class kisi (var isim: String, var yas: Int) {
    init {
        println("Yeni kisi nesnesi olusturuldu.")
    }
}

// class 4
class sinif_a {
    companion object {
        var sayi = 69

        fun func () {
            println("altmis dokuz")
        }
    }
}

// class 5
enum class bardaklar {
    Shot, Kucuk, Orta, Buyuk
}

// class 6
class adresBilgisi (var il: String, var ilce: String) {}

// class 7
class temelBilgiler (var isim: String, var yas: Int, var adres: adresBilgisi) {}
// adresBilgisi classindan yaratilmis bir nesneyi parametre olarak girmeyi sart kosuyor.

// class 8
class birClass {

    var id: Int? = null

    var isim: String? = null

    // bu sekilde secondary constructor olusturulabiliyor. bunu sag tik yapip otomatik de generate edebiliyosun.
    // hatta istersen bunu da ampul simgesine basarak primary constructor'a cevirebiliyosun.
    // teknolojinin gozunu seveyim.
    constructor(id: Int?, isim: String?) {
        this.id = id
        this.isim = isim
    }
}

fun main () {
    // class 1 (Car3)

    var myCar3 = Car3()
    myCar3.color = "Black"
    // bu kod calismayacak. cunku brand ozelligini val olarak belirttim ve
    // default degerin degisemeyecegini soyledim.
    //myCar.brand = "Volvo"
    println(myCar3.color) ///Black
    // brandi degistiremedim. default ve degismez olan degeri getirdi.
    println(myCar3.brand) ///Mercedes

    val yourCar = Car3()
    // bu classtan val ile de nesne uretilebilir.
    // bunu henuz neden yapmamiz gerektigini anlamadim!

    /* Car4 classini ve ilgili kodlari yorum satirina aliyorum cunku sebepsizce hata aliyorum.
    // class 2 (Car4)

    /* default degeri olmayan class olusturmak (constructors)
    classlari default sekilde olusturdugumuzda bizden icindeki ozellikler icin
    default degerleri girmemizi zorunlu kilar. ancak constructor class olusturdugumuzda
    default deger olmayan ozellikleri classa inject edebiliriz */

    var myCar4 = notes_1_.Car4("Gray", "Volkswagen", true)
    myCar4.color = "Yellow"
    // brandi yine val olarak belirledigim icin, nesneyi olustururken ilk degerini atadim ve daha sonra
    // bu degeri degistirmeme izin vermedi. Volkwagen degerini degismez deger olarak kabul etti.
    //myCar4.brand = "Audi"
    println(myCar4.color) ///Yellow
    println(myCar4.brand) ///Volkswagen
    println(myCar4.running) ///true

    myCar4.running = false

    // yarattigim func ile ozellikleri bu sekilde direkt yazdirabilirim
    myCar4.ozellikleriYazdir()
    */

    ///////////////////////
    // null safety ozelligi

    // null olabilecek bir stringi degiskene atarken "bu null olabilir, bunu kontrol altina al"
    //anlamina gelen ? operatorunu String? seklinde kullaniriz.
    // ? koyduk ve bu degisken null deger alabilir dedik. ve null deger atadik.
    var str1:String? = null
    // artik degiskene herhangi bir fonk uygulamak istedigimizde bize uyari verecektir
    //str1.trim()
    /* Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
    uyarida bu yaziyor. null degerli bir string degiskene bu fonk uygulanamaz diyor.
    eger kontrol ederek uygula (?) ya da ille uygula (!!) diyorsan bunu belirt diyor. */
    // simdi degiskene ? ekleyerek diyoruz ki "bu degisken null degilse calistir, null ise bu fonksiyonu calistirma"
    str1?.trim()
    // degiskenin degeri null oldugu icin fonksiyon calismadi ancak hata da vermedi. null safe olmus.
    // !! kullandigimizda ise, string null'sa bile ne olursa olsun calistir diyoruz.
    // iste bunda hata aliyoruz. cunku string null. sadece null olmayacagina emin oldugumuzda bunu kullanabiliriz.
    //str1!!.trim()
    // hata: NullPointerException
    // bu null degilse sunu yap null sa sunu yap muhabbetini if ile de kontrol edebilirdik. bu onun kisayolu.

    // kodun icinde ? ve !! olmasini istemiyorsak ve kodumuza guveniyorsak!
    // lateinit keywordu ile bunu belirtebiliriz.
    var str2:String? = null
    lateinit var str3:String
    // bu ikisi ayni anlama gelmektedir. ? = null kismini yazmamiza ve degiskeni kullanirken
    // ? ve !! kullanmamiza gerek kalmaz
    //str3?.trim()
    //str3!!.trim()    // ikisi de hata verecegi icin ikisini de yorum satirina aldim
    // uyari: Unnecessary safe call on a non-null receiver of type String
    // yani bu ? ve !! isaretlerini gereksiz yere kullaniyorsun diyor. zaten lateinit dedin diyor.
    // eger lateinit kullandiysan zaten String null olmamali ve ? ve !! kullanmana gerek yok diyor

    // overloading
    // fonksiyon adini degistirmeden, farkli parametreler tanimlayarak yeni fonksiyonlar uret.
    fun topla (a: Int, b: Int) {println("Toplam: ${a + b}")}
    fun topla (a: Int, b: Double) {println("Toplam: ${a + b}")}
    fun topla (a: Double, b: Double) {println("Toplam: ${a + b}")}
    topla(5,8)
    topla(5,8.0)
    topla(5.0,8.0)

    // vararg keywordu
    // fonksiyon parametresi kisminda dizi gibi calisan bir parametre kullanmamizi saglayan keyword.
    fun ortHesapla(vararg sayilar: Int): Double {
        var sonuc = 0
        for (i in sayilar) {sonuc = sonuc + i}
        var ortalama = sonuc.toDouble() / sayilar.size
        return ortalama
    }
    val ortalama = ortHesapla(15,74,52,63,58,69)
    println("Puanlarin ortalamasi: $ortalama") ///55.166

    ////////////////////
    // extension functions
    // kotlindeki default siniflari gelistirmemizi ve kendimize gore
    // dizayn etmemizi saglayan fonksiyonlar yaratabiliriz.

    // ornegin Int sinifina bir fonksiyon ekledik. artik herhangi int bir degerden sonra .carpi(sayi)
    // yaparak o sayiyi "sayi" ile carpabiliriz.
    infix fun Int.carpi(sayi: Int): Int {return this * sayi}
    // buradaki this bastaki Int'i yani Int sinifini temsil eder
    var sonuc = 5.carpi(2)
    println(sonuc)
    // extra olarak infix keywordunu ekledigimizda cok daha pratik kullanabiliriz
    sonuc = 6 carpi 4
    println(sonuc)

    ////////////////////
    // init blogu ornek
    // class 3 (kisi)

    // icinde init blogu bulunan classtan bir nesne olusturuldugunda
    // islem yap ya da yapma init blogu calisacaktir.
    var kisi1 = kisi("Oguzhan", 23)
    println(kisi1.isim + " - " + kisi1.yas)
    ///Yeni kisi nesnesi olusturuldu.
    ///Oguzhan - 23

    val girdi = Scanner(System.`in`)
    // java.util.Scanner import edildi. eger java.util.* yaziyor olsaydi bu tum paketin import edildigi
    // anlamina gelecekti. burada ise sadece scanner ozelligi import edildi.

    //////////////////////
    // data class
    // genelde veritabani tablolarinin modelleri icin kullanilir

    ///////////////////
    // class 4 (sinif_a)
    // static degisken ve fonksiyonlar (companion object)
    // bir classtan nesne olusturmadan degisken ve fonksiyonlarina erismek icin kullanilir
    // cok sik kullanmamak gerekir. performansi etkiler.

    sinif_a.sayi = 46
    sinif_a.func()
    // bu sekilde nesne olusturmadan bu static degisken ve fonksiyonlari kullanabildik.

    ////////////////////
    // class 5 (bardaklar)
    // enumeration (enum)
    // parametrelerde kullanilir. verilerin eslestirilmesi sonucunda islem yapilabilir.
    // yazilimciyi detaydan kurtarir ve verimlilik saglar.
    // class'in basina enum keywordu getirilerek yapilir.

    fun ucretOgren (boyut: bardaklar): Int {
        var ucret = 5
        when (boyut) {
            bardaklar.Shot -> ucret *= 20
            bardaklar.Kucuk -> ucret *= 40
            bardaklar.Orta -> ucret *= 60
            bardaklar.Buyuk -> ucret *= 70
        }
        return ucret
    }
    println(ucretOgren(bardaklar.Shot).toString() + " TL") ///100 TL

    /////////////////////////
    // composition yapisi
    // class 6 (adresBilgisi) ve class 7 (temelBilgiler)

    // bir classta baska bir classin nesnesini parametre olarak alabiliriz.
    // bu sekilde ic ice class yapisi kurarak nesnenin nesnesine erisebiliriz.
    // dependency injection ile composition farkli seylerdir.

    val adres2 = adresBilgisi("Hatay", "Iskenderun")
    val kisi2 = temelBilgiler("Oguzhan", 23, adres2)
    // adres2 nesnesini arguman olarak kisi2 nesnesinde kullandik.
    println(kisi2.isim)
    println(kisi2.yas)
    println(kisi2.adres.il)
    println(kisi2.adres.ilce)
    ///Oguzhan
    ///23
    ///Hatay
    ///Iskenderun

    ///////////////////////
    // farkli bir constructor (secondary cons) olusturma sekli
    // class 8 (birClass)'a bak.
}