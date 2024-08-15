package io.github.thwisse.launchmodes.standart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivity : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)


        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}