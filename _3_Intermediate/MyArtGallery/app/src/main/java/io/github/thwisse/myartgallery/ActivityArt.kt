package io.github.thwisse.myartgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.github.thwisse.myartgallery.databinding.ActivityArtBinding

class ActivityArt : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonSave_Clicked(view: View) {

    }

    fun imageViewSelect_Clicked(view: View) {

    }
}