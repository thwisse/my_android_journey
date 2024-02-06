package io.github.thwisse.kentsimgeleri

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
    }

    // kac item ile islem yapilacaksa burada belirliyoruz.
    override fun getItemCount(): Int {
        return listLandmark.size
    }
}