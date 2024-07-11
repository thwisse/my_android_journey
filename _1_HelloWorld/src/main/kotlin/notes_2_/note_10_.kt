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
        println("yikamayi unutma") //ekstra eklemek istersek ekleriz
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
    // Elma tipinden bir AmasyaElmasi nesnesi:
    val amasyaElmasi: Elma = AmasyaElmasi()
    val aslan = Aslan()

    // dilersek su sekilde bir interface tipinden referans olusturabiliriz.
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
    // elma:
    ///notes_2_.Elma@5b6f7412
    ///dilimleyerek
    ///blendirdan gecirerek

    // AmasyaElmasi classinda Squeezable interfaceine ait function kullanilmamasina ragmen calisti (A1).
    // cunku AmasyaElmasi classi ile Elma classi arasinda kalitim baglantisi var. o fonksiyon
    // elma classinda var oldugu icin AmasyaElmasi classina da kalitim yoluyla gecmis oldu.

    // amasya elmasi:
    ///notes_2_.AmasyaElmasi@27973e9b
    ///dilimleyerek
    ///yikamayi unutma
    ///blendirdan gecirerek (A1)

    // aslan:
    ///notes_2_.Aslan@312b1dae
    ///yenmez ya da sikilmaz

    // tavuk:
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
    // sanki (set - get) yapilmis gibi.
    ayakkabi1.indirim = false
    println(ayakkabi1.indirim)

}