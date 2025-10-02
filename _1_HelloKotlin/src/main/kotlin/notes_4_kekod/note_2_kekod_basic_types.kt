package notes_4_kekod

fun main() {
    val long = 8348L // kucuk l kullanilamaz 1'e benzedigi icin.
    val float1 = 2.34F
    val float2 = 156.9f // (her ikisi de olur.)
    val double1 = 3.6
    val double2 = 3.14e10 // yapilabilir
    val decimal = 1907 // yapilabilir
    //val octal = 0167 // tanimi yapilamaz 0'la baslayamayacagi icin.
    val hexadecimal = 0x985 // yapilabilir
    val binary = 0b01000011 // yapilabilir

    //////////////

    // boxed & unboxed

    // boxed: degiskenin obje referansi olarak tutulmasidir.
    // unboxed: degiskenin primitive olarak tutulmasidir.

    // == operatoru degiskenlerin degerini karsilastirirken kullanilir.
    // === operatoru degiskenlerin referansini karsilastirirken kullanilir.
    // (point edilen memorydeki adresler karsilastiriliyor. c dilinden hatirlarsan.)

    //////////////

    // bir degiskeni ? ile nullable yapmak madem avantajli bir seyse o zaman degiskenleri hep oyle tanimlayalim
    // hic sikinti yasamayiz? hayir. ornegin int olmasi gereken bir degiskeni nullable yaparsan o degisken runtime'da
    // primitive olan int'e donusecekken, sen onu nullable yaptigin icin class olan Integer olarak kalir. bu da
    // tabii ki primitive olan intten cok daha maliyetlidir.

    // iste burada detay ama havali bir bilgi var. Int olarak tanimlanan ve nullable yapilan bir degisken eger
    // -128 ile 127 araliginda ise, kotlindeki ozel optimizasyonlar sayesinde hala bu degisken javadaki
    // primitive haline donusturulur. ancak bu araligin disinda bir deger ise yukarida dedigim gibi Integer olarak
    // kalacaktir. yani belirttigim araliktaki sayilara torpil gecilmis kisaca. bu aralikta olan ve nullable yapilan
    // degiskenleri kullanirken bu bilgi sayesinde maliyetli olmadiklarini bilirsin.
    // int, short, long vs. nasil tanimlandigi fark etmez bu arada. deger bu aralikta olsun yeter.
    // su kodla kontrol edilebilir:
    val number: Int = 129
    val boxedNumber: Int? = number
    val anotherBoxedNumber: Int? = number
    println(boxedNumber === anotherBoxedNumber)
    // ornegin 125 verince true, 129 verince false donuyor.

    //////////////

    // longun deger araligindan da daha buyuk bir sayi kullanacak olsan sayiyi string olarak degiskene atayip
    // kullanabilirsin. yani araligin disinda olsan bile string olarak istedigini yapabilirsin.

    // sayi iceren iki degiskenle islem yapildiginda esitlenen degiskenin tipi, tipi buyuk olan degiskenin tipiyle
    // ayni olacaktir. sonuc bunu etkilemez. eger tipin araligi yeterli olmazsa da yanlis bir sonuc olabilir ancak
    // uygulama hata vermez.

    //////////////

    // smart casting (akilli tur donusumu)

    // bir degiskenin tipinin degisebilecegi durumlarda kosullu sekilde is yaptirmak daha mantikli.

    var x: Any?

    x = 43
    x = "Helloooooo"

    if (x is String) { // eger x string ise
        println(x.length) // uzunlugunu yazdir (toString() kullanmak zorunda kalmadik)
    } else if (x is Int) { // eger x int ise
        println(x.plus(26)) // su sayiyla topla (toInt() kullanmak zorunda kalmadik)
    }
    // yani Stringse sunu yap Int ise sunu yap diyebiliyoruz.
    // bir ornek daha:

    val age: Number?

    age = 35345L // diyelim bu sunucudan gelen bir bilgi

    when (age) {
        is Short -> println("this is Short")
        is Long -> println("this is Long")
        is Int -> println("this is Int")
        else -> println("unknown type")
    }

    //////////////

    // edge case'ler icin uygulamaya performans kaybi yasatmak: defansif programlama
    // ornegin: nadiren yasanacak bir case icin, diger siradan caselerin de bundan etkilenecegi bir if yaratmak.
    // yani o if kontrolu mantikli olsa bile nadiren yasanacagi icin diger case'lerin de bu if ile gereksiz yere
    // kontrol edilmesi performansi olumsuz etkileyecektir. bu gibi durumlar icin daha ozel islemler yapmak, test
    // yazmak vs. daha mantikli olacaktir.

    //////////////

    // back slash (ters egik cizgi) = \

    val exampleString =  "satir atlamak icin = \n" +
            "\t ile bir tab bosluk birak \n" +
            "\b ile bir karakter sil (backspace) \n" +
            "\r ile satir basina don \n" +
            "\' ile tek tirnak karakterini kullan \n" +
            "\" ile cift tirnak karakterini kullan \n" +
            "\\ ile backslash kullan \n" +
            "\$ ile dolar isaretini kullan \n"

    println(exampleString)

    //////////////

    // char tipi unicode karakterleri de atamak icin kullanilabilir.

    val heart = '\u2665'
    println("First commit with $heart")

    //////////////

    // || (veya, or) - && (ve, and) - ! (degil, not) operatorleri

    // && (ve) için: Eğer ilk koşul false ise, ikinci koşul hiç kontrol edilmez (çünkü sonuç zaten $false$ olacaktır)
    // || (veya) için: Eğer ilk koşul true ise, ikinci koşul hiç kontrol edilmez (çünkü sonuç zaten $true$ olacaktır)
    // bunlar optimizasyon amacli yapilmistir.

    val zeki: Boolean = true
    val caliskan: Boolean = true

    if (zeki && caliskan) {}
    if (zeki and caliskan) {}
    if (zeki.and(caliskan))

    if (zeki || caliskan) {}
    if (zeki or caliskan) {}
    if (zeki.or(caliskan)) {}

    if (zeki.not()) {}
    if (!zeki) {}


}