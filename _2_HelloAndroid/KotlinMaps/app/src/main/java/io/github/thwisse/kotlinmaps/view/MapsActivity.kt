package io.github.thwisse.kotlinmaps.view

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import androidx.room.RoomDatabase

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import io.github.thwisse.kotlinmaps.R
import io.github.thwisse.kotlinmaps.databinding.ActivityMapsBinding
import io.github.thwisse.kotlinmaps.model.Place
import io.github.thwisse.kotlinmaps.roomdb.PlaceDao
import io.github.thwisse.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private lateinit var sharedPreferences: SharedPreferences
    var trackBoolean: Boolean? = null

    private var selectedLatitude: Double? = null
    private var selectedLongitude: Double? = null

    private lateinit var db: PlaceDatabase
    private lateinit var placeDao: PlaceDao

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()

        //////////////////////////////////////////
        // son bilinen konum ve konumu kaydetme

        // sharedPreferences kullaniyorum.
        sharedPreferences = this.getSharedPreferences("io.github.thwisse.kotlinmaps", MODE_PRIVATE)
        trackBoolean = false
        // her uygulama acildiginda onCreate calisacagi icin, if check icinde eger false gelirse
        // bir defaya mahsus olmak uzere onLocationChanged fonk calistir, eger true ise calistirma
        // anlamina gelen bir islem yapacagiz.

        ///////////////////////////
        selectedLatitude = 0.0
        selectedLongitude = 0.0

        db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places")
            .allowMainThreadQueries()
            .build()
        // database adimiz Places, table adimiz Place olmus oldu.
        placeDao = db.placeDao()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)

        //////////////////////////////////
        // konum belirle, kamerayi yonlendir, marker ekle:

        // lat - latitude - enlem
        // lng - longitude - boylam
        /*
        val micoogullari = LatLng(36.58846709883251, 36.161950338084225)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(micoogullari, 16f))
        mMap.addMarker(MarkerOptions().position(micoogullari).title("Micoogullari"))
        */

        ///////////////////////////////////////
        // cihazin konum bilgisini alma:

        // locationManager ve locationListener degiskenlerini tanimladim yukarida.

        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        // as LocationManager kismini yazmayinca hata veriyor. Required: LocationManager, Found: Any!
        // diyor. yani sen hizmeti LOCATION_SERVICE olarak sectin ancak ben bunun loc manager icin
        // dogru servis oldugundan emin degilim diyor, Any? yani herhangi bir sey olarak goruyorum diyor.
        // biz de as ile casting yapiyor ve LocationManager oldugunu belirtiyoruz.

        // requestLocationUpdates ozelligini kullanacagimiz icin locationListener degiskenini yarattik.
        // simdi onu tanimlayalim ardindan devam edelim.

        locationListener = object : LocationListener {
            // object kullandim ve onLocationChanged fonksiyonunu direkt implement ettim.
            override fun onLocationChanged(location: Location) {
                //requestLocationUpdates ile benzer islemi yapacak. location degistikce bize location
                // bilgisini verecek.

                trackBoolean = sharedPreferences.getBoolean("trackBoolean", false)
                if (!trackBoolean!!) { // trackBooleand == null manasina gelir.
                    val userLocation = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 20f))
                    sharedPreferences.edit().putBoolean("trackBoolean", true).apply()
                }
            }
            // burada baska fonksiyonlar da var.
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                super.onStatusChanged(provider, status, extras)
            }
        }

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        //    1000, 10f, locationListener)

        // bu kodu yazmama ragmen izin almadan calismaz. o yuzden uses permission'i halledelim.
        // ACCESS_FINE_LOCATION -> gercek konum
        // ACCESS_COARSE_LOCATION -> yakin konum
        // izinler icin gerekenleri yaptik.


        // ContextCompat kullaniyoruz cunku versiyonlarin uyumlu olabilmesi icin. onceki android
        // surumlerinde de yeni surumlerde de calisabilsin diye.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // permission denied

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Snackbar.make(binding.root, "Permission needed for location",
                    Snackbar.LENGTH_INDEFINITE).setAction("Give Permision") {
                        // request permission

                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()

            } else {
                // request permission

                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        } else {
            // permission granted

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0f, locationListener)
            // konum bilgisi gps'ten degil de wifi'dan vs gelseydi ornegin saglayici olarak
            // NETWORK_PROVIDER falan secerdik.
            // 0 saniyede bir location bilgisini update et
            // 0f metre yakindaki yerini goster
            // eger 0,0 yaparsan anlik olarak net konumunu gosterecektir.

            // son bilinen konumu alma
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            // lastLocation degiskeni nullable. cunku daha once bir konum alinmamis olabilir.
            // o yuzden bir if check yapalim
            if (lastLocation != null) {
                val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))
            }
            // bunun sayesinde konum degistikce surekli ekran degismeyecek. ilk basta kullanici
            // neredeyse orayi gosterecek, ardindan kullanici mapte istedigi yere zoomlayabilecek.
            // aynisini register launcher (permission launcher) icinde granted eidlen yere de ekliyorum.

            mMap.isMyLocationEnabled = true
            // konumumu etkinlestirdim mi? evet.
        }

        ////////////////////////////////////
        // uzun tiklayarak konum bilgisi alma ve isaretleme

        // MapsActivity'mizin tanimlamasina ekleyebilecegimiz bircok interface var.
        // GoogleMap.Click yazarak hangi click listenerlarin oldugunu da gorebiliriz.
        // biz burada GoogleMap.OnMapLongClickListener kullanacagiz. zorunlu olan member'i yani
        // onMapLongClick fonskiyonunu implement ediyoruz. bu interface'i kullanacagimizi en yukarida
        // mMap.setOnMapLongClickListener ile bildirmemiz gerekiyor. simdi baslayabiliriz.
        // ardindan secilen latitude ve longitude bilgisini farkli degiskenlere atayacagiz.
        // en yukarida yaratiyorum ve onCreate icinde initialize ediyorum. simdi fonksiyona ekliyorum.
        // bu islem de sona erdi. bundan sonra bu degerleri db'ye ekleme, ui tasarlama islemlerini
        // yapacagiz. yani root activity'i tasarlayacagiz. bunu mainActivity'de yapacagiz.
    }

    private fun registerLauncher() {
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                // permission granted

                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        0, 0f, locationListener)

                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null) {
                        val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))
                    }
                    mMap.isMyLocationEnabled = true
                }
            } else {
                // permission denied
                Toast.makeText(this@MapsActivity, "Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onMapLongClick(p0: LatLng) {

        mMap.clear()
        // secilmis bir marker varsa onu silecek.

        mMap.addMarker(MarkerOptions().position(p0))
        // p0 degiskeniyle secilen yerin konumunu alacagiz ve marker ekleyecegiz.

        selectedLatitude = p0.latitude
        selectedLongitude = p0.longitude
    }

    fun save(view: View) {
        val place = Place(binding.edtPlace.text.toString(), selectedLatitude!!, selectedLongitude!!)
        // istersen enlem ve boylam icin null kontrolu yapabilirsin. ancak zaten onCreate icinde
        // biz 0.0 olarak baslatmistik, ordan null gelmez. ancak belki onMapLongClick icinde p0 null
        // gelebilir. bu yuzden kontrol yapmak istersen yapabilirsin.

        // db ve placeDao nesnelerimizi olusturduk ve initialize ettik. artik placeDao ile
        // db fonksiyonlarimiza erisebiliriz.

        //placeDao.insert(place)
        // Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
        // bu sekilde bir hata aldik. diyor ki bu db islemini main thread'de degil arkaplanda yapmalisin.
        // su an kaydettigimiz verilerden bir zarar gelmez ancak ileride buyuk boyutlu veriler kaydetmek
        // istersek diye bunu boyle guvenli yapmislar. implemasyonlardaki rxjava ile bunlari asenkron
        // bir sekilde yapmayi ogrenecegiz.

        // Thread havuzlari
        // Main Thread UI (burada izin vermedi)
        // Default Thread (yogun cpu gucu gerektiren isler icin kullanilir)
        // IO Thread (internetten veri cekmek icin kullanilir) (internet/database)
        // RxJava (simdi yapicaz)
        // Coroutines (ilerde gorucez)

        // simdi onCreate icinde db'yi initialize ettigimiz koda .allowMainThreadQueries() ekliyorum.
        // sadece bunu yaparak uygulama calisir. cunku izin vermis oluyoruz.
        // ancak boyle yapmayacagiz. biz rxjava kullanacagiz.
        // rxjava endustride kullanilan open source bir kutuphane.
        // javada rxjava, kotlinde coroutines tavsiye ediliyormus. ancak biz ikisini de ogrenecegiz.

        // Completable (tamamlanabilir): geriye bir sey dondurmeyenlerde (insert, delete, update)
        // Flowable: Query'lerde yani SELECT * yaptigimiz yerlerde kullanilir.
        // ilerde internetle calisacagimiz zaman da Observable ve Single'i da kullanacagiz.
        // DAO'muzu bunlara gore yeniliyoruz.

        // simdi bu dosyada compositeDisposable objesini yarattim.
        // disposable: kullan at anlamina gelir
        // database ile ilgili internete cok fazla call yaptiginda bi zaman sonra hafizada bu call'lar
        // cok yer ediyormus. bu call'lari bu obje ile kullanip atabiliyormusuz.
        // simdi asagida compositeDisposable.add islemini ve handleResponse fonksiyonunu yaratalim
        // ardindan onDestroy'da compositeDisposable.clear() yaparak atma islemini gerceklestiriyoruz.

        compositeDisposable.add(
            placeDao.insert(place)
                .subscribeOn(Schedulers.io()) // arkaplanda calistir
                .observeOn(AndroidSchedulers.mainThread()) // androidde gozlemle
                .subscribe(this::handleResponse) // bitince bu fonksiyonu calistir (referans veriyoruz)
        )
    }

    private fun handleResponse() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun delete(view: View) {

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}