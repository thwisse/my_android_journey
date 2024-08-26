package com.example.pomegranate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pomegranate.databinding.ActivityFourBinding

class ActivityFour : AppCompatActivity() {

    private lateinit var binding: ActivityFourBinding

    // 4.2-
    lateinit var sharedPref: SharedPreferences

    // 4.5 devami
    var userAgePref: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///////////////////////////
        // shared preferences
        // 4.1-
        // bir xml dosyasidir. androidde yani linuxta data klasorunde uygulamamiz icin bu xml dosyasi
        // olusturulur. ardindan key-value eslesmesi ile verileri bu xmle yazdirabilir ya da okuyabiliriz.
        // burada basit, kucuk veriler tutulur. onemli verilerin cloudda ya da ayri bir yerde tutulmasi
        // gerekir. cunku uygulama silinince bu xml de silinecektir.
        // getSharedPreferences ya da this.getSharedPreferences seklinde bir methodla kullanilabilir.
        // activity icinde her yerde kullanilabillir.

        // once activitynin basinda sharedPref degiskenimi tanimladim.
        // name olarak paket adini verdik cunku baska uygulamalarin xmlleri ile karismasin diye.
        // ikinci argumanda da bu xmle sadece benim uygulamam erisebilsin dedik.
        sharedPref = getSharedPreferences("com.example.pomegranate", Context.MODE_PRIVATE)

        // 4.3-
        // daha sonra tasarimda olusturdugum butonlara onclick ozelligiyle alttaki fonksiyonlari verdim.
        // geri kalanini fonksiyonlar icinde yapacagim.

        // 4.5-
        // uygulama acilinca xml var mi xmlde veri var mi onu kontrol edecek kod:
        // once activity basinda userAgePref'i tanimladim.
        // key'i ayni girdik. 2. argumanda da default bir deger girdik. xmlde veri yoksa -1 donecek.
        userAgePref = sharedPref.getInt("anAgeValue", -1)

        if (userAgePref == -1) {
            binding.textViewYourAge.text = "Your age: ?"
            // kayitli veri yoksa -1 yazdiracagina, icinde default ne yaziyorsa o kalmaya devam etsin.
        } else {
            // kayitli veri varsa onu gostersin.
            binding.textViewYourAge.text = "Your age: ${userAgePref}\nYou can change or delete it if you want."
        }
        // ayni anahtari kullandigimiz icin, eger xmlde veri varken bir daha veri girilirse oncekinin
        // ustune yazacaktir.

        // 4.8-
        // su an tikir tikir calisiyor. ancak bu bir database degildir. keyi biz belirliyoruz.
        // olusturdugumuz keylerden fazla veri tutmak isteyebilir kullanici, ya da cok fazla veri olsa
        // kullanisli olmaz, yonetimi de zor olur. basit bir xml dosyasi. kucuk, cok onemli olmayan
        // verileri burada tutabiliriz.

        binding.buttonGoToActivityFive.setOnClickListener {
            startActivity(Intent(this@ActivityFour, ActivityFive::class.java))
        }
    }

    // 4.4-
    fun save (view: View) {
        val age = binding.editTextEnterAge.text.toString().toIntOrNull()

        if (age != null) {

            // 4.6-
            if (age < 0) {
                binding.textViewYourAge.text = "Enter an age!"
                return
                // 4.5 kisminda -1 ile def deger vermistik. yasini -1 girmesin, hatta 0 dan kucuk
                // int da giremesin diye boyle bir kontrol yaptik.
                // 4.6 bitti.
            }

            binding.textViewYourAge.text = "Your age: ${age}\nSaved."
            // edit() ile xmle edit yapacagimizi belirttik. daha sonra ekleyecegimiz degeri sectik.
            // apply() ya da commit() ile de bu islemi onayladik. commit() geriye boolean bir deger
            // donuyor. bu degerle ekledi mi eklemedi mi onu da kontrol edebiliriz.
            sharedPref.edit().putInt("anAgeValue", age).apply()
            // bu islemden sonra artik xml olustu ve save butonuna basinca ekleme yapildi.
            // ancak bir de xmldeki veriyi okuyacak kodu yazmaliyiz. bunu da yapalim.
        } else {
            binding.textViewYourAge.text = "Enter an age!"
        }
    }

    // 4.7-
    fun delete (view: View) {
        // yine keyini yazarak veriye ulastik.
        userAgePref = sharedPref.getInt("anAgeValue", -1)

        // eger degeri -1 degilse sil diyoruz. cunku -1 degilse bir veri vardir ve onu silmeye
        // calisiyoruz. ama -1 ise daha once silinmistir ya da hic olusturulmamistir. o sebeple
        // 4 satir yukaridaki kod sayesinde default olarak -1 atanmistir. -1 ise silecek bir
        // veri de yoktur.
        if (userAgePref != -1) {
            sharedPref.edit().remove("anAgeValue"). apply()
            binding.textViewYourAge.text = "Your age: ?"
        }
    }
}