package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // logcat'te kendimize cikti verebilmek icin bu kullanimi uygulayabiliriz
        Log.e("cikti", "Hello World!")
    }
}