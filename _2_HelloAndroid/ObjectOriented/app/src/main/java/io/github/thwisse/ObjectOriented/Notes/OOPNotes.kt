package io.github.thwisse.ObjectOriented.Notes

import io.github.thwisse.ObjectOriented.Classes.Animal
import io.github.thwisse.ObjectOriented.Classes.Character
import io.github.thwisse.ObjectOriented.Classes.Dog
import io.github.thwisse.ObjectOriented.Classes.Mathematics
import io.github.thwisse.ObjectOriented.Classes.Person
import io.github.thwisse.ObjectOriented.Classes.User

fun main() {
    // object oriented programming
    // zaten bu konulari C# da ve kotlinin onceki kursunda gormustum. o sebeple burada
    // sadece yeni ogrendigim seyleri not alacagim.
    //
    // User class'ini olusturdum.

    val user1 = User("Amy Winehouse", 27, "female", false)
    user1.name = "Yildiz Tilbe"
    user1.age = 57
    // propertyleri var olarak yarattigim icin degerlerini sonradan degistirebiliyorum.
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
    // miras alinabilen class = open class
    // miras alinamayan class = final class (default)
    // yani bir class'in basina open ekleyince baska classlar o classi miras olarak alabilir.
    // miras alinan class: superclass
    // miras alan class: subclass

    val user3 = Character("Frank Gallagher", 66, "male", false)
    // subclass'in final methodu: (class Character)
    user3.resuscitate()
    // superclass'in super methodu: (class User)
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

    // Dog sinifi icinde Animal superclassindan override ettigimiz fonksiyon Dog ile cagirildigi zaman
    // farkli, animal ile cagirildigi zaman farkli islem yapacak. ancak fonksiyon isimleri ayni.
    // iste dynamic polymorphism boyle bir sey. isim ayni olmasina ragmen superclasstaki fonk
    // override edilip subclassta uzerine farkli seyler yazildigi icin ikisi farkli islem yapiyor.

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
    // (class Piano
    // bir classa birden fazla class ile inheritance uygulanamiyor. bir class, tek bir classtan miras
    // alabilir. ancak bazen birden fazla classin ozelliklerini tek bir classta kullanma geregi duyulabiliyor
    // bu yuzden birden fazla inheritance yapilamadigi icin birden fazla implementation yapiliyor.
    // yani bir classta birden fazla interface'i implemente ederek kullanabiliyoruz.

}