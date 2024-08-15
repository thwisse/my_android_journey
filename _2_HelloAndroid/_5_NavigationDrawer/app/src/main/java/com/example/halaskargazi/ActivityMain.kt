package com.example.halaskargazi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.halaskargazi.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragmenT = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        // navView & navHostFragment:
        NavigationUI.setupWithNavController(binding.navView, navHostFragmenT.navController)

        binding.toolbar.title = "Halaskargazi"
        // toolbar & drawerLayout:
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
            0,0)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    // drawer acikken geri tusuna basmak:
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}