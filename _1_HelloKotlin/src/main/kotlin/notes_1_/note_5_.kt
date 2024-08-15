package notes_1_

// class 1
class Car {
    // class icindeki propertylerin val ile olusturulduguna dikkat et
    val year = 2011
    val model = "Jeep"
    val color = "Blue"
}

// class 2
// primary constructor
// bu parametrelere val ekledigin zaman birer member (data member) olmus olurlar.
//class Car2(val year: Int, val model: String, val color: String)

/////////////////////////////////////////////
// data member, bir classa ait olan degiskenlerdir.
// classin parametrelerine val yazmadigin zaman onlar sadece parametre olurlar. kullanilip yok olurlar.
// ve ornegin main icinde nesne olustururken o parametre adiyla bir islem yapilamaz
// ornek:
class Foo (x: Int) {
    // bu sekilde tanimlanmis bir parametre main icinde su sekilde kullanilamaz:
    //val foo = Foo()
    //foo.x = 5    // bu yapilamaz
}
class Koo (val x: Int) {
    // fakat bu sekilde tanimlanmis bir parametre artik bir data member olur ve
    // main icinde su sekilde kullanilabilir:
    //val koo = Koo()
    //koo.x = 5    // bu yapilabilir
}
//////////////////////////////////////////////////

// class 3
class Mascot(val name: String, val platform: String, val yearCreated: Int) {
    var age: Int
    // default bi classta sadece propertyler olur. ama init blogu ile class icinde islem yapabiliriz.
    init {
        age = 2020 - yearCreated
        println("$name is a $platform mascot and is $age years old. ")
    }
}

// class 4
class Employee(val firstName: String, val lastName: String, val yearsWorked: Int) {
    val fullName = firstName + " " + lastName
    //val fullName = "$firstName $lastName"
    // bu sekilde de yazilabilirdi.
    init {
        if (yearsWorked > 1) {
            println("$fullName is eligible for a raise!")
        } else {
            println("$fullName is not eligible for a raise just yet.")
        }
    }
}

// class 5
class Cat(val name: String, val breed: String) {
    fun speak() {
        println("Meow!")
    }
    fun mrrrr() {
        println("Mrrrr!!")
    }
}

// class 6
class Dog(val name: String, val breed: String, val competitionsParticipated: List<String>) {
    init {
        for (comp in competitionsParticipated) {
            println("$name participated in $comp.")
        }
    }
    fun speak() {
        println("$name says: Woof!")
    }
}

fun main() {
    // classes

    // class 1 (Car)

    // myCar nesnesini var ya da val ile olusturabilirim.
    // bir classtan nesne olusturmak = creating an instance (emsal)
    var myCar = Car()
    println(myCar.year)  ///2011
    println(myCar.model) ///Jeep
    println(myCar.color) ///Blue

    // constructors

    // primary constructor
    // class Name(val x: String, val y: String)

    /* Car2 classini ve bu kodlari yorum satirina aliyorum cunku sebepsizce hata aliyorum.
    // class 2 (Car2)
    val yourCar = Car2(2011, "Jeep", "Blue")
    val friendsCar = Car2(2015, "Mazda", "Red")
    println(yourCar.year)      ///2011
    println(friendsCar.year)   ///2015
    */

    // init block
    // init blogu, classtan bir nesne olusturuldugunda calisir.

    // class 3 (Mascot)
    val codey = Mascot("Codey", "Codecademy", 2018)
    ///Codey is a Codecademy mascot and is 2 years old.
    // boyle bir komut vermedim ancak classin init'i icinde boyle bir komut oldugu icin nesneyi
    // olusturdugum gibi bu ciktiyi aldim.

    // class 4 (Employee)
    var projectManager = Employee("Maria", "Gonzalez", 2)
    ///Maria Gonzalez is eligible for a raise!
    // yani Employee classini construct etmek, otomatik olarak init blogunu calistirmak anlamina gelecek

    // member functions
    // class 5 (Cat)
    var myCat = Cat("Garfield", "Persian")
    myCat.speak() ///Meow!
    myCat.mrrrr() ///Mrrrr!!
    // bir sinifin instace i olusturuldugunda, nesne sinif govdesi icinde bulunan tum fonksiyonlara erisebilir.
    // otomatik calisan init blogunun aksine, fonksiyonlar icindeki kod yalnizca fonksiyon cagirildiginda calisir.

    // class 6 (Dog)
    var maxie = Dog("Maxie", "Poodle",
        listOf("Westminster Kennel Club Dog Show", "AKC National Championship"))
    //Maxie participated in Westminster Kennel Club Dog Show.
    //Maxie participated in AKC National Championship.
    maxie.speak()
    //Maxie says: Woof!

    ////////////////////////////////////////////////
    /* codecademy learn kotlin kursu bitti. ilk 5 file dosyasi bu kurstan alinan
    notlardan olusmaktadir. bundan sonraki notlar udemydeki kasim adalan kotlin kursundan
    alinan notlardan olusacaktir. codecademy kursunu hizlica bitirmeme sebep olan yazilim gecmisim
    buraya daha az not almama sebep oldu. simdi hem yine yazilim gecmisim hem de codecademy kursundan
    oturu bundan sonraki kursta da bildigim seyleri degil yeni ogrendigim seyleri not alacagim. bunlari
    buraya niye yaziyorum bilmiyorum sanki birisi okuyacak... canim sikildi sanirim... */
}