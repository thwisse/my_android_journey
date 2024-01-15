package com.example.materialdesign3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.materialdesign3.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    private lateinit var herosList: ArrayList<Heros>

    private lateinit var adapter: AdapterRV

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CardView
        // ornegin bir oyunun adi, resmi, fiyati ve satin al tusunu iceren bir card taslagi olusturursak,
        // bu card taslagiyla bircok oyunu tek ekranda cardlarla goruntuleyebiliriz.
        // 1. bir veri kumemiz var. bu veri kumesini adapter'a bagliyoruz.
        // 2. cardview ile satir tasarimini yani card taslagini tasarliyoruz.
        // 3. adapter verilerin cardview uzerinde neyin nereye gelecegini belirliyor.
        // 4. adapter belirlemeleri yaptiktan sonra bunu recylerview'e aktariyor.
        // 5. recylerview de bunlari ekranda listeliyor.
        //
        // adapter veri kumelerinin gorsel nesne uzerinde duzenli bir sekilde tutulmasini saglar.
        // cardview adapter icerisinde baglanir.
        //
        // ornek anlasilir olmasi acisindan linearlayout kullanacagiz. layout icinde design_card adinda
        // yeni bir resource file olusturduk. root elementi LinearLayout oldu.
        // bu yeni xmlimize bir cardView ekliyor ve idsini bu yapiyoruz. cardView icine de bir constraint
        // layout ekliyoruz. linear layout yuksekligini 50dp ayarliyorum. icine bir textview ekleyip id
        // veriyorum. sabitliyorum. ardindan bir icon tasarlayip onu da ekliyorum.
        // linear layoutun height degerini wrap content ayarladim ve cardview'in de contentPadding degerini
        // 15 dp ayarladim. constraint layout ile cardview arasindaki ic boslugu ayarladim.
        // bu sayede tasarim ortalanmis oldu. contentpadding ic bosluktur.
        // cardBackground rengini degistirdim. cardCornerRadius degerini 10 dp ayarladim, bu kenarlara
        // ovallik veriyor. cardElevation ise card'in arkasina derinlik efekti veriyor.
        // card ile linear layout arasinda dis bosluk olusturmak icin ise xmlde cardview icine
        // android:layout_margin="10dp" degerini yaziyorum.
        // simdi activitytwo'ya bir recyclerview ekliyorum. tasarim ayarlarini yapiyorum.
        // Heros adinda bir data class olusturdum. bu siniftan nesneler uretip bunlari bir arraylist
        // icine ekleyecegim ve o arraylisti de adapter ile cardview'de sergileyecegim.
        // simdi de bir Adapter classi olusturdum. icinde gereken islemleri yaptim, incelersin.
        //
        // simdi burada en tepede arraylistimi ve adapterimi tanimliyorum.
        //
        // simdi rv icin islemleri yapiyorum:
        // tasarim uzerinde duzgun bir sekilde oturmasini sagliyoruz:
        binding.recyclerView.setHasFixedSize(true)

        // ekstra bilgi olarak: rv yapisinda kaydirma islevi de var. asagi kaydirmayi otomatik yapiyor.
        // yani eger cardlar cok fazlaysa sorun yok. slider eklememize gerek yok.

        // 3 farkli rv gorunum var. rv'lerin gorunumlerini belirleyebiliriz.
        // 1.
        // lineer olarak gorunmesini istiyoruz:
        binding.recyclerView.layoutManager = LinearLayoutManager(this@ActivityTwo)
        // 2.
        // yine lineer gorunum ancak bir satirda birden fazla card gosterebiliyoruz.
        // her bir satirda kac tane card olacagini seciyoruz: (spanCount = aralik sayisi)
        // VERTICAL: dikeyde kaydir.
        //binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // 3.
        // HORIZONTAL: yatayda kaydir.
        //binding.recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        // simdi arraylistimiz icin nesneler tanimlayalim
        val h1 = Heros(1, "Soldier Boy")
        val h2 = Heros(2, "Stormfront")
        val h3 = Heros(3, "Homelander")
        val h4 = Heros(4, "The Deep")
        val h5 = Heros(5, "Black Noir")
        val h6 = Heros(6, "Starlight")
        val h7 = Heros(7, "Translucent")
        val h8 = Heros(8, "A-Train")
        val h9 = Heros(9, "Queen Maeve")

        herosList = ArrayList<Heros>()

        herosList.add(h1); herosList.add(h2); herosList.add(h3); herosList.add(h4); herosList.add(h5)
        herosList.add(h6); herosList.add(h7); herosList.add(h8); herosList.add(h9)

        // adapter'i olusturduk
        adapter = AdapterRV(this@ActivityTwo, herosList)

        // adapteri aktardik
        binding.recyclerView.adapter = adapter

        ///////////////////////
        // simdi ekstra olarak card'daki bir gorsel nesneye tiklayinca popup menu acilma islemini yapalim.
        // card'a ekledigim icon'a tiklayinca acilmasini istiyorum.
        // bir menu olusturuyorum. menu itemlari girdim. daha sonra AdapterRV classimda
        // onBindViewHolder isimli fonskiyonda bu tasarim nesnesine tiklaninca yasanacak olan seyleri
        // kodluyorum. kalan notlari oraya aliyorum.
    }
}