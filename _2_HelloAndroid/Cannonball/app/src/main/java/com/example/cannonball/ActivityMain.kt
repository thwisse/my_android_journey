package com.example.cannonball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.example.cannonball.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /////////////////////////
        // default toast message ile tasarlanmis toast message gosterimi
        // ozel toast message sacma sapan bisey. guzel de gozukmuyor. belirledigim gibi de gozukmuyor.
        // zaten deprecated olmus. gerek olur mu bilemedim. her surumde calismayabilirmis.

        // siradan bir toast message gostermek:

        binding.buttonToast1.setOnClickListener {

            Toast.makeText(this, "Merhaba", Toast.LENGTH_SHORT).show()
        }

        // ozel toast message olusturmak ve gostermek:

        // layoutta toast_2 adinda yeni bir tasarim xmli olusturdum. yuksekligini 50dp ayarladim.
        // xml koduna android:background="@color/gray" degerini ekledim rengini degistirdim.
        // bir resim ekledim. tasarim islemlerini yaptim. simdi bu tasarimi main icine alip daha sonra
        // butona basinca sergilenecek sekilde ayarlayacagim.

        binding.buttonToast2.setOnClickListener {
            // toast tasarimi activity'e alalim
            val toastDesign = layoutInflater.inflate(R.layout.toast_2, null)
            // root null olacak cunku bu tasarim bagimsizdir ve sadece burada bu amacla kullanilacak.

            // mesaji girelim
            val message = toastDesign.findViewById(R.id.textViewToast2Message) as TextView
            // bu as TextView islemi castingdir, anlama gelir: bunu TextView sinifina donustur.
            message.text = "Horse washing in and out!"

            // deprecated olmus. toast mesaji bir design ile gostermek artik desteklenmiyor.
            val toastMessage = Toast(this)
            toastMessage.view = toastDesign

            // design'i konumlandiralim
            toastMessage.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                0, 0)
            // yatay ve dikeyde ortaladim.
            //TODO neden or dedik anlamadim. ikisinin de olmasi icin and dememiz gerekmez miydi?

            // ekranda durma suresini ayarlayalim.
            toastMessage.duration = Toast.LENGTH_LONG

            toastMessage.show()
        }

        binding.buttongoToActivityTwo.setOnClickListener {
            startActivity(Intent(this, ActivityTwo::class.java))
        }
    }
}