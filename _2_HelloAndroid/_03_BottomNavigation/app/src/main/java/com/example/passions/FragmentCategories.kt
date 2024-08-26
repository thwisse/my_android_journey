package com.example.passions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.databinding.FragmentCategoriesBinding

class FragmentCategories : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        binding.switch1.setOnCheckedChangeListener { compoundButton, b ->

            if (b) {
                Log.e("ilgi alanlari", "dahil et")
                binding.switch1.text = "Ilgi alanlarimi dahil et"
            } else {
                Log.e("ilgi alanlari", "dahil etme")
                binding.switch1.text = "Ilgi alanlarimi dahil etme"
            }
        }
        binding.toggleButton1.setOnCheckedChangeListener { compoundButton, b ->

            if (b) {
                Log.e("+18", "acik")
            } else {
                Log.e("+18", "kapali")
            }
        }
        //2024-01-03 21:19:53.139  4907-4907  ilgi alanlari   com.example.passions  E  dahil et
        //2024-01-03 21:19:54.706  4907-4907  ilgi alanlari   com.example.passions  E  dahil etme
        //2024-01-03 21:20:02.513  4907-4907  +18             com.example.passions  E  acik
        //2024-01-03 21:20:05.000  4907-4907  +18             com.example.passions  E  kapali

        binding.button1.setOnClickListener {
            val switchDurum = binding.switch1.isChecked
            val toggleDurum = binding.toggleButton1.isChecked
            var switchMsg = "dahil etme"
            var toggleMsg = "kapali"

            if (switchDurum == true) switchMsg = "dahil et" else "dahil etme"
            if (toggleDurum == true) toggleMsg = "acik" else "kapali"

            Log.e("ilgi alanlari (durum)", switchMsg)
            Log.e("+18 (durum)", toggleMsg)
        }
        //2024-01-03 21:38:34.903  5212-5212  ilgi alanlari (durum)  com.example.passions E  dahil etme
        //2024-01-03 21:38:34.904  5212-5212  +18 (durum)            com.example.passions E  kapali
        //2024-01-03 21:38:47.075  5212-5212  ilgi alanlari (durum)  com.example.passions E  dahil et
        //2024-01-03 21:38:47.075  5212-5212  +18 (durum)            com.example.passions E  acik




        return binding.root
        //return inflater.inflate(R.layout.fragment_categories, container, false)
    }
}