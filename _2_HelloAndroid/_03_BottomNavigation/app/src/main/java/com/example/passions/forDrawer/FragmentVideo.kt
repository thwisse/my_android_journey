package com.example.passions.forDrawer

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.R
import com.example.passions.databinding.FragmentVideoBinding

class FragmentVideo : Fragment() {

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)

        // videoyu baslat
        binding.buttonStart2.setOnClickListener {
            val uri = Uri.parse("android.resource://" + "com.example.passions" + "/" + R.raw.cmylmz_depp)

            binding.videoView.setVideoURI(uri)
            binding.videoView.start()
        }

        // videoyu duraklat
        binding.buttonStop2.setOnClickListener {
            binding.videoView.stopPlayback()
        }

        // videoyu kaldigi yerden devam ettir?
        binding.buttonResume.setOnClickListener {
            binding.videoView.resume()
        }
        //TODO bu calismiyor. neden anlamadim. belki de boyle degildir bilmiyorum. sonra bak.

        return binding.root
    }
}