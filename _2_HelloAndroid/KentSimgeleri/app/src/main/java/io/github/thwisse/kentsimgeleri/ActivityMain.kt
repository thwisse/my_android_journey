package io.github.thwisse.kentsimgeleri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.thwisse.kentsimgeleri.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var listLandmark: ArrayList<Landmark> // listview icin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///////////////////////////////////
        // listview ile listeleme

        // Landmark classimi olusturdum. buradan nesneleri turetelim.
        // image parametresinin type'i int. cunku image'lar res icinde int bir degerle tutulurlar.

        val pisa = Landmark("Pisa", "Italy", R.drawable.image_pisa)
        val colosseum = Landmark("Colosseum", "Italy", R.drawable.image_colosseum)
        val eiffel = Landmark("Eiffel", "France", R.drawable.image_eiffel)
        val londonBridge = Landmark("London Bridge", "United Kingdom", R.drawable.image_londonbridge)

        listLandmark = ArrayList<Landmark>()

        listLandmark.add(pisa); listLandmark.add(colosseum)
        listLandmark.add(eiffel); listLandmark.add(londonBridge)

        // dizimiz ve verilerimiz hazir. simdi bu verileri tasarimda siralayacagiz.
        // bi verimli olan bi de verimsiz olan siralama sekli var.
        // verimsiz olan: listView. once onu yapalim.

        // listview icin adapter:
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listLandmark)
        // 2. arguman olarak android'in bizim icin hazir olarak sundugu list layout'unu kullandik.
        //binding.listView.adapter = adapter

        // ancak bu sekilde yaptigimda listedeki elemanlarin konumunu listeliyor tasarimda. (img.png)
        // bunun yerine ben listedeki nesne elemanlarinin name degerinin gozukmesini istiyorum.
        // bunun icin mapping islemini kullanacagiz. mapping bir veriyi baska bir veriye benzetmeye
        // ya da cevirmeye olanak taniyan bir islem.

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            listLandmark.map { landmark -> landmark.name })
        binding.listView.adapter = adapter

        // artik isimleri gozukuyor. simdi bunlara tiklaninca detaylarinin gozukme islemini yapalim.

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@ActivityMain, ActivityDetails::class.java)

            //intent.putExtra("name", listLandmark.get(position).name)
            //intent.putExtra("country", listLandmark.get(position).country)
            //intent.putExtra("image", listLandmark.get(position).image)
            // bu sekilde 3 property'i de diger activity'e iletebiliriz. ancak cok fazla verimiz ya da
            // propertymiz olsaydi bu ugrastirici olurdu. bunun yerine yani listLandmark.get(position).property
            // yazmak yerine direkt olusturdugumuz listeyi iletelim. bunun icin olusturdugumuz landmark sinifini
            // serializable hale getiriyoruz. serializable listeyi veriye cevirip yollamamizi saglayacak.
            // bu kadar. artik listeyi iletelim.

            intent.putExtra("landmark", listLandmark.get(position))

            // artik activiydetails'a gidiyoruz. bu veriyi serilestirilebilir bir veri olarak alacagiz.
            startActivity(intent)
        }

        binding.buttonGoToActivityRV.setOnClickListener {
            startActivity(Intent(this@ActivityMain, ActivityRecyclerView::class.java))
        }

        // listview sorunsuz calisiyor. ancak dedigimiz gibi bu listView verimli degil.
        // cunku classtan uretilen nesne sayisi arttikca uygulamanin verimi dusecek. 1000 tane nesne
        // uretilmis olsa uygulama arka planda 1000 tane xml olusturacak her bir nesne icin.
        // bu da uygulamanin verimini dusurecek. cok verili bir sey kullanmamiz gerektiginde
        // verimli olan recycler view yapisini kullanmaliyiz. simdi bide onu yapalim.

        //////////////////////////
        // recyclerview ile listeleme
        // bununla ilgili notlar ActivityRecyclerView icinde olacak.
    }
}