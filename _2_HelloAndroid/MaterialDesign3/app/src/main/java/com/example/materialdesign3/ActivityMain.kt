package com.example.materialdesign3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.materialdesign3.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////////////////////
        // material design 3
        // md3 hakkinda yeni seyler ogrendim. figma kullanarak uygulama icin theme, sheme, color vs.
        // tasarimlari yapmayi ogrenecegim.

        ///////////////////////
        // floating action button
        // fab, ekran asagi kayarken sabit bir sekilde ekranda kalan butondur.
        // icine koydugumuz iconun rengini xmlde fab icinde
        //app:tint="@color/white"
        // ile degistirebiliriz.

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show()
        }

        //////////////////////////
        // text input layout
        // edittext'e verdigimiz hint animasyonlu bir sekilde hareket eder ve yukariya cikar.
        // eskiden bulundugu yere de girdi girilmis olur.
        // icinde default bir edittext ile gelir. edittext olarak bunu kullanacagiz.

        binding.buttonSave.setOnClickListener {

            // girdi kontrolleri

            // trim ile whitespaceleri temizleme:
            val name = binding.editTextName.text.toString().trim()
            val surname = binding.editTextSurname.text.toString().trim()

            // inputType
            // numara kismina basinca phone klavyesi acilmasi icin design'da inputType'i ayarladim.
            val phonenumber = binding.editTextPhoneNumber.text.toString().trim()

            // bos birakma kontrolu
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Isim bos birakilamaz.", Toast.LENGTH_SHORT).show()
                // eger bos birakmissa buton islevi devam etmesin burda sonlansin istiyoruz.
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(surname)) {
                Toast.makeText(this, "Soyisim bos birakilamaz.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phonenumber)) {
                Toast.makeText(this, "Telefon numarasi bos birakilamaz.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // stringe boyut kisitlamasi koyma
            if (phonenumber.length != 11) {
                Toast.makeText(this, "Telefon numarasi 11 haneli olmalidir.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "$name $surname - $phonenumber", Toast.LENGTH_SHORT).show()
        }

        //////////////////
        // toolbar
        // toolbar ekledim. baslik, altbaslik, logo gibi eklemeler yaptim (common attributes)
        //
        // toolbar'a options menu ekleme:
        // menuyu olusturup menu itemlari girdim. menu itemlar icin showAsAction degerlerini girdim.
        // always hep gorunur, never hic gorunmez, ifroom yer varsa gorunur yoksa gorunmez,
        // collapseActionView tiklayinca bise yapiyomus ama tam anlamadim ilerde kullanacakmisiz,
        //TODO withText de calismiyormus bir sebepten dolayi neden bilmiyorum.
        //
        // simdilik ilk ucunu kullandim.

        // menu ile toolbar baglantisini yaptik
        setSupportActionBar(binding.toolbar)
        // bu islemden sonra onCreateOptionsMenu ve onOptionsItemSelected fonskiyonlari ile toolbarda
        // menu goruntuleme islemini bitirdik.
        //
        // toolbara arama islemi yapan item koymak:
        // arama itemi icin bir icon olusturdum. menude yeni bir menu item olusturdum. id, isim, icon girdim.
        // showAsAction icin always ve collapseActionView ozelliklerini sectim. collapseAV, butona basildigi
        // anda toolbar uzerinde bir edittext acilmasini ve girdi alinmasini saglayacak. menu xmlinde search
        // itemine app:actionViewClass="androidx.appcompat.widget.SearchView" ozelligini ekledik. arama islemi
        // icin bu sinifa ihtiyaci var. simdi ActiviyMain classima yeni bir interface'i implement ediyorum.
        // SearchView.OnQueryTextListener
        // bunu implemente ettikten sonra interface icindeki tum ozellikleri kullanmam gerekiyor.
        // hata ciktigi icin sag tiklayip implement members diyorum ve otomatik memberlari koda ekliyor.
        // simdi onCreateOptionsMenu icinde bu arama isleminin gerceklesmesi icin kodlar yazacagiz.
        // kodlar bitti. simdi arama islemi yapildikca calisacak olan kodlarin kontrolu icin ciktilari
        // logda goruntuleyecegiz. onQueryTextChange ve onQueryTextSubmit icine log kodlarini yaziyorum.
    }

    // menuyu toolbar'a ekleyelim:
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        // arama islemi icin kodlar:
        val searchitem = menu!!.findItem(R.id.action_search) // itemi'i id'si ile yakaladik
        // menu'den hemen sonra . karakterinde hata veriyordu. cunku bu fonksyionda menu parametresi
        // Menu? olarak tanimlanmis ve nullable oldugu icin hata veriyor. o sebeple menuden sonra !! ekliyorum.
        // veya !! eklemek yerine direkt Menu? deki ? isaretini silerek de bu hata giderilebilir.

        // item'i arama sinifindan bir nesneye donusturelim:
        val searchView = searchitem.actionView as SearchView
        // onCreate ana fonksiyonumuzdaki interface implementation islemindeki listener'a bunu bagladik:
        searchView.setOnQueryTextListener(this@ActivityMain)
        // arama islemini tetikledik.

        return true
    }

    // menudeki itemlara tiklanildiginda islem yaptiralim:
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_help -> {
                Toast.makeText(this@ActivityMain, "Help", Toast.LENGTH_SHORT).show()
                return true
                // function bizden boolean bir deger istedigi icin her birinde bir deger returnluyoruz.
            }
            R.id.action_settings -> {
                Toast.makeText(this@ActivityMain, "Settings", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_reset -> {
                Toast.makeText(this@ActivityMain, "Reset", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_logout -> {
                Toast.makeText(this@ActivityMain, "Log out", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
            // default return'u buraya koydum.
        }
    }

    // harf girdikce ya da sildikce anlik olarak arama yapan fonksiyon
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("onQueryTextChange:", newText.toString())
        //2024-01-14 23:11:41.287  5176-5176  onQueryTextChange:  com.example.materialdesign3  E  a
        //2024-01-14 23:11:41.453  5176-5176  onQueryTextChange:  com.example.materialdesign3  E  ar
        //2024-01-14 23:11:41.635  5176-5176  onQueryTextChange:  com.example.materialdesign3  E  ara
        //2024-01-14 23:11:41.673  5176-5176  onQueryTextChange:  com.example.materialdesign3  E  aram
        //2024-01-14 23:11:41.768  5176-5176  onQueryTextChange:  com.example.materialdesign3  E  arama
        // harf girdikce ariyor.

        return true
    }

    // bir kelime girip arama tusuna basinca calisan ve arama yapan fonksiyon
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("onQueryTextSubmit:", query.toString())
        //2024-01-14 23:11:52.232  5176-5176  onQueryTextSubmit:  com.example.materialdesign3  E  arama
        // arama tusuna basinca ne yaziyorsa onu ariyor.

        return true
    }
}