package com.example.cannonball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.example.cannonball.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /////////////////////////
        // default toast message ile tasarlanmis toast message gosterimi
        // ozel toast message sacma sapan bisey. guzel de gozukmuyor. belirledigim gibi de gozukmuyor.
        // zaten deprecated olmus. gerek olur mu bilemedim. her surumde calismayabilirmis.

        // siradan bir toast message gostermek:

        binding.buttonToast1.setOnClickListener {

            Toast.makeText(this@ActivityMain, "Merhaba", Toast.LENGTH_SHORT).show()
        }

        // ozel toast message olusturmak ve gostermek:

        // layoutta toast_2 adinda yeni bir tasarim xmli olusturdum. yuksekligini 50dp ayarladim.
        // xml koduna android:background="@color/gray" degerini ekledim rengini degistirdim.
        // bir resim ekledim. tasarim islemlerini yaptim. simdi bu tasarimi main icine alip daha sonra
        // butona basinca sergilenecek sekilde ayarlayacagim.

        binding.buttonToast2.setOnClickListener {
            // toast tasarimi activity'e alalim
            val toastDesign = layoutInflater.inflate(R.layout.toast_2, null)
            // root null olacak cunku bu tasarim bagimsizdir ve sadece burada bu amacla kullanilacak.

            // mesaji girelim
            val message = toastDesign.findViewById(R.id.textViewToast2Message) as TextView
            // bu as TextView islemi castingdir, anlama gelir: bunu TextView sinifina donustur.
            message.text = "Horse washing in and out!"

            // deprecated olmus. toast mesaji bir design ile gostermek artik desteklenmiyor.
            val toastMessage = Toast(this@ActivityMain)
            toastMessage.view = toastDesign

            // design'i konumlandiralim
            toastMessage.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
                0, 0)
            // yatay ve dikeyde ortaladim.
            //TODO neden or dedik anlamadim. ikisinin de olmasi icin and dememiz gerekmez miydi?

            // ekranda durma suresini ayarlayalim.
            toastMessage.duration = Toast.LENGTH_LONG

            toastMessage.show()
        }

        binding.buttonGoToActivityTwo.setOnClickListener {
            startActivity(Intent(this@ActivityMain, ActivityTwo::class.java))
        }

        //////////////////////////////
        // pop up menu olusturma
        // bununla ilgili ornegi ActivityTwo'da yapiyorum.

        ///////////////////////////
        // alertview olusturma
        // kullaniciya uyari mesaji veren bir yapidir.
        // alertview uzerinden veri de alabiliriz. butonlari ozellestirebiliriz vs.
        // bununla ilgili ornegi ActivityThree'de yapiyorum.

        ///////////////////////////
        // snackbar olusturma
        // ekranin altinda cikan bir bildirim seklidir. kullanici etkilesimli olabilir.
        // bununla ilgili ornegi ActivityFour'da yapiyorum.

        /////////////////////
        // aydinlik tema / karanlik tema tasarimlari
        // night modda baska bir renk paleti kullanmak icin colors xmlini res icindeki values/night
        // klasorunun icine kopyaliyoruz. project barinda default olarak android dizilimi gorundugu
        // icin night klasoru gozukmuyor. bunun icin project barindan project dizilimini secip o
        // klasore ulasabiliriz.
        // bu konuyla alakali kodlama ve bilgiler ActivityFive'da olacak.


    }
}