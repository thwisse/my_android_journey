package com.example.catchthemahur

import android.content.Context
import android.content.SharedPreferences
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet
import com.example.catchthemahur.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var runnable: Runnable = Runnable {}
    var handler: Handler = Handler(Looper.getMainLooper())
    var countdown = 15
    var score = 0
    var horBias: Float = 0.289f
    var verBias: Float = 0.421f
    var runnable2: Runnable = Runnable {}
    var handler2: Handler = Handler(Looper.getMainLooper())
    lateinit var sharedPref: SharedPreferences
    var bestScore: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alert1 = AlertDialog.Builder(this@ActivityMain)
        alert1.setTitle("Mahuru yakala!")
        alert1.setMessage("Mahuru 15 saniye icinde kac kere yakalayabileceksin?")
        alert1.setPositiveButton("Basla!") { dialog, witch ->
            startCountdown()
        }
        alert1.setCancelable(false)
        alert1.show()

        val soundPool = SoundPool.Builder()
            .setMaxStreams(10) // Aynı anda çalabilecek maksimum ses sayısı
            .build()
        val soundId = soundPool.load(this, R.raw.sound_mahur_meow, 1)

        binding.imageViewMahur.setOnClickListener {
            score += 1

            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        sharedPref = getSharedPreferences("com.example.catchthemahur", Context.MODE_PRIVATE)
        bestScore = sharedPref.getInt("bestScore", -1)
        if (bestScore == -1) {
            binding.textViewBestScore.text = "En iyi skor: 0"
        } else {
            binding.textViewBestScore.text = "En iyi skor: ${bestScore}"
        }

    }

    fun biasValue(): Float {
        return Math.random().toFloat()
    }

    fun runAwayMahur() {
        val layout = binding.constraintLayout
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        runnable2 = object: Runnable {
            override fun run() {
                horBias = biasValue()
                verBias = biasValue()

                constraintSet.setHorizontalBias(R.id.imageViewMahur, horBias)
                constraintSet.setVerticalBias(R.id.imageViewMahur, verBias)
                constraintSet.applyTo(layout)

                handler2.postDelayed(runnable2, 400)
            }
        }
        handler2.post(runnable2)
    }

    fun playAgain() {
        val alert2 = AlertDialog.Builder(this@ActivityMain)
        alert2.setTitle("Sure bitti!")
        alert2.setMessage("Skor: ${score}")
        alert2.setPositiveButton("Tekrar oyna") { dialog, witch ->
            startCountdown()
        }
        alert2.setCancelable(false)
        alert2.show()

        if (score > bestScore!!) {
            bestScore = score
            binding.textViewBestScore.text = "En iyi skor: ${bestScore}"
            sharedPref.edit().putInt("bestScore", bestScore!!).apply()
        } else {
            binding.textViewBestScore.text = "En iyi skor: ${bestScore}"
        }

        val layout = binding.constraintLayout
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)
        constraintSet.setHorizontalBias(R.id.imageViewMahur, 0.5f)
        constraintSet.setVerticalBias(R.id.imageViewMahur, 0.15f)
        constraintSet.applyTo(layout)
    }

    fun startCountdown() {
        runAwayMahur()

        countdown = 15
        score = 0

        runnable = object: Runnable {
            override fun run() {

                if (countdown > 0) {
                    countdown -= 1
                    binding.textViewCountdown.text = "${countdown}"
                    binding.textViewScore.text = "Skor: ${score}"
                    handler.postDelayed(runnable, 1000)
                } else {
                    stopCountDown()
                    playAgain()
                }
            }
        }
        handler.post(runnable)
    }

    fun stopCountDown() {
        binding.textViewCountdown.text = "15"
        binding.textViewScore.text = "Skor: 0"
        handler.removeCallbacks(runnable)
        handler2.removeCallbacks(runnable2)
    }
}