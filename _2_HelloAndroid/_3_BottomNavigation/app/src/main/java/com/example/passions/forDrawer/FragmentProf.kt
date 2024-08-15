package com.example.passions.forDrawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.passions.R
import com.example.passions.databinding.FragmentProfBinding

class FragmentProf : Fragment() {

    private lateinit var binding: FragmentProfBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentProfBinding.inflate(inflater, container, false)

        binding.buttonIlet.setOnClickListener {
            val kampDurum = binding.checkBox1.isChecked
            val oyunDurum = binding.checkBox2.isChecked
            val resimDurum = binding.checkBox3.isChecked

            val cinsiyet = binding.radioButtonMale.isChecked

            if (kampDurum == true) Log.e("kampYapmak", "seviyor") else Log.e("kampYapmak", "sevmiyor")
            if (oyunDurum == true) Log.e("oyunOynamak", "seviyor") else Log.e("oyunOynamak", "sevmiyor")
            if (resimDurum == true) Log.e("resimYapmak", "seviyor") else Log.e("resimYapmak", "sevmiyor")

            if (cinsiyet == true) Log.e("cinsiyet", "erkek") else Log.e("cinsiyet", "kadin")
        }
        //2024-01-04 17:09:52.873  6543-6543  kampYapmak   com.example.passions  E  sevmiyor
        //2024-01-04 17:09:52.873  6543-6543  oyunOynamak  com.example.passions  E  sevmiyor
        //2024-01-04 17:09:52.874  6543-6543  resimYapmak  com.example.passions  E  seviyor
        //2024-01-04 17:09:52.874  6543-6543  cinsiyet     com.example.passions  E  erkek

        // compoundButton, radiobutton'i temsil ediyor, x ise true/false degerini temsil ediyor.
        binding.radioButtonMale.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "erkek", Toast.LENGTH_SHORT).show()
            }
        }
        binding.radioButtonFemale.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "kadin", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }
}