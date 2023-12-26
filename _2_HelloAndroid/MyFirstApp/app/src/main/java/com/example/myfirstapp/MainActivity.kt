package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // R ile res yani resources kismindaki kaynaklarima erisebilirim.
    //R.color.myRed
    // kaynaklara java, kt, xml uzantili dosyalardan erisebilirim.
    //R.string.welcome

    val mesaj = "Hello World!"

    //Log.e("cikti", mesaj)











}