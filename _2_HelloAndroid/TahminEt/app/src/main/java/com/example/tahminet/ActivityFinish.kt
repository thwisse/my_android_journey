package com.example.tahminet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tahminet.databinding.ActivityFinishBinding

class ActivityFinish : AppCompatActivity() {

    //private var counterWin = 0

    //private var counterLose = 0

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_finish)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sonuc = intent.getBooleanExtra("result", false)
        val number = intent.getIntExtra("number", 0)

        //counterWin = intent.getIntExtra("counterWin", 0)
        //counterLose = intent.getIntExtra("counterLose", 0)

        if (sonuc) {
            binding.textViewResult.text = "Afferim. Bildin..."
            binding.textViewNumber.text = ""
            binding.imageViewResult.setImageResource(R.drawable.icon_sad)
            //binding.textViewWinCount.text = "$counterWin kez bildin."
            //binding.textViewLoseCount.text = "$counterLose kez bilemedin."
        } else {
            binding.textViewResult.text = "Zaaa Bilemedin!!"
            binding.textViewNumber.text = "Sayi $number idi xd"
            binding.imageViewResult.setImageResource(R.drawable.icon_happy)
            //binding.textViewWinCount.text = "$counterWin kez bildin."
            //binding.textViewLoseCount.text = "$counterLose kez bilemedin."
        }

        binding.buttonPlayAgain.setOnClickListener {

            finish()
            // back stack temizleme islemi

            startActivity(Intent(this, ActivityMain::class.java))
        }




    }
}