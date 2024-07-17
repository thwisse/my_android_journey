package io.github.thwisse.kentsimgeleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.thwisse.kentsimgeleri.databinding.ActivityDetailsBinding

class ActivityDetails : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        ///////

        // 1- intent ile veri aktarimi

        //val selectedLandmak = intent.getSerializableExtra("landmark") as Landmark
        // intent ile getSerializableExtra kullanarak diger activityden veriyi aldik.
        // daha sonra casting islemi yaparak (as) verileri tekrardan Landmark sinifi elemanlarina donusturduk.
        // cunku bunu yapmayinca nesnelere ve propertylerine erisip islem yapamiyoruz.
        // artik selectedLandmark bir Landmark objesi oldu.

        ///////

        // 2- global (public) bir degisken ile veri aktarimi
        // ustteki intenti devre disi birakman gerek bunun icin.

        //val selectedLandmak = chosenLandmark
        // bu 2. yontemi uygularken asagidaki name, country, image icin nullable uyarisi veriyor.
        // hepsi icin !! koyuyorum. ancak bu islem kullanilacak olursa onlarin kontrolunun yapilmasi sart.

        ///////

        // 3- singleton
        // 1deki intenti devre disi birakman gerek bunun icin.
        val selectedLandmak = MySingleton.chosenLandmark2
        // yine alttakiler icin nullable uyarisi veriyor. !! ile gecistiriyorum.

        ///////


        binding.textViewLandmarkName.text = selectedLandmak!!.name
        binding.textViewCountry.text = selectedLandmak!!.country
        binding.imageView.setImageResource(selectedLandmak!!.image)

        // listView artik sorunsuz calisiyor.
        //TODO su yukaridaki 2. ve 3. yontemde listview ile calismiyor. sadece recyclerview ile calisiyor.

    }
}