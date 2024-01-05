package com.example.passions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.passions.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        // binding oldugu icin bunu kullanmayacagiz.

        // bottom navigation iceren navigation component tasarimi

        // nav comp kurulumunu gradle dosyalarinda yaptim. nav xml olusturdum.
        // fragmentlari olusturdum. gereken ayarlari yaptim. nav xml'e fragmentlari ekledim.

        // bottom navigation
        // olusturdugumuz fragment sayfalari ekranin alt kisminda bir menu araciligiyla acabilmek
        // icin kullanacagiz. oncelikle res icerisinde bir menu directory'si ve resource file'i
        // olusturuyoruz (bottom_bar_menu). menu xml'i icinde 2 fragment icin 2 farkli  menu item ekliyoruz.
        // ve bu itemlarin idlerini fragment idleri ile ayni yapiyoruz. bu onemli. idler eslesiyor.
        // fragmentlarin idleri activity_main_nav xmlindeki idlerdir. karisiklik olmasin.
        // isimlerini de sayfalar ile ayni isimde yapabiliriz.
        // daha sonra bu itemlar icin res/drawable icinde 2 farkli icon olusturup itemlara ekledim.
        //
        // simdi activity main xml'inde BottomNavigationView isimli componenti paletten bulup
        // tasarima ekliyoruz. id'sini bottomNavView yaptim. attributes > menu kismindan menuyu sectim.
        // hizalama, sabitleme ve width/height islemlerini tamamladim. en altta ve saga sola yapisik
        // olmasi mecburi olacagi icin genisligini match constraint, yuksekligini wrap content yaptim.
        // bottom navigationu ayarladiktan sonra navHostFragment componentini main xmle ekliyorum.
        // ekledigimde adi fragmentContainerView oluyor. kafan karismasin sorun yok.
        // idsini navHostFragment yapiyorum.
        //
        // simdi bottomNav componenti ile fragmentContainerView componentinin birlikte calisabilmesi
        // icin activity main xml'i icin calisan main activity kotlin dosyasinda kodlama yapacagiz.
        // binding islemi icin gradleda viewbinding'i aciyoruz.
        // yani burada:

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // fragmentContainerView icin kullanacagimiz degiskenin adi navHostFragment olsun:
        val nav_host_fragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        // iki componentin birlikte calisacagini belirttik:
        NavigationUI.setupWithNavController(binding.bottomNavView, nav_host_fragment.navController)

        ////////////////////////
        // navigation drawer
        // genelde soldan cekince gelen o kayan ekrana deniyor. fragmentlari oradan actirabiliyoruz.
        // bu islem icin yeni bir activity, yeni bir nav dosyasi, yeni fragmentlar ve yeni menu olusturacagim.
        // hepsini de bu projenin icinde yapiyorum.
        // yeni 2 menu item ekledim ve idlerini fragmentlarla eslestirdim.
        // simdi activity_main2 xmlindeki tasarimi constraint'ten drawerlayout'a ceviriyorum.
        // idsini drawer yaptim. icerisinde tasarim yapabilmek icin bir constraint layout aktariyorum.
        // onun da icine bir toolbar ekliyorum. idsini toolbar yapiyorum. hizalama, sabitleme,
        // buyukluk, yukseklik degerlerini ayarladim. constraint layout uzerine bir navhostfragment
        // ekledim ve olusturdugum yeni activity_main_nav2 xmlini yerlestirdim. tasarim ayarlarini yaptim.
        // idsini navHostFragment2 yaptim. simdi bir navigationView ekliyorum ancak constraint altina
        // degil drawer altina ekliyorum. idsini navView yaptim (api 34 iken render problem hatasi aldim.
        // apiyi 34ten 32ye dusurunce calisti). width degerini wrap content yaptik.
        //
        // simdi xml code icinde navigationView'e layout gravity ozelligi verdik ve start degerini girdik.
        // xmlde yukarida drawer'a da open drawer ozelligi verdik ve ona da start degerini girdik.
        //
        // simdi activity main2 xmlinde navView ile navHostFragment2 yi birlikte calistiracak kodu
        // yaziyorum. bundan sonra da toolbar ile drawer'in birlikte calismasi icin gereken kodu yaziyorum.
        // toolbar'da toggle button olusturacagiz ve drawer'in bu butonla acilmasini saglayacagiz.
        //
        // drawer acikken geri tusuna basinca uygulamadan cikiyor. kapaliyken bu okay ama acikken
        // drawer'in kapanmasini istiyorum. simdi bunu kodlayacagiz.
        //
        // drawer'a bir baslik ekleyelim. bunun icin yeni bir xml olusturdum (nav_drawer_title).
        // height degerini dusurdum. xml code icinde background rengini degistirdim. daha sonra uzerine
        // bir yazi yazabilmek icin bir textview olusturdum. bundan sonrasi icin gereken kodlari
        // activity xmlinde yazdim.
        //
        // her sey tamam. bu forDrawer.ActivityMain2 ile .ActivityMain 'i manifest icerisinde yer degistirip
        // calistirdigimda sorunsuz calisiyor. su an hem bottom navigation'a hem drawer'a ihtiyacim
        // olmadigi icin ikisini bir kullanacagim sekilde tek activityde yapmaya calismadim.
        // gerekirse yaparim. simdilik boyle calistigi icin problem yok.

        ////////////////////////////
        // material design 3 - buton tasarimlari
        // background tint: buton rengi degistir
        // cornerRadius: butonun kose kivrimini ayarla. 0 dp = koseli, 5 dp vs = hafif koseli
        //
        // text features
        // editText (Plain Text) component: kullanicinin uygulamaya veri girmesini saglar
        // fontFamily: font ayarla
        // hint: silik yazi
        // inputType: kullanicinin girecegi girdiye gore klavye acilacak
        //
        // simdi profile fragmenti icinde kullanicidan isim soyisim bilgisi alip ekrana yazdiriyorum.
        // bunu FragmentProfile'da yaptim. ordan bakarsin.
        //
        // toggle button ve switch
        // bir seyleri true false sekilde belirlemeye yarayan gorsel yapilar.
        // simdi FragmentCategories kisminda bunla ilgili ornek yapiyorum.
        //
        // radioButton ve checkBox
        // fragment_prof xmlinde bununla ilgili ornek yaptim.
        //
        // ProgressBar, Slider (SeekBar), RatingBar
        // fragment_cate xmlinde bununla ilgili ornek yapiyorum.
        // progressbar ilk acilista invisible olsun. bunu ayarladim. Basla tusuna basinca visible olsun.
        // sonra Dur tusuna basinca da yine invisible olsun istiyoruz.
        //
        // webView kullanimi
        // uygulama icinde internet sitesi acmaya yarar. fragment_web_page icinde ornegini yaptim.
        // bunun icin manifest dosyasinda internet izni aliyorum.
        //<uses-permission android:name="android.permission.INTERNET"/>
        // bunun icin ozel bi fragment olusturuyorum. artik bundan sonra deneyecegim yeni
        // feature'lar icin de fragment olusturacagim. bunu da drawer olan activity'de sergileyecegim.
        //
        // imageView
        // bir resmi uygulama icinde gostermeye yarar. fragment_pictures icinde ornegini yaptim.
        //
        // videoView
        // bir videoyu uygulamada kullanmaya yarar.
        // bunun icin res icine raw isimli resource directory olusturuyoruz.
        // raw klasorunde ses ve videolari barindiririz.
        // ornegini fragment_video xmlinde yaptim.
        //
        // scrollView
        // sayfayi asagi yukari kaydirma islemini yapar.
        // eger sayfa gozuken sayfaya sigmayacak kadar buyukse otomatik devreye girer. buyuk degilse
        // haliyle kaydirma islemi gerceklesmez.
        // bunun ornegini fragment_scroll xmlinde yaptim. kod gerekmedi.


    }
}