package com.example.tablayouteg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

// fragment sinifini sinifa kalitim yoluyla ekledim.
class FragmentFirst: Fragment() {
    // onCreateView metodunu ekledim.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {





        return inflater.inflate(R.layout.layout_frag_first, container, false)
        // bu fragment icin olusturdugumuz layoutu yani gorsel nesneyi aktardik, bununla birlikte container
        // yazarak yazilimsal nesneyi de aktardik. false ile de zaten baska tasarimi eklemeyecegimizi belirttik.
        // simdi bu islemin aynisini diger olusturdugum fragmentlara da yapacagim.
    }
}