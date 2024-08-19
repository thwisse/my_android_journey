package io.github.thwisse.kekodappnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // app navigation

        // deep link & up button
        //
        // eger uygulama acikken ve birkac fragment vs ilerlemisken, deeplink ile uygulamadaki
        // herhangi bir yere gidilirse, uygulamanin o onceki backstack'i silinir, deeplinkle acilan
        // kisim ve sonrasi yeni backstack'i olusturur.
        // orn: A -> B -> C gibi bir backstack varken deeplink ile D gibi bir fragment acildiginda
        // backstack suna donusur: A -> D
        //
        // deeplink ile geldigin fragment'tan back tusu ya da yukaridaki geri butonu (up button)
        // ile geri gitmeye calistiginda uygulamanin menusune dogru gitmelisin, yani uygulamadan
        // cikmamalisin. o standart akis kullaniciya yine geriye dogru yaratilmali.
        //
        // up button ile cihazdaki back tusu ayni isi yapmali. ikisi de ayni yere geri donmeli.
        //
        // up butonu olmayan bir ekran yalnizca launcher activity olabilir. diger ekranlarda
        // up button olmali.
        //
        // BottomSheetDialogFragment gibi fullscreen olarak da kullanilabilen dialog'larda geri
        // tusu yerine genellikle sagda X ikonu kullanilir. bu ikon ile de geri gidilebilir. bu ayrimin
        // yapilma sebebi ekran acilis ve kapanis animasyonlarinin activity ve fragment'larda benzer,
        // dialoglarda ise farkli olusundan oturudur.
    }
}