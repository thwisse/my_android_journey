package io.github.thwisse.kekodlessonone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private val cameraComponent = CameraComponent()
private val cameraComponent2 = CameraComponent2()
private val cameraComponent3 = CameraComponent3()

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        Log.e("SecondActivity", "onCreate")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnOpenThirdActivity = findViewById<Button>(R.id.btnOpenThirdActivity)
        btnOpenThirdActivity.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        // lifecycle devami:

        // main activity acilinca calisanlar:

        ///MainActivity            io.github.thwisse.kekodlessonone     E  onCreate
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onResume

        // butona basip second yani bu activity'e gecince calisanlar:

        ///MainActivity            io.github.thwisse.kekodlessonone     E  onPause
        // onceki activity pause edildi ve yeni activity aciliyor:
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onCreate
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onStart
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onResume
        // second activity active olduktan sonra main stop edilecek:
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStop
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onSaveInstanceState
        // burada finish() olmadigi icin destroy edilmedi.
        // eger finish() olsaydi burada onStoptan sonra onDestroy calisirdi ve onSaveInstanceState
        // calismazdi.

        // back tusuna basilinca:

        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onBackPressed
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onPause
        // onceki activity'e donulmeden once second activity pause edildi.
        // onceki activity restart ile aciliyor:
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onRestart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onStart
        ///MainActivity            io.github.thwisse.kekodlessonone     E  onResume
        // onceki activity'e donuldukten sonra second activity destroy ediliyor.
        // yani yeni activity'nin ui'i ekranda gozuktukten sonra onceki activity arkaplanda vefat ediyor.
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onStop
        ///SecondActivity          io.github.thwisse.kekodlessonone     E  onDestroy
        // geri tusuna basmak finish() cagirmak gibi. destroy edecektir.
        // bu islemi kendimiz yaptigimiz icin de onSaveInstanceState'i burada gormuyoruz.

        /////////////////////////////////////////

        // intent ile activity/fragment'lar arasi veri aktariminda en performansli yontem:
        // (bir class referansini aktarmak)

        // intentin icine getStringExtra vs primitive typelari veri olarak aktarabiliyorduk.
        // bunun disinda getParcelableExtra var. bu, getSerializableExtra'nin android'e ozel
        // gelistirilmis formu. serializable java ve kotlinde kullanilan bir yontem, parcelable ise
        // bunun android sdk'i icin en optimize olani (10x daha hizli serializabledan).
        // bir classi stringe cevirip aktarmak: serializable
        // diger taraftan alinan stringi tekrar classa cevirmek: deserializable
        // iste bu sekilde nesneler serializable hatta parcelable ile aktarilabilir.

        // activity/fragment'lar arasi veri aktariminda sinir 1 mb dir. daha buyuk bir aktarim
        // yapmaya calistiginda hata alacaksin.

        // primitive veri aktarim 1

        val param = intent.getStringExtra("key") ?: "default value for log"
        // deger gelmezse diye default deger verdik. bunu log istiyor.
        Log.e("my string", param)
        ///my string               io.github.thwisse.kekodlessonone     E  value

        // primitive veri aktarim 2
        val bundle = intent.extras
        val bundleParam = bundle?.getString("keyBundle",
            "default value") ?: "default value for log"
        // burdaki ilk default value'yu da bundle istiyor. 2.yi yine log istiyor.

        Log.e("my string 2", bundleParam)
        ///my string 2             io.github.thwisse.kekodlessonone     E  valueBundle

        // intent = alisveris sepeti
        // bundle = poset
        // istersen verileri direkt alisveris sepetine de koyabilirsin.
        // istersen de belli verileri belli posetlere koyup o posetleri alisveris sepetine koyabilirsin.

        //TODO ders 2 activities videosu 3:36.00 da anlatilan bu kodu daha iyi yazma kismina daha sonra bak.

        ///////////////////////////////////

        // bir class'i lifecycle aware etmek (lifecycle farkindaligi kazandirmak)
        // lifecycle aware components

        // bu classlari baska yerlerde kullanarak hem hiz kazaniriz hem temiz kod yazariz.
        // classin activity'ler gibi onCreate, onStop vs lifecycle fonksiyonlarina gore
        // calismasini istiyorsam 3 yontem var:

        // 1 - : LifecycleObserver (deprecated) (class = CameraComponent)
        // bu interface'i implement ederek ve eventleri kullanarak yapabiliriz.
        // implement (interface) oldugunu nasil anliyoruz? () yok. () olsa extend olurdu.
        // ancak bu reflection kullandigi icin deprecated oldu. yerine gelen yontemler:

        // 2 - : DefaultLifecycleObserver (class = CameraComponent2)
        // bu interface'i implement ederek ve fonklari override ederek yapabiliriz.

        // 3 - : LifecycleEventObserver (class = CameraComponent3)
        // bu interface'i implement ederek ve zorunlu olarak onStateChanged member'ini implemente
        // ederek yapabiliriz.
        // 2. ve 3. arasinda cok bir fark yok gibi. ikisi de kullanilabilir.

        // classin hangi activity ya da fragment'in lifecycle'ini takip etmesini istiyorsan ona gidip
        // kodunu yazman gerekir:
        // camereComponent, 2 ve 3 nesnelerini en ustte olusturuyorum.

        lifecycle.addObserver(cameraComponent)
        // buranin lifecycle'ini takip edecek. bunu onCreate'de tanimliyoruz.
        // daha sonra bunu remove et. bunu buranin onDestroy'unda remove ediyorum.
        lifecycle.addObserver(cameraComponent2)
        //lifecycle.addObserver(cameraComponent3)
        // activitynin onResume calisinca
        ///CameraComponent         io.github.thwisse.kekodlessonone     E  startCamera
        ///CameraComponent2        io.github.thwisse.kekodlessonone     E  startCamera
        // activitynin onPause calisinca
        ///CameraComponent         io.github.thwisse.kekodlessonone     E  stopCamera
        ///CameraComponent2        io.github.thwisse.kekodlessonone     E  stopCamera
        //TODO 3. calismiyo neden anlamadim.

        // 2. ve 3.yu bilsen yeterli.

        // ornegin 3rd party sdk'ler kullanmak gerektiginde onlarin manager'larina bu interfaceleri
        // implement ettikten sonra istedigimiz activity/fragmentta add ve remove yapabiliriz.

        // bunun yapilma sebebi, lifecycle'a bagli calismasi gereken bir componenti activity/fragment
        // icinde calistirirsan, bunu bir baskasi baska bir lifecycle fonksiyonuna alabilir, degisiklik
        // yapabilir vs. ancak sen componenti lifecycle aware hale getirirsen bu islemlerin hepsi
        // componentin bulundugu file icinde halledilmis olur. isin garanti altina alinmis olur.

        //////////////////////////////

        // onBackPressed ve guncellenmis haliyle alakali notlar thirdactivity'de olacak.

    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.e("SecondActivity", "onContentChanged")
    }

    override fun onStart() {
        super.onStart()
        Log.e("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SecondActivity", "onDestroy")

        lifecycle.removeObserver(cameraComponent)
        lifecycle.removeObserver(cameraComponent2)
        //lifecycle.removeObserver(cameraComponent3)
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("SecondActivity", "onRestart")
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        //Log.e("SecondActivity", "onUserInteraction")
    }


    // sadece bundle olani:
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("SecondActivity", "onSaveInstanceState")
    }

    // sadece bundle olani:
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("SecondActivity", "onRestoreInstanceState")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("SecondActivity", "onBackPressed")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.e("SecondActivity", "onUserLeaveHint")
    }
}