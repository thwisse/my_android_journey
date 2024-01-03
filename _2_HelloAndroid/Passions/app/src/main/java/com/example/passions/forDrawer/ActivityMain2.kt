package com.example.passions.forDrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.passions.R
import com.example.passions.databinding.ActivityMain2Binding

class ActivityMain2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val nav_host_fragment2 =
            supportFragmentManager.findFragmentById(R.id.navHostFragment2) as NavHostFragment
        // navView ile navHostFragment2 ortaklasmasi:
        NavigationUI.setupWithNavController(binding.navView, nav_host_fragment2.navController)

        binding.toolbar.title = "Passions"
        // toolbar ile drawer ortaklasmasi:
        val toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                    0, 0)
        // this = context
        // 0,0 degerleri offset degerleridir. drawer acilip kapanirken yasanan kaymalarin miktarini
        // ayarlamak icin. biz 0,0 yaparak default degerleri atadik.

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        // bu iki kodla toggle ile acilan drawer ve toolbar synchronize bir sekilde calisacak.

        // olusturdugumuz title xml layout'unu navView uzerine ekledik:
        val title = binding.navView.inflateHeaderView(R.layout.nav_drawer_title)
        // basliktaki yaziya istersek erisip farkli seyler yazdirabiliriz:
        val textView_title = title.findViewById(R.id.textViewTitle) as TextView
        textView_title.text = "Passions"
        // bunu zaten direkt tasarimda da yazabilirdik de, boyle bir seyi de yapmak istersek
        // nasil yapildigini hatirlayabileyim diye yazdim kodunu.
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            // drawer'i kapat:
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            // geriye gitme islemini yap:
            // api 32 ve oncesi icin:
            super.onBackPressed()
            // 33 ve sonrasi icin de yine calisir ama gunceli var. onu kullansan daha iyi

            // api 33 ve sonrasi icin:
            //onBackPressedDispatcher.onBackPressed()
            //TODO hala bu dogru olan kod degil. guncel olanini ogren.
        }


    }

}