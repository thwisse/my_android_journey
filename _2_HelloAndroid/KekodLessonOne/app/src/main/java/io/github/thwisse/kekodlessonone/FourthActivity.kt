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
        setContentView(R.layout.activity_fourth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // views

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

        // frame layout = componentler ust uste biner.
        if (false) { R.layout.activity_fourth_frame_layout }
        // en az performans gerektiren, en lightweight viewgroup'tur.
        // tek bir componenti ekranda gostermek ve baska hicbir islem yapmamak gibi bir istegin
        // varsa framelayout kullanabilirsin. digerlerinden daha performansli olacaktir.
        
        // linear layout
        if (false) { R.layout.activity_fourth_linear_layout }
        // orientation ozelligi ile componentleri vertical(dikey)de ya da horizontalda
        // siralayabiliriz.

        // gravity: herhangi bir componente (layout'un kendisine, icindeki componentlere vs)
        // bu ozelligi vererek hizasini belirleyebilirsin. layout'a verdiginde icindeki komponentlerin
        // hepsini layout'a gore ortaliyor. ornegin textview'e verdiginde yazi komponentin
        // kendi sinirlarina gore hizalaniyor. gravity'e "center|end" gibi | ile farkli hizlamalar
        // da yaptirabiliyorsun. start ve end kullanarak ornegin basa ya da sona ortalatabildim.
        // ornegin yazilari arapcaya gore ayarlaman gerektiginde bunu kullanabilirmissin.

        // layout_gravity: componentin kendisini parent'ina gore hizalar.

        // padding: ornegin textview'e padding verince text'in sinirlarini textview componentin icinde
        // verdigin kadar kucultur. kenarlardan x kadar uzaklas anlamina geliyor.
        // paddingStart, paddingTop vs. ile de istedigin kenara gore ayarlama yapabiliyorsun.

        // layout_margin: padding gibi mesafe koymaya yarar. ancak bu sefer componentin kendisine uygulanir.
        // margin padding'in tersi gibi dusun. biri componentin icine digeri disina etki ediyor.

        // gravity -> surada dur,    padding ve margin -> suradan su kadar uzak dur

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

        // constraint



    }
}