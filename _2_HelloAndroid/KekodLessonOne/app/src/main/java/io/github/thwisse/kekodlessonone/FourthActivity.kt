package io.github.thwisse.kekodlessonone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth_constraint_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /////////////////////////
        // views

        // view -> bildigin views iste.
        // view groups -> icine baska view'lari alabilen yapilar.
        // view grouplar icin temelde 3 layout turu vardir: frame, linear, constraint.

        // tum view komponentler view class'ini extend eder.

        // palette bulunan componentler disinda baskalarinin yaptigi custom componentleri ya da
        // kendi yapacagin custom componentleri de ui'da kullanabileceksin.

        // not1: xml icin de kt uzantili dosyalar icin de encoding'in dogru ayarlandigindan emin ol
        // turkce karakterlerde sorun olursa buradan duzenleyeceksin.

        // wrap = sarmak.
        // wrap content = icerige gore boyutunu ayarla
        // match parent = icinde bulundugun ebeveynin sinirlarina kadar genisle

        // bir componentin width ve height degerleri olmasi zorunludur. baska hicbir degeri olmasa
        // bile bunlar olmak zorundadir.

        // running devices > toggle layout inspector > snapshot export
        // view'lari 3d bir sekilde inceleyebilirsin.
        // ornegin cok fazla komponenti ust uste kullanip da ayni pikseli cok kez farkli amaclarla
        // cizdigin icin ekranin acilis performansi etkilenebilir. bunu bu 3d mode ile gorebilir,
        // hangi bolgelere fazladan yuklenme var inceleyebilirsin.

        /////////////////////////////
        // frame layout

        // componentler ust uste biner.
        if (false) { R.layout.activity_fourth_frame_layout }
        // en az performans gerektiren, en lightweight viewgroup'tur.
        // tek bir componenti ekranda gostermek ve baska hicbir islem yapmamak gibi bir istegin
        // varsa framelayout kullanabilirsin. digerlerinden daha performansli olacaktir.

        /////////////////////////////
        // linear layout

        if (false) { R.layout.activity_fourth_linear_layout }
        // orientation ozelligi ile componentleri vertical(dikey)de ya da horizontalda
        // siralayabiliriz.

        ////////////////////////////
        // component attributes

        // gravity: herhangi bir componente (layout'un kendisine, icindeki componentlere vs)
        // bu ozelligi vererek hizasini belirleyebilirsin. layout'a verdiginde icindeki komponentlerin
        // hepsini layout'a gore ortaliyor. ornegin textview'e verdiginde yazi komponentin
        // kendi sinirlarina gore hizalaniyor. gravity'e "center|end" gibi | ile farkli hizlamalar
        // da yaptirabiliyorsun. start ve end kullanarak ornegin basa ya da sona ortalatabildim.
        // ornegin yazilari arapcaya gore ayarlaman gerektiginde bunu kullanabilirmissin.

        // layout_gravity: componentin kendisini parent'ina gore hizalar.
        // ancak mesela bunu linear layoutta bir componentte kullanmak istersek istedigimizi yapamayabiliriz
        // cunku layoutun kendisinin orientation'i ile cakisabilir. bu yuzden gravity'i layout'un
        // kendisine vererek cozebiliriz.

        // padding: ornegin textview'e padding verince text'in sinirlarini textview componentin icinde
        // verdigin kadar kucultur. kenarlardan x kadar uzaklas anlamina geliyor.
        // paddingStart, paddingTop vs. ile de istedigin kenara gore ayarlama yapabiliyorsun.

        // layout_margin: padding gibi mesafe koymaya yarar. ancak bu sefer componentin kendisine uygulanir.
        // margin padding'in tersi gibi dusun. biri componentin icine digeri disina etki ediyor.

        // gravity -> surada dur,    padding ve margin -> suradan su kadar uzak dur
        // layout_gravity ve layout_margin -> komponentin kendisine uygulanir. parenta gore belirlenir.

        // alpha: seffafligini ayarlayabiliyorsun.

        // src ve background: arkaplan rengini ya da resmini eklemek icin kullanilabilir.
        // linear layoutta bu ikisini de bir komponentte kullanmak istediginde hangi altta ise o
        // ui'da gozukuyor.

        // style: componente belli bir style verebiliyoruz. yani bircok komponente hep ayni ozellikleri
        // vermek yerine values klasoru icinde style.xml adinda bir dosya olusturup onun icinde style'lar
        // olusturarak bu komponentlere direkt stylelari verebiliriz.
        // style dosyasinin icinde <style name=""> kismina style adi verebiliriz.
        // isimden sonra <style name="" parent=""> seklinde bir parent belirleyebiliriz, yani
        // classlardaki superclass subclass gibi. alt stylelar ust stylelarin ozelliklerini alir, ekstra
        // kendi ozellikleri de belirlenebilir falan. linear layoutta ornegini yaptim.
        // eger style'i vermene ragmen belirttigin style icinde olan bir ozelligin degerinden
        // farkli bir degeri verirsen de bunu kabul eder. styledaki gecersiz olur o component icin.

        // dp sp gibi degerlerimizi eger bircok komponentte stabil ve ayni anda degisebilir sekilde
        // ayarlamak istersek values icinde dimen.xml adinda bir dosya olusturup degerleri
        // oraya ekleyebiliriz.

        /////////////////////////////////
        // constraint layout

        // frame layout -> componentler ust uste biner. hizalama yapilmaz.
        // linear layout -> componentler belli bir orientation'a gore hizalanir.
        // constraint layout -> componentler parent'a ya da baska componentlere gore hizalanir.

        // componentlere verilmesi zorunlu olan width ve height degerleriyle birlikte, eger constraint
        // layout kullanilirsa verilmesi gereken zorunlu 2 deger daha olacaktir.
        if (false) { R.layout.activity_fourth_constraint_layout }
        // constraintTop, constraintBottom, constraintStart, constraintEnd. bu 4'unden birinin
        // tanimlanmis olmasi gerekir.
        //app:layout_constraintBottom_toBottomOf="parent"
        // bu, componentin bottom'ini parent'on bottom'ina bagla anlamina gelir.
        // iki componenti birbirine baglarsan, baglanan component gone oldugunda digerinin hizasi bozulur.
        // nested layouts: ic ice layoutlar. bir constraint layout icine baska bir constraint layout
        // koyup islem yapilabilir. ancak bu islem bu sekilde cok fazla yapilirsa, tasarimdaki piksel
        // basina cizdirme miktari arttigi icin performans kayiplari yasanacaktir. ancak ornegin bir
        // constraint layout'un icinde birden fazla ayni levelda constraint layout koymanin bir sakincasi
        // yoktur. yani ic ice olmadigi muddetce ayni levelda olanlarda sorun olmaz.

        // bu kisim da sona erdi. diger konu fifth activity'de islenecek.


    }
}