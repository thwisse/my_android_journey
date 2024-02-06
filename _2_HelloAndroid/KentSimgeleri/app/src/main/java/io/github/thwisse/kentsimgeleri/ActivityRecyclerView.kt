package io.github.thwisse.kentsimgeleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.thwisse.kentsimgeleri.databinding.ActivityRecyclerViewBinding

class ActivityRecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private lateinit var listLandmark: ArrayList<Landmark> // listview icin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bu sefer kendi adapter'imizi ve kendi item layout'umuzu olusturacagiz.
        // oncelikle layout_item_rv xmlimizi olusturup linear layout'a donusturduk. ardindan xml
        // kodlarini kullanarak item'i tasarliyoruz. bu islem bitince AdapterLandmark sinifini
        // olusturup adapter ve holder'imizi bunun icinde olusturuyoruz. gereken kod ve notlar orada.

        // adapter tamamlandi.
        // main'de listview icin olusturdugumuz liste ve elemanlari burada da olusturuyorum.

        val pisa = Landmark("Pisa", "Italy", R.drawable.image_pisa)
        val colosseum = Landmark("Colosseum", "Italy", R.drawable.image_colosseum)
        val eiffel = Landmark("Eiffel", "France", R.drawable.image_eiffel)
        val londonBridge = Landmark("London Bridge", "United Kingdom", R.drawable.image_londonbridge)

        listLandmark = ArrayList<Landmark>()

        listLandmark.add(pisa); listLandmark.add(colosseum)
        listLandmark.add(eiffel); listLandmark.add(londonBridge)

        // itemlarin tasarimda nasil listelenecegini belirtecegiz:
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // LinearLayoutManager = alt alta goster
        // GridLayoutManager = yan yana goster

        // adapteri burda olustururken listeyi de adapter'a yollayalim:
        val adapterLandmark = AdapterLandmark(listLandmark)
        // adapter tamamlandi. artik adapter'i rv'ye iletebiliriz.
        binding.recyclerView.adapter = adapterLandmark

    }
}