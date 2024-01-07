package com.example.tahminet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tahminet.databinding.ActivityGuessBinding
import com.example.tahminet.databinding.ActivityMainBinding
import java.util.Random

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {

            startActivity(Intent(this, ActivityGuess::class.java))
        }


    }
}