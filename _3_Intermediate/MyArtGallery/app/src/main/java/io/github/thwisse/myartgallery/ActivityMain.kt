package io.github.thwisse.myartgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.github.thwisse.myartgallery.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // activity_art.xml tasarimini yaptim.
        // recycler_row.xml tasarimini yaptim. linear'e cevirdim, manuel textview ekledim vs.
        // main activity icin bir option menu (menu_add_art.xml) ve menu klasorunu yarattim.
        // option menunun manuel tasarimini yaptim. simdi menuyu main activitye ekleyeme ve
        // tiklama kodlarini yazacagiz. asagida.
        // ikisini de hallettik. actionbar gozukmuyordu themesden actim.

        // simdi activityart'ta galeri izni ile alakali kodlar yazacagiz.
        // manifestte uses permission kullandik. gerisini activityde yapacagiz.
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
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}