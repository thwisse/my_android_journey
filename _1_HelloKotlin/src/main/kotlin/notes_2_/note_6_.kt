package notes_2_

import kotlin.random.Random

class islem {
    var x = 5
    var y = 15

    fun topla() {
        var x = 40

        var z = x + y

        println(z)
    }
}

fun main () {
    var a = 10
    var b = 20
    // string template bu sekilde de kullanilabilir
    println("$a ve $b nin toplami = ${a + b}")

    var c = 200
    //c = "ahmet"
    // bu yapilamaz. buna type safety denir.

    ////////////////////////////
    // global ve local degiskenler
    // islem isimli class bu ders icin olusturuldu.
    /* islem classi icindeki x ve y degiskenleri global degiskenlerdir.
    z degiskeni ise bir func icinde tanimlanmistir ve local bir degiskendir.
    bu durumda z = x + y islemini yaptigimizda bir karisiklik olmamasi ve func icindeki x'in baskin gelmesi
    onun local bir degisken olmasindan oturudur. y ise global degisken olarak kullanilmistir.
    global degiskenlere o class icinde her yerden erisilebilir ancak adi ustunde local
    degiskenlere sadece bulunduklari brackets icinde erisilebilir. */
    var toplama = islem()
    toplama.topla()

    ////////////////////
    // degiskeni var ya da val olarak yaratmak performans acisindan fark olusturur.
    // val ile yaratilan degiskenlerin degismeyeceginin bilinmesi performansi artirir.

    ////////////////////////
    // type conversion (tur donusumu)
    // toDouble, toFloat, toInt, toString....

    var i1 = 15
    println(i1 + 10) ///25
    // i2 degiskenine i1 i stringe donusturerek atayalim
    var i2 = i1.toString() + " sayisi"
    println(i2) ///15 sayisi
    var i3 = i1.toDouble()
    println(i3) ///15.0

    // string to numbers
    /* sayiya donusturulecek olan string tamamen sayidan olussa donusumde zaten bir problem
    olusmayacaktir. ancak icinde eger sayidan farkli bir karakter varsa donusum hata verecegi
    icin bunu try catch ya da baska mekanizmalarla kontrol etmemiz gerekmektedir. */

    // yontem 1 (try catch ile kontrol etmek)
    var yazi1 = "34"
    try {
        var x1 = yazi1.toInt()
        println(x1)             // string 34 oldugunda cikti: ///34
    } catch (e: Exception) {
        println("Error")        // string 34p olsa mesela cikti: ///Error
    }

    // yontem 2 (if else ile kontrol etmek)
    var yazi2 = "48.60"
    // string ifade doubleity ise doublea cevirir, degilse null yapar:
    var x2 = yazi2.toDoubleOrNull()
    if (x2 != null) {
        println(x2)             // string 48.60 oldugunda cikti: ///48.60
    } else {
        println("Error")        // string 48.60a olsa mesela cikti: ///Error
    }

    // yontem 3 (? ve let kullanarak kontrol etmek)
    var yazi3 = "14"
    var x3 = yazi3.toIntOrNull()

    x3?.let {
        println(x3)           // string 14 oldugunda cikti: ///14
    }
    // bunda string int disinda bir sey ise degiskene null atar. hatayi cikti verdirmez.

    ////////////////////////////
    // konsoldan girdi almak

    /* tekrar ederken ya da kodu calistirmak icin bu yorum seysini silebilirsin
    print("Adiniz: ")
    // Scanner yazdiginda java.util.Scanner import edildi
    // burada Scanner sinifindan bir nesne olustugunun farkinda ol
    val girdi = Scanner(System.`in`)
    // Scanner ile alinan veri girdi'ye atanir. girdideki string veriyi .next() ile herhangi
    // bir degiskene atayabiliriz:
    val name = girdi.next()
    println(name)

    print("Yasiniz: ")
    val girdi2 = Scanner(System.`in`)
    // simdi de .nextInt() kullandik.
    val age = girdi2.nextInt()
    println(age)
     */

    ///////////////////////
    // diziler (arrays)

    // boyutlari sabittir. collectionslardan en onemli farki budur.
    // eger boyutu sabit olacak bi yapiya ihtiyacimiz varsa collections yerine array
    // kullanabiliriz. cunku performans acisindan arrays daha iyidir. ama genelde collections kullanilir.

    // 1- Array<Type>(size){value1,value2...} seklinde dizi olusturulabilir.
    // ayni zamanda bu bos dizi olusturmanin bir yolu:
    val array1 = Array<Int>(5) {0} // bos int dizi
    val array1_ = Array<String>(5) {""} ///["","","","",""] // bos string dizi
    // dizi icerigini ogrenmek icin:
    println(array1.contentToString()) ///[0,0,0,0,0]
    // tamamen fantazi amacli:
    val array2 = Array<Int>(5) { index -> index + 1 }
    println(array2.contentToString()) ///[1,2,3,4,5]

    // 2- arrayOf
    val array3 = arrayOf(1,5,9)

    // 3- arrayOf<Type>
    val array4 = arrayOf<String>("elma","armut","karpuz")

    // tip belirtmedigimizde sadece tek tip degerler girmek zorunda degiliz
    val array5 = arrayOf(1,5,9,"elma","armut","karpuz")
    println(array5.contentToString()) ///[1, 5, 9, elma, armut, karpuz]

    // dizideki bir degeri cagirmak
    println(array5[5]) ///karpuz
    // ikisi ayni islevi gorur.
    println(array5.get(5)) ///karpuz

    // dizideki bir degeri degistirmek
    array5[2] = 10
    // ikisi ayni islevi gorur. ikisinde de indisinden degisiklik yapilir
    array5.set(3,"muz")
    println(array5.contentToString()) ///[1, 5, 10, muz, armut, karpuz]

    // dizinin bos olup olmama kontrolu
    println(array5.isEmpty()) ///false
    //dizinin boyutunu ogrenme
    println(array5.count()) ///6
    // dizi icindeki degerin indexini ogrenme
    println(array5.indexOf("karpuz")) ///5
    // dizi icerisinde bir degerin olup olmadigini ogrenme
    println(array5.contains("portakal")) ///false
    // dizideki string karakterleri ascii tablosuna gore alfabetik siralamak ve max min ogrenmek
    val markalar = arrayOf("Huawei", "Apple", "Nokia", "Samsung")
    println(markalar.max()) ///Samsung   // bu iki fonksiyon, once sort eder sonra max ya da mini getirir
    println(markalar.min()) ///Apple
    val puanlar = arrayOf(40, 86, 30, 100, 50)
    println(puanlar.max()) ///100
    println(puanlar.min()) ///30
    // diziyi minden maxa direkt siralayan fonk
    markalar.sort()
    println(markalar.contentToString()) ///[Apple, Huawei, Nokia, Samsung]
    puanlar.sort()
    println(puanlar.contentToString()) ///[30, 40, 50, 86, 100]
    // diziyi tersine ceviren fonk
    markalar.reverse()
    println(markalar.contentToString()) ///[Samsung, Nokia, Huawei, Apple]

    //destructuring ve withIndex() i hatirlayalim
    for ((indeks, marka) in markalar.withIndex()) {
        println("Marka ${indeks + 1}: $marka")
    }
    ///Marka 1: Samsung
    ///Marka 2: Nokia
    ///Marka 3: Huawei
    ///Marka 4: Apple

    // rastgele sayi uretme
    // her seferinde 10 tane rastgele sayi uretecek:
    for (i in 0..9) { // uretilecek sayi adedi
        val random = Random.nextInt(0, 10) // sayilarin buyukluk siralamasi
        println(random)
    }
}