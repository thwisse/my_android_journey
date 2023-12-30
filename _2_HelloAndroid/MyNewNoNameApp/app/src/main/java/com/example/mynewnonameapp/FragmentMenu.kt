package com.example.mynewnonameapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mynewnonameapp.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val design = inflater.inflate(R.layout.fragment_menu, container, false)
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.buttonGoToCategories.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_goToCategories)
        }


        return binding.root
    }
}