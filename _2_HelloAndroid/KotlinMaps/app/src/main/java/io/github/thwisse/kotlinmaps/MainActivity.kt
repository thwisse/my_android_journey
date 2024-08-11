package io.github.thwisse.kotlinmaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bir mapsActivity yaratildi, launcher activity olarak.
        // google maps ve cloud sitelerinden gereken islemler yapildi ve map icin gereken
        // api kopyalanip projeye eklendi. proje calistirilinca su an map gozukuyor.

        // mapsActivity'e geciyorum.

        // mapsActivity'de simdilik islerimiz bitti. oradan alinan konum bilgilerini db'ye
        // kaydedip, bu verileri cekip sergileyecegimiz yer mainActivity olacak. bunlari yapalim.

        // recycler view tasarima eklendi
        // recycler row layoutta yaratildi. tasarlandi.
        // activity_maps xml'inde onemli degisikler yapildi. map fragment'i constraint layout icine
        // alindi. diger componentler eklendi. save ve delete fonksiyonlari yaratildi.

        //not1: match constraint: constraint'lere saygi duy. geri kalan yerlere yayil.

        // activity_maps tasarimi bitti. simdi bir menu yaratip bunu main activity'e ekleyecegiz.
        // menu yaratildi.
        // bu menuyle alakali iki fonksiyon override etti hoca. onCreateOptionsMenu ve
        // onOptionsItemSelected adinda. ancak bunlari kullanirsam uygulamada toolbar olmasi gerekiyor.
        // toolbar kullanmak yerine ben fab olusturdum ve menuyu ona ekledim. fab kodlari asagida:

        val fab: FloatingActionButton = findViewById(R.id.fab)

        // PopupMenu'yu bir kez oluştur
        val popup = PopupMenu(this, fab)
        popup.menuInflater.inflate(R.menu.menu_place, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itemAddPlace -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        // FAB'a her tıklamada popup'u göster
        fab.setOnClickListener {
            popup.show()
        }

        // roomdan devam
    }
}