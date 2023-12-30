package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapp.databinding.LoginPageBinding

//TODO login_page xmlinde sorun var. layout-normal turevini olusturmadigim surece calismiyor

class LoginPage : AppCompatActivity() {

    private lateinit var binding: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO su alttakini beceremedim. daha sonra ogrenince yapicam. simdilik value'yu elle yazdim

        // activiyler arasi veri transferi icin yazdim:
        // loginPage'deki kullanici adi kismina bir veri yazilmis olmasi gerekecek.
        //val nickname: String = binding.plainTextA1Nickname.text

        /////////////////
        // activityler arasi nesne aktarimi
        // Kisiler classini olusturdum ve o classtan uretecegim nesneleri diger sayfaya aktaracagim
        val kisi1 = Kisiler("cmylmz", 50)

        binding.buttonA1Login.setOnClickListener {
            val newIntent = Intent(this@LoginPage, ActivityMenu::class.java)

            newIntent.putExtra("nickname", "thwisse")
            newIntent.putExtra("yasBilgisi", 23)

            newIntent.putExtra("kisi1", kisi1)

            startActivity(newIntent)
        }
    }
    // B
    //TODO bunda sikinti var. daha sonra bakacagiz.

    /* bunu bi islem icin devre disi biraktim
    // bu fonksiyon sayesinde geri tusunun islemini degistirebiliriz.
    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
        // finishAffinity fonksiyonu ile de uygulamayi kapatabiliriz.
        // direkt yasam dongusundeki onDestroy'u calistirir.
    }
    */
}
