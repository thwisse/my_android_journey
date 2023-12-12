package package_7_

// class 1
class Car1 {
    var color = "Red"
    val brand = "Mercedes"
}

// class 2
class Car2 (var color:String, val brand:String, var running:Boolean) {

    fun ozellikleriYazdir() {
        var durum:String

        println("Renk: $color")
        println("Marka: $brand")
        if (running == true) durum = "Calisiyor" else durum = "Calismiyor"
        print("Durum: $durum")
    }
}

fun main () {

    // class 1 (Car1)

    var myCar = Car1()
    myCar.color = "Black"
    // bu kod calismayacak. cunku brand ozelligini val olarak belirttim ve
    // default degerin degisemeyecegini soyledim.
    //myCar.brand = "Volvo"
    println(myCar.color) ///Black
    // brandi degistiremedim. default ve degismez olan degeri getirdi.
    println(myCar.brand) ///Mercedes

    val yourCar = Car1()
    // bu classtan val ile de nesne uretilebilir.
    // bunu henuz neden yapmamiz gerektigini anlamadim!

    // class 2 (Car2)

    /* default degeri olmayan class olusturmak (constructors)
    classlari default sekilde olusturdugumuzda bizden icindeki ozellikler icin
    default degerleri girmemizi zorunlu kilar. ancak constructor class olusturdugumuzda
    default deger olmayan ozellikleri classa inject edebiliriz */

    var myCar2 = Car2("Gray","Volkswagen", true)
    myCar2.color = "Yellow"
    // brandi yine val olarak belirledigim icin, nesneyi olustururken ilk degerini atadim ve daha sonra
    // bu degeri degistirmeme izin vermedi. Volkwagen degerini degismez deger olarak kabul etti.
    //myCar2.brand = "Audi"
    println(myCar2.color) ///Yellow
    println(myCar2.brand) ///Volkswagen
    println(myCar2.running) ///true

    myCar2.running = false

    // yarattigim func ile ozellikleri bu sekilde direkt yazdirabilirim
    myCar2.ozellikleriYazdir()

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
}