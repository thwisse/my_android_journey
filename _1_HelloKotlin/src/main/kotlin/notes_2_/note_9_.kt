package notes_2_

open class Personel (val isimSoyisim: String, var maas: Int, val ozelOda: Boolean, var terfiDuzeyi: Int) {
    fun iseAlindi () {
        println("Ise alim gerceklesti.")
    }
}

class Mudur (isimSoyisim: String = "Kenan Birkan",
             maas: Int = 100000, ozelOda: Boolean = true,
             terfiDuzeyi: Int = 9): Personel(isimSoyisim, maas, ozelOda, terfiDuzeyi) {

    fun iseAl(perX: Personel) {
        perX.iseAlindi()
    }

    fun terfiEttir (perX: Personel, zam: Int = 0) {
        perX.terfiDuzeyi += 1
        println("Personelin terfisi gerceklesti. Yeni terfi seviyesi: ${perX.terfiDuzeyi}")

        // Ustabasi sinifinin fonksiyonuna erisebilmek icin downcasting yapalim

        // if kontrolu yapmiyor olsaydik ve tek bir sinifa bu islemi
        // yapacak olsaydik tek satirda su sekilde yapabilirdik:
        //(perX as Ustabasi).maasArtir(perX, zam)
        // if kontrolu var oldugu icin casting yapmamiza gerek yok.

        if (perX is Ustabasi) {
            perX.maasArtir(perX, zam)
        }
        if (perX is DeneyimliIsci || perX is Isci) {
            println("Gecici bir sureligine iscilerin maasina zam yapilmasi sirketce yasaklandi.")
            // simdi burda boyle bi hile yaptim. aslinda iscileri de terfi alabilir olarak tasarlicaktim da
            // cok vakit alacakti her seyi degistirmek istemedim. onun yerine anlatayim.
            // Ustabasi ile Isci arasinda bir kalitim baglantisi olmadigi icin Iscilere bu islemi uygulayamiyorum.
            // ClassCastException hatasi aliyorum. Bu projenin mantigina gore bu maasArtir
            // fonksiyonunun Ustabasi'nda
            // olmamasi gerekiyordu, o yuzden problem oluyor. ancak sorun yok sonucta mantigini biliyorum.
        }
    }
}

class Ustabasi (isimSoyisim: String,
                maas: Int, ozelOda: Boolean = true,
                terfiDuzeyi: Int = 2): Personel(isimSoyisim, maas, ozelOda, terfiDuzeyi) {

    fun maasArtir (perX: Personel, zam: Int) {
        perX.maas += zam
        println("Personelin maasi artirildi. Yeni maas: ${perX.maas}")
    }

}

class DeneyimliIsci (isimSoyisim: String,
                     maas: Int, ozelOda: Boolean = false,
                     terfiDuzeyi: Int = 1): Personel(isimSoyisim, maas, ozelOda, terfiDuzeyi)

class Isci (isimSoyisim: String,
            maas: Int, ozelOda: Boolean = false,
            terfiDuzeyi: Int = 0): Personel(isimSoyisim, maas, ozelOda, terfiDuzeyi)


fun main () {
    val mudur = Mudur()

    val ustabasi1: Personel = Ustabasi("Ezel Bayraktar", 10000)
    // ustabasi icin ozelOda ve terfiDuzeyi argumanlarini doldurmadim cunku default degerleri var.

    //val ustabasi1 = Ustabasi()
    // ustabasi1 tanımlamasının türü belirtilmemiştir ve Ustabasi sınıfından
    // nesne ustabasi1 tanımlamasına aktarılmıştır, standart bir nesne oluşturulmuştur.

    //val ustabasi1: Personel = Ustabasi()
    // ustabasi1 tanımlamasının türü Personel sınıfıdır ve Ustabasi sınıfından
    // nesne ustabasi1 tanımlamasına aktarılmıştır , polymorphism yapılmıştır.

    // Polymorfism kullanım amacı sınıf dönüşümleri yaparak kullanım alanlarını genişletmektir.
    // kullanim alani genisletme mevzusunu tam anlamadigim icin hocaya sordum o da bunu yazdi:
    /* Bazı durumlarda size sunulan metodların veya sınıfların parametrelerinde elinizde
    bulunan sınıfın super classı istenebilir, bu durumda elinizdeki nesnenin görünümünü
    polymorphism ile değiştirebilirsiniz. Çok yaygın olmasada bu durumlar ile karşılaşabilirsiniz,
    bu kullanımlar duruma göre değişebilir ve android tarafında karşınıza çıktıkça daha anlaşılır olacaktır. */
    //TODO bir gun anlarim herhalde bu poliformizmi

    val d_isci1: Personel = DeneyimliIsci("Eser Yenenler", 1000)
    val isci1: Personel = Isci("Ekber Habesi", 500)
    val isci2: Personel = Isci("Roberto Carlos", 600)

    mudur.iseAl(ustabasi1)
    mudur.iseAl(d_isci1)
    mudur.iseAl(isci1)
    mudur.iseAl(isci2)
    ///Ise alim gerceklesti.
    ///Ise alim gerceklesti.
    ///Ise alim gerceklesti.
    ///Ise alim gerceklesti.

    mudur.terfiEttir(ustabasi1, 500)
    mudur.terfiEttir(isci1)
    mudur.terfiEttir(isci2)
    ///Personelin terfisi gerceklesti. Yeni terfi seviyesi: 3
    ///Personelin maasi artirildi. Yeni maas: 10500
    ///Personelin terfisi gerceklesti. Yeni terfi seviyesi: 1
    ///Gecici bir sureligine iscilerin maasina zam yapilmasi sirketce yasaklandi.
    ///Personelin terfisi gerceklesti. Yeni terfi seviyesi: 1
    ///Gecici bir sureligine iscilerin maasina zam yapilmasi sirketce yasaklandi.
}