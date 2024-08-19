package io.github.thwisse.launchmodes.singleinstancepertask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivitySingleInstancePerTask : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button
    private lateinit var btnOpenThirdActivity: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_single_instance_per_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)
        btnOpenThirdActivity = findViewById<Button>(R.id.btnOpenThirdActivity)

        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivitySingleInstancePerTask::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivitySingleInstancePerTask::class.java))
        }

        btnOpenThirdActivity.setOnClickListener {
            startActivity(Intent(this, ThirdActivitySingleInstancePerTask::class.java))
        }

        // yapisal olarak single instance gibi davraniyor, second uzerinden baska open islemleri
        // yapilmaya calisildiginda single task gibi davraniyor.

        // eger main-second-third yapmaya calisirsak:
        ///* Hist  #1: ActivityRecord{e4e4064 u0 io...ThirdActivitySingleInstancePerTask t56}
        ///* Hist  #0: ActivityRecord{1472ddd u0 io...SecondActivitySingleInstancePerTask t56}
        ///* Hist  #0: ActivityRecord{71ab8e8 u0 io...MainActivitySingleInstancePerTask t55}
        // yine task degisti. ancak bu sefer third, second'in backstack'ine eklendi.
        // ayri task'ta devam etmesi konusunda singleInstance gibi davraniyor.

        // ama singleInstance backstack'ini baska activity'lerle paylasmiyorken, singleInstancePerTask
        // paylasiyor.

        // eger main-second-main-second yapmaya calisirsak:
        ///* Hist  #0: ActivityRecord{7320873 u0 io...SecondActivitySingleInstancePerTask t58}
        ///* Hist  #0: ActivityRecord{59f7f4f u0 io...MainActivitySingleInstancePerTask t57}
        // yeni instance'in acilmasina izin vermedi. onNewIntent calisti. kendinden sonraki
        // tum activity'leri de sildi. burada da singleTask gibi davraniyor.

        // bunu en son singleInstance icin de yaptik simdi bura icin de yapalim.
        //
        // eger main-second-third-second yapmaya calisirsak:
        // once main-second-third kismini tekrar gorelim:
        ///* Hist  #1: ActivityRecord{5b0714f u0 io...ThirdActivitySingleInstancePerTask t74}
        ///* Hist  #0: ActivityRecord{6813c25 u0 io...SecondActivitySingleInstancePerTask t74}
        ///* Hist  #0: ActivityRecord{1e2ba50 u0 io...MainActivitySingleInstancePerTask t73}
        //
        // simdi third'deyken second'i back ile degil button ile tekrar acmaya calisalim:
        ///* Hist  #0: ActivityRecord{6813c25 u0 io...SecondActivitySingleInstancePerTask t74}
        ///* Hist  #0: ActivityRecord{1e2ba50 u0 io...MainActivitySingleInstancePerTask t73}
        // yeni instance olusturulmadi. onNewIntent calisti. ilk second uzerindeki tum activity'ler
        // silindi. yani burada da singleTask gibi davraniyor.

        // kisaca, singleInstancePerTask, affinity olmamasina ragmen kendi taskini yaratarak calisma
        // konusunda singleInstance'a benzerken, bundan sonraki her seyi singleTask'a benziyor.

        // singleTask ve singleInstancePerTask, Task uzerindeki stack'te kendilerinden sonra gelen
        // activity'leri silerler.

        /////////////////////////////////////////
        // singleInstancePerTask flagleri
        // perTask adi verilme sebebi bu flag'lerden geliyor.

        // ancak bu activity'i FLAG_ACTIVITY_MULTIPLE_TASK ve FLAG_ACTIVITY_NEW_DOCUMENT
        // flagleri ile acarsak farkli task'lar uzerinde birden fazla instance'i bulunabilir.
        // yani bu yukaridaki orneklerde bu flaglerin verilmedigi durumda islemler tek taskta gerceklesiyordu.

        // simdi bu flagleri third activity'deki second'a donus butonuna uygulayacagiz.

        // birden fazla flagi ayni anda uygulamak icin "or" keywordu kullanabiliriz.

        // flaglerden sonra simdi main-second-third-second yapmaya calisirsak:
        // once main-second-third kismini tekrar gorelim:
        ///* Hist  #1: ActivityRecord{5a20ec0 u0 io...ThirdActivitySingleInstancePerTask t80}
        ///* Hist  #0: ActivityRecord{40fa771 u0 io...SecondActivitySingleInstancePerTask t80}
        ///* Hist  #0: ActivityRecord{7af2ed3 u0 io...MainActivitySingleInstancePerTask t79}
        //
        // simdi flagler varken thirdde second'u buton ile acalim:
        ///* Hist  #0: ActivityRecord{c3ad488 u0 io...SecondActivitySingleInstancePerTask t81}
        ///* Hist  #1: ActivityRecord{5a20ec0 u0 io...ThirdActivitySingleInstancePerTask t80}
        ///* Hist  #0: ActivityRecord{40fa771 u0 io...SecondActivitySingleInstancePerTask t80}
        ///* Hist  #0: ActivityRecord{7af2ed3 u0 io...MainActivitySingleInstancePerTask t79}
        // onNewIntent calismadi. yeni instance olusturuldu, hatta yeni instance yeni bir
        // task uzerinde olusturuldu. hatta yeni task recents kisminda 2. bir app gibi de gozukuyor.
        // flagler kullanilmadan once bunu yapacak olsak acilan son second acilmaz, third de silinirdi.
        // geriye sadece main ve second kalirdi. ancak su an main ayri, second ve third ayri, yeni acilan
        // second ise ayri tasklerde olmus oldu.

        // her baslatilan second activity kendisine yeni bir task acacak.

        // single instance per task = her task basina single instance
        // yani activitynin her taskta bir tane yalniz ornegi olabilecek.


    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "New Intent", Toast.LENGTH_SHORT).show()
    }
}