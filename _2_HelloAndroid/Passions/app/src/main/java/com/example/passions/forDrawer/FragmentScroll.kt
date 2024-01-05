package com.example.passions.forDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.R
import com.example.passions.databinding.FragmentScrollBinding

class FragmentScroll : Fragment() {

    private lateinit var binding: FragmentScrollBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentScrollBinding.inflate(inflater, container, false)

        // kod gerekmedi.

        return binding.root
    }
}