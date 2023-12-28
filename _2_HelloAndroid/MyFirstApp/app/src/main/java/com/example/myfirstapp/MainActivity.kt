package com.example.myfirstapp

// bu ikisini default olarak import ediyor:
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// klasik baglanti icin bu ikisini import etti:
import android.widget.Button
import android.widget.TextView
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

        binding.buttonA2.setOnClickListener {
            Snackbar.make(it, "Afferim buna da guzel tikladin!", Snackbar.LENGTH_SHORT).show()
            binding.textViewA2.text = "Helaaaaaaal"
        }




    }
}
