package notes_4_kekod

fun main() {
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

    //////////////

    // val = value (deger - degismez)
    // var = variable (degisken - degisebilir)

    // val mi var mi daha performansli?
    // ikisi arasindaki performans farki yok denecek kadar azdir. yine de kucuk de olsa fark varsa eger,
    // var daha performanslidir, val daha maliyetlidir. cunku val icin bir if sureci vardir. yine de bu gunluk
    // hayat icin bu onemsiz bir fark. o yuzden fark yok desen yanlis olmaz, bu sadece akademik bir bilgidir.
    // fakat multi thredding'den oturu genelde sirketlerde, projelerde falan val kullanimi onerilir, olabildigince
    // var kullanilmamaya calisilir. yani val'i sevmeye, var'i tepiklemeye devam.

    //////////////

    // var tipinde olan bir degiskeni sanki val'mis gibi sonradan deger atayamayan bir hale getirmenin yolu var mi?
    // evet var. ornegin bu degisken class icinde olursa ve private set olarak ayarlanirsa, bu degisken bir yerde
    // olusturuldugunda deger atanamayacaktir. bu sekilde sanki val'mis gibi davranir. disaridan deger atanamaz,
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
    // primitiveler aslinda nesnedir ve calisirken javadaki primitivelere donusturulurler. javada bir degisken
    // int olarak da Integer olarak da tanimlanabilirken, burada sadece Int olarak tanimlanir. bu javadaki Integer'a
    // denk gelir ancak burada da buna primitive denir.


}