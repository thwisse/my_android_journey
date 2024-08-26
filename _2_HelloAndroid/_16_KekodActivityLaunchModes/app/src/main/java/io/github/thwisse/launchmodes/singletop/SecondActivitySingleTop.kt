package io.github.thwisse.launchmodes.singletop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivitySingleTop : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_single_top)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)

        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivitySingleTop::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivitySingleTop::class.java))
        }

        // singleTop'i deneyecegimiz activity SecondActivitySingleTop olacak. yani MainActivitySingleTop
        // standart olmaya devam edecek.

        // eger main-second-main-second-second yapmaya calisirsak:
        // second tekrar acilmaz, new intente girer. yani son activity ne ise, eger ekranda acik
        // olmasina ragmen tekrar acilmaya calisirsa onNewIntent calisir, activity tekrar acilmaz.
        ///* Hist  #3: ActivityRecord{3264e4d u0 io...singletop.SecondActivitySingleTop t7941}
        ///* Hist  #2: ActivityRecord{f23a984 u0 io...singletop.MainActivitySingleTop t7941}
        ///* Hist  #1: ActivityRecord{de20cb6 u0 io...singletop.SecondActivitySingleTop t7941}
        ///* Hist  #0: ActivityRecord{f3c5c1d u0 io...singletop.MainActivitySingleTop t7941}

        // tekrar acilmaya calisan instance acilmaz, en tepedeki (singleTop) instance'in onNewIntent
        // fonskiyonu calisir. onnewintent, bu activity'nin yeni bir intent ile tekrar acilmak
        // istendiginde calisan fonskiyondur. yani intent islemi gerceklesir ancak singleTop old icin
        // activity acilmaz. redirection islemlerini onnewintent'te yaparsin.
        // activity'nin yeni bir instance'i olusmadigi icin state'i kaybolmuyor. ekranda bir degisiklik
        // olmuyor.

        // ornegin uygulamanin icinde x activityde iken push notif gelirse ve kullanici bastiginda
        // kullaniciyi tekrar x activitye yonlendirmeye calisirsa x activity 2 kez acilmis olur ust
        // uste. ancak activityi singleTop olarak ayarlarsan boyle bir sorunun olmaz. ornegin push notif
        // ile acilmaya calisan activitynin intentinde de veriler varsa bunlari onnewintent icinden
        // redirect edeceksin ya da activity'deki verileri yeni verilerle guncelleyeceksin.

        // FLAG_ACTIVITY_SINGLE_TOP flag'i singleTop launchMode'u ile ayni isi yapar.
        // bir activity'nin default olarak standart modda calismasini ancak bazen farkli bir
        // modda calismasini istedigimizde o mod'un flagini bir yerde kullanabiliriz.

        //btnOpenSecondActivityAgain.setOnClickListener {
        //    val intent = Intent(this, SecondActivitySingleTop::class.java).apply {
        //        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        //    }
        //    startActivity(intent)
        //}
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "New Intent", Toast.LENGTH_SHORT).show()
    }
}