package com.example.pomegranate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pomegranate.databinding.ActivityThreeBinding

class ActivityThree : AppCompatActivity() {

    private lateinit var binding: ActivityThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_three)

        binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoToActivityFour?.setOnClickListener {
            startActivity(Intent(this@ActivityThree, ActivityFour::class.java))
        }

    }
}