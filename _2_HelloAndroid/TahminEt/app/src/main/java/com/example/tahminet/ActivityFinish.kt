package com.example.tahminet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tahminet.databinding.ActivityFinishBinding

class ActivityFinish : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_finish)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sonuc = intent.getBooleanExtra("result", false)
        val number = intent.getIntExtra("number", 0)

        if (sonuc) {
            binding.textViewResult.text = "Afferim. Bildin..."
            binding.textViewNumber.text = ""
            binding.imageViewResult.setImageResource(R.drawable.icon_sad)
            binding.textViewWinCount.text = "${SharedVariable.counterWin} kez bildin."
            binding.textViewLoseCount.text = "${SharedVariable.counterLose} kez bilemedin."
        } else {
            binding.textViewResult.text = "Bilemedin!!"
            binding.textViewNumber.text = "Sayı $number idi xd"
            binding.imageViewResult.setImageResource(R.drawable.icon_happy)
            binding.textViewWinCount.text = "${SharedVariable.counterWin} kez bildin."
            binding.textViewLoseCount.text = "${SharedVariable.counterLose} kez bilemedin."
        }

        if (SharedVariable.counterWin > 2) {
            binding.buttonWin.visibility = View.VISIBLE
        }

        if (SharedVariable.counterLose > 2) {
            binding.buttonLose.visibility = View.VISIBLE
        }

        binding.buttonWin.setOnClickListener {
            //finish()

            val intent = Intent(this, ActivityWinLose::class.java)

            SharedVariable.winLoseResult = true

            startActivity(intent)
        }

        binding.buttonLose.setOnClickListener {
            //finish()

            val intent = Intent(this, ActivityWinLose::class.java)

            SharedVariable.winLoseResult = false

            startActivity(intent)
        }

        binding.buttonPlayAgain.setOnClickListener {
            finish()
            // back stack temizleme islemi

            startActivity(Intent(this, ActivityMain::class.java))
        }
    }
}