package com.example.mynewnonameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // design with navigation component

        // olusturdugumuz navigation component icin birkac fragment olusturduk.
        // fragment default FrameLayout olarak geldi. bunlari Constraint'e cevirdik.
        // daha sonra hepsine viewbinding ekledik.

        // fragmentlari navigationa ekledik.
        // navigation'i MainActivity'e ekledik. Constrait widgetta hepsini kenarlara baglayarak
        // uzakliklarini 0 yaptik. layout width ve layout height degerlerini de match constrait
        // olacak sekilde ayarladik.

        // simdi fragmentlari birbirine bagliyoruz. baglayinca bizim icin bir action olusturuyor.
        // bu actionlara anlasilir id'ler tanimliyoruz. daha sonra bu actionlari fragment.kt
        // dosyalarinda butonlara ekliyoruz.

        // backstack
        // Main, Menu ve Categories fragmentlari = A B C
        // C ye kadar geldikten sonra geri tusuna basinca B ye degil A ya donmesini istersek
        // B yi backstackten silmemiz gerekir.
        // B yi C ye bagladigimiz action'da pop behaviour attribute'unu kullanarak C den geri B ye
        // donme islemini degistirip B yi backstacten sil anlamina gelen islemi yapiyoruz.
        // popUpTo icin B yi sectik, popUpToinclusive attribute'unu da true yaptik.
        // bu sayede B backstackten silinmis oldu. C den dogruca A ya gecis yapacagiz.
    }
}