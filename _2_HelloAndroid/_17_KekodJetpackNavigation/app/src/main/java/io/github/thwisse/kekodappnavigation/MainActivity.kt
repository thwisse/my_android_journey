package io.github.thwisse.kekodappnavigation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

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
        // backstacki takip edilir. true vermediginde jetpack navigation'in backstack'i takip edilemez.
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

        ///////////////////////////////
        // navcontroller'i kullanma

        // navcontroller'a 3 sekilde erisilebilir:
        // fragment uzerinden:  Fragment.findNavController()
        // view uzerinden:      View.findNavController()
        // activity uzerinden:  Activity.findNavController(viewId: Int)

        // 1. yol
        //getNavControllerViaFragment()
        //getNavControllerViaView()
        // ister fragment ister view kullan, en az hata ile karsilasagin yontem bu.
        // onCreate'de iki yapi da sorunsuz calisir.

        // 2. yol
        //getNavControllerViaFragment2()
        //getNavControllerViaView2()  // bu burada crash veriyor.
        // onCreate'de fragment sorunsuz calisir. ancak view crash verir. onResume'da kullanabilirsin.

        // 3. yol
        //getNavControllerViaFragment3()
        //getNavControllerViaView3()  // bu burada crash veriyor.
        // onCreate'de fragment sorunsuz calisir. ancak view crash verir. onResume'da kullanabilirsin.

        // best practices: FragmentContainerView kullanmak ve :
        getNavControllerViaView() // bu fonksiyondaki gibi erismek.

        //////////////////////////////////////
        // navgraph fragment'lari baglama

        // eskiden supportFragmentManager ve Fragment transaction kullanarak add yaparak bunlari
        // bagliyorlarmis. manuel yontem buymus. cok ugrastiriciymis. ancak artik tum bunlar action
        // ile yapiliyor.

        // fragment'larda cizdirme islemi activity'lerin aksine onCreate'de degil onCreateView'de
        // yapilir. fragment'larin da onCreate fonksiyonu vardir ancak asil islemleri orada yapmayacagiz.
        // ekstra olarak onViewCreated fonksiyonunu da kullanacagiz.
        // gereken islemler burada yapilacak:
        if (false) { DashboardFragment() }
        // dashboardFragment'imiza bir fragment, bir dialogFragment, bir placeholder, bir activity
        // baglamayi ogrendik.

        /////////////////////////////////////
        // navGraph'da argument tanimlama
        // ornegini nav_graph.xmlde fragment profile icinde yaptim.

        /////////////////////////////////////
        // nested graphs

    }

    override fun onResume() {
        super.onResume()

        //getNavControllerViaView2()

        //getNavControllerViaView3()
    }

    fun getNavControllerViaFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        // sorunsuz calisti.

        // fragment'a casting yapilacaksa erisme yontemi kesinlikle findFragmentById olmali.
        // aksi taktirde typecast hatasi alirsin.
    }

    // best practices:
    private fun getNavControllerViaView() {
        val navHostFragmentView =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentView) as NavHostFragment
        val navControllerView = navHostFragmentView.navController
        // navHostFragmentView.navController kismindaki navController bir property'dir.
    }

    fun getNavControllerViaFragment2() {
        val navHostFragment = findViewById<View>(R.id.navHostFragment)
        val navController = navHostFragment.findNavController()
        // fragment'in kendisine eristigimiz icin sorunsuz navController'a erisebildik.
    }

    private fun getNavControllerViaView2() {
        val navHostFragmentView = findViewById<View>(R.id.navHostFragmentView)
        val navController = navHostFragmentView.findNavController()
        // navHostFragmentView.findNavController() kismindaki findNavController() bir extension function'dir.
        // 1. yoldakinden farkli olarak bu bir property degil. eger bunu kullanacak olursan
        // view ile calisirken findViewById kullanirsan onCreate'de crash yersin. istersen
        // bu fonksiyonu onresume'da calistirarak crash'i engelleyebilirsin.
        // eger su sekilde
        //val navHostFragmentView = findViewById<View>(R.id.navHostFragmentView) as NavHostFragment
        //val navController = navHostFragmentView.navController
        // yapmaya calissaydik, onResume'da bile crash yerdik. yani cunku fragment'i view'e cast edemeyiz.
        // iki class'in birbirine cast edilebilmesi icin ortak atadan turemesi gerekir. fragment'in
        // uzerinde bir View class'i bulunmuyor.
        // cast etmeye calismayip, findNavController()'i View extension'u olarak kullaninca
        // sorunsuz calisiyor.
        // fragment'a casting yapilacaksa erisme yontemi kesinlikle findFragmentById olmali.
        // aksi taktirde typecast hatasi alirsin.
    }

    fun getNavControllerViaFragment3() {
        findNavController(R.id.navHostFragment)
        // sorunsuz calisiyor.
    }

    fun getNavControllerViaView3() {
        findNavController(R.id.navHostFragmentView)
        // bu da onCreate'de crash veriyor. ama onResume'da calisiyor.
        // aslinda bu bir lifecycle problemi. fragment henuz ayaga kaldirilmadigi icin onCreate'de
        // navController'a sahip olamiyoruz. ama ayaga kaldirma islemi vs onCreate islemleri bitince
        // onResume'da cagirinca calisiyor. onStart'da da calisir.
    }
}