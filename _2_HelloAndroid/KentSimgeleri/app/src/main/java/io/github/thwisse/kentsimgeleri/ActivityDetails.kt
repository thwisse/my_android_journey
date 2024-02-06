package io.github.thwisse.kentsimgeleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.thwisse.kentsimgeleri.databinding.ActivityDetailsBinding

class ActivityDetails : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val selectedLandmak = intent.getSerializableExtra("landmark") as Landmark
        // intent ile getSerializableExtra kullanarak diger activityden veriyi aldik.
        // daha sonra casting islemi yaparak (as) verileri tekrardan Landmark sinifi elemanlarina donusturduk.
        // cunku bunu yapmayinca nesnelere ve propertylerine erisip islem yapamiyoruz.
        // artik selectedLandmark bir Landmark objesi oldu.

        binding.textViewLandmarkName.text = selectedLandmak.name
        binding.textViewCountry.text = selectedLandmak.country
        binding.imageView.setImageResource(selectedLandmak.image)

        // listView artik sorunsuz calisiyor.

    }
}