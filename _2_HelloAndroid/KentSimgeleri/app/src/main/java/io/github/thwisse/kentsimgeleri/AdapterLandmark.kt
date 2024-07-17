package io.github.thwisse.kentsimgeleri

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.thwisse.kentsimgeleri.databinding.LayoutItemRvBinding

class AdapterLandmark (val listLandmark: ArrayList<Landmark>):
    RecyclerView.Adapter<AdapterLandmark.HolderLandmark>() {

    class HolderLandmark(val binding: LayoutItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    }

    // layout (layout_item_rv) ile baglama islemini burada yapacagiz
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderLandmark {
        val binding = LayoutItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // adapterda context icin bir parametre olusturmadik. daha onceki kursta oyle yapmistik.
        // ancak burada context icin parent.context kullaniyoruz. zaten onCreateViewHolder parametresi
        // olan parent, context islemi icin yeterli oluyor.
        // simdi de son olarak bizden istenen view'u veriyoruz:
        return HolderLandmark(binding)
    }

    // baglandiktan sonra ne yapilacaksa buraya yaziyoruz.
    override fun onBindViewHolder(holder: HolderLandmark, position: Int) {
        // activity main'de olusturdugumuz listeyi burada kullanabilmek icin, adapter'in primary
        // constructor'inda bir liste istedik. bu listeyi de activitymain'de vermeliyiz.
        holder.binding.rvTextView.text = listLandmark.get(position).name

        // itemlara tiklaninca ne olacagini yapalim:
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActivityDetails::class.java)
            // context icin yukaridaki onCreateViewHolder gibi parent parametresi olmadigi
            // icin burada bu sefer itemView'den cekecegiz contexti.

            // hangi item'a tiklandiysa onunla ilgili verilerin position sayesinde iletilerek
            // details activity'sinde goruntulenmesini sagliyorum:
            //intent.putExtra("landmark", listLandmark.get(position))

            /////////
            // 2
            // global (public) degisken ile veri aktarimi ornegi kismi (ActivityDetails'tan bak):
            // (ustteki intentin devre disi birakilmasi gerekir bunun icin)
            //chosenLandmark = listLandmark.get(position)

            /////////
            // 3
            // singleton
            // (41deki intentin devre disi birakilmasi gerekir bunun icin)
            MySingleton.chosenLandmark2 = listLandmark.get(position)


            // startActivity'i de direkt cagiramiyorum. cunku activity'de degiliz. onu da
            // itemview kullanarak cagiracagim:
            holder.itemView.context.startActivity(intent)
            // ve sorunsuz calisiyor.
        }
    }

    // kac item ile islem yapilacaksa burada belirliyoruz.
    override fun getItemCount(): Int {
        return listLandmark.size
    }
}