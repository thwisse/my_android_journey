package com.example.passions.forDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passions.R
import com.example.passions.databinding.FragmentPicturesBinding

class FragmentPictures : Fragment() {

    private lateinit var binding: FragmentPicturesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPicturesBinding.inflate(inflater, container, false)

        // imageView

        binding.buttonDefaultPicture.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.picture1)
            // sabit bir sekilde bir resme eristik.
        }

        binding.buttonOtherPicture.setOnClickListener {
            // simdi bir resme dinamik bir sekilde eriselim. yani ornegin database'den string olarak
            // verilen bir resmi gostermek istersek asagidaki gibi yazmamiz gerekir:
            binding.imageView.setImageResource(resources.getIdentifier(
                "picture2", "drawable", "com.example.passions"))
            // string sekilde resmin adini, hangi directoryde ve hangi pakette oldugunu verdik.
            // resme bu sekilde eristik ve gosterdik.
        }

        // ekledigim 2. imageView'de bir xml gorsel var. bazen xml uzantili gorsellerde hata verebiliyor
        // kod calisiyor ama yine de kirmizi hata gozukuyor. bunu duzeltmenin yolu, gradle dosyasinda
        //vectorDrawables.useSupportLibrary = true
        // ozelligini true yapmak. bunda hata almadim ama ilerde olursa diye yazayim dedim.


        return binding.root
    }
}