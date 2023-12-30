package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapp.databinding.ActivityMenuBinding

class ActivityMenu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // parantez icindeki nickname key'i ile baska sayfadan bir value'yu aldim.
        val nickname = intent.getStringExtra("nickname")
        // tasarimdaki textView'e diger sayfadan aldigim veriyi aktardim.
        binding.textViewB1Nickname.text = nickname

        // sayisal degerler icin bizden default bir value istiyor.
        // karsidan veri gelmemesi ihtimali icin.
        val yasBilgisi = intent.getIntExtra("yasBilgisi", 0).toString()
        binding.textViewB2YasBilgisi.text = yasBilgisi

        //TODO serializable icin yeni bir alternatif mi var?

        // nesne aktarimi
        val kisi1 = intent.getSerializableExtra("kisi1") as Kisiler
        binding.textViewB1Nickname.text = kisi1.nickname
        binding.textViewB2YasBilgisi.text = (kisi1.age).toString()

        // C
        binding.buttonA1GoToActivityProfile.setOnClickListener {
            val intent = Intent(this@ActivityMenu, ActivityProfile::class.java)

            // backstackten C yi silip D den direkt B ye atlamak icin bunu yaziyorum:
            finish()
            // dogru bir sekilde calisiyor.

            startActivity(intent)
        }
    }

    //TODO onBackPressed yerine kullanilan baska bise var mi? buna deprecated diyor.

    // geri tusuna yeni islev atama:
    /* bunun yeni versiyonunu ActivityProfile'da yapacagim icin bu kismi blok yoruma aldim.
    override fun onBackPressed() {
        super.onBackPressed()
        val newIntent = Intent(this@ActivityMenu, MainActivity::class.java)
        startActivity(newIntent)
    }
    */
    // intent ile activity gecisiyle ilgili kod yazildiysa bu isleme
    // geri donmek denmez sayfa acmak denir. yani bu sekilde intent olusturup geri tusunun
    // islevini bu sayfa ozelinde degistirmis olduk. geri tusu default olarak onceki sayfayi acar.
    // ancak bu sayfada islevi degisti ve baska bir sayfayi acar oldu.



}