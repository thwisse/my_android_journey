package notes_3_

import notes_3_.files_for_note_14_.*
import notes_3_.files_for_note_14_.User

fun main() {
    // object oriented programming
    // zaten bu konulari C# da ve kotlinin onceki kursunda gormustum. o sebeple burada
    // sadece yeni ogrendigim seyleri not alacagim.
    //
    // (class User)

    val user1 = User("Amy Winehouse", 27, "female", false)
    user1.name = "Yildiz Tilbe"
    user1.age = 57
    // propertyleri "var" olarak yarattigim icin degerlerini sonradan degistirebiliyorum.
    // yani user1'in val olmasiyla ilgisi yok.

    println(user1.name + " - " + user1.age)

    ////////////////
    // primary cons & encapsulation

    val user2 = User("Suleyman Cakir", 40, "male", true)
    // name ve age'i okuyabilir ya da degistirebilirim.
    // gender'i sadece okuyabilirim, degistiremem.
    // maritalStatus'u okuyamam ve degistiremem.

    ///////////////////////
    // inheritance
    // (class User & class Character)

    // miras alinabilen class = open class
    // miras alinamayan class = final class (default)
    // yani bir class'in basina open ekleyince baska classlar o classi miras olarak alabilir.
    // miras alinan class: superclass
    // miras alan class: subclass

    val user3 = Character("Frank Gallagher", 66, "male", false)
    // (class Character)
    // subclass'in final methodu (default fonksiyon iste. fun yani.):
    user3.resuscitate()
    // (class User)
    // superclass'in super methodu (open fun):
    user3.drink()

    ///////////////////////////
    // polimorfizm (cok bicimlilik)
    // ayni ismi kullanarak farkli islemler yapabilme ozelligi.
    // static polymorphism / dynamic polymorphism

    // static polymorphism
    // (class Mathematics)
    // ayni sinif icinde ayni isimde ozelliklerle calismak.

    val math = Mathematics()
    println(math.sum(5))
    println(math.sum(5, 6))
    println(math.sum(5, 6, 7))
    //TODO buna overloading denmiyor muydu ya?

    // dynamic polymorphism
    // (class Animal - class Dog)
    // farkli siniflarda ayni isimli ozelliklerle calismak.
    // superclasstaki bir function'i ayni isimle subsclassta kullanacaksan, bu fonksiyonun override
    // edilmesi gerekir. yani superclasstaki fonksiyonun uzerine yaziyoruz. ve subclassta override
    // edilebilmesi icin de superclasstaki fonk open yapilmasi gerekir.

    val animal = Animal()

    // superclass'in kendi metodu:
    animal.walk()
    ///animal is walking...

    val dog = Dog()
    // subclassin kendi metodu:
    dog.bark()
    ///dog is barking...
    // super classtan miras yoluyla alinan metod:
    dog.walk()
    ///dog is walking...
    // super class metodunun super keywordu ile cagirildigi metod:
    dog.test()
    ///animal is walking...
    // yani istersem superclasstaki fonksiyonu subclass icinde istedigim fonksiyonun icinde cagirabilirim.
    // super.funcionName()

    // Dog sinifi icinde Animal superclassindan override ettigimiz fonksiyon Dog ile cagirildigi zaman
    // farkli, animal ile cagirildigi zaman farkli islem yapacak. ancak fonksiyon isimleri ayni.
    // iste dynamic polymorphism boyle bir sey. isim ayni olmasina ragmen superclasstaki fonk
    // override edilip subclassta uzerine farkli seyler yazildigi icin ikisi farkli islem yapiyor.
    //TODO polimorfizm budur demisim ama valla hic emin degilim bu olduguna. alti ustu fonskiyonu open
    // edip kalittigim diger classta kullandim. bu da inheritance degil mi zaten. polimorfizm diye bise
    // olmayabilir mi acaba? kafayi mi yiyorum?

    //////////////////////////
    // abstract & interface
    // (abstract class People - class Person)

    // abstract

    // soyut sinif olusturma yetisi. birbirine benzeyen classlari tekrar tekrar surekli olusturarak
    // vakit ve verim kaybi yasamak yerine, fonksiyonlari bir kereligine tek bir soyut sinifa yazarak, diger
    // classlarda da o soyut sinifi implemente ederek rahatca kullanabilirim.

    val person1 = Person("Lev Tolstoy", 46, true)
    println(person1.name)
    println(person1.age)
    println(person1.gender)
    println(person1.jump())
    println(person1.run())
    println(person1.dance())
    //Lev Tolstoy
    //46
    //true
    //Jump!
    //Run!
    //Dance!
    // soyut sinifta tanimladigim metodlari, soyut sinifi implemente ettikten sonra nesne ile birlikte
    // kullanabiliyorum. abstract class bu ise yarar.

    // interface
    // (class Piano - interface HouseDecor - interface Instrument)

    // bir classa birden fazla class ile inheritance uygulanamiyor. bir class, tek bir classtan miras
    // alabilir. ancak bazen birden fazla classin ozelliklerini tek bir classta kullanma geregi duyulabiliyor
    // bu yuzden birden fazla inheritance yapilamadigi icin birden fazla implementation yapiliyor.
    // yani bir classta birden fazla interface'i implemente ederek kullanabiliyoruz.

    val piano1 = Piano(1)

    piano1.brand = "Yamaha"
    piano1.isDigital = false

    println(piano1.roomName)
    println(piano1.roomNumber)
    ///Saloon
    ///1
    piano1.information()
    piano1.pricing()
    ///piano info...
    ///piano is so cheap!
    piano1.information2()
    ///instrument info...

    //////////////////////////////
    // lambda expressions (lambda gosterimleri)
    // fonksiyonlari tek bir satirda yazabilmek icin gelistirilen bir gosterim.
    // genellikle fonksiyonun body'si olmadan inputunu alip outputunu dondurecek sekilde tasarlamak,
    // fonksiyonu kisaltmak.

    // klasik kullanim:
    fun printString (myString: String) {
        println(myString)
    }
    printString("test")
    ///test

    // lambda:
    val printString2 = { myString: String -> println(myString) }
    // val ile printString2 adinda bir sabit olusturduk. bunu bir degisken gibi gorme. bununla lambda islemi
    // gerceklesecek.
    // bu fonskiyon tipki yukardaki klasik fonksiyon gibi geriye bir sey dondurmuyor. o yuzden
    // printString2 expression'i (String) -> Unit seklinde bir input output tanimina sahip.
    printString2("test2")
    ///test2

    // bir baska lambda ornegi:

    val multiplyLambda = { a: Int, b: Int -> a * b }
    // input output tanimi: (Int, Int) -> Int
    println(multiplyLambda(4,5))
    ///20

    // i/o tanimini kendimiz de yapabiliriz:
    val multiplyLambda2 : (Int, Int) -> Int = { a, b -> a * b }
    // curlyler icinde a ve b yi tekrardan Int diye belirtmemize gerek kalmadi.
    println(multiplyLambda2(5,6))
    ///30

    ////////////////////////////
    // ileri seviye lambda gosterimleri

    // lambda genelde internetten veri alma ya da veri yollama islemleri icin kullanilir.
    // bu islemlere async/asynchronous adi verilir. senkronize olmayan islemlerdir.
    // islem uzun surecek olursa onu beklemeden diger islemleri de yapabilmek icin bunu kullaniriz.
    // eger bunu yapmazsan kullanici arayuzu kitlenir. senkronize olmayan farkli islemlerin olmasi
    // uygulamanin kitlenmesini ve diger islemlerin yapilabilmesini saglar.
    // callback function, listener function, completion function isimleri verilebilen, islem tamamlandiktan
    // sonra ne yapilacagiyla alakali bir geri donus mekanizmasi tasarlanmasi gerekebilir.

    // bir ornek yapalim:

    // completion: () ile icine (parantez ici) hicbir sey almadigimiz ve -> Unit ile hicbir sey dondurmedigimiz
    // bir fonksiyon yaziyoruz. fonksiyonun asil islevini kodluyoruz ve daha sonra completion parametresini
    // fun icinde cagiriyoruz.
    fun downloadAnything(url: String, completion : () -> Unit) {
        // bu fonksiyonla bir download islemi yaptigimizi hayal edelim.
        // fonksiyonun bu kisminda istenen url ile download islemi yapiliyor varsayalim.
        // data kaydedildi varsayalim. daha sonra asagidaki completion parametresini cagiracagiz.
        completion()
    }
    downloadAnything("metallica.com", { println("completed & ready & finished etc.") })
    // bu sekilde siteden veri indirildi, data kaydedildi vs islemlerinden sonra eger hepsi basarili oldu
    // ise {} icindeki completion blogu calisacak ve tamamlanma islemiyle alakali kodlar da burada calisacak.
    // simdi burada tek satirda yaptik ancak {} ici genisleyip her sey yazilabilir. asagida her gorunumuyle
    // ilgili ornek olacak.

    // icine bir sey alan bir completion da verebiliriz. baska bir ornek:

    fun registerSomebody(isHuman: Boolean, completion : (User) -> Unit) {
        // islemler
        completion(user1)
        // burada completion, () icinde User sinifindan bir nesne istedi.
    }

    // func cagirirken {} icinde completion ile verilen nesnenin ozellikleriyle alakali kodlar da yazabiliriz:

    // placeholder'li hali (hint'li)
    registerSomebody(true, {
        println(it.name)
    })
    // Lambda argument should be moved out of parentheses - uyarisi ile donusturulen hali:
    registerSomebody(true) {
        println(it.name)
    }
    // tek satirda:
    registerSomebody(true) { println(it.name) }


    // placeholder'siz hali (hint'siz)
    // (it yerine fonskiyondan gelen nesnenin buradaki kullanim adini biz veriyoruz.)
    registerSomebody(false, { newUser ->
        println(newUser.name)
    })
    // Lambda argument should be moved out of parentheses - uyarisi ile donusturulen hali:
    registerSomebody(false) { newUser ->
        println(newUser.name)
    }
    // tek satirda:
    registerSomebody(false) { newUser -> println(newUser.name) }
}