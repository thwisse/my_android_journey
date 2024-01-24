package notes_3_

// class 1
class User() {

    var name: String = ""
    var age: Int = 0

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
        // this.name, this.age = bu classdaki name/age degiskeni. anlamina gelir.
    }
}

fun main() {
    //////////////////////
    // for each dongusu

    val mySet1 = mutableSetOf(1,2,2,3,4,4,5)
    println("Size:" + mySet1.size)

    mySet1.forEach { print("$it ") }
    ///1 2 3 4 5

    print("\n")

    ////////////////////
    // hashMapOf / HashMap

    val calories = hashMapOf<String, Int>()
    calories.put("Apple", 100)
    calories.put("Banana", 150)

    // sik kullanilan:
    val prices = HashMap<String, Int>()
    prices.put("Toy", 20)
    prices.put("Book", 5)

    /////////////////////////
    // fonskiyonlar

    // alt space ile ya da fareyi uzerine getirince fonksiyona baktigimizda yaninda
    // hangi return type yaziyorsa o return'u verecegiz evet. ama unit yaziyorsa
    // bu onun herhangi bir deger dondurmedigi anlamina gelir.

    // bir fonskiyonun donus degeri yok ise o fonksiyonun sonucunu bir degiskende saklayamayiz.

    fun fun1 (a: Int, b: Int) : Int {  // Int
        println("sum1: " + (a + b))

        return a + b
    }
    fun fun2 (a: Int, b: Int) {  // Unit
        println("sum2: " + (a + b))
    }
    // bu ciktilarda sorun yok. cunku fonksiyon fonksiyondur. fonk cagirildikca icinde ne varsa
    // calisacak sonucta:
    ///sum1: 8
    ///sum2: 8
    val result1 = fun1(3, 5)
    val result2 = fun2(3, 5)
    // bu arada fun2'nin return degeri de a+b olmasina ragmen icinde bir print islemi yaptik. cunku return
    // edilecek olan sey ayri, fonk icindeki islemler ayri.
    println("result1: " + result1)
    println("result2: " + result2)
    // burada goruyoruz ki return degeri olmadigi icin fun2 fonksiyonunu direkt olarak bir degiskene
    // deger verecek sekilde yazsak bile bu mumkun olmuyor. return degeri olmali.
    ///result1: 8
    ///result2: kotlin.Unit

    //////////////////////
    // siniflardaki ozellikler: properties
    // class 1
    //
    // constructor, siniftan her obje olusturuldugunda calisan bir fonskiyondur.
    //
    // this keywordu, icinde bulundugu sinifa ya da icince bulundugu bir ust seviyeye hitap eder.
    // mesela bi classta birden fazla x adinda degisken var diyelim. bulundugu yerdeki x'i kullanmak
    // istedigimizi belli etmek icin this.x yazabiliriz. bunu nereye yazarsak oradaki x'i isleme alacaktir.
    // this icin ornek amacli bir de User sinifini olusturdum. yukarida bak.

    var jack = User("Homer", 50)

    // secondary constructor cok sik kullanilan bir sey degil. primary daha okunakli ve kullanisli.
    //
    // constructor icine alinan propertyler nesne olustururken girilmesi zorunlu olan propertylerdir.
    // ancak constructor icine koymadigimiz diger propertyler ise opsiyoneldir. nesne olustuktan sonra
    // ister degistiririz ister degistirmeyiz.
    //
    // constructor ile yaratilan parametreleri val yaparsam, o siniftan uretilen nesne uretilirken verilen degerler
    // haricinde hicbir degeri daha sonra degistiremem.

    ///////////////////////
    // visibility modifiers (gorunurluk seviyeleri)
    // kotlinde 4 tanedir. public, private, protected, internal.
    // protected: ayni sinif degil ayni dosya icinde her yerde erisilebilir. onun disindakilerde erisilemez.
    // internal: ayni modul/package icinde her yerde erisilebilir. onun disindakilerde erisilemez.
    //
    // constructor icinde bir propertyi private yapip, o degiskeni public bir fonksiyonda kullanabilirim.
    // yani propertye nesne aracigiliyla erisemem ama fonksiyonu kullanarak o property ile islem yapabilirim.

    /////////////////////
    // nullability
    // String = String
    // String? = String nullable

    var myString: String?
    //println(myString)
    // println hata veriyor. cunku initialize edilmedi. baslatilmadi. bu degisken null'dir diyor.
    var myString2: String? = null
    println(myString2)
    // simdi println hata vermiyor. cunku initialize edildi. ancak simdi de uyari veriyor. diyor ki bu null.
    // zaten burada bir hata vermez. bir degiskenin null olabilmesine onay veriyorsak bunu yapariz ve hata almayiz.
    // burada cok anlamli degil. cunku degeri ben giriyorum. ancak veri databaseden kullanicidan vs geliyor olsa idi
    // o zaman bu hayat kurtaran bir sey olacakti.
    var myInt: Int? = null
    //println(myInt * 5)
    // bu printte de hata aliyorum. null olabilecek bir seyi operand olarak kullanmak tehlikeli diyor.
    // bunun icin de ya ? ya da !! kullanacagiz.

    // 1) !!
    //myInt = 5
    //println(myInt!! * 5) // ben eminim myInt null degil sen isine bak diyoruz.
    // ama null oldugu icin uygulama cokecektir (NullPointerException). burada !! ile inisiyatif almis oluyoruz.

    // 2) ? = safe call
    //myInt = 5
    println(myInt?.minus(10)) // myInt null ise null dondur, null degilse calistir diyoruz
    // null dondu. uygulama da cokmedi. isi garantiye aldik.
    // myInt?.minus(10) ifadesi null dondugu icin, bunu da baska bir yerde isleme soksak yine hata aliriz.
    // o sebeple ? de her zaman guvenli olacagi anlamina gelmez.
    // !! ve ? dan daha guvenli ve mantikli bir yontem var:

    // 3) if kontrolu.
    //myInt = 5
    if (myInt != null) {
        println(myInt.minus(10))
        // "Smart cast to kotlin.Int" yaziyor. null gelmeyecegi zaman bana birak islemi yapacagim diyor.
    } else {
        println("myInt is null.")
        // myInt null geldiginde ise baaaam diye bunu calistiracak.
    }
    // bir alternatif daha:

    // 4) elvis operatoru (?:)
    // null degilse islemi yap, null ise null yollama sana verdigimi (burada 0) yolla diyoruz.
    //myInt = 5
    println(myInt?.minus(10) ?: 0)
    // alternatiflere doyamadik. bir alternatif daha:

    // 5) let kullanimi
    // let kullandigimizda eger null degilse islemi yapacak. ancak null ise islemi es gececek hic calistirmayacak.
    // if kontrolune benziyor. if kontrolunde else olmadigi durumda bununla ayni seymis gibi dusunebilirsin.
    myInt?.let {
        println(it * 5)
        // burada null degilse gelen deger it yerine gecer ve islem yapilir.
        // eger myInt null ise bu curly bracket'a hic girilmez bile. hata da vermez.
    }

    // bu nullability iyi ogrenmek icin Pomegranate projesinde bir ornek yaptim. oraya bakabilirsin.


}