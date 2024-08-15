package com.example.cannonball

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cannonball.databinding.ActivityFourBinding
import com.google.android.material.snackbar.Snackbar

class ActivityFour : AppCompatActivity() {

    private lateinit var binding: ActivityFourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // snackbar kullanimi

        // kullanmak icin material design kutuphanesine ihtiyacimiz vardir.
        // bunu projeye yuklemenin farkli yontemleri var. ancak benim gradle module scriptimde
        //implementation("com.google.android.material:material:1.11.0")
        // seklinde zaten var. sanirim bu kutuphane artik default olarak geliyor projelerde, emin degilim.
        // 3 ayri snackbar olusturacagiz.

        // klasik butonsuz vs snackbar

        binding.buttonSnackBarKlasik.setOnClickListener {  view ->
            // view yapisi yerine direkt view yerine butonun adini da yazabilirdik.
            // snackbar ilk arguman olarak bizden view edecek nesneyi istiyor. yani hangi gorsel nesne
            // snackbari calistiracaksa onu ogrenmek istiyor. biz de bu nesneyi
            // liseteneri olusturduktan sonra view -> yazarak ilettik ve snackbar icinde kullandik.
            Snackbar.make(view, "Merhaba", Snackbar.LENGTH_SHORT).show()
        }

        // snackbar ile geri donus veren, butonlu snackbar

        binding.buttonSnackBarOzel1.setOnClickListener { view ->
            Snackbar.make(view, "Mesaj silinsin mi?", Snackbar.LENGTH_LONG).setAction("Sil") { view2 ->
                Snackbar.make(view2, "Mesaj silindi.", Snackbar.LENGTH_SHORT).show()
                // mesela burda da actionu temsil eden view2 nesnesini verdik.
            }.show() // ana snackbar'in show'u.
            //TODO birden fazla action eklenebiliyor mu? vazgec diye action eklemek istiyorum.

            //TODO bir de snackbar'in suresini kisa ya da uzun diye degil de butona basana kadar duracak
            // sekilde ayarlamak istiyorum. bunlari ogren.
        }

        // bir snackbar nesnesi olusturarak snackbar gostermek

        binding.buttonSnackBarOzel2.setOnClickListener {view ->
            val snackbar = Snackbar.make(view, "Internet baglantisi yok!", Snackbar.LENGTH_LONG)

            snackbar.setAction("Tekrar Dene") {
                // islem
            }
            // action butonunun rengini degistirebiliriz.
            snackbar.setActionTextColor(Color.WHITE)
            // Color android'in renk sinifidir.
            // text rengi degisimi:
            snackbar.setTextColor(Color.YELLOW)
            // arkaplan renk degisimi:
            snackbar.setBackgroundTint(Color.RED)

            snackbar.show()
        }

        binding.buttonGoToActivityFive.setOnClickListener {
            startActivity(Intent(this@ActivityFour, ActivityFive::class.java))
        }
    }
}