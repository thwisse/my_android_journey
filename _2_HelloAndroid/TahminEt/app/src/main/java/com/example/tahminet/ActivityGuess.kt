package com.example.tahminet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tahminet.databinding.ActivityGuessBinding
import kotlin.random.Random

class ActivityGuess : AppCompatActivity() {

    private lateinit var binding: ActivityGuessBinding

    fun RandomNumber(): Int {
        val randomNumber = Random.nextInt(1,21)

        return randomNumber
    }

    private var number = 0

    private var counter = 3

    //private var counterWin = 0

    //private var counterLose = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_guess)

        binding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

                //counterWin += 1
                //intent.putExtra("counterWin", counterWin)

                startActivity(intent)

                return@setOnClickListener
                // son tahminde bilirse counterdan dolayi kaybettin yazmasin diye burada bitirttik.
            }
            if (inputGuess > number) {
                binding.textViewInfo.text = "Daha kucuk!"
                binding.textViewCounter.text = "Kalan hakkin: $counter"
            }
            if (inputGuess < number) {
                binding.textViewInfo.text = "Daha buyuk!"
                binding.textViewCounter.text = "Kalan hakkin: $counter"
            }
            if (counter == 0) {
                finish()

                val intent = Intent(this, ActivityFinish::class.java)

                intent.putExtra("result", false)
                intent.putExtra("number", number)

                //counterLose += 1
                //intent.putExtra("counterLose", counterLose)

                startActivity(intent)
            }

            binding.editTextInputGuess.setText("")

            //TODO girdiyi girdikten sonra bosluga basinca klavyenin kapanmasini sagla.
            // eksta olarak klavyede okay anlamindaki tusa basinca butonu calistirabilir.
        }
    }
}