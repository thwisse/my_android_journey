package com.example.mynewnonameapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mynewnonameapp.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val design = inflater.inflate(R.layout.fragment_menu, container, false)
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        // veri aktariminda gelecek olan veriyi karsilayalim (nesne degil):
        val bundle: FragmentMenuArgs by navArgs()

        val gelenAd = bundle.name
        val gelenYas = bundle.age

        // gelen verileri konsolda yazdiralim:
        Log.e("gelenAd:", gelenAd)
        Log.e("gelenYas:", gelenYas.toString())
        //2023-12-31 18:14:19.831  7243-7243  gelenAd:    com.example.mynewnonameapp  E  Oguzhan
        //2023-12-31 18:14:19.832  7243-7243  gelenYas:   com.example.mynewnonameapp  E  23
        // butona basildiginda logcatte veriler bu sekilde gozukmektedir.

        // veri aktariminda gelecek olan veriyi karsilayalim (nesne):
        val gelenPerson = bundle.person

        Log.e("personName:", gelenPerson.name)
        Log.e("personAge:", gelenPerson.age.toString())
        //2023-12-31 18:45:26.695  7488-7488  personName: com.example.mynewnonameapp  E  Deniz Alp
        //2023-12-31 18:45:26.695  7488-7488  personAge:  com.example.mynewnonameapp  E  1


        binding.buttonGoToCategories.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_goToCategories)
        }


        return binding.root
    }
}