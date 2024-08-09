package io.github.thwisse.kekodlessonone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // views

        // tum view komponentler view class'ini extend eder.

        // palette bulunan componentler disinda baskalarinin yaptigi custom componentleri ya da
        // kendi yapacagin custom componentleri de ui'da kullanabileceksin.

        // not1: xml icin de kt uzantili dosyalar icin de encoding'in dogru ayarlandigindan emin ol
        // turkce karakterlerde sorun olursa buradan duzenleyeceksin.

        // wrap = sarmak.
        // wrap content = icerige gore boyutunu ayarla
        // match parent = icinde bulundugun ebeveynin sinirlarina kadar genisle

        // bir componentin width ve height degerleri olmasi zorunludur. baska hicbir degeri olmasa
        // bile bunlar olmak zorundadir.

        // running devices > toggle layout inspector > snapshot export
        // view'lari 3d bir sekilde inceleyebilirsin.
        // ornegin cok fazla komponenti ust uste kullanip da ayni pikseli cok kez farkli amaclarla
        // cizdigin icin ekranin acilis performansi etkilenebilir. bunu bu 3d mode ile gorebilir,
        // hangi bolgelere fazladan yuklenme var inceleyebilirsin.

        // frame layout = componentler ust uste biner.
        if (false) { R.layout.activity_fourth_frame_layout }
        // en az performans gerektiren, en lightweight viewgroup'tur.
        // tek bir componenti ekranda gostermek ve baska hicbir islem yapmamak gibi bir istegin
        // varsa framelayout kullanabilirsin. digerlerinden daha performansli olacaktir.
        
        // linearlayout


    }
}