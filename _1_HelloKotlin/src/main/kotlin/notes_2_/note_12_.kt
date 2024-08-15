package notes_2_

import java.lang.ArithmeticException
import java.util.*

// kalitim yoluyla (Thread classi) thread kullanimi.
class KalitimThread: Thread() {
    override fun run() {
        for (i in 100..200){
            println("Birinci thread: $i")
            Thread.sleep(1000)
        }
    }
}
// interface (Runnable) yoluyla thread kullanimi.
class InterfaceThread: Runnable {
    override fun run() {
        for (i in 100..200){
            println("Ikinci thread: $i")
            Thread.sleep(1000)
        }
    }
}

fun main () {
    ////////////////
    // strings

    // uc cift tirnak ile string:
    // bu sekilde paragraflari uzun yazilari vs rahatlikta kullanabiliriz.
    val message = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod 
    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
    """.trimIndent()
    // trimIndent fonksiyonu otomatik eklendi. bu fonk sayesinde yazidan onceki tablar trimleniyor.
    // istersek o fonksiyonu ordan kaldirip kafamiza gore tab koyabiliriz.
    println(message)

    // farkli bir bos (Empty) string olusumu:
    var isim = String()
    var isim2 = ""
    // bu ikisi ayni isi yapar. ve isEmpty ile kontrol edildiginde true donerler. lenght'leri 0 dir.

    //////////////////////////
    // string fonksiyonlari
    // stringler birer dizi oldugu icin karakterlere parcalayip farkli islemler yapabiliriz.

    // subSequence
    var nickname = "thwisse"
    println(nickname.subSequence(0,3)) ///thw
    // t-0, h-1, w-2, i-3
    // ilk indexten (inclusive) basla 3 e kadar (3 dahil degil - exclusive) olanlari al dedik.
    // boyle de kullanilabilir
    var nickname2 = nickname.subSequence(0,3)
    println(nickname2) ///thw
    // sonucta bu fonskiyonlarin dondugu bir deger var. bunlari bir degiskene atayarak kullanabiliriz.

    // contains
    println(nickname.contains("isse")) ///true

    // toUpperCase() ve toLowerCase() kullanilabilir. geciyorum.

    // split
    // bosluklardan olusan bir stringi bosluklardan ayirarak parcalanan stringleri bir diziye aktarabiliriz.
    val siir = "tahir olmak da ayip degil zuhre olmak da"
    val dizi = siir.split(' ')
    for (k in dizi) {
        print("$k, ")
    } ///tahir, olmak, da, ayip, degil, zuhre, olmak, da,
    println("")

    // trim
    // string basindaki ve sonundaki whitespaceleri (bosluklari) silerek geri donduren fonksiyon.
    val siir2 = "     her zaman elimi tut diye var olacagim seninle     "
    println(siir2.trim() + "(burda bitti)")
    ///her zaman elimi tut diye var olacagim seninle(burda bitti)

    // string.length-1
    // bize stringin son indexini verir.
    // ornegin stringi tersten yazdiracak olsak for (i in string.length-1 downTo 0) yapabiliriz.

    ////////////////////////
    // try catch yapilanmasi

    var a : Int
    val dizi2 = Array<Int>(2){0} // 2 degerli bir dizi olusturduk

    try {
        a = 10/0 // 10u 0a bolmeye calistik

        dizi2[4] = 15 // olmayan indexe deger atamaya calistik

    } catch (e: Exception) {
        if (e is ArithmeticException) {
            println("Bu sayi sifira bolunmez")
        }
        if (e is ArrayIndexOutOfBoundsException) {
            println("Dizinin boyutu asildi")
        }
    }
    ///Bu sayi sifira bolunmez
    // bu kullanimla birden fazla catch kullanimi ayni isi gorur.
    // birden fazla farkli hatayi ayni try icinde barindirmak mantiksiz.
    // cunku e ilk neye esitlenirse o if'e giriyor ve digerine hic bakamiyor.
    // ama bunu da sunun icin kullanabiliriz: eger hata bu ise bu ife gir, bu ise buna gir.
    // digerine bakmadan cik. boyle bir ihtiyacimiz olursa kullanilabilir.
    // bunun disinda her bir olasi hata icin farkli try catchler olusturmak en mantiklisi.
    // veya hatanin ne olduguyla ilgilenmiyorsak sadece catchte e: Exception kullaniriz ve hata ne olursa olsun
    // istedigimizi yaptiririz. ama o hatadan sonraki kodlar calismayacaktir. pek efektif olmayabilir.

    try {
        a = 10/0

        println("aaaa")
        // ustte hata oldugu icin catche girdi sonra break yapti gibi dusunebilirsin. burasi calismadi.

    } catch (e: ArithmeticException) {
        println("Bu sayi sifira bolunmez")

    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Dizinin boyutu asildi")
    }
    ///Bu sayi sifira bolunmez

    try {
        dizi2[4] = 15

    } catch (e: ArithmeticException) {
        println("Bu sayi sifira bolunmez")

    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Dizinin boyutu asildi")
    }
    ///Dizinin boyutu asildi

    /////////////////////////////////////
    // thread kullanimi
    // multitasking icin kullaniliyormus. ornek kullanimlar:
    // web sunucular her istek geldiginde yeni bir thread olusturur.
    // mail kutusunu kontrol ederken ayni anda yeni bir mail yazabiliriz. bunun gibi ornekler.

    // timemachine: thread android applerde kullanicinin arayuzunu blocklayabiliyor. o sebeple
    // arayuz calismalarinda kullanma. main thread'de threading yapilmaz.

    // burada ise ornek olarak sleep komutunu kullanmak icin thread kullanacagiz.
    // kalitim yoluyla ya da interface yoluyla thread kullanilabilir. yukarida classlari olusturdum.
    // start komutuyla baslar.

    // sleep komutu
    // static bir fonksiyondur. her yerde cagirilabilir.
    // yalnizca o an bulundugu threadi etkiler.
    /* eger calistiracaksan bunu kaldir. haliyle cok vakit alir diye kapadim.
    // kalitim thread
    val birinci = KalitimThread()
    birinci.start()
    // interface thread
    val ikinci = Thread(InterfaceThread())
    ikinci.start()
    // main thread (mainde dogrudan olusturduk herhangi bir classa bagli olmadan)
    for (i in 100..200){
        println("Main thread: $i")
        Thread.sleep(1000)
    }
    */
    // konsol ciktisi:
    /*Birinci thread: 100
    Main thread: 100
    Ikinci thread: 100
    Ikinci thread: 101
    Birinci thread: 101
    Main thread: 101
    Ikinci thread: 102
    Main thread: 102
    Birinci thread: 102
    Birinci thread: 103
    Main thread: 103
    Ikinci thread: 103
    Ikinci thread: 104
    Main thread: 104
    Birinci thread: 104
    Main thread: 105
    Birinci thread: 105
    Ikinci thread: 105*/

    ///////////////////////////
    // if kisaltmasi

    var input = Scanner(System.`in`)
    println("Sayilari sirayla gir:")
    var x = input.next().toInt()
    input.nextLine()
    var y = input.next().toInt()
    var durum: String

    // en kisa if kullanimi:
    if (x == y) durum = "esit" else if (x > y) durum = "ilki buyuk" else durum = "ikincisi buyuk"
    println(durum)

    // curly bracket olmadan if kullanimi:
    if (x == y)
        durum = "esit"
    else if (x > y)
        durum = "x buyuk"
    else
        durum = "y buyuk"
    // tabii bunda sadece tek satirlik bir islem yapacaksan kullanabilirsin.
    println(durum)
}