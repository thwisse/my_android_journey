package com.example.passions.forDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.passions.R
import com.example.passions.databinding.FragmentSpinnerBinding

class FragmentSpinner : Fragment() {

    private lateinit var binding: FragmentSpinnerBinding

    private val countries = ArrayList<String>()

    private lateinit var myDataAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSpinnerBinding.inflate(inflater, container, false)

        // yukarida countries adinda veri kumemi saklayacak arraylist'i olusturdum
        // ardindan veri kumemi isleyecek olan myDataAdapter adindaki veri adaptorumu olusturdum

        countries.add("Turkiye")
        countries.add("Germany")
        countries.add("French")
        countries.add("USA")
        countries.add("United Kingdom")
        countries.add("Portugal")

        myDataAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, android.R.id.text1, countries)
        // hazir bir liste tasarimini adaptor icin kullandik.
        // olusturdugum veri kumesini belirttigim tasarima ekle dedim
        //TODO android.R.id.text1 kismini tam anlamadim ama sanirim prosedur geregi yapilan bir sey

        // olusturdugum adaptoru spinner adaptorune atadim.
        binding.spinner.adapter = myDataAdapter

        // secilen item'i toast msg ile ekranda gosterelim.
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            // item secildiginde calisan fonskiyon
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // buradaki position isimli parametre list'deki verilerin indeksidir.

                Toast.makeText(context, "Secilen ulke: ${countries[position]}", Toast.LENGTH_SHORT).show()
            }

            // hicbir sey secilmediginde calisan fonksiyon
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Hicbir ulke secilmedi!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {
            Toast.makeText(context, "Kesinlestirildi. Ulke: " +
                    countries[binding.spinner.selectedItemPosition], Toast.LENGTH_SHORT).show()
            // bu ozellikle de spinnerda secilmis olan itemi kodda baska yerlerde kullanabiliriz.
        }


        return binding.root
    }
}