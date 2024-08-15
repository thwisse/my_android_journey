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
            // sayfalar arasi veri aktarimi icin (nesne degil):
            //val gecis = FragmentMainDirections.actionGoToMenu("Oguzhan", 23)
            // farkli bir gecis yapisi olusturduk. o sebeple bunu command line'a aldim.
            // ancak argumanlar B de hala istenmeye devam ettigi icin altta yeni olusturdugum
            // geciste o argumanlari da koydum.

            // sayfalar arasi veri aktarimi icin (nesne):
            val person = Categories("Deniz Alp", 1)
            val gecis = FragmentMainDirections.actionGoToMenu(person,"Oguzhan", 23)
            // nesneyi de diger argumanlarla beraber parantez icine ekledim.
            // daha sonra baktiginda karistirma diye yazayim, person ile yanindaki diger degerler
            // birbirinden farkli amacla girilmistir.

            // bununla hem gecis saglanacak hem veri aktarilacak.
            Navigation.findNavController(it).navigate(gecis)

            // veri aktarimi yapmadan once bu sekilde gecisi sagliyorduk:
            // navigation componentte olusturdugumuz actioni butona ekleyelim.
            //Navigation.findNavController(it).navigate(R.id.action_goToMenu)
            // it keywordu butonu temsil ediyor.
        }

        return binding.root
        //return design
    }
}