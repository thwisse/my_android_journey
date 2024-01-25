package com.example.pomegranate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pomegranate.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///////////////////////
        // grid layout

        // gridlayout'u tasarima attim. icine birden fazla image view ekledim.
        // bu imageview'larin attributelarindan row ve column degerlerini degistirebiliyorum.
        // zaten istedigim yere de surukleyip birakabiliyorum, ama xmlde elle de yazabiliyorum.
        //app:layout_column="0"
        //app:layout_row="0"

        binding.buttonGoToActivityThree.setOnClickListener {
            startActivity(Intent(this@ActivityTwo, ActivityThree::class.java))
        }
    }
}