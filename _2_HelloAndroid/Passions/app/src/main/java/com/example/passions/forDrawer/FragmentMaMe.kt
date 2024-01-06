package com.example.passions.forDrawer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.ActivityMain
import com.example.passions.R
import com.example.passions.databinding.FragmentMaMeBinding

class FragmentMaMe : Fragment() {

    private lateinit var binding: FragmentMaMeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMaMeBinding.inflate(inflater, container, false)

        binding.buttonGoToActivityMain.setOnClickListener {
            val newIntent = Intent(context, ActivityMain::class.java)

            startActivity(newIntent)
        }


        return binding.root
    }
}