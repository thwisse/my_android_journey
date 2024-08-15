package io.github.thwisse.launchmodes.standart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R
import io.github.thwisse.launchmodes.singletask.SecondActivitySingleTask
import io.github.thwisse.launchmodes.singletop.SecondActivitySingleTop

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

        findViewById<Button>(R.id.btnOpenSecondActivity).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        //////////////////////////////////////////////
        // backstack

        // single activity pattern: tek activity, cok fragment

        // recents tusuna basip da uygulamayi stop ettigimiz zaman recents/overview gorunumundeki o
        // uygulama gorunumu task'tir. tum uygulamalarin oradaki gorunumu ayri task'lardir.
        // yani sadece backstack degildir. bir uygulama icin birden fazla task olabilir, bu yuzden
        // birden fazla backstack de olabilir.

        // C:\...\Android\Sdk\platform-tools\adb shell dumpsys activity activities | findstr "Hist"
        // terminalde bu komutla mevcut backstack izlenebilir. her activity degisikliginden sonra
        // komutu tekrar yazarak mevcut backstack'i gorebiliriz.
        // activity'lerin yaninda t7940 gibi kodlar var. bunlar task'larini ifade eder. ayni taskta
        // olan activitylerde ayni kod yazar.

        // tasklarin icinde birden fazla activity bulunabilir. ornegin startActivity ile actigimiz diger
        // activity'ler backstackte tutulur. bu backstack task'in icindedir.

        // ozel degisiklikler yaparak kendi uygulamamin taski icerisinde farkli uygulamalarin activity'lerini
        // de bulundurabilirim.

        ///////////////////////////////////////////
        // launch modes

        // intent flag olarak ya da manifest uzerinden launch mode tanimlamasi yapilabilir.
        // her ikisine de ozel olan launch mode'lar var.
        // ornegini yaparken her bir mod icin manifestten intent filter'i oraya tasimak gerekir.

        // bir mod en dogrusu gibi bir sey yok. hangi mod nerede kullanilmasi gerekiyorsa orada
        // kullanilmalidir. launch mode fanatikligine hayir!

        // standart
        // SecondActivity -> standart.
        if (false) run { SecondActivity::class.java }
        // hicbir launch mode verilmediginde default'u manifestte android:launchMode="standard"
        // seklindedir. yani her zamanki gordugumuz tek task olayi standart olandir.

        // eger main-second-main-second-second yapmaya calisirsak:
        // hepsi acilir hic de sikinti olmaz. standart launch mode bu sekildedir.
        ///* Hist  #4: ActivityRecord{7f76634 u0 io.github.thwisse.launchmodes/.SecondActivity t7940}
        ///  keysPaused=false inHistory=true idle=true
        ///* Hist  #3: ActivityRecord{6052509 u0 io.github.thwisse.launchmodes/.SecondActivity t7940}
        ///  keysPaused=false inHistory=true idle=true
        ///* Hist  #2: ActivityRecord{c006344 u0 io.github.thwisse.launchmodes/.MainActivity t7940}
        ///* Hist  #1: ActivityRecord{96ad1ab u0 io.github.thwisse.launchmodes/.SecondActivity t7940}
        ///  keysPaused=false inHistory=true idle=true
        ///* Hist  #0: ActivityRecord{598b56b u0 io.github.thwisse.launchmodes/.MainActivity t7940}

        // singleTop
        // SecondActivitySingleTop -> singleTop.
        if (false) run { SecondActivitySingleTop::class.java }
        // singletop package'i icinde incelenecek.

        // singleTask
        // SecondActivitySingleTask -> singleTask.
        if (false) run { SecondActivitySingleTask::class.java }
        // singletask package'i icinde incelenecek.

    }
}