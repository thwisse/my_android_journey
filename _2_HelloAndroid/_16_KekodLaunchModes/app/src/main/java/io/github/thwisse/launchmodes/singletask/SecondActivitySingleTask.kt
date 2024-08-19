package io.github.thwisse.launchmodes.singletask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivitySingleTask : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_single_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)

        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivitySingleTask::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivitySingleTask::class.java))
        }

        // eger ki bir activity singleTask olarak ayarlanirsa, bu activity backstack'e bir kez bile
        // eklendiyse (backstackteki yeri onemli degil top vs.) ve biz o activity'i tekrar acmak
        // istersek (singleTask olarak ayarlanan activity'i) backstacte uzerinde bulunan tum activity'leri
        // siler, kendinin yeni instance'ini olusturmaz ve onNewIntent'i calisir. bu sekilde her
        // zaman en tepedeki activity olmayi garantilemis olur.

        // ornegin bankacilik uygulamasinda dashboard activity > havale > fast > hesap secimi vs.
        // bir suru ekrana gidip haval islemi gerceklestirdin. kullanicinin islemi gerceklestikten sonra
        // onay ekranindan dashboard'a donerse ve olur da back tusuna basarsa geri havalenin onay ekranina
        // gitmesin diye, dashboard'a gelindikten sonra backstackteki ustundeki tum activity'ler silinir
        // ve top olarak kendisi kalir. zaten havale activity'i finish ettiginde de oraya geri
        // donemeyecektir de o ayri konu mantigini anlaman icindi bu ornek. ama mesela finish kullanamiyor
        // olsan bunu kullanacaktin iste.

        // singleTop'tan farki, activity'nin top'ta olmasina gerek yok baska instance'inin acilmasina
        // izin vermemesi icin. ekstra olarak 2. instance'inin acilma istegi yasanana kadar acilan
        // diger tum activity'ler de siliniyor kendi haric.

        // eger main-second-main-second yapmaya calisirsak:
        ///* Hist  #1: ActivityRecord{afa8a23 u0 io...singletask.SecondActivitySingleTask t11}
        ///* Hist  #0: ActivityRecord{e358d94 u0 io...singletask.MainActivitySingleTask t11}

        ///////////////////////////////////
        // taskAffinity

        // bir activity'e taskAffinity ozelligi vermediysen default olarak kendi package name'ini alir.

        // singleTask verilmis bu activity'e eksta olarak ozel bir affinity degeri verecegiz.
        // bu sayede bu activitynin kendi taski ve kendi backstacki oluyor. hatta recents kisminda
        // ayri bir uygulama gibi gozukuyor. asagidaki ornekleri incele:

        // affinity'si farkli olmadiginda task degismez.

        // eger main-second yapmaya calisirsak:
        ///* Hist  #0: ActivityRecord{fac3f97 u0 io...singletask.SecondActivitySingleTask t17}
        ///* Hist  #0: ActivityRecord{c3e665e u0 io...singletask.MainActivitySingleTask t16}
        // ikisinin de task id'si farkli.

        // eger main-second-main-second yapmaya calisirsak:
        ///* Hist  #0: ActivityRecord{f7f0800 u0 io...singletask.SecondActivitySingleTask t20}
        ///  * Hist  #0: ActivityRecord{b06b4e8 u0 com...launcher/.activities.LauncherActivity t6}
        ///* Hist  #0: ActivityRecord{ddf0e7f u0 com...launcher/com.android.quickstep.RecentsActivity t7}
        ///* Hist  #0: ActivityRecord{6167323 u0 io...singletask.MainActivitySingleTask t19}
        // ayri olarak acilan taskta main aciliyor ancak second'i tekrar cagirinda newintent calisiyor
        // ve ustteki tum activity'ler silinerek ilk second'a donuyor.

        // ornegin guvenlik onlemi amacli bir islemi ayri bir task icinde yaptirip islem bitince
        // ana task'a donecek sekilde kullanilabilir.

        // taskAffinity + allowParenting
        // veya diyelim sirketin birden fazla uygulamasi var ve bu uygulamalar kendi aralarinda
        // navigation'lar gerceklestiriyor. o zaman activity'lerin taskAffinity'lerini ayni yapip
        // allowParenting degerlerine de true verdiginde uygulamalar birbirlerinin task'larina erisebilir
        // ve o tasklar icinde islemler yapabilirler.
        // yani baska uygulamanin task'ina da girebiliriz, kendi uygulamamizin taskinda baska
        // uygulamayi da calistirabiliriz.

        // ayri taskta acilan activity'den onceki task'a donerken eger 2. task'i ortadan kaldirmak
        // istiyorsak finish etmemiz gerekir. cunku etmezsek arkaplanda kaliyor o, kendi kapanmiyor.
        // finish etmediysek hatta:
        // 2. tasktan back ile 1. task'e donersek ana taskimizdan devam edebiliriz. ancak recents
        // kismindan 2. taski tekrar acarsak artik o ayri bir uygulama gibi davranacaktir. kendi
        // lifecycle'ine gore calisacaktir. geri tusuna bassan ana activity'e donmez, home button'a
        // basmissin gibi background'a atar.

        // FLAG_ACTIVITY_NEW_TASK bununla ayni isi yapar.
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "New Intent", Toast.LENGTH_SHORT).show()
    }
}