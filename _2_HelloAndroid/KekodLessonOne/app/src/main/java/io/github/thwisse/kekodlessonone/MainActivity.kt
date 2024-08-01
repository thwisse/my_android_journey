package io.github.thwisse.kekodlessonone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //android uygulamalari 4 ana bilesen uzerinde calisir (app components).
        //- activities
        //- services
        //- broadcast receivers
        //- content providers

        //ctrl+u ile superclasslara gidilebilir

        // tek activity ve multiple fragment ile uygulama tasarlamak daha verimli.
        // activity'ler ozunde contextten tureyen yapilar oldugu icin birden cok activity
        // ile calismak uygulamanin verimini dusurur.

        // bir activity'nin activity olabilmesi icin AppCompatActivity ya da o hiyerarsiden
        // super olan classlardan birini miras alarak yaratilmis olmasi gerekir. ornegin
        // Activity()'i miras olarak alsaydi bu activity yine bir activity idi.

        // manifest icindeki .MainActivity ifadesindeki . = io.github.thwisse.kekodlessonone
        // yani bu . ile namespace isaret edilir. "bu klasorun icindeki MainActivity dosyasi"
        // anlamina gelir.

        // onCreate fonksiyonuna ctrl+u caktigimda superclassta kullanildigi yere gidiyoruz.
        // bu bir java belgesi ve bu yuzden bu fonksiyon protected.

        // lifecycle fonksiyonlarini ve activity'de sikca kullanacagimiz fonksiyonlari yazdik.
        // profesyonel bir uygulamada hangi log taglerin kullanildigi onemlidir.
        // logcatte soldaki bir butonla loglari export edebiliriz.

        // simdi yaklasik 12 tane falan fonksiyona log girdik ve lifecycle'i inceleyecegiz.

        Log.e("MainActivity", "onCreate1")

        setContentView(R.layout.activity_main)

        Log.e("MainActivity", "onCreate2")

        // lifecycle

        ///////////////////////////////////

        // uygulama ilk calistiginda:

        ///onCreate1 (oncreate ilk calisti)
        ///onContentChanged (activity_main layout'u verildigi icin calisti)
        ///onCreate2 (ardindan oncreate fonksiyonuna devam edildi)
        ///onStart
        ///onResume

        ////////////////////////////////////

        // uygulamadayken:

        ///onUserInteraction
        ///onUserInteraction
        ///onUserInteraction
        // kullanici ui uzerinde bir islem yapmaya calisiyor diyor.
        // uygulamada herhangi bir yere tiklanmasi durumunda bu fonksiyon calisir.
        // android studio'da fare ekran uzerinde gezerse bunu da interaction sandigi icin
        // bunun logunu deactive ediyorum.

        ////////////////////////////////////////////

        // geri tusuna basinca:

        ///onBackPressed

        /////////////////////////////////////////

        // home butonuna basinca:

        ///onUserLeaveHint (home butonuna basildigini ifade eden fonksiyon)
        ///onPause
        ///onStop
        ///onSaveInstanceState

        // ARDINDAN
        // uygulamalar kismindan
        // ya da
        // uygulama ikonuna tekrar tiklayarak
        // geri uygulamaya donunce:

        ///onRestart
        ///onStart
        ///onResume

        /////////////////////////////

        // soldaki butona (recents) basinca:

        ///onPause
        ///onStop
        ///onSaveInstanceState

        // ARDINDAN
        // uygulamaya geri tiklayinca
        // ya da
        // geri tusuna basinca
        // ya da
        // soldaki butona tekrar basinca:

        ///onRestart
        ///onStart
        ///onResume

        // ARDINDAN
        // home tusuna basinca:

        // hicbir sey olmuyor. hatta onUserLeaveHint logu bile gelmiyor. cunku recents kismindayiz
        // ve uygulamaya tekrar girene kadar uygulama ile ilisigimiz kesildi.

        ///////////////////////////////////

        // uygulamayi kapatilinca:

        ///onPause
        ///onStop
        ///onSaveInstanceState
        ///onDestroy
        //TODO bazen onDestroy logu gozukmuyor uygulamayi kapatinca. bazen gozukuyor. nedenini anlamadim.

        /////////////////////////////////////

        // bu activity kapanip baska activity acilinca:

        ///onPause
        ///onStop
        // ardindan diger activity'nin lifecycle'i baslar.

        ///////////////////////////////////

        // popup dialog cikarsa:

        ///onPause

        // ARDINDAN
        // popup dialog kapatildiktan sonra:

        ///onResume

        //////////////////////////////////////

        // lifecycle problemi 1:
        // app process killing islemi yapilirsa:

        // telefonda cok fazla uygulama acilip da ram doldugunda garbage collector islemi icin
        // bazi uygulamalarin bazi gecmiste acilip da yakin zamanda acilma ihtimali az olan
        // activity'lerini android oldurur.

        ///onPause
        ///onStop

        // ARDINDAN
        // sen o activity'e tekrar ihtiyac duyup girdiginde:

        ///onCreate1
        ///onContentChanged
        ///onCreate2
        ///onStart
        ///onResume
        // activity tekrar onCreate'den baslayacagi icin xml de tekrar baslayacaktir. bu yuzden eger
        // activity'de veri alinmissa bu veriler temizlenmis bir sekilde tekrar temiz bir activity
        // gelecektir. bununla bas edebilmek icin onCreate ekraninda girilen verileri hafizada tutacak
        // bir mekanizma yapmalisin. yani garbage collector calissa bile onCreate ile tekrar baslayan
        // activity'ye o hafizada tuttugun verileri doldurmalisin.

        /////////////////////////////////////////

        // lifecycle problemi 2:
        // configuration change - yasanirsa:

        // telefon calmasi, telefonun dilinin degismesi, telefonun fontunda degisiklikler olmasi,
        // portrait'den landscape'e gecis ya da tam tersi olmasi gibi durumlarin yasanmasiyla
        // activity ayni yukaridaki app process killing gibi davranir.

        // olay gerceklesince:

        ///onPause
        ///onStop
        ///onSaveInstanceState
        ///onDestroy
        ///onCreate1
        ///onContentChanged
        ///onCreate2
        ///onStart
        ///onRestoreInstanceState
        ///onResume

        // bu noktada artik veri kaybi coktan gerceklesmis ve activity default state'ine donmus olur.
        // eger ui'daki datalar cok karmasik degilse bunun onune gecmek icin onSaveInstanceState ve
        // onRestoreInstanceState fonksiyonlarini kullanabiliriz. eger datalar cok karmasiksa bunlar
        // onerilmez, bunun yerine viewmodellar kullanarak (lifecycle aware components) bu sorunun
        // cozulmesi gerekir. basit datalar icin ya da viewmodel kullanilmiyorsa bu fonskiyonlari
        // kullanirsin. save ve restore ederek basit veriler kurtarilmis olur. configuration change
        // yasandiginda state'i degisebilecek tum komponentler icin bu islemler yapilmalidir.
        // gerektiginde bunun nasil yapildigini ogrenirsin. simdilik bunun detaylarini not almiyorum.
        // ders 2: activities - 1.30.00 civari

        // onCreate'de (savedInstanceState: Bundle?) yazmasinin sebebi, eger conf change yasanirsa diye
        // savedInstanceState icinde yaptigimiz islemlerin yaninda veri ekleme kismini onCreate icinde
        // de yapabiliriz. eger bunu yapmak istersek diye nullable bundle ile savedInstanceState verilir.
        // ancak bu savedInstanceState bundle veri atama islemini eger oncreate icinde yapacaksak bir
        // if check yapmamiz sart. ui'i ilgilendiren kullanici girisleri icin oncreate'de yapmamiz gerekebiliyor
        // bu islemi create icinde degil de onRestoreInstanceState methodu icinde yapmak en normalidir.
        // yasansa ici dolu olacak, yasanmazsa null donecek sorun olmayacak.
        // savedInstanceState fonskiyonu conf change yasanmadiysa bile uygulamayi kullanirken bircok yerde
        // zaten calisiyor. ancak onRestoreInstanceState fonskiyonu conf change yasandiysa
        // kesinlikle calisacaktir.

        // xml'de id'si olan componentler icin bu islemi android otomatik yapar. tabii data sabitse. db'den
        // ya da uzak sunucudan alinan datalar icin bu islem otomatik yapilamaz. bu fonklari kullanarak
        // kendin yapmalisin.

        ////////////////////////////////////////
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.e("MainActivity", "onContentChanged")
    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart")
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        //Log.e("MainActivity", "onUserInteraction")
    }


    // sadece bundle olani:
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("MainActivity", "onSaveInstanceState")
    }

    // sadece bundle olani:
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e("MainActivity", "onRestoreInstanceState")
        // bu fonskiyon calistiysa uygulama kesinlikle conf change'e ugramistir.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("MainActivity", "onBackPressed")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.e("MainActivity", "onUserLeaveHint")
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }



}