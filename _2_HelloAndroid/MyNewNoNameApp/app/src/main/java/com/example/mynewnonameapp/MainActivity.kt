package com.example.mynewnonameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // design with navigation component
        //
        // olusturdugumuz navigation component icin birkac fragment olusturduk.
        // fragment default FrameLayout olarak geldi. bunlari Constraint'e cevirdik.
        // daha sonra hepsine viewbinding ekledik.
        //
        // fragmentlari navigationa ekledik. navigation'i MainActivity'e ekledik.
        // bu islem icin NavHostFragment (fragment container view) kullandik.
        // Constrait widgetta hepsini kenarlara baglayarak
        // uzakliklarini 0 yaptik. layout width ve layout height degerlerini de match constrait
        // olacak sekilde ayarladik.
        //
        // simdi fragmentlari birbirine bagliyoruz. baglayinca bizim icin bir action olusturuyor.
        // bu actionlara anlasilir id'ler tanimliyoruz. daha sonra bu actionlari fragment.kt
        // dosyalarinda butonlara ekliyoruz.

        // backstack
        //
        // Main, Menu ve Categories fragmentlari = A B C
        // C ye kadar geldikten sonra geri tusuna basinca B ye degil A ya donmesini istersek
        // B yi backstackten silmemiz gerekir.
        // B yi C ye bagladigimiz action'da pop behaviour attribute'unu kullanarak C den geri B ye
        // donme islemini degistirip B yi backstacten sil anlamina gelen islemi yapiyoruz.
        // popUpTo icin B yi sectik, popUpToinclusive attribute'unu da true yaptik.
        // bu sayede B backstackten silinmis oldu. C den dogruca A ya gecis yapacagiz.
        // bunu ogrendikten sonra geri kaldirdim. projenin geri kalaninda ihtiyacim olursa
        // bir yerlerde kullanirim.

        // sayfa gecis animasyonu ekleme
        // navigation xmlinde gecis animasyonu eklemek istedigimiz actiona tiklayip animations
        // attributelarini kullanarak giris ve cikis animasyonlarini ekleyebiliriz.

        // sayfalar arasi veri transferi
        // bu islem icin ...safeargs.kotlin plugininin id'sini gradle module'une ekledik.
        // plugin:2.7.0 classpath'ini de gradle project'e ekledik.
        // A fragmentindan B fragmentina veri aktarmak icin, B fragmentindaki arguments kismina
        // aktarmak istedigim veriyi karsilayacak argumanlari ekliyorum.
        // argumanlari ekledikten sonra A fragmentinda FragmenNameDirections isimli yapi ve
        // B fragmentinda FragmentNameArgs yapilari olusacaktir.
        // A fragmentinda gecis islemini tanimlayip bu yapiyi da kullanarak hem veri aktarimini
        // hem sayfalar arasi gecis islemini ayni anda yapmis oluyoruz. B fragmentinda da bir
        // bundle yaratarak gelen verileri karsiliyoruz. daha sonra bu bundle araciligiyla verileri
        // istedigimiz yerde kullanabiliriz.

        // sayfalar arasi veri transferi (nesnelerle)
        // Categories isimli bir class olusturdum. data class'a donusturdum. Serializable interface'ini
        // implement ettim. buradan yarattigim nesneleri de A dan B ye veri olarak aktarmak icin
        // yine B de arguman olarak (Custom Serializable) ekledim. A da nesneyi yarattim ve
        // directions yapisina ekledim. B de de bundle ile yakaladim.

        // butonlarla sayfa gecisi islemini yaptiktan sonra bottom navigation ile ayni islemi
        // yapmaya calistim ve projenin icinden gectim. sikintisiz calisiyordu ancak ikisi bir arada
        // calismadi. simdi islemleri geri aldim ancak yine eskisi gibi de calismiyor.
        // yapacak bise yok burayi boyle birakiyorum ve yeni bir proje olusturup bottom navigation'i
        // orada calisacagim. bb.
        

    }
}