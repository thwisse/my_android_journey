package com.example.passions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.passions.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom navigation iceren navigation component tasarimi

        // nav comp kurulumunu gradle dosyalarinda yaptim. nav xml olusturdum.
        // fragmentlari olusturdum. gereken ayarlari yaptim. nav xml'e fragmentlari ekledim.

        // bottom navigation
        // olusturdugumuz fragment sayfalari ekranin alt kisminda bir menu araciligiyla acabilmek
        // icin kullanacagiz. oncelikle res icerisinde bir menu directory'si ve resource file'i
        // olusturuyoruz (bottom_bar_menu). menu xml'i icinde 2 fragment icin 2 farkli  menu item ekliyoruz.
        // ve bu itemlarin idlerini fragment idleri ile ayni yapiyoruz. bu onemli.
        // fragmentlarin idleri activity_main_nav xmlindeki idlerdir. karisiklik olmasin.
        // isimlerini de sayfalar ile ayni isimde yapabiliriz.
        // daha sonra bu itemlar icin res/drawable icinde 2 farkli icon olusturup itemlara ekledim.
        //
        // simdi activity main xml'inde BottomNavigationView isimli componenti paletten bulup
        // tasarima ekliyoruz. id'sini bottomNavView yaptim. attributes > menu kismindan menuyu sectim.
        // hizalama, sabitleme ve width/height islemlerini tamamladim. en altta ve saga sola yapisik
        // olmasi mecburi olacagi icin genisligini match constraint, yuksekligini wrap content yaptim.
        // bottom navigationu ayarladiktan sonra navHostFragment componentini main xmle ekliyorum.
        // ekledigimde adi fragmentContainerView oluyor. kafan karismasin sorun yok.
        // idsini navHostFragment yapiyorum.
        //
        // simdi bottomNav componenti ile fragmentContainerView componentinin birlikte calisabilmesi
        // icin activity main xml'i icin calisan main activity kotlin dosyasinda kodlama yapacagiz.
        // binding islemi icin gradleda viewbinding'i aciyoruz.
        // yani burada:

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // fragmentContainerView icin kullanacagimiz degiskenin adi navHostFragment olsun:
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        // iki componentin birlikte calisacagini belirttik:
        NavigationUI.setupWithNavController(binding.bottomNavView, navHostFragment.navController)

        //TODO NavHostFragment componenti uygulamada gozukmuyor.



    }
}