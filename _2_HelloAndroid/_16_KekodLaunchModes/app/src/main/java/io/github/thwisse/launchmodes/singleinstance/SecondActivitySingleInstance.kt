package io.github.thwisse.launchmodes.singleinstance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivitySingleInstance : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button
    private lateinit var btnOpenThirdActivity: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_single_instance)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)
        btnOpenThirdActivity = findViewById<Button>(R.id.btnOpenThirdActivity)

        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivitySingleInstance::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivitySingleInstance::class.java))
        }

        btnOpenThirdActivity.setOnClickListener {
            startActivity(Intent(this, ThirdActivitySingleInstance::class.java))
        }

        // eger activity singleInstance olarak ayarlandiysa, bir affinity degeri vermememize ragmen
        // kendini yeni bir taskta acar. ancak backgroundda ilk task gorunmez.
        ///* Hist  #0: ActivityRecord{5e179e4 u0 io...singleinstance.SecondActivitySingleInstance t29}
        ///* Hist  #0: ActivityRecord{9d1df2b u0 io...singleinstance.MainActivitySingleInstance t28}

        // bu durumda second activity'den baska bir (third) activity'e gidecek olursak
        // o acilan 3. activity 2. activity'nin degil main activity'nin backstackine eklenir.
        ///* Hist  #1: ActivityRecord{6b34cf9 u0 io...singleinstance.ThirdActivitySingleInstance t28}
        ///* Hist  #0: ActivityRecord{9d1df2b u0 io...singleinstance.MainActivitySingleInstance t28}
        ///* Hist  #0: ActivityRecord{5e179e4 u0 io...singleinstance.SecondActivitySingleInstance t29}

        // bu durumda geri tusuna basildinda ise main activity'e donecektir:
        ///* Hist  #0: ActivityRecord{9d1df2b u0 io...singleinstance.MainActivitySingleInstance t28}
        ///* Hist  #0: ActivityRecord{5e179e4 u0 io...singleinstance.SecondActivitySingleInstance t29}
        // geri tusuna bastiktan ve main'e dondukten sonra ayri taskta olan second kapanmaz.
        // recents kisminda da ayri bir task olarak gozukmez.

        // bu durumda maindayken tekrar second acildiginda:
        ///* Hist  #0: ActivityRecord{5e179e4 u0 io...singleinstance.SecondActivitySingleInstance t29}
        ///* Hist  #0: ActivityRecord{9d1df2b u0 io...singleinstance.MainActivitySingleInstance t28}
        // ilk haline dondu. task degismedi.

        // bu durumda second'dayken third yerine tekrar main acilirsa:
        ///* Hist  #1: ActivityRecord{e8af0a8 u0 io...singleinstance.MainActivitySingleInstance t28}
        ///* Hist  #0: ActivityRecord{9d1df2b u0 io...singleinstance.MainActivitySingleInstance t28}
        ///* Hist  #0: ActivityRecord{5e179e4 u0 io...singleinstance.SecondActivitySingleInstance t29}
        // ayni third gibi bunu da main'in task'inde tekrar acti.

        // bu durumda backstackte bir second activity varken tekrar second acilmaya calisirsa:
        // hicbir sey degismedi. yeni instance olusmasina izin vermedi. onNewIntent calisti.
        // backstack ayni yukardaki olarak kaldi.

        // yani main-second-third yapinca geri gelince main'e doneriz. anca maindeyken geri yapinca
        // seconda doneriz. backstackte en geriye atilmis olur cunku second.

        // yani bir activity'nin kendi taskinda durmasini ve baska hicbir activity'nin bu taska
        // girmesini istemiyorsak bunu kullanacagiz.

        // sonradan yaptik:
        //
        // eger main-second-third-second yapmaya calisirsak:
        // once main-second-third kismini tekrar gorelim:
        ///* Hist  #1: ActivityRecord{9cd0ef u0 io...singleinstance.ThirdActivitySingleInstance t63}
        ///* Hist  #0: ActivityRecord{29d65f7 u0 io...singleinstance.MainActivitySingleInstance t63}
        ///* Hist  #0: ActivityRecord{6e6a281 u0 io...singleinstance.SecondActivitySingleInstance t64}
        //
        // simdi third'deyken second'i back ile degil button ile tekrar acmaya calisalim:
        ///* Hist  #0: ActivityRecord{6e6a281 u0 io...singleinstance.SecondActivitySingleInstance t64}
        ///* Hist  #1: ActivityRecord{9cd0ef u0 io...singleinstance.ThirdActivitySingleInstance t63}
        ///* Hist  #0: ActivityRecord{29d65f7 u0 io...singleinstance.MainActivitySingleInstance t63}
        // onNewIntent calisti. yeni instance olusturulmadi. ancak second backstack'te uste yerlesti.

        // eger main-second-third-second-third yapmaya calisirsak:
        ///* Hist  #2: ActivityRecord{c6f73e9 u0 io.g..singleinstance.ThirdActivitySingleInstance t68}
        ///* Hist  #1: ActivityRecord{471149f u0 io...singleinstance.ThirdActivitySingleInstance t68}
        ///* Hist  #0: ActivityRecord{9c90214 u0 io...singleinstance.MainActivitySingleInstance t68}
        ///* Hist  #0: ActivityRecord{6e11cd7 u0 io...singleinstance.SecondActivitySingleInstance t69}
        // yine second kendini arkaplana atti. kimseyle backstack'ini paylasmadi.
        // acilan son activity olan third yine main activity'nin backstack'ine eklendi.

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "New Intent", Toast.LENGTH_SHORT).show()
    }
}