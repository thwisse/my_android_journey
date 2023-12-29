package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import com.example.myfirstapp.databinding.ActivityProfileBinding

class ActivityProfile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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