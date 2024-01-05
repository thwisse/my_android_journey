package com.example.passions.forDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.SeekBar
import com.example.passions.R
import com.example.passions.databinding.FragmentCateBinding

class FragmentCate : Fragment() {

    private lateinit var binding: FragmentCateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCateBinding.inflate(inflater, container, false)

        // ProgressBar

        binding.buttonStart.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.buttonStop.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        // SeekBar (slider)

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            // slider'in anlik olarak degerini verir.
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewSeekBarValue.text = "Seciminiz: $progress"
                // sadece progress'i yazdiracak olsam toString yapmam gerekirdi.
                // ancak string ifadenin icine ekledigim icin bunu yapmama gerek kalmadi.
            }
            // slider'a dokunduktan sonra yapilacak islemler
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            // istersen kullanirsin
            }
            // slider'i biraktiktan sonra yapilacak islemler
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            // istersen kullanirsin
            }
        })
        //val sliderDeger = binding.seekBar.progress
        // dilersek boyle kodun baska bir yerinde de seekBar'in anlik degerini kullanabiliriz.

        // ratingBar

        // su alttakini kullanacaktim ama lambda ile yapilmis daha guncel hali varmis sanirim.
        // onu kullandim.
        /*
        binding.ratingBar.setOnRatingBarChangeListener(object: RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                binding.textViewRatingBarValue.text = "${rating}/5"
            }
        })
        */
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.textViewRatingBarValue.text = "${rating}/5"
        }
        //val ratingDeger = binding.ratingBar.rating
        // dilersek boyle kodun baska bir yerinde de ratingBar'in anlik degerini kullanabiliriz.

        return binding.root
    }
}
