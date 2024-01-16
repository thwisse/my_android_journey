package com.example.materialdesign3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.materialdesign3.databinding.ActivityThreeBinding

class ActivityThree : AppCompatActivity() {

    private lateinit var binding: ActivityThreeBinding

    private lateinit var adapter: AdapterMovies

    private lateinit var arrayListMovies: ArrayList<Movies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // yeni ornek.
        //
        // resimler hocanin projeden alinip drawable'a eklendi. activitye recyclerview (rv) eklendi.
        //
        // card xmli lineer (ll) olarak yaratildi. cardview (cv) eklendi. cv kodda kullanilmayacagi icin id verilmedi.
        // cd icine const layout (cl) eklendi. cl icine gereken gorsel nesneler eklendi. ll nin height degeri
        // wrap content yapilarak sikistirildi.
        //
        // movies data classi yaratildi.
        //
        // adapter yaratildi. ilgili notlar orada olacak.
        //
        // artik burada adapter ve arraylist tanimini yapalim.

        binding.recyclerViewMovies.setHasFixedSize(true)
        binding.recyclerViewMovies.layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)

        val m1 = Movies(1, "Interstellar", "image_interstellar", 8)
        val m2 = Movies(2, "Bir Z. Anadoluda", "image_anadoluda", 5)
        val m3 = Movies(3, "Django", "image_django", 6)
        val m4 = Movies(4, "Inception", "image_inception", 3)
        val m5 = Movies(5, "The Hateful Eight", "image_thehatefuleight", 8)
        val m6 = Movies(6, "The Pianist", "image_thepianist", 7)

        arrayListMovies = ArrayList<Movies>()

        arrayListMovies.add(m1); arrayListMovies.add(m2); arrayListMovies.add(m3);
        arrayListMovies.add(m4); arrayListMovies.add(m5); arrayListMovies.add(m6);

        adapter = AdapterMovies(this@ActivityThree, arrayListMovies)
        // context ve veri kumesini adapter'a yolladik. onu da tepede yarattigimiz adapter degiskenine atadik.

        binding.recyclerViewMovies.adapter = adapter
    }
}