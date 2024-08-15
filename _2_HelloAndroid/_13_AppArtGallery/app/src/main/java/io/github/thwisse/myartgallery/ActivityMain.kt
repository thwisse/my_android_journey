package io.github.thwisse.myartgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.thwisse.myartgallery.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var artList: ArrayList<Art>

    private lateinit var artAdapter: ArtAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artList = ArrayList<Art>() // initialize etmeden kullanamayiz

        // recyclerview islemleri:
        // adapter'i yukariya ekliyoruz. ardindan bos listeyi adapter'a verelim.
        artAdapter = ArtAdapter(artList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = artAdapter
        // adapter islemlerini burada yaptik ancak list bos yani activity bos gozukecek.
        // verileri asagidaki while loop'un icinde ekliyordum. burda ise cekmeye calisiyorum. sebebi bu.
        // o yuzden while loopun altinda bir kod ile veri setini guncelletecegiz.


        ////////////// proje baslangici:

        // activity_art.xml tasarimini yaptim.
        // recycler_row.xml tasarimini yaptim. linear'e cevirdim, manuel textview ekledim vs.
        // main activity icin bir option menu (menu_add_art.xml) ve menu klasorunu yarattim.
        // option menunun manuel tasarimini yaptim. simdi menuyu main activitye ekleyeme ve
        // tiklama kodlarini yazacagiz. asagida.
        // ikisini de hallettik. actionbar gozukmuyordu themesden actim.

        // simdi activityart'ta galeri izni ile alakali kodlar yazacagiz.
        // manifestte uses permission kullandik. gerisini activityartda yapacagiz.

        // simdi veritabanindan verileri cekip main activity'de sergileyecegiz.

        try {
            val database = this.openOrCreateDatabase("MyArtGallery", MODE_PRIVATE, null)

            val cursor = database.rawQuery("SELECT * FROM arts", null)

            val idIx = cursor.getColumnIndex("id")
            val artNameIx = cursor.getColumnIndex("artName")
            // Ix = index

            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIx)
                val artName = cursor.getString(artNameIx)
                // class Art (val id: Int, val name: String)
                // bu sekilde bir class olusturduk.
                val art = Art(id, artName)
                // nesneleri icinde tutmak icin artList adinda bir ArrayList olusturdum ve
                // onCreate basinda initialize ettim.
                artList.add(art)
            }
            cursor.close()

            artAdapter.notifyDataSetChanged()
            // su anlama gelir: adapter'a haber ver, veri seti degisti.
            // adapter kendine ceki duzen verir.


        } catch (e:Exception) {
            e.printStackTrace()
        }

        // artik bu diziyi kullaniciya gosterme islemi kaldi.
        // zaten bir recyler row tasarlamistik. simdi bir recycler adapter hazirlayacagiz.
        // ArtAdapter classini olusturdum. adapter islemleri bitti, recyclerview ile ilgili
        // bazi islemler yapilacak, burada classin basinda yapiyorum.

        // artik veritabanindaki eserlerin isimlerini main activity'de kullaniciya gosterebiliyoruz.
        // ancak uzerine tiklaninca eserin tum bilgilerinin gorunecegi kodu yazmamiz gerekiyor.
        // su an hem options menudeki add art item'ina basinca hem de recycler view'deki eserlerin
        // isimlerine tiklayinca Activity art aciliyor. bunu ayristirmamiz gerek. ikisinde de
        // activityArt acilacak ancak farkli amaclarla acilmalilar. bunu yapalim.
        // adapter'dan da option menu'den de intent ile old ve new isimli value'lar yollayip
        // bunu activityart'ta if ile kontrol edip gereken islemleri yaptiracagiz.
    }

    // menuyu baglama:
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_add_art, menu)

        return super.onCreateOptionsMenu(menu)
    }

    // menuye tiklaninca ne olacagi:
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // bizim tek item'imiz var aslinda bir problem olmaz ancak normalde options menu'lerde
        // birden fazla item olur. bu yuzden bir kontrol yapmamiz lazim.

        if (item.itemId == R.id.itemAddArt) {
            val intent = Intent(this@ActivityMain, ActivityArt::class.java)

            intent.putExtra("info", "new")

            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}