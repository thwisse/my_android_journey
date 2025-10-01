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

}