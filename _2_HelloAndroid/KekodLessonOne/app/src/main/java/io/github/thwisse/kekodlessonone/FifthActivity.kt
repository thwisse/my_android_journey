package io.github.thwisse.kekodlessonone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FifthActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fifth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // android:text="Open Settings Activity" ile tools:text="Open Settings Activity" farki:

        // android kullanirsan runtime sirasinda o yazi gozukecektir haliyle. ancak tools
        // kullanirsan design kisminda sen ciktinin ne oldugunu gorursun ancak uygulama calistiginda
        // yani runtime sirasinda o yazi gozukmez. bunu niye yapmak isteyelim? sundan dolayi:
        // backendden gelen bir veriyi design'da test amacli gormek ancak ekrana yazdirmamak istersek
        // android yerine tools kullanabiliriz. veri strings.xml'in icinden, backend'den vs gelecekse
        // tools kullanarak oraya rasgele bir sey yazip testini tasarimini yaparsin. uygulama
        // calisirsa da gozukmez. veri geldiginde ise senin tasariminla gozukur. sikinti olmamis olur.

        val btnOpenSettingsActivity = findViewById<Button>(R.id.btnOpenSettingsActivity)


        // findViewById'nin sikintisi neydi ki databinding cikti? databinding'in sikintisi neydi
        // ki viewbinding cikti?
        // findviewbyid, bir id'ye sahip componenti bulmak icin idlerin hepsini tek tek tariyor
        // ve o sekilde baglama yapiyordu. bir de type safety ve null safety yok.
        // yani ayni id'ye sahip baska bir component varsa ona baglama yapabiliyor, bazen baglama
        // yapmayabiliyor, kodu yazarken bunla alakali compile time'da ya da
        // build time'da hata bile vermiyor, anca runtime'da veriyor. bu da sikintili bir durum.
        // artik viewbinding kullanildigi icin farkli xml'lerde ayni id'ye sahip componentler yaratmak
        // sikinti yapmiyor. ancak findviewbyid kullanacak olursan bu sikinti yaratir. ekstra olarak
        // multi-module calistiginda her module icin farkli R dosyasi oldugu icin R.id.bilmemne
        // kullanimlarinda R dosyalari karisabiliyor. bu da sikinti yaratabilir.

        /////////////////////////////////
        // setOnClickListener kullanimlari (tum eventler icin gecerli kullanimlar)

        // 1- lambda kullanimi (sam interfaces)
        // en sik kullanilan bu.
        btnOpenSettingsActivity.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // (favori)
        // 2- kendi fonksiyonunu yazma
        btnOpenSettingsActivity.setOnClickListener(::openSettingsActivity)
        // en temizi bu.

        // 3- implementation
        btnOpenSettingsActivity.setOnClickListener(this)
        // bu this'i yazinca bu class'a View.OnClickListener interface'ini implement etmemizi ve
        // onClick methodunu override etmemizi istiyor. click islemini o fonskiyonda yapabiliriz.
        // bu da fena degil ancak birden fazla onClick icin this kullanacak olursan onClick fonk
        // icinde when ile onlari ayristirman gerekiyor.

        // 4-5-6-7 cok onerilmiyor. ustteki 3'u daha kullanisli ve mantikli imis.
        // ama bu tarzlari bilmende yarar var kod okuma icin.

        // 4- object expression use (lambda kullaniminin tam tersi)
        btnOpenSettingsActivity.setOnClickListener(object: View.OnClickListener{
            // implement members uyarisiyla override ettik.
            override fun onClick(v: View?) {
                startActivity(Intent(this@FifthActivity, SettingsActivity::class.java))
            }
            // zaten bunu lambda'ya cevir diye uyari veriyor.
        })

        // 5- degiskene atayip sonra kullanimi
        val onClickListener = { view: View? ->
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        btnOpenSettingsActivity.setOnClickListener(onClickListener)

        // 6- isimsiz class ile
        val onClickListener2 = fun (view: View?) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        btnOpenSettingsActivity.setOnClickListener(onClickListener2)

        // 7- xml'deki onClick ozelligi (public function ile)
        //android:onClick="openSettingsActivity2"
        // bu onerilmeyen bir sey. zaten deprecated olmus. digerlerini kullan.
        // zaten fonksiyon da public oldugu icin mantikli bir yol degil.

        //////////////////////////////////////////////
        // backstack

        // single activity pattern: tek aktivite multiple fragment

    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun openSettingsActivity (view: View?) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    fun openSettingsActivity2 (view: View?) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}