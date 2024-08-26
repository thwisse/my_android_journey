package com.example.pomegranate

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ActivityFive : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)

        /////////////////
        // uyari mesaji vermek
        // 1- toast message, 2- alert dialog, 3- snackbar

        ///////////////
        // context nedir
        // android uygulamalarinda nerede ne oluyor takip edebilmek uzere yaratilmis bir yapidir.
        // android, biz kullaniciyla etkilesime gecmeye calistigimizda o islemi nerede yapmak
        // istedigimizi ogrenmek amaciyla context ister.
        // genelde 2 farkli contexti kullaniriz.
        // 1- activity context = activity icinde kullanilan context (this) ya da (this@ActivityName)
        // 2- application context = tum uygulamada kullanilan context (applicationContext)
        // bazen activity icinde calistirmadigimiz kodlar oluyor. buralarda context istendiginde
        // applicationContext kullanabiliriz. ancak bir activity icinde calisiyorsak da this ya da
        // kesin sonuc icin this@ActivityName kullaniriz. this her zaman activity'e referans vermeyebiliyor.
        // activity icinde applicationContext kullanmak pek kullanisli degilmis.

        // toast message
        Toast.makeText(this@ActivityFive, "Hello", Toast.LENGTH_LONG).show()

        // contextle alakali bazi egzersizler:

        // onClick attribute'una function ekleme ve findviewbyid ile setOnClickListener calistirma
        // mevzularindan ikisinden birini secmek gerekiyo. ikisi ayni anda calismiyor.

        val button = findViewById<Button>(R.id.buttonDamn)

        // setOnClickListener lambda gosterim
        button.setOnClickListener {
            println("button clicked")
            // logcatten kontrol edebilirsin.
        }

        // setOnClickListener'in lambda olmayan gosterimi. interface implemente edilisi gozukuyor vs.
        // bu arada buna sag tiklayip convert lambda diyince usttekine donusuyor.
        button.setOnClickListener(object: View.OnClickListener {
            // object isimli class, anonim bir classtir.
            override fun onClick(v: View?) {
                println("clicked clicked")
                // bu arada buraya this yazdiginda artik fonksiyonun kendisine referans verdigini
                // ve activity'i isaret etmedigini gorebiliriz. bunun da ornegi olmus oldu.
            }
        })
        // iki tane setOnClickListener olmus oldu ayni button icin. hangisini deneyeceksen digerini
        // yorum satirina al. gerci deneyecek bise de yok da. ama ikisi de acikken 2. calisiyor.

        ////////////////////
        // alert dialog
        // clickButton fonksiyonu icinde yapiyorum.

        val buttonSix = findViewById<Button>(R.id.buttonGoToActivitySix)

        buttonSix.setOnClickListener {
            startActivity(Intent(this@ActivityFive, ActivitySix::class.java))
        }

    }

    fun clickButton (view: View) {
        // alert dialogda context olarak applicationContext girdigimizde uygulama cokuyor.
        // iste burada da goruldugu uzere activity icinde mumkun oldugunca this ya da this@ActivityName
        // kullanmak gerekiyor
        val alert = AlertDialog.Builder(this@ActivityFive)
        alert.setTitle("Title")
        alert.setMessage("Message")
        // pozifif mesaji lambda gosterimi olmadan hazirlayalim.
        alert.setPositiveButton("Yes", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@ActivityFive, "Yes!!", Toast.LENGTH_LONG).show()
                // (dialog: DialogInterface?) ve (which: Int) parametrelerini kullanmadim.
                // kullanmak zorunda da degiliz. ama orada yazilmak zorunda.
            }
        })
        // negatif mesaji da lambda gosterimi yaratarak hazirlayalim.
        alert.setNegativeButton("No") { dialog, which ->
            // { dialog, which -> } kismini kendim yazdim. positive gosterimde de vardi. onclick methodunda
            // olmasi gereken parametreler bunlar. kullanmak zorunda da degiliz. ama orada yazilmak zorunda.
            Toast.makeText(this@ActivityFive, "No!!", Toast.LENGTH_LONG).show()
        }
        // notr button da varmis. tasarimda en solda gozukuyor.
        alert.setNeutralButton("Hmm") { dialog, which ->
            Toast.makeText(this@ActivityFive, "Hmmmm!!", Toast.LENGTH_LONG).show()
        }

        alert.show()
    }
}