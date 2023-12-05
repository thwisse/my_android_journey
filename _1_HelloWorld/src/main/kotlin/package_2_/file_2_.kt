package package_2_

fun main() {
    // collections (lists, sets, maps)

    // lists

    var x = 5
    x = 6                           // x degiskeni var yani mutable oldugu icin degisim yapilabilir

    val y = 5
    //y = 6                         // y degiskeni val yani immutable oldugu icin degisim yapilamaz

    var list1 = mutableListOf(1, 2)
    list1 = mutableListOf(3, 4)     // list1 degiskeni var yani mutable oldugu icin degisim yapilabilir
    list1.add(5)                    // mutableListOf listesi mutable oldugu icin ekleme yapilabilir

    var list2 = listOf(1, 2)
    list2 = listOf(3, 4)            // list2 degiskeni var yani mutable oldugu icin degisim yapilabilir
    //list2.add(5)                  // listOf listesi immutable oldugu icin ekleme yapilamaz

    val list3 = mutableListOf(1, 2)
    //list3 = mutableListOf(3, 4)   // list3 degiskeni val yani immutable oldugu icin degisim yapilamaz
    list3.add(5)                    // mutableListOf listesi mutable oldugu icin ekleme yapilabilir

    val list4 = listOf(1, 2)
    //list4 = listOf(3, 4)          // list4 degiskeni val yani immutable oldugu icin degisim yapilamaz
    //list4.add(5)                  // listOf listesi immutable oldugu icin ekleme yapilamaz

    /////////////
    // lists devam

    // listelerin tipini belirtebiliriz:
    val list5: List<String> = listOf("Metin", "Ali", "Feyza")
    val list6: MutableList<Char> = mutableListOf('k', 'o', 't', 'l', 'i', 'n')

    // listelerde size kullanimi
    val majorRivers = listOf("Volga", "Danube", "Loire", "Rhine", "Elbe")
    println(majorRivers.size)
    // string icinde degiskenle bir property birlikte kullanilacaksa bu ifade {} icine alinir
    println("There are ${majorRivers.size} major rivers in Europe.")
    // koleksiyonun son elemanina ulasmak istersek:
    println(majorRivers[majorRivers.size - 1])

    ////////////////
    // list functions

    val vowels = listOf('A', 'E', 'I', 'O', 'U')
    // listede belirtilen degerin icerip icermedigini ogrenmek
    println(vowels.contains('A'))
    println(vowels.contains('B')) ///False
    //listedeki elemanlardan rastgele birini secmek
    println(vowels.random())

    //mutableList only functions:
    val primeNumbers = mutableListOf(4, 5, 7, 11, 13)
    // listeye eleman eklemek (sona ekler)
    primeNumbers.add(17)
    // listeden eleman cikarmak
    primeNumbers.remove(4)

    ////////////////
    // sets

    // setOf
    val colorsOfTheRainbow = setOf("red", "orange", "yellow", "green", "blue", "red")
    // setler kumeler oldugu icin ve bir kumede ayni elemandan birden fazla bulunamayacagi icin
    // bu eleman bi kez daha eklenmeye calisilsa bile set bunu gerceklestirmez
    println(colorsOfTheRainbow)  ///[red, orange, yellow, green, blue]

    // mutableSetOf
    var nycRadioStations = mutableSetOf(106.7, 101.1, 97.1, 103.5)

    // tipini belirterek bu sekilde olusturulurlar
    val gs: Set<String> = setOf("sari", "kirmizi", "en buyuk", "cimbom")
    val tr: MutableSet<String> = mutableSetOf("kirmizi", "beyaz", "en buyuk", "turkiye")

    // setlerde elemanlara erismek icin elementAt propertysi kullanilir
    var nonEndangered = setOf("Louisiana Black Bear", "Northern Brown Kiwi", "Gray Wolf", "Arabian Oryx")
    nonEndangered.elementAt(2) ///Gray Wolf
    //nonEndangered.elementAt(5) // Exception error alirsin
    // eger set icinde aradigin indexte eleman yoksa NullPointerException hatasi
    // almak istemiyorsan elementAt yerine elementAtOrNull prop'unu kullanmalisin
    nonEndangered.elementAtOrNull(5) ///null  // hata vermez, geriye null doner

    //////////////////////
    // koleksiyonlar icin bazi fonksiyonlar

    var participants = listOf("elePHPant", "Gopher", "Lenny", "Moby Dock", "Codey", "Gopher")
    var uniqueParticipants = mutableSetOf<String>()
    // bir listenin elemanlarini baska bir listeye eklemek
    // uniqueParticipants mutableSetOf oldugu icin ayni olan degerler tekillestirildi
    uniqueParticipants.addAll(participants)
    // listedeki tum elemanlari silmek
    uniqueParticipants.clear()
    println(uniqueParticipants) ///[]

    var numbers = setOf(10, 15, 20, 25)
    numbers.first() ///10
    numbers.last() ///25
    numbers.sum() ///70

    ////////////////////////
    // maps

    // mapOf
    var leadSingers = mapOf("The Rolling Stones" to "Mick Jagger",
        "Blondie" to "Debbie Harry", "Queen" to "Freddie Mercury")
    println(leadSingers) ///{The Rolling Stones=Mick Jagger, Blondie=Debbie Harry, Queen=Freddie Mercury}
    // keyleri cagirinca cikti olarak degerleri verir
    println(leadSingers["Blondie"]) ///Debbie Harry
    println(leadSingers["Debbie Harry"]) ///null  // degerleri cagirinca keyleri vermez
    // sadece keyleri ogrenmek istersek:
    println(leadSingers.keys) ///[The Rolling Stones, Blondie, Queen]
    // sadece degerleri ogrenmek istersek:
    println(leadSingers.values) ///[Mick Jagger, Debbie Harry, Freddie Mercury]

    // bir mapOf ornegi:

    val uniqueTransport = mapOf("India" to "Tuktuk", "Egypt" to "Felucca",
        "Peru" to "Totora Boat", "Hong Kong" to "Junk Boat")
    // mapin icindeki bir degere keyini cagirarak eristik ve print icinde kullandik.
    println("A unique mode of transportation that exists in Peru is ${uniqueTransport["Peru"]}.")

    // mutableMapOf
    var students = mutableMapOf("Willow" to 15, "Elijah" to 14, "Florence" to 16, "Muhammed" to 15)
    // bir keye ait degeri degistirmek:
    students["Willow"] = 13
    // mape yeni bir deger eklemek
    students.put("Mami", 31)
    // mapten bir degeri kaldirmak
    students.remove("Florence") // keyi belirterek degeri kaldiriyoruz


    // bir mutableMapOf ornegi:
    var momaPaintings = mutableMapOf("Les Demoiselles d'avignon" to "Pablo Picasso",
        "The Starry Night" to "Vincent Van Gogh", "Mona Lisa" to "Leonardo Da Vinci",
        "The Persistance of Memory" to "Salvador Dali")
    // icinde su deger yoksa ekle seklinde bir if olustur:
    if (!momaPaintings.containsValue("Claude Monet")) {
        momaPaintings.put("Water Lillies", "Claude Monet")
    }
    println(momaPaintings)
}