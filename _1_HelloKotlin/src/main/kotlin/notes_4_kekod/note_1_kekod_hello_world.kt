package notes_4_kekod

fun main() {
    // bu yorum satiridir. notlarimi bu sekilde alirim.
    //bu sekildeyse bi kodu yorum satirina almisim demektir.
    ///bu sekildeyse konsol ciktisidir.

    //////////////
    // type inference (tip cikarimi)

    // degisken tipini vermesen de ide degiskenin tipini tahmin edip default tipini verir.
    // tabii degiskenin degerini hemen atiyorsan. eger degeri sonra atayacaksan tipini simdi vermen sart.
    // implicit type (kapali tip)
    val name1 = "ozi"
    // explicit type (acik tip)
    val name2: String = "ozi"
    // ide buna uyari veriyor ve tip gosterimine redundant yani gereksiz diyor. zaten bende type inference var
    // senin String diye belirtemene gerek yok salak degiliz anliyoruz diyor.

    // ama ornegin bi sayiyi tip belirtmeden yazmakla ornegin Short seklinde tip belirtip yazmak arasinda
    // fark var. sayi default olarak Int algilanir ama sen bunu Short yap dersen bu redundant olmaz. cunku ikisi
    // farkli seyler ve farki belirtirsen bunu gereksiz algilamaz.

    // sayisal degerler icin:
    // eger sayi integer'in deger araligi icinde ise int atanir, byte ve short atanmaz. int'ten buyukse de
    // long atanir.

    //////////////

    // val = value (deger - degismez)
    // var = variable (degisken - degisebilir)

    // soru: val mi var mi daha performansli?
    // cevap: ikisi arasindaki performans farki yok denecek kadar azdir. yine de kucuk de olsa fark varsa eger,
    // var daha performanslidir, val daha maliyetlidir. cunku val icin bir if sureci vardir. yine de bu gunluk
    // hayat icin bu onemsiz bir fark. o yuzden fark yok desen yanlis olmaz, bu sadece akademik bir bilgidir.
    // fakat multi thredding'den oturu sirketlerde, projelerde falan val kullanimi onerilir, olabildigince
    // var kullanilmamaya calisilir. dogrudur, bu noktada val daha performanslidir.
    // yani val'i sevmeye, var'i tepiklemeye devam.

    // saka bir yana, best practice: degiskenini hep val olarak tanimla. eger degismesi gereken bir durum olusursa
    // sonradan var olarak degistirirsin.

    //////////////

    // buyuk sirketler, buyuk projeler genellikle native kullanir.
    // girisimler bir an once tutmak ve buyuyebilmek icin flutter gibi cross platformlarla baslayabilirler.
    // sirketlerde projeler ornegin once flutterla yazilip, tuttuktan sonra ornegin 3 5 ay sonra native ile
    // bastan yazilmaya baslayabilir. yani neyle baslarsan onla devam edecek diye bir kural yok.

    //////////////

    // soru: var tipinde olan bir degiskeni sanki val'mis gibi sonradan deger atayamayan
    // bir hale getirmenin yolu var mi?
    // cevap: evet var. ornegin bu degisken class icinde olursa ve private set olarak ayarlanirsa, bu degisken bir
    // yerde olusturuldugunda deger atanamayacaktir. bu sekilde sanki val'mis gibi davranir. disaridan deger atanamaz,
    // class'i icinde ne deger verildiyse o kullanilir.

    //////////////

    // degiskenleri olabildigince en tepelerde tanimlamaya calis.

    //////////////

    // show kotlin bytecode
    // kotlin kodunun bytecode karsiligini gormeyi saglar. ideden acabilirsin
    // kodun java karsiligini da alabilirsin burdan.

    //////////////

    val isim: String = "Richard Thomas"
    val yas: Short = 45
    val isOxfordlu: Boolean = true
    // kotlinde her sey nesnedir. ornegin buradaki string short boolean tiplerini ctrl + sol tik ile
    // kaynagini acabilirsin. normalde primitive tiplerde boyle bir sey olmasini beklemeyiz. ancak kotlindeki bu
    // primitive tipler, javadaki primitive tiplerin kotline nesne olarak donusturulmus halidir. yani kotlindeki
    // primitiveler aslinda nesnedir ve calisma zamaninda javadaki primitivelere donusturulurler. javada bir degisken
    // int olarak da Integer olarak da tanimlanabilirken, burada sadece Int olarak tanimlanir. bu javadaki Integer'a
    // denk gelir ancak burada da buna primitive denir.

    //////////////

    // val degisken immutable olarak bilinir. var da mutable olarak bilinir. genel olarak dusununce bunda bir
    // yanlis yok gibidir ancak val degiskenler aslinda immutable degil readonly'dir. COK BILINENIN AKSINE.
    // immutable degistirilemez anlamina gelir. ancak val degiskenlerin degeri degistirilebilir. (get olayiyla)
    // bir classtaki bir degiskenin get fonksiyonuna sabit bir degeri degil de degiskenlerden olusan bir islemi
    // verirsek, degiskenin degeri her cagirdiginda farkli olabilecegi icin immuable olamaz. readonly olabilir.
    // readonly: okunabilir, set edilemez
    // immutable: degeri asla degistirilemez
    //TODO bu kismi const val'a gelince daha detayli gorecegiz.

    //////////////

    // test yazmak
    // ideal olan: once testini yaz. bu testin calisacagi sekilde kodunu gelistir. testi de devamli guncel tut.
    // bircok sirkette yapilan yanlis: kodu yaz. kodun calistiginin ve bozulmadiginin testini yaz.

    //////////////

    // bir degiskene tip atamasi yapilmadan deger atanirsa, degiskene oto olarak Nothing? tipi verilir.
    val value = null // Nothing?
    val value2: Nothing? = null

    // !!. ile ?. secimi hakkinda
    // soru: eger ?. ile exception almiyorsak (crash yemiyosak) hep onu kullanalim. !!. nicin kullanalim?
    // cevap: bazi ciddi durumlarda exception almamak yerine almayi tercih edebiliriz. ornegin bir bankacilik
    // uygulamasinda kullanicinin bakiyesini yanlis gostermek (?.) yerine app'e crash verdirmek (!!.) daha mantikli
    // olabilir. kullanicinin guvenini kaybetmektense o an uygulamayi kullanamamasina sebep olmak daha iyi olabilir.
    // daha basit, isminin gozukmesi gibi durumlardan ise crash verdirmek yerine null gostermek daha iyi olacaktir.
    // sonucta ismi duzgun gozukmeyecek belki ama bu bankayla ilgili islemlerini yapmasina engel olmasin. ismiyle
    // ilgili problem var diye crash yemesine gerek yok.

    // null donulmemesi gereken yerlerin tespitini yapmak icin test amacli !!. kullanilabilir. bir taktik.
}