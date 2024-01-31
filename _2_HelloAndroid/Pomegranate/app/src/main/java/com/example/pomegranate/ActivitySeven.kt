package com.example.pomegranate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.pomegranate.databinding.ActivitySevenBinding

class ActivitySeven: AppCompatActivity() {

    private lateinit var binding: ActivitySevenBinding

    var runnable: Runnable = Runnable {}  // = dan sonrasi ile aslinda deger atadik ama bos atadik.
    var handler: Handler = Handler(Looper.getMainLooper()) // bunun detaylarini su an ogrenmiyoruz.
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySevenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // threading

        // bir kronometre yapacagiz.
        // 0 dan 100e sayan bir sayac icin bir dongu ve Thread.sleep(1000) kullanacak olursak,
        // kullanicinin arayuzu blocklaniyor ve hicbir islem yapilmaz hale geliyor hatta uyg cokuyor.
        // arayuz calismalarinda kullanma. main thread'de threading yapilmaz. bu tur islemler gerektiginde
        // arkaplanda yapilir, kullanici arayuzunu olumsuz etkilemez.
        // yani simdi bunu kullanarak kronometre yapamayiz. biz runnable kullanacagiz

        // runnable
        // threading islemleri icin esas olarak kullanilir. bu islem icin de, yani kullanici arayuzune
        // yansiyacak islemler icin de runnable kullanmak gerekiyor.
        // runnable kullanabilmek icin handler objesine ihtiyacimiz var.
        //
        // handler'i runnable objeleri yonetebilmek icin yardimci obje olarak kullanacagiz.
        //
        // object: Runnable {} seklinde kullanabiliriz ancak iki fonk icinde de kullanmamiz gerekecegi
        // icin ve ikisinde de ayni objeyi kullanamayacagimiz icin boyle kullanmayacagiz.
        // classin basinda bos bir runnable objesi olusturuyorum. onu her yerde kullanacagiz.
        //
        // daha sonra handler'i yaratiyorum ayni yerde.
        //
        // artik fonksiyonlara basliyorum.

    }

    fun startTimer (view: View) {
        // class basinda number olusturdum. bu sayi fonk her calistiginda 0a esitlenecek.
        number = 0

        // runnable'in asil degerini atadim. islemin aynisini yaptik. sadece degiskene atadik.
        runnable = object: Runnable {
            override fun run() {
                number += 1
                binding.textViewTimer.text = "Timer: ${number}"
                // post delayed = rotarli gecikmeli olarak bu runnable'i gerceklestir
                handler.postDelayed(runnable, 1000)
                // burada ilk arguman olarak runnable yazdik ancak this de yazilabilirdi.
                // bazi kaynaklarda oyle kullaniliyormus. zaten this BURADA activity'ye degil
                // runnable'a ref veriyor.
            }
        }
        // handler'in post metodu ile runnable'i baslatiyoruz.
        handler.post(runnable)
        // bu halde kod calisiyor. ancak butona birden fazla kez basildiginda birden fazla threading
        // ayni anda calistigi icin sayac bozuluyor ve saniyede cook kez artiyor. bunun yasanmamasi
        // icin butona bir kere basinda bir daha basilamaz hale getirecegiz. eger stopa basilirsa o
        // zaman yeniden start butonu aktif olacak. bu sekilde birden fazla basilamayacak.
        binding.buttonStart.isEnabled = false
    }

    fun stopTimer (view: View) {
        binding.buttonStart.isEnabled = true
        number = 0
        binding.textViewTimer.text = "Timer: 0"
        // bu kadari yeterli degil. bir de runnable'in durmasi gerekiyor. cunku stopa bassan
        // bile 0dan baslayip artmaya devam ediyor.
        handler.removeCallbacks(runnable)
        // bu sekilde sikintisiz durduruyor.
    }
}