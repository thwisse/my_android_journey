package io.github.thwisse.kotlinmaps

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import io.github.thwisse.kotlinmaps.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    private lateinit var sharedPreferences: SharedPreferences
    var trackBoolean: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()

        // son bilinen konum ve konumu kaydetme
        // sharedPreferences kullaniyorum.
        sharedPreferences = this.getSharedPreferences("io.github.thwisse.kotlinmaps", MODE_PRIVATE)
        trackBoolean = false
        // her uygulama acildiginda onCreate calisacagi icin, if check icinde eger false gelirse
        // bir defaya mahsus olmak uzere onLocationChanged fonk calistir, eger true ise calistirma
        // anlamina gelen bir islem yapacagiz.

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // konum belirle, kamerayi yonlendir, marker ekle:

        // lat - latitude - enlem
        // lng - longitude - boylam
        /*
        val micoogullari = LatLng(36.58846709883251, 36.161950338084225)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(micoogullari, 16f))
        mMap.addMarker(MarkerOptions().position(micoogullari).title("Micoogullari"))
        */

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
                if (!trackBoolean!!) {
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

                // son bilinen konumu alma
                val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                // lastLocation degiskeni nullable. cunku daha once bir konum alinmamis olabilir.
                // o yuzden bir if check yapalim
                if (lastLocation != null) {
                    val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 20f))
                }
                // bunun sayesinde konum degistikce surekli ekran degismeyecek. ilk basta kullanici
                // neredeyse orayi gosterecek, ardindan kullanici mapte istedigi yere zoomlayabilecek.
                // aynisini register launcher icine de ekliyorum.

                mMap.isMyLocationEnabled = true
                // konumumu etkinlestirdim mi? evet.
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
        }
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
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 20f))
                    }
                    mMap.isMyLocationEnabled = true
                }
            } else {
                // permission denied
                Toast.makeText(this@MapsActivity, "Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}