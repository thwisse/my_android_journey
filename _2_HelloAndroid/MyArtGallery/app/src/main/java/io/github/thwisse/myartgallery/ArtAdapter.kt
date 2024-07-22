package io.github.thwisse.myartgallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.thwisse.myartgallery.databinding.RecyclerRowBinding

// atil hocanin ilk kursunda 24. bolumde "veri cekmek" isimli videoyu izleyerek bunu tekrar
// edebilirsin. su adapteri not alarak kodlamaya calissam hem bir gun surer hem de nottan daha
// sonra hicbir sey anlamam.

class ArtAdapter (val artList: ArrayList<Art>): RecyclerView.Adapter<ArtAdapter.ArtHolder>() {

    class ArtHolder (val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.textViewName.text = artList.get(position).artName
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActivityArt::class.java)

            intent.putExtra("info", "old")
            // database'deki bir veriyi gostereceksem onun id'sini de yollamam lazim.
            intent.putExtra("id", artList.get(position).id)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}