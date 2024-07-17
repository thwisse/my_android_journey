package com.example.tahminet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tahminet.databinding.ActivityWinLoseBinding

class ActivityWinLose : AppCompatActivity() {

    private lateinit var binding: ActivityWinLoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_win_lose)

        binding = ActivityWinLoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri1 = Uri.parse("android.resource://" + "com.example.tahminet" + "/" + R.raw.kedi)
        val uri2 = Uri.parse("android.resource://" + "com.example.tahminet" + "/" + R.raw.kopek)

        if (SharedVariable.winLoseResult) {

            binding.textViewWinLose.text = "Dilber evin barkın yok mu? eşliğinde kıvırtan konsomatris kedi.\nBunu hak ettin..."
            binding.videoViewWinLose.setVideoURI(uri1)
            binding.videoViewWinLose.start()

        } else {

            binding.textViewWinLose.text = "Klarnet eşliğinde uluyan efkarlı sokak köpeği.\nÜzgünüm, sana bunu yapmak istemezdim..."
            binding.videoViewWinLose.setVideoURI(uri2)
            binding.videoViewWinLose.start()
        }

        binding.buttonPlayAgain2.setOnClickListener {
            finish()

            startActivity(Intent(this, ActivityMain::class.java))
        }

    }
}