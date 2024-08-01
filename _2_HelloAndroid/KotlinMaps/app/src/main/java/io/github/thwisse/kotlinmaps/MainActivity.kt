package io.github.thwisse.kotlinmaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bir mapsActivity yaratildi, launcher activity olarak.
        // google maps ve cloud sitelerinden gereken islemler yapildi ve map icin gereken
        // api kopyalanip projeye eklendi. proje calistirilinca su an map gozukuyor.

        // mapsActivity'e geciyorum.

    }
}