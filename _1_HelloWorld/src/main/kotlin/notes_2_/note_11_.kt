package notes_2_

// class 1 (iki farkli konu icin kullanilmistir)
class Urun (var urunNo: Int, var urunAd: String, var urunFiyat: Double) {

    override fun hashCode(): Int {
        return this.urunNo
        // biz kayit yapip yapmama kararini urunNo'ya gore vermesini istiyoruz.
        // burada da bunu ona belirtiyoruz.
        // this burada sinifi temsil ediyor.
    }
    // Any? isimli seyi classlarin tanrisi gibi dusun. superclass gorevi var burada.
    // if conditioninda da other as Urun diyerek downcasting yaptik.
    override fun equals(other: Any?): Boolean {
        if (this.urunNo == (other as Urun).urunNo) {
            return true
        } else {
            return false
        }
        // "urunNo'lar ayniysa kayit yapma, ayni degilse yap" gibi bir komut veriyoruz.
    }
}

// class 2
class Ogrenci (var okulNo: Int, var isimSoyisim: String, var sinif: String) {}

fun main () {
    /////////////////////
    // ArrayList
    // daha once sirasiyla listOf, setOf, mapOf ve arrayOf ogrenmistik. bunu yeni ogreniyorum.

    val meyveler = ArrayList<String>()
    // bu sekilde de yaratilabilir:
    //val meyveler: ArrayList<String> = ArrayList ()

    meyveler.add("Cilek")
    meyveler.add("Karpuz")
    meyveler.add("Muz")
    meyveler.add("Elma")
    meyveler.add("Portakal")

    println(meyveler.get(3)) ///Elma
    println(meyveler[3]) ///Elma

    // bi indexteki degeri degistirmek. o indexteki degerin yerine eklemek:
    meyveler[3] = "Ananas"
    println(meyveler.toString()) ///[Cilek, Karpuz, Muz, Ananas, Portakal]

    // bi indexe yeni eleman eklemek. o indexteki degeri ve sonraki degerleri saga kaydirmak:
    meyveler.add(3, "Kivi")
    println(meyveler.toString()) ///[Cilek, Karpuz, Muz, Kivi, Ananas, Portakal]

    // ikisi de dizinin boyutunu veriyor.
    println(meyveler.count()) ///5
    println(meyveler.size)    ///5
    // indexi belirterek eleman silme
    meyveler.removeAt(4)
    println(meyveler.toString()) // Ananas silindi
    // elemani belirterek eleman silme
    meyveler.remove("Karpuz")
    println(meyveler.toString()) // Karpuz silindi

    meyveler.clear()
    println(meyveler.toString()) ///[]  // dizi icerigi temizlendi.

    ///////////////////////////////////////

    // nesnelerden olusan diziler
    // class 1 (Urun)

    val urun1 = Urun(1, "Asus TUF FX506LI", 26999.0)
    val urun2 = Urun(2, "Logitech MX Keys Eng Q Keyboard", 4427.72)
    val urun3 = Urun(3, "Logitech G305 Lightspeed Mouse", 1049.0)
    val urun4 = Urun(4, "OneOdio A70 Headset", 1827.90)
    val urun5 = Urun(5, "Samsung Galaxy S22 128GB", 23999.0)

    val urunler = ArrayList<Urun>()

    urunler.add(urun3)
    urunler.add(urun1)
    urunler.add(urun5)
    urunler.add(urun4)
    urunler.add(urun2)
    // kayit sirasi karisik fark ettiysen.

    println("\nUrunlerin kayit sirasina gore default ilkel siralama:\n")
    var i = 1

    for (urun in urunler) {
        println("------- $i -------")
        println("Urun Numarasi: ${urun.urunNo}")
        println("Urun Adi: ${urun.urunAd}")
        println("Urun Fiyati: ${urun.urunFiyat}")
        i += 1
    }

    ////////////////////
    // nesnelerden olusan dizileri farkli sekilde siralamak (sortedWith func)

    // urunNo'ya gore kucukten buyuge sirala ve siralama1 List'ine bu listeyi aktar.
    val siralama1 = urunler.sortedWith(compareBy{ it.urunNo })
    // buradaki it keywordu Urun nesnelerinin atandigi urun1, urun2 gibi degiskenleri ifade eder.
    // compareBy kucukten buyuge siralar.
    // buyukten kucuge siralamak icin compareByDescending kullanabilirdik.

    println("\nUrun numarasina gore siralama:\n")
    i = 1

    for (x in siralama1) {
        println("------- $i -------")
        println("Urun Numarasi: ${x.urunNo}")
        println("Urun Adi: ${x.urunAd}")
        println("Urun Fiyati: ${x.urunFiyat}")
        i += 1
    }

    // simdi de urunFiyat'ina gore kucukten buyuge siralayalim.
    val siralama2 = urunler.sortedWith(compareBy{ it.urunFiyat })

    println("\nUrun fiyatina gore siralama:\n")
    i = 1

    for (x in siralama2) {
        println("------- $i -------")
        println("Urun Numarasi: ${x.urunNo}")
        println("Urun Adi: ${x.urunAd}")
        println("Urun Fiyati: ${x.urunFiyat}")
        i += 1
    }

    ////////////////////////////
    // filter func
    // olusturacagim yeni siralamalarda filtreleme islemi yapabilirim.

    // ornegin 3ten buyuk numaraya sahip olan urunleri listeleyelim:
    val siralama3 = siralama1.filter { it.urunNo > 3 }
    // urunler listesini kullanmadim cunku urunleri karisik yerlestirmistik. siralama1 ile
    // onlari duzenledigim icin filtrelemede onu kullandim.

    println("\nNumarasi 3'ten buyuk olan urunleri siralama:\n")
    i = 1

    for (x in siralama3) {
        println("------- $i -------")
        println("Urun Numarasi: ${x.urunNo}")
        println("Urun Adi: ${x.urunAd}")
        println("Urun Fiyati: ${x.urunFiyat}")
        i += 1
    }

    // urunAd icinde Logitech bulunan urunleri siralayalim
    val siralama4 = siralama1.filter { (it.urunAd).contains("Logitech") }

    println("\nLogitech urunleri siralama:\n")
    i = 1

    for (x in siralama4) {
        println("------- $i -------")
        println("Urun Numarasi: ${x.urunNo}")
        println("Urun Adi: ${x.urunAd}")
        println("Urun Fiyati: ${x.urunFiyat}")
        i += 1
    }
    println("\n")

    //////////////////////////////////////////

    // set (HashSet) yapisi
    // setler bilindigi gibi icine ayni degerden birden fazla almaz. ancak bir ozelligi daha var.
    // set icindeki degerler duzenli bir sekilde sirayla vs. degil daginik bir sekilde yerlestirilir.
    // setOf ile mutableSetOf ogrenmistik. simdi bir de HashSet<Type>() ogrenecegiz.
    // mutableSetOf ile HashSet<Type>() ayni gorevi gorur.

    val sayilar = HashSet<Int>()
    //TODO dersteki videoda boyle ekleme yaptiginda karisik ekliyordu bende sirayla ekliyor. bunu anlamadim.
    sayilar.add(10)
    sayilar.add(15)
    sayilar.add(11)
    sayilar.add(12)
    sayilar.add(13)
    sayilar.add(15) // bir daha 15
    sayilar.add(14)
    sayilar.add(15) // bir daha 15
    // birden fazla ayni degeri girmeme ragmen eklemedi.
    println(sayilar.toString()) ///[10, 11, 12, 13, 14, 15]

    // eger nesnelerden olusan bir hashset yapisi kullaniyorsak ve ayni argumani iceren nesneler eklersek
    // default olarak sorun cikarmadan nesneleri set'e ekleyecektir. ancak biz belli bir argumani ayni olan
    // nesneleri eklememesini istersek bunu belirtmemiz gerekir. mesela tum argumanlari ayni 2 tane farkli nesneyi
    // diziye eklersek hep yaptigi gibi bunlar ayni seyler diyip eklememezlik yapmaz, nesnelerde baaam diye ekler.
    // bu yuzden ornegin biraz asagidaki ornekte urunNo'suna gore ayrim yaptiricaz ve ayni urunNo'dan baska
    // nesne eklemek istedigimizde reddedecek.

    val esyalar = HashSet<Urun>()

    // urunleri baska konu icin yaratmistim burda da kullaniyorum.
    esyalar.add(urun1)
    esyalar.add(urun2)
    esyalar.add(urun3)
    esyalar.add(urun4)
    esyalar.add(urun5)

    // ayni nesneden birden fazla ekleyelim:
    //val urun5 = Urun(5, "Samsung Galaxy S22 128GB", 23999.0) boyle idi. hatirlatmak icin yazdim.
    val urun6 = Urun(5, "Samsung Galaxy S22 128GB", 23999.0)
    esyalar.add(urun6)
    // hic sikinti cikarmadan ekledi. argumanlari ayni olsa da ekledi.
    esyalar.add(urun6)
    // ama aynisini bida ekledigimde sonradan ekleneni kabul etmedi.

    // hashCode() ve equals()
    // bu fonksiyonlari class icinde yaratarak nesnenin belli bir argumanina gore HashMap'in bilinen
    // dogal davranisini yonlendiriyoruz. bunlarla ilgili aciklamalar class1 icinde var.

    val urun7 = Urun(5, "Samsung Galaxy S22 128GB", 23999.0)
    esyalar.add(urun7)
    // urun7 nesnesinin adi farkli olmasina ragmen urunNo'sundan var oldugu icin eklenmedi.
    // urun7 ile birlikte urun6 da listeden cikarildi.
    // artik bu set'e sadece urunNo'su farkli olan urunler ekledigimde ekleyecek.

    //region hashset liste yazdirma kodu. bu region'i yukarida istedigin araliga gotur koyabilirsin.
    println("\nHashSet Urun Listesi:\n")
    i = 1

    for (esya in esyalar) {
        println("------- $i -------")
        println("Urun Numarasi: ${esya.urunNo}")
        println("Urun Adi: ${esya.urunAd}")
        println("Urun Fiyati: ${esya.urunFiyat}")
        i += 1
    }
    //endregion

    //////////////////////////////////
    // map (hashmap)
    // mapOf ve mutableMapOf ogrenmistim. simdi HashMap<KeyType,ValueType>(x to y, a to b) ogrenecegiz.
    // mutableMapOf ile HashMap ayni isi gorur.
    // map'te kisaca key'ler ile value'lara erisiriz.
    // mapOf ve mutableMapOf'da da <KeyType, ValueType> belirterek yaratabiliriz ama genelde gerek olmuyor.

    var iller_ = mutableMapOf(46 to "Maras", 31 to "Hatay") // bunlarda boyle ekleyebiliyorduk.
    // zaten bunlarda da put kullaniliyor.
    // ama HashMapte parantez icinde olusturulmuyor. put ile koyabiliriz.
    var iller = HashMap<Int, String>()

    iller.put(46, "Maras")
    iller.put(31, "Hatay")
    iller.put(1, "Adana")

    println(iller.toString()) ///{1=Adana, 46=Maras, 31=Hatay}

    // istersek yine put ile bir key'e ait value'yu degistirebiliriz.
    iller.put(82, "Washington")
    iller.put(82, "London")
    // bir keye ait valueyi get'irme :d
    println(iller.get(82)) ///London
    // diger collection yapilarindaki gibi count() ve size ile boyut ogrenilebilir.
    // containsKey ve containsValue func'lari ile de icinde bir key ya da value'nun olup olmadigi ogrenilebilir.
    // remove ile bir keye bagli deger silinebilir. clear ile hashmap icindeki tum degerler silinebilir.

    // hem key hem value ile dongu islemi yapmak istedigimizde destructing islemi ile birlikte
    // sadece hashmapin adini kullanmak yeterli. withIndex'e gerek yok.
    // hashmap adi zaten key ve value'yu beraber getirir.
    for ((anahtar, deger) in iller) {
        println("Key: $anahtar - Value: $deger")
    }
    ///Key: 1 - Value: Adana
    ///Key: 82 - Value: London
    ///Key: 46 - Value: Maras
    ///Key: 31 - Value: Hatay

    ///////////////////////////////
    // nesnelerin kullanildigi hashmap'lerle islemler
    // class 2 (Ogrenci)

    val ogr1 = Ogrenci(1, "Fatih Sultan Mehmet", "10-B")
    val ogr2 = Ogrenci(2, "Kanuni Sultan Suleyman", "9-C")
    val ogr3 = Ogrenci(3, "Yavuz Sultan Selim", "12-A")

    val ogrenciler = HashMap<Int, Ogrenci>()

    // Int Key olarak ogr nesnesinin okulNo parametresini kullanalim, Nesne Value icin de ogr nesnesini
    ogrenciler.put(ogr1.okulNo,ogr1)
    ogrenciler.put(ogr2.okulNo,ogr2)
    ogrenciler.put(ogr3.okulNo,ogr3)

    for ((anahtar, deger) in ogrenciler) {
        // anahtar ogrenci no, deger ise nesne olacak.
        println("$anahtar numarali ogrenci: ${deger.isimSoyisim}, Sinif: ${deger.sinif}")
    }
    ///1 numarali ogrenci: Fatih Sultan Mehmet, Sinif: 10-B
    ///2 numarali ogrenci: Kanuni Sultan Suleyman, Sinif: 9-C
    ///3 numarali ogrenci: Yavuz Sultan Selim, Sinif: 12-A
}