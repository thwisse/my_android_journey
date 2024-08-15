package com.example.cannonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.cannonball.databinding.ActivityFiveBinding

class ActivityFive : AppCompatActivity() {

    private lateinit var binding: ActivityFiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // night mod icin olusturdugum colors xmlinde light modda kullandigim renklerle ayni isimde
        // renkler olusturup renklerin kodlarini degistirdim. bu renkleri kullandigim seyler
        // ligth/night degisiminde otomatik degisecek.

        // kullandigim bi iconun rengini de mod degisimde degistirebilirim.
        // bunun icin res icinde drawable-night adinda yeni bir resource directory olusturmaliyiz.
        // iconu bu klasor icine kopyaliyorum. night icindeki icon xmlini acip rengini degistiriyorum.
        // mod degisiminde bu da otomatik degisecek. eger night mod icin bir icon ve renk belirlenmemisse
        // o icon icin varsayilan yani light moddaki renk goruntulenecektir.
        // bu islem icin de project dizilimini kullanmak daha faydali. android dizilimi kullanmak biraz zor.

        // light ve night mod icin kullanilan themes xmllerindeki renkleri light/night colors xmllerinde
        // ayni isimle ayri ayri tanimlarsam, tema degistiginde o renkler de otomatik degisecektir.
        // orn: statusBarColor

        // butonlarla tema degisimini saglama:
        binding.buttonLightMode.setOnClickListener {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
        binding.buttonDarkMode.setOnClickListener {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        }







    }
}