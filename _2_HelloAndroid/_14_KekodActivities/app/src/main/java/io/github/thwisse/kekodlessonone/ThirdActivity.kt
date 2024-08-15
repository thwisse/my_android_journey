package io.github.thwisse.kekodlessonone

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /////////////////////////
        // geri tusu guncel:

        val callback = object: OnBackPressedCallback(true) {
            // enabled argumanina true dersen:
            // bu backpressed'i ben kendim handle edecegim, sen karisma anlamina gelir.
            // yani asagidaki onBackPressed fonksiyonundaki gibi kurgulama yapabilirsin.
            // enabled degerini asagidaki gibi false edene kadar backpressed'i ben handle edecegim
            // super'ini cagirma demis oluyorsun. akisi sana birakiyor, yazdigin kodlarin icinde
            // bir yerde activity'i sen finish edeceksin.

            override fun handleOnBackPressed() {
                // onbackpressed icinde calistirdigimiz kodu buraya aktardim.

                Log.e("ThirdActivity", "handleOnBackPressed")

                counter += 1

                if (counter <= 1) {
                    Toast.makeText(this@ThirdActivity, "Emin misin? Eminsen tekrar yap.", Toast.LENGTH_LONG).show()
                    isEnabled = false
                    // enabled bi kere false'a doner ver ardindan hic if'e girmeyecegi icin false kalir.
                    // false kaldigi icin de super'i cagirilir ve onBackpressed islemi gerceklesir.
                    // yani enabled false oldugu an icindeki kodlarin bir onemi olmuyor. back tusuna
                    // basildigi gibi super'i cagirilir ve back islemi gerceklesir. kodlar calismaz.
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

        // not1: ornegin geri tusuna basildiginda eger bir delay oluyor ve onceki activity yavas aciliyorsa,
        // bunun 2 sebebi olabilir. 1.si: backpressed icinde cok fazla logic vardir yani cok islem
        // yapiliyordur bu yuzden super'i gec cagiriliyordur. 2. si: onceki activity'nin onStart
        // ve/veya onResume fonksiyonlarinda cok fazla islem yapiliyordur bu sebeple de bir turlu
        // ekrana cikamiyor olabilir. bunlar hep bilgi.

        // not2: root activity'de iken back tusuna basildiginda
        // api 30dan (android 11) once: uygulama tamamen kapanir.
        // api 31 ve uzeri: uygulama home tusuna basilmis gibi arkaplana atilir.

        // not3:
        // cold start: uygulamanin ikonuna tiklayip sifirdan acilmasi.
        // warm start: arkaplandaki bir uygulamaninin ikonuna tiklayip onplana getirilmesi.
        // hot start: hizli bi sekilde activityler arasi gecis yapildiginda, bir activity'nin
        // tekrar acilmasi. hizli bir sekilde activity'e dondugumuz durumlarda gerceklesir.
        // bunlarin sureleri var. eger uygulamanin belli performans sorunlari varsa bunlarin sureleri
        // artabiliyor.
        if (false) { R.drawable.img }

        //////////////////////////////////////

        // test

        // project gorunumunde src klasoru icinde androidTest ve test [unitTest] adinda iki farkli
        // klasor var. bunlarin icinde de kt uzantili test dosyalari var.
        // test -> ExampleUnitTest.kt
        // burada temel unit testler yapilir.

        // androidTest -> ExampleInstrumentedTest.kt
        // integration ya da ui test olarak da adlandirilir. android environment'i icin ihtiyacimiz
        // olan ui testleri burada yapariz.
        //TODO testler kismini ders 3 activities 3:25:00 buradan acip devam et

        ///////////////////////////////////////

        // FourthActivity'de View'larla alakali notlar alinacak.
    }

    /*
    override fun onBackPressed() {

        Log.e("ThirdActivity", "onBackPressed")

        //if(true) {
        //    return
        //}
        // asagidaki kodu silmek istedigimde fonskiyon buna izin vermedi. hem silemiyoruz hem de
        // calismasini istemiyoruz. o yuzden yukaridaki if check yapildi. return her turlu calisir
        // ve return ile fonksiyondan cikilir. asagidaki kod calismaz.
        //super.onBackPressed()
        // bunu gerektiginde kullanirsin. simdi yoruma aliyorum. daha bilindik bir ornek yapalim:
        // yukarida counter yarattim.
        counter += 1

        if (counter <= 1) {
            Toast.makeText(this, "Emin misin? Eminsen tekrar yap.", Toast.LENGTH_LONG).show()
        } else {
            super.onBackPressed()
        }
        // onBackPressed, su ustteki super kodu olmasa hicbir anlam ifade etmez. super kodunu da bu
        // sekilde if check ile istedigimiz zaman calisacak sekilde kurgulayabiliriz.
    }
    */
}