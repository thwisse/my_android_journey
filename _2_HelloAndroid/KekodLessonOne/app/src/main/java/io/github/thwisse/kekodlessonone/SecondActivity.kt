package io.github.thwisse.kekodlessonone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        Log.e("SecondActivity", "onCreate")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnOpenThirdActivity = findViewById<Button>(R.id.btnOpenThirdActivity)
        btnOpenThirdActivity.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        // lifecycle devami:

        // main activity acilinca calisanlar:

        ///MainActivity            io.github.thwisse.kekodlessonone     E  onCreate
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onResume

        // butona basip second yani bu activity'e gecince calisanlar:

        ///MainActivity            io.github.thwisse.kekodlessonone     E  onPause
        // onceki activity pause edildi ve yeni activity aciliyor:
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onCreate
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onStart
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onResume
        // second activity active olduktan sonra main stop edilecek:
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStop
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onSaveInstanceState
        // burada finish() olmadigi icin destroy edilmedi.
        // eger finish() olsaydi burada onStoptan sonra onDestroy calisirdi ve onSaveInstanceState
        // calismazdi.

        // back tusuna basilinca:

        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onBackPressed
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onPause
        // onceki activity'e donulmeden once second activity pause edildi.
        // onceki activity restart ile aciliyor:
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onRestart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onResume
        // onceki activity'e donuldukten sonra second activity destroy ediliyor.
        // yani yeni activity'nin ui'i ekranda gozuktukten sonra onceki activity arkaplanda vefat ediyor.
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onStop
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onDestroy
        // geri tusuna basmak finish() cagirmak gibi. destroy edecektir.
        // bu islemi kendimiz yaptigimiz icin de onSaveInstanceState'i burada gormuyoruz.

        /////////////////////////////////////////

        // intent ile veri aktarma:

        // intent ile activity/fragment'lar arasi veri aktariminda en performansli yontem
        // parcelable
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.e("SecondActivity", "onContentChanged")
    }

    override fun onStart() {
        super.onStart()
        Log.e("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SecondActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("SecondActivity", "onRestart")
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        //Log.e("SecondActivity", "onUserInteraction")
    }


    // sadece bundle olani:
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("SecondActivity", "onSaveInstanceState")
    }

    // sadece bundle olani:
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("SecondActivity", "onRestoreInstanceState")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("SecondActivity", "onBackPressed")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.e("SecondActivity", "onUserLeaveHint")
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}