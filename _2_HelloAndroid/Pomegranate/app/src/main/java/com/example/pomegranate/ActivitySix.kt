package com.example.pomegranate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import com.example.pomegranate.databinding.ActivitySixBinding

class ActivitySix : AppCompatActivity() {

    private lateinit var binding: ActivitySixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // countdowntimer (geri sayim)
        // abstract bir sinif oldugu icin, bunu uygulayabilmek icin "object" kullanacagiz.
        // soyut bir sinifi ya da arayuzu vs. uygulayabilmek icin object kullaniriz.

        // ilk arguman = kactan geriye sayacagim, ikinci arguman = kacar kacar sayacagim
        // milisaniye cinsinden yaziyoruz. (10 sn, 1 sn):
        object : CountDownTimer(10000, 1000) {
            // her bir tick'te yani her bir saniyede ne yapayim:
            // eger 1000 yerine 2000 yazsak her iki saniyede bir ne yapayim anlamina gelecekti.
            override fun onTick(millisUntilFinished: Long) {
                // millisUntilFinished parametresi kac mil sn kaldigini bilgisini verir.
                binding.textViewLeft.text = "Left: ${millisUntilFinished / 1000}"
                // veri milisn cinsinden gelecegi icin 1000 e bolerek saniye olarak yazdirdik
            }
            // bittiginde ne yapayim:
            override fun onFinish() {
                binding.textViewLeft.text = "Done!"
            }
        }.start()
        //.start() ile de son olarak sayaci calistiriyoruz.

        //////////////////////////
        // simdi bunun cok daha gelismisini bir diger activityde yapacagiz. kronometre yapacagiz.
        // bu sefer gerisayim olmayacak.

        binding.buttongoToActivitySeven.setOnClickListener {
            startActivity(Intent(this@ActivitySix, ActivitySeven::class.java))
        }
    }
}