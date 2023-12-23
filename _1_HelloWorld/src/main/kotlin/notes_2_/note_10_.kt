package notes_2_

interface Squeezable {
    fun howToSqueeze () {}
}
interface Eatable {
    fun howToEat () {}
}

// bir class birden fazla interface alabilir:
open class Elma (): Eatable, Squeezable {
    override fun howToEat() {
        println("dilimleyerek")
    }
    override fun howToSqueeze() {
        println("blendirdan gecirerek")
    }
}

// bu subclassa interface yazmadim ancak yine de kullanabilirim. cunku superclassta interface kullanildi.
// yine de istersem bu kalitimin ustune yine virgulle interface de ekleyebilirim.
class AmasyaElmasi (): Elma () {
    override fun howToEat() {
        super.howToEat()
        println("yikamayi unutma")
    }
}

class Tavuk (): Eatable {
    override fun howToEat() {
        println("firinda kizartarak")
    }
}

class Aslan () {
    // yenmez ya da sikilmaz
}

//////////////////////////////////////
// ustteki classlar interface yapisi icin olusturulmus classlardir.
// alttaki class ise encapsulation icindir.
class Ayakkabi {
    // en temel encapsulation private ile gerceklesir. ancak private ile olusan ozelliklere hicbir sekilde
    // erisilemedigi icin bu bazen kullanisli olmaz.
    private var marka: String

    var tarz: String
        set
        get

    var numara: Int
        private set
        get

    var indirim: Boolean

    constructor(marka: String, tarz: String, numara: Int, indirim: Boolean) {
        this.marka = marka
        this.tarz = tarz
        this.numara = numara
        this.indirim = indirim
    }
}

fun main () {
    ///////////////////////////////
    // abstract (soyutlama)
    // abstract class olusturdugumuzda soyut bir sinif olusturmus oluruz ve soyut siniflardan
    // instance (ornek ya da nesne) uretilemez.
    // en bilindik abstract sinif turu interface'dir.
    // abstract keywordu ile de class olusturulabilir ancak bunu ilerde lazim olursa ogrenirim.

    ////////////////////
    // interface
    // bir class taslagi olarak dusunulebilir. genelde interfaceler able eki ile biter.
    // interfacelerin inheritance'dan farki sudur: bir classa birden fazla kalitim yapilamaz.
    // ancak bir classa birden fazla interface implemente edilebilir. iste bu implementation islemi ile
    // inheritance islemleri birbirinden farklidir. interfaceler iki siniftan birden ozellik cekebilmemize yarar.

    val elma = Elma()
    // elma tipinden bir AmasyaElmasi nesnesi:
    val amasyaElmasi: Elma = AmasyaElmasi()
    val aslan = Aslan()
    // dilersek bu sekilde bir interface tipinden referans olusturabiliriz.
    // ona da bir Tavuk nesnesi atadim.
    // ilerde kullanabiliriz diye burada boyle bir ornek yaptik.
    val tavuk: Eatable = Tavuk()

    val dizi = arrayOf(elma, amasyaElmasi, aslan, tavuk)

    for (x in dizi) {
        println(x.toString())

        if (x is Eatable) {
            x.howToEat()
        }
        if (x is Squeezable) {
            x.howToSqueeze()
        }
        // !is kullanimini da burada ilk kez gormus olduk. rastgele kesfettim oyle :d
        if (x !is Squeezable && x !is Eatable) {
            println("yenmez ya da sikilmaz")
        }
    }
    ///notes_2_.Elma@5b6f7412
    ///dilimleyerek
    ///blendirdan gecirerek

    // AmasyaElmasi classinda Squeezable interfaceine ait function kullanilmamasina ragmen calisti.
    // cunku AmasyaElmasi classi ile Elma classi arasinda kalitim baglantisi var. o fonksiyon
    // elma classinda var oldugu icin AmasyaElmasi classina da kalitim yoluyla gecmis oldu.
    ///notes_2_.AmasyaElmasi@27973e9b
    ///dilimleyerek
    ///yikamayi unutma
    ///blendirdan gecirerek

    ///notes_2_.Aslan@312b1dae
    ///yenmez ya da sikilmaz

    ///notes_2_.Tavuk@7530d0a
    ///firinda kizartarak

    /////////////////////////////////
    // Encapsulation
    // Ayakkabi classi bu konu icin olusturulmustur.
    // classlardaki ozelliklere ne olcude erisilebilecegini belirlemeye yarar.

    var ayakkabi1 = Ayakkabi("Nike", "Spor", 42, true)

    // marka ozelligi (private) oldugu icin ne degistirebiliyorum ne de okuyabiliyorum.
    //ayakkabi1.marka = "Adidas"
    //println(ayakkabi1.marka)

    // tarz ozelligi (set - get) oldugu icin hem degistirebilirim hem okuyabilirim.
    ayakkabi1.tarz = "Kislik"
    println(ayakkabi1.tarz)

    // numara ozelligi (private set - get) oldugu icin set edemiyorum ancak get edebiliyorum.
    //ayakkabi1.numara = 36
    println(ayakkabi1.numara)

    // indirim ozelliginde de hicbir kontrol yapilmadigi icin istedigimizi yapariz
    ayakkabi1.indirim = false
    println(ayakkabi1.indirim)

}

/*

package projects_1_

import java.util.Scanner
import kotlin.random.Random

// Bir okul kayit uygulamasi yap. Ogrencinin isimSoyisim'ni kullanici girsin. Ogrencinin okulNo'sunu
// 100 ile 1000 arasindaki degerlerden sen rastgele olarak belirle.

class Ogrenci (var isimSoyisim: String, var okulNo: Int) {}

fun main() {
    var secim: Int

    val ogrenciList = ArrayList<Ogrenci>()

    fun okulNoYarat (): Int {
        val yeniNo = Random.nextInt(100, 1000)
        return yeniNo
    }

    println("Yeni ogrenci kaydi programina hosgeldiniz.")

    do {

        println("1- Yeni Kayit\n2- Ogrencileri Sirala\n3- Okul Mevcudunu Goruntule\n4- Ogrenci Sil\n5- Cikis Yap")
        print("Yapmak istediginiz secimi giriniz: ")
        val girdi = Scanner(System.`in`)

        try {
            secim = girdi.next().toInt()
        } catch (e: NumberFormatException) {
            println("Bir hata oldu. Lutfen tekrar girin.")
            continue
        }

        girdi.nextLine() // fflush islemi

        when (secim) {
            1 -> {
                var isimSoyisim = "isim soyisim"

                do {
                    print("Kaydi yapilacak ogrencinin adi soyadi: ")
                    val girdi2 = Scanner(System.`in`)
                    // useDelimeter fonksiyonu ile string icindeki bosluklari da stringe dahil etmesini istedik.
                    // aksi taktirde girdiyi yazdirdigimda bosluktan sonrasini yazdirmiyordu.
                    girdi2.useDelimiter("\n")

                    try {
                        isimSoyisim = girdi2.next().toString()

                        girdi2.nextLine()  // fflush islemi
                    } catch (e: NumberFormatException) {
                        println("Bir hata oldu. Lutfen tekrar girin.")
                        continue
                    }
                    break
                } while (true)

                val okulNo = okulNoYarat()
                println("Ogrenci kaydi basariyla yapildi. Yeni okul numarasi belirlendi: $okulNo")
                ogrenciList.add(Ogrenci(isimSoyisim, okulNo))
            }
            2 -> {
                //TODO ogrenci listesini okul numarasina gore mi yoksa isme gore mi sirala diye sor

                println("----------- Ogrenci Listesi -----------\n")
                for (i in ogrenciList) {
                    println("Okul No: ${i.okulNo}")
                    println("Ogrenci adi soyadi: ${i.isimSoyisim}")
                }
            }
            3 -> {
                println("Okul mevcudu: ${ogrenciList.size}")
            }
            4 -> {
                //TODO burayi yarim biraktim. tamamlanmali. bide ogrenci nolari ayni olan ogrenci olusmasini engelle

                var silNo = 0

                do {
                    print("Silinmesini istediginiz ogrencinin numarasini giriniz: ")
                    var girdi3 = Scanner(System.`in`)

                    try {
                        silNo = girdi3.next().toInt()

                        girdi3.nextLine()  // fflush islemi

                        for (nesne in ogrenciList) {
                            if (nesne.okulNo == silNo) {
                                ogrenciList.remove(nesne)
                                break
                            }
                        }
                        println("Ogrenci basariyla silindi.")

                    } catch (e: NumberFormatException) {
                        println("Bir hata oldu. Lutfen tekrar girin.")
                        continue
                    }
                    break
                } while (true)
            }
            5 -> {
                println("Cikis yapiliyor...")
                break
            }
            else -> {
                println("Hatali giris. Tekrar deneyin.")
            }
        }
    } while (true)
}

 */