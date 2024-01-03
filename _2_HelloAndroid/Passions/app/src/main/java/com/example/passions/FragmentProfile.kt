package com.example.passions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.databinding.ActivityMainBinding
import com.example.passions.databinding.FragmentProfileBinding

class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.buttonSignIn.setOnClickListener {
            val nameSurname = binding.editTextName.text.toString() + " " + binding.editTextSurname.text.toString()
            binding.textViewNameSurname.text = nameSurname
            binding.textViewWelcome.text = "Hosgeldiniz!"

        }





        return binding.root
        //return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}