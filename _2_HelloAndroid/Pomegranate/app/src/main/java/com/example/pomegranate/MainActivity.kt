package com.example.pomegranate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pomegranate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var myTextView: TextView

    // simpson ornegi icin tanimlamalar:
    lateinit var editTextName: EditText
    lateinit var editTextAge: EditText
    lateinit var editTextJob: EditText

    // binding yapmam gerekti. diger activity'e gecemedim.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //////////////////
        // viewBinding kullanmadan tasarim nesnelerine erismek:
        // tasarim nesnelerine erismenin en verimsiz yollarindan biri imis. viewBinding iyidir.
        // yukarida degiskeni yarattim.

        myTextView = findViewById(R.id.textView)

        fun summary (x: Int, y: Int): Int {
            return x + y
        }
        // myTextView. yazdigimda cikan ve kullanabilecegim ozellikler xml icinde degistirebildigim
        // ozellikler ve hatta daha fazlasini icerir.

        findViewById<Button>(R.id.button).setOnClickListener {
            myTextView.text = "Result: ${summary(5, 7)}"
        }

        ////////////////////
        // lateinit: late initialize. yani degerini daha sonra veririm.

        //lateinit var myTextView: TextView = findViewById(R.id.textView)
        // bunu yazdigimda lateinit error veriyor. cunku gerek yok zaten (= findViewById(R.id.textView))
        // yazarak degerini verdim. degerini vermeseydim:
        //lateinit var myTextView: TextView
        // seklinde yazarak yaratabilir ve degerini daha sonra verebilirdim. dogru kullanimi bu.
        //
        //var myTextView = findViewById<TextView>(R.id.textView)
        // ile
        //var myTextView: TextView = findViewById(R.id.textView)
        // ayni seydir. bu iki kullanim da sikintilidir. eger tasarimla calisacaksak, kod icinde
        // tasarimla ilgili tanimlamalar en erken onCreate methodu icinde tanimlanmalidir.
        // cunku eger o method icinde
        // tanimlanmayacaksa da null safety'si ayarlanmis olmali. lateinit olmadigi icin bu kullanimlarla
        // uygulama cokuyor. cokmemesi icin degisken tanimini method disinda null safety'li yaptim,
        // degisken atamasini ise method icinde yaptim. bu sekilde uygulama cokmeyecektir ve degisken
        // sadece onCreate icinde degil main class icinde her yerde kullanilabilir olmustur.

        ////////////////////
        // butona basinca fonksiyon calistirmanin bir baska yolu:

        // butonun attributeslarindan onClick olanina herhangi bir sey yaziyorum (funForButtonP).
        // bu fonksiyonun adidir. bu isimle bir fonksiyon yarattigimda o butona basinca o fonskiyon calisacaktir.
        // fonskiyonu oncreate'nin disinda hemen altinda yaratiyorum.
        //
        // nedense burada calismadi bu onClick olayi. ama daha sonra baska projede denedim calisti.
        // findViewById ile viewBinding is goruyor simdilik.
        // findViewById kullanacaksan bu arada class basinda her yerden erisilecek sekilde yaratmak
        // daha mantikli. her seferinde findViewById<Button>(R.id.button) yazmana gerek kalmaz, direkt
        // buttonX yazar gecersin.

        ////////////////////////
        // nullability onemini gormek amacli simpson ornegi:
        // bir simpson sinifi yarattim. burada yukarida da edittext icin degiskenleri tanimladim.

        findViewById<Button>(R.id.buttonMS).setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            val age = findViewById<EditText>(R.id.editTextAge).text.toString().toIntOrNull()
            val job = findViewById<EditText>(R.id.editTextJob).text.toString()
            // edittexte toString ekleme sebebimiz: editText.text'in Editable? bir deger donmesi.
            // bu degeri String'e cevirip kullanmak icin toString kullaniyoruz.
            //
            // bu arada viewbindingde edittext.text yapamayiz, edittext.settext kullaniriz.

            //val newSimpson = Simpson(name, age, job)
            // age string olmadigi icin sikinti cikiyordu. toInt ile cozduk mevzuyu.
            // ancak soyle bir sikinti var ki, mesela age yerine int yerine string bir deger
            // girerse yani 30 yerine otuz girse mesela bunu int'e ceviremeyecegi icin hata aliriz.
            // hata almamak icin toInt yerine toIntOrNull kullanacagiz. basarili olursa int? deger doner,
            // basarisiz olursa null doner. basarili oldugunda int? donecegi icin bu yine sikinti olacak.
            // cunku null donerse bu sefer nesneyi yaratmayiz, zaten newSimpson'da hata da veriyor:
            // Type mismatch. Required: Int, Found: Int?
            // bunun icin ogrendigimiz 5 farkli null safe kontrolunden istedigimizi kullanacagiz.

            if (age != null) {
                val newSimpson = Simpson(name, age, job)
                findViewById<TextView>(R.id.textViewNewSimpson).text = "$name, $age yo, $job"
            } else {
                findViewById<TextView>(R.id.textViewNewSimpson).text = "Enter the age!"
            }
            // ve bu sayede age yerine string girilse bile sikintiyi onleyecek bir mekanizma oldu.

            /////////////////////////////
            // inflate terimi, bir kod ile xml'i birbirine baglamak icin kullanilir.


            binding.buttonGoToActivityTwo.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActivityTwo::class.java))
            }

            ////////////////////////////
            // grid layout
            // bunu activitytwo'da yapiyorum. ordan bakarsin.

            ///////////////////////
            // uygulama icin yatay modu aktiflestirmek

            // bunu activitythree'de yapiyorum.
            // yatay modu aktiflestirmek icin uygulamanin manifest dosyasinda ilgili activity'e
            // bir kod ekliyoruz:
            //android:screenOrientation="sensor"
            // bu sayede isteyen o activity acikken telefonu yan cevirdiginde tasarim da cevrilecek.
            //
            // ancak tasarim her zaman mukemmel gozukmez yatay modda. sikinti cikarabilir.
            // bu sebeple yatay moda izin vereceksek istersek ekstra olarak activity xml'inde
            // create landscape quailifier ile yatay moda ozel tasarimi aktiflestirebiliriz.
            // iki farkli xmlimiz olur. bu ikisi tamamen birbirinden farkli xmllerdir. ikisi icin de ayri
            // tasarimlar yapabilirsin. ancak icinde kullanacagin tasarim nesnelerinin birbirinin kopyasi
            // oldugundan ve idlerinin ayni oldugundan emin ol (eger buna ihtiyacin yoksa sikinti yok).

            //////////////////////////
            // shared preferances - storing data
            // uygulama kapanip acildiginda bile kullanicinin daha once girdigi veriye tekrar
            // erisebilmesini ogrenecegiz. bunu ActivityFour'da yapacagim.

            //////////////////////
            // context nedir? alert dialog nedir? uyari mesaji nasil verilir?
            // bununla ilgili calismayi ActivityFive icinde yapacagim.
        }

        fun funForButtonP (view: View) {
            Toast.makeText(this@MainActivity, "Hello World!", Toast.LENGTH_SHORT).show()
        }
    }
}