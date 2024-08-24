package io.github.thwisse.kekodappnavigation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // app navigation

        // deep link & up button
        //
        // eger uygulama acikken ve birkac fragment vs ilerlemisken, deeplink ile uygulamadaki
        // herhangi bir yere gidilirse, uygulamanin o onceki backstack'i silinir, deeplinkle acilan
        // kisim ve sonrasi yeni backstack'i olusturur.
        // orn: A -> B -> C gibi bir backstack varken deeplink ile D gibi bir fragment acildiginda
        // backstack suna donusur: A -> D
        //
        // deeplink ile geldigin fragment'tan back tusu ya da yukaridaki geri butonu (up button)
        // ile geri gitmeye calistiginda uygulamanin menusune dogru gitmelisin, yani uygulamadan
        // cikmamalisin. o standart akis kullaniciya yine geriye dogru yaratilmali.
        //
        // up button ile cihazdaki back tusu ayni isi yapmali. ikisi de ayni yere geri donmeli.
        //
        // up butonu olmayan bir ekran yalnizca launcher activity olabilir. diger ekranlarda
        // up button olmali.
        //
        // BottomSheetDialogFragment gibi fullscreen olarak da kullanilabilen dialog'larda geri
        // tusu yerine genellikle sagda X ikonu kullanilir. bu ikon ile de geri gidilebilir. bu ayrimin
        // yapilma sebebi ekran acilis ve kapanis animasyonlarinin activity ve fragment'larda benzer,
        // dialoglarda ise farkli olusundan oturudur.

        // destination: activity, fragment, dialog fragment, custom views vs.
        // hepsine verilen tek bir isimdir.

        // navigation component - benefits and features
        // animasyonlar ve ekran gecisleri, deep link kullanimi, drawer ve bottom navigation gibi
        // ui patternlere destegi var, type safety var, viewmodel destegi var.

        // key concepts:
        // host -> NavHostFragment
        // graph -> NavGraph
        // controller -> NavController
        // destination -> NavDestination
        // route -> any serializable data type.

        // https://developer.android.com/guide/navigation/design#destination_types
        // destination types: hosted (fragments), dialog, activity.
        // navgraph icinde dialog ve activity'leri de ekleyebildigini unutma. yani bunlari fragmentlar
        // icinde manuel olarak acmak yerine navgraph ile acabilirsin.
        // single activity pattern ile calisiyorsan bile gerektigi zaman (launch modelarda ornegin)
        // activity'i tekrar cagirman fln gerekebilir. o zamanlarda da bu islemleri navgraphda
        // yapabilirsin.

        // multiple activity ve fragments kullanimindan single activity ve fragments kullanimina
        // gecilirken, fragment'larin da activity'ler kadar olmasa da yuklu yapilar olduklari
        // konusuluyordu. bu yuzden bundan bi sonraki teknoloji olarak single activity ve view'ler
        // ile calisilmaya baslanmali deniyordu. compose buradan cikti.

        //////////////////////////////////////////
        // baslayalim.
        // navigation component icin dependicies ekledik.
        // navigate ettirmek istedigimiz ekranlarimiz olmali.
        // SecondActivity yarattik. navigation icin bu activity kullanilacak.
        // ProfileFragment, DashboardFragment, SomeBottomSheetFragment yarattik.
        // SettingsDialogFragment yarattik. DialogFragment()'i miras aldirdik.
        // CustomView yarattik. gokhan abinin yazdigi bir custom view'i ve layout'unu ekledim.

        // not1: bir kutuphanenin androidx'li olani varsa mumkun oldugunca android olani degil x'li
        // olani kullanmaya calis.

        // activity uzerinde bir navhostfragment bulunacak.
        // bunun icinde bir navgraph bulunacak. icinde destination'lari, baglantilari vs barindiracak.
        // bu navhostfragment ile navgraph'in iletisimini saglayan bir navcontroller olacak.

        ////////////////////////////////////
        // activity_main layout'una host'u koyacagiz.

        // layout'a fragment ekleme 1. yontem:
        // fragment componenti eklemek

        // bu, fragment class'inin bir componentidir, view degildir.
        // birincil olarak eger bir fragment
        // componenti ekleyeceksen name attribute'una NavHostFragment'i eklemeliyiz.
        //android:name="androidx.navigation.fragment.NavHostFragment"
        // ikincil olarak buna navGraph attribute'unu eklemeliyiz.
        //app:navGraph="@navigation/nav_graph"
        // ucuncul olarak da defaultNavHost attribute'unu eklemeliyiz.
        //app:defaultNavHost="true"
        // bu defaultNavHost true ise graph'in backstack'i takip edilir, false ise bildigimiz activity'nin
        // backstacki takip edilir.
        // id'sini de navHostFragment verdik.

        // bu 1. yontemin asil kullanim amaci: ekranda tek bir fragment olacaksa ve yerine baska
        // fragment'lar gelmeyecekse bu componenti kullanmaliyiz. daha performansli olur yani.

        // layout'a fragment ekleme 2. yontem:
        // FragmentContainerView

        // bu 2. yontemin asil kullanim amaci: ekranda tek bir alana birden fazla fragment eklenip
        // cikarilmak istenirse bu yontemi kullanmaliyiz. bu ozunde bir FrameLayout oldugu icin
        // bunu kullanmak istedigimizde NavHostFragment'a cast etmemiz gerekiyor.
        if (false) { R.drawable.img }
        // buyuk projelerde elbette bu kullaniliyor. bu yontem multiple fragmentlar icin daha performansli.

        // fragment componenti icin verdigimiz tum attribute'leri buna da verdik.
        // idsini bu sefer navHostFragmentView verdik.

        // layout_weight attribute: fragment'in diger fragment'a olan oransal agirligi.
        // ornegin ikisine de 1 degerini verince vertical olarak ayarlanan layoutta dikeyde
        // yari yariya yer kapliyorlar.
        // alternatif mantikli bir yontemin varken bu attribute'u cok kullanma. cok performansli degilmis.

        ///////////////////////////////////////
        // nav_graph.xml'imizi olusturduk. zaten fragment'larda da bunu baglamistik.

        ///////////////////////////////////////
        // nav controller

        // Not: Oluşturduğunuz her NavHost'un kendine karşılık gelen bir NavController'ı vardır.
        // NavController, NavHost'un grafiğine erişim sağlar.

        // navcontroller'a 3 sekilde erisilebilir:
        // Fragment.findNavController()
        // View.findNavController()
        // Activity.findNavController(viewId: Int)

        // asagida getNavController fonskiyonlari ile navcontroller'i elde etme yollarini gorecegiz.

        // nav controller graph'in ekranda gorunmesiyle alakali degil. graph icindeki destination'larin
        // baglantisiyla alakali. controller olmasa da graph ekranda gozukur.

        ////////////////////////////////////////
        // fragment managers

        // activity'ler uzerinde calisacaksak supportFragmentManager kullanmaliyiz.

        // manuel olarak fragment'lar ekleyeseydik, manuel olarak gecisleri ekleme cikarmalari vs
        // yapsaydik yine Fragment Manager ve Fragment Transactions kullanacaktik.

        // Fragment'larin icerisinde baska fragmentlar kullaniyorsak ParentFragmentManager ve
        // ChildFragmentManager gibi manager'lar kullanacaktik.

        ////////////////////////////////////

        // simdi nav_graph xml'imizde bir startDestination ayarlayalim. yani graph'a bir destination
        // verecegim ve bununla basla diyecegim. xml kisminda da gozukuyor.
        //app:startDestination="@id/dashboardFragment"
        // nav graph'a ekledigimiz bu ilk fragment'a label attribute'u ile bir etiket verebiliriz.
        // bu etiket editor uzerinde fragmentlari incelerken gormek icindir, inanilmaz onemli bir sey degil.
        // tools:layout attribute'u ile de graph uzerinde o fragment'in preview'ini gorebiliriz.

        getNavController1()


    }

    // FragmentContainerView kullaniyorsan ve OnCreate'de navController'a ihtiyacin varsa:
    fun getNavController1() {

        // fragment:

        // view ile calissaydik findViewById kullanirdik. fragment ile calisacagimiz icin
        // ve findFragmentById kullanmak istersek supportFragmentManager'a ihtiyacimiz var.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // view (FragmentContainerView):

        // supportFragmentManager yok.
        val navHostFragmentView = findViewById<View>(R.id.navHostFragmentView) as NavHostFragment
        val navControllerView = navHostFragmentView.navController


    }
}