package com.example.myfirstapp

// bu ikisini default olarak import ediyor:
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// log kaydi icin bunu import etti:
import android.util.Log
// klasik baglanti icin bu ikisini import etti:
// viewBinding icin bunu import etti:
import com.example.myfirstapp.databinding.ActivityMainBinding
// Snackbar icin bunu import etti:
import com.google.android.material.snackbar.Snackbar

// klasik baglanti icin yazdim:
//private lateinit var myTextView_a_1: TextView
//private lateinit var myButton_a_1: Button

class MainActivity : AppCompatActivity() {

    // gradle module'une buildFeatures ile viewBinding ekleyince ActivityMainBinding
    // ismi otomatik olustu. buraya da gelip bu kismi yazdik:
    // viewBinding icin ekledik:
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // logcat'te kendimize cikti verebilmek icin bu kullanimi uygulayabiliriz
        //Log.e("cikti", "Hello World!")

        //////////////////////////////
        // tasarimsal nesneleri id ile baglamak (klasik baglanti)
        /* viewBinding kullanacaksan bu iptal olmali. bunu kullanacaksan viewBinding iptal olmali.
        setContentView(R.layout.activity_main)

        myTextView_a_1 = findViewById(R.id.textView_a_1_)
        myButton_a_1 = findViewById(R.id.button_a_1_)

        myButton_a_1.setOnClickListener {
            Snackbar.make(it, "Afferim guzel tikladin!", Snackbar.LENGTH_SHORT).show()
            myTextView_a_1.text = "Helaal"
        }
        */
        ////////////////////////////
        // tasarimsal nesneleri id ile baglamak (view binding)
        // view binding = goruntu baglama

        // viewBinding icin ekledik:
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        // bu sekildeydi.
        // viewBinding icin degistirdik:
        setContentView(binding.root)

        binding.buttonA2Tikla2.setOnClickListener {
            Snackbar.make(it, "Afferim buna da guzel tikladin!", Snackbar.LENGTH_SHORT).show()
            // it = binding.buttonA2 anlamina gelir. it yerine onu yazsan yine calisir.
            binding.textViewA2Yazi2.text = "Helaaaaaaal"
        }

        /////////////////////
        // yasam dongusu
        // onCreate, onStart, onResume, onPause, onStop, onDestroy, onRestart
        // isimli AppCombatActivity interface fonksiyonlarini kullanarak uygulamanin
        // aygittaki (alttaki dokunmatik) tuslarla ya da farkli durumlarla yasam dongusunu
        // kontrol edebiliriz. MainActivity class'i icine tum bu fonklari giriyorum.

        Log.e("onCreate", "Calisti")

        //////////////////////////
        // activity'ler (sayfalar) arasi gecis yapmak
        // sayfalardaki butonlarla activityler arasi gecis yapacagiz.
        // viewbinding kullanarak yapalim.

        // A
        //binding ile ilgili kodlari yukarida zaten yazmistik. artik sadece butonlara erismek kaldi.
        binding.buttonB1GoToLoginActivity.setOnClickListener {
            val newIntent = Intent(this@MainActivity, LoginPage::class.java)
            // yeni bir intent olusturduk ve gecis yapilacak olan activity'i belirttik
            startActivity(newIntent)
            // butona bastigim anda bu fonksiyon calisacak ve gecis yapacak.
        }
        // x activitysinden y activitysine intent ayarladiysak y'deyken geri tusuna bastigimizda
        // haliyle default olarak geri x'e donecektir.
        // ancak bunu degistirebiliriz. onBackPressed fonksiyonunu kullanacagiz.
        // sirasiyla 3. aktivityde (ActivityMenu) bunu yapacagim. ordan bakarsin.

        ///////////////////////
        // geri tusuna basinca geri gitmek yerine uygulamadan cikmasini saglamak
        // bu islemi LoginPage isimli activityde yapacagim. ordan bakarsin.

        ///////////////
        // activiyler arasi veri transferi
        // bunu LoginPage icinde yaptim. oradan bakarsin.
        // bununla birlikte nesneleri activityler arasi aktarmayi da yaptim. o da orda var.
        // Kisiler sinifini bunun icin olusturdum.

        //////////////////////
        // backstack kontrolu
        // sayfalar arasi geri tusuyla gecis yaparken istedigimiz sirayla olmasini istedigimizde
        // bu kontrolu saglamak icin belli kodlar yazmamiz gerekiyor.
        // ActivityProfile'da bunu yapacagim. ordan bakarsin
        //TODO bunu tam anlamadim. daha sonra bakacagim

        ///////////////////////////
        // finish() metodu ile bir onceki activity'i backstackten kaldirmak
        // bazi projelerde boyle bir seye ihtiyacin olabilir. o zaman kullanirsin
        // A B C D projelerinde sirayla D ye kadar geldikten sonra D den direkt B ye atlayacagiz
        // bunun icin kodu C de yazacagiz. C yi backstackten silip B ye atlayacagiz.

        ///////////////////
        // context kullanimi
        // android'in ozelliklerini kullanmak istedigimizde android bize bir sart kosuyor.
        // bulundugu activity ile ilgili bir ozelligi context olarak belirtmen gerekiyor diyor
        // bir toast message gosterecegiz ve bunu ornekleyecegiz.
        // bunu ActivityProfile'da yapiyorum. ordan bakarsin
    }
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "Calisti")
    }
    override fun onResume() {
        super.onResume()
        Log.e("onResume", "Calisti")
    }
    override fun onPause() {
        super.onPause()
        Log.e("onPause", "Calisti")
    }
    override fun onStop() {
        super.onStop()
        Log.e("onStop", "Calisti")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Calisti")
    }
    override fun onRestart() {
        super.onRestart()
        Log.e("onRestart", "Calisti")
    }

}
