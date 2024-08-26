package com.example.tahminet

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tahminet.databinding.ActivityGuessBinding
import kotlin.random.Random

// kac kere bildigini ve bilemedigini tum activitylerde erisilebilecek iki degiskenle tutuyorum.

class ActivityGuess : AppCompatActivity() {

    private lateinit var binding: ActivityGuessBinding

    fun RandomNumber(): Int {
        val randomNumber = Random.nextInt(1,21)

        return randomNumber
    }

    private var number = 0
    private var counter = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_guess)

        binding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewCounter.text = "Tuttum! \n3 hakkın var. Başla!"
        //TODO tasarim kisminda alt satira gecmeyi bilmedigim icin stringi burda atadim
        // strings kisminda stringi olusturup yapabilirim ama tasarimda satir atlama var mi
        // ilerde lazim olur mu bilmiyorum ama ogren

        number = RandomNumber()
        Log.e("random number:", number.toString())

        binding.buttonGuessForward.setOnClickListener {

            counter -= 1

            val inputGuess = binding.editTextInputGuess.text.toString().toInt()

            //TODO girdinin bosluk,sayi olmama, ve 1-20 arasinda olmama durumunu da kontrol et

            if (inputGuess == number) {
                finish()
                val intent = Intent(this, ActivityFinish::class.java)

                intent.putExtra("result", true)

                SharedVariable.counterWin += 1

                startActivity(intent)

                return@setOnClickListener
                // son tahminde bilirse counterdan dolayi kaybettin yazmasin diye burada bitirttik.
            }
            if (inputGuess > number) {
                binding.textViewInfo.text = "Daha küçük!"
                binding.textViewCounter.text = "Kalan hakkın: $counter"
            }
            if (inputGuess < number) {
                binding.textViewInfo.text = "Daha büyük!"
                binding.textViewCounter.text = "Kalan hakkın: $counter"
            }
            if (counter == 0) {
                finish()

                val intent = Intent(this, ActivityFinish::class.java)

                intent.putExtra("result", false)
                intent.putExtra("number", number)

                SharedVariable.counterLose += 1

                startActivity(intent)
            }

            binding.editTextInputGuess.setText("")

            //TODO girdiyi girdikten sonra bosluga basinca klavyenin kapanmasini sagla.
            // eksta olarak klavyede okay anlamindaki tusa basinca butonu calistirabilir.
        }
    }
}