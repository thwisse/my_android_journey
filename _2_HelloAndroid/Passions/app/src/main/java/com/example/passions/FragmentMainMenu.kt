package com.example.passions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.databinding.FragmentMainMenuBinding
import com.example.passions.forDrawer.ActivityMain2

class FragmentMainMenu : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        binding.buttonGoToActivityMain2.setOnClickListener {
            val newIntent = Intent(context, ActivityMain2::class.java)

            startActivity(newIntent)
        }


        return binding.root
    }
}