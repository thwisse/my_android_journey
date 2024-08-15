package io.github.thwisse.launchmodes.singletask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.thwisse.launchmodes.R

class SecondActivitySingleTask : AppCompatActivity() {

    private lateinit var btnOpenMainActivity: Button
    private lateinit var btnOpenSecondActivityAgain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_single_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenMainActivity = findViewById<Button>(R.id.btnOpenMainActivity)
        btnOpenSecondActivityAgain = findViewById<Button>(R.id.btnOpenSecondActivityAgain)

        btnOpenMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivitySingleTask::class.java))
        }

        btnOpenSecondActivityAgain.setOnClickListener {
            startActivity(Intent(this, SecondActivitySingleTask::class.java))
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "New Intent", Toast.LENGTH_SHORT).show()
    }
}