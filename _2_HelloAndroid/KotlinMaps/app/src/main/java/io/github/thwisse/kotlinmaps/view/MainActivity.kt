package io.github.thwisse.kotlinmaps.view

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.thwisse.kotlinmaps.R
import io.github.thwisse.kotlinmaps.adapter.PlaceAdapter
import io.github.thwisse.kotlinmaps.databinding.ActivityMainBinding
import io.github.thwisse.kotlinmaps.model.Place
import io.github.thwisse.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ////////////////////////////////////////////

        // database'imizi ve db'mizi burada da olusturduk.
        val db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places").build()
        val placeDao = db.placeDao()

        compositeDisposable.add(
            placeDao.getAll() // verileri alacagiz
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
            // handleResponse fonskiyonunu asagida yarattim.
        )

        // artik bu islem de bitti.

        //////////////////////////////////////

        // bir mapsActivity yaratildi, launcher activity olarak.
        // google maps ve cloud sitelerinden gereken islemler yapildi ve map icin gereken
        // api kopyalanip projeye eklendi. proje calistirilinca su an map gozukuyor.

        // mapsActivity'e geciyorum.

        // mapsActivity'de simdilik islerimiz bitti. oradan alinan konum bilgilerini db'ye
        // kaydedip, bu verileri cekip sergileyecegimiz yer mainActivity olacak. bunlari yapalim.

        // recycler view tasarima eklendi
        // recycler row layoutta yaratildi. tasarlandi.
        // activity_maps xml'inde onemli degisikler yapildi. map fragment'i constraint layout icine
        // alindi. diger componentler eklendi. save ve delete fonksiyonlari yaratildi.

        // not1: match constraint: constraint'lere saygi duy. geri kalan yerlere yayil.

        // activity_maps tasarimi bitti. simdi bir menu yaratip bunu main activity'e ekleyecegiz.
        // menu yaratildi.
        // bu menuyle alakali iki fonksiyon override etti hoca. onCreateOptionsMenu ve
        // onOptionsItemSelected adinda. ancak bunlari kullanirsam uygulamada toolbar olmasi gerekiyor.
        // toolbar kullanmak yerine ben fab olusturdum ve menuyu ona ekledim. fab kodlari asagida:

        val fab: FloatingActionButton = findViewById(R.id.fab)

        // PopupMenu'yu bir kez oluştur
        val popup = PopupMenu(this, fab)
        popup.menuInflater.inflate(R.menu.menu_place, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itemAddPlace -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        // FAB'a her tıklamada popup'u göster
        fab.setOnClickListener {
            popup.show()
        }

        ///////////////////////////////////
        // RoomDB - konumlari veritabanina kaydetmek
        // room icin gereken implemasyonlari vs build.gradle icinde yaptik.
        if (false) {
            R.drawable.img
        }
        // simdi kotlinmaps klasorume 4 yeni klasor ekledim. model, roomdb, view ve adapter.
        // view icine activitylerin kt dosyalarini koydum.
        // room kullanabilmek icin 3 sey olusturacagiz. room database, dao ve entity.
        // once modeli yapiyoruz. entity'i burada olusturduk.
        // simdi roomdb icinde dao (data access object) olusturuyoruz. bu interface olacak.
        // simdi roomdb icinde room database'i olusturuyoruz.
        // artik save fonksiyonunu yazabiliriz.
        // save fonk vs bitti. simdi recycler view icin islemler yapacagiz.
        // buraya yani main activity'ye donduk. binding ayarladik ve yukarida compositeDisposible
        // yaratiyoruz.
        // burdaki compositeDisposible islemi de bitti. tek kalan verileri recyclerview'de sergilemek.
        // PlaceAdapter'imizi olusturalim.
    }

    private fun handleResponse(placeList: List<Place>) {
        // bu methodun biza List<Place>'i vermesi lazim.
        // response'u handle edecek olan fonk bu. mapsactivity icinde bu fonksiyonla
        // mainactivity'e gecis yapiyorduk. burada ise bu fonksiyonla listeyi alip iletecegiz.

        binding.rvLocations.layoutManager = LinearLayoutManager(this)
        // database row'dan gelen listeyi burada adapter'a yollayalim:
        val adapter = PlaceAdapter(placeList)
        // adapter'i da tanimladik:
        binding.rvLocations.adapter = adapter
    }
}