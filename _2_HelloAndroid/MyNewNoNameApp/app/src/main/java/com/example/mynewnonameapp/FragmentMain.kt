package com.example.mynewnonameapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mynewnonameapp.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // binding yapmadan once baglama ve return bu sekildeydi:
        //val design = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.buttonGoToMenu.setOnClickListener {
            // navigation componentte olusturdugumuz actioni butona ekleyelim.
            Navigation.findNavController(it).navigate(R.id.action_goToMenu)
            // it keywordu butonu temsil ediyor.
        }


        return binding.root
        //return design
    }
}