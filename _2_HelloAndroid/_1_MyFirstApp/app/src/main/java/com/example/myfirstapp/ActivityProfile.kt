package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import com.example.myfirstapp.databinding.ActivityProfileBinding

class ActivityProfile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // context kullanimi ornek (this ile)
        binding.buttonA1ShowToastMessage.setOnClickListener {
            // this ile context bu sekilde belirtilir.
            // ancak this her zaman kullanisli olmuyor. eger curly bracket icinde cok fazla
            // kod olursa this sikinti yaratabilir, baska bir seyi referans olarak alabilir.
            Toast.makeText(this, "Hi Barbie!", Toast.LENGTH_SHORT).show()
        }
        binding.buttonA2ShowToastMessage.setOnClickListener {
            // bu yuzden this@ActivityName ile activity'i direkt belirtebiliriz. daha kullanisli:
            Toast.makeText(this@ActivityProfile, "Hi Ken!", Toast.LENGTH_SHORT).show()
            // ve this@ActivityProfile gibi yine direkt activity'nin ozelligini alan bir baskasi:
            Toast.makeText(applicationContext, "Hi Ken! (2.)", Toast.LENGTH_SHORT).show()
            // bunlar disinda fragment yapilarinda da context kullanacagiz.
            // onlarda bu islemleri kullanamiyoruz. cunku fragment bir activity degil. farkli.
            // baska bir sekilde context belirtecegiz.
            // bunun ornegini FragmentForContext classinda yapacagim.
        }

    }

    // D
    //TODO backstack islemi yarim kaldi. calismiyor. sonra bakacagiz.

    // back stack kontrolu ile geri tusu gecisini ayarlama
    /*
    override fun onBackPressed() {
        startActivity(Intent(this@ActivityProfile, LoginPage::class.java))

        // bu kod sayesinde back stack temizlenecek. B sayfasina donuldukten sonra
        // geri tusuna basinca A ya donecek. A da da basinca uygulamadan cikis yapacak.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        super.onBackPressed()
    }
    */
    /*
    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {

        //startActivity(Intent(this@ActivityProfile, LoginPage::class.java))

        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        return super.getOnBackInvokedDispatcher()
    }
    */




}