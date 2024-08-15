package io.github.thwisse.kotlinmaps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import androidx.recyclerview.widget.RecyclerView
import io.github.thwisse.kotlinmaps.databinding.RecyclerRowBinding
import io.github.thwisse.kotlinmaps.model.Place
import io.github.thwisse.kotlinmaps.view.MapsActivity

class PlaceAdapter(val placeList: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {

    class PlaceHolder(val recyclerRowBinding: RecyclerRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.recyclerRowBinding.tvRvPlace.text = placeList.get(position).name // sadece isimleri goster
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            // burada veriyi yollayalim (serializable):
            intent.putExtra("selectedPlace", placeList.get(position))
            // eski bir seyi gostermeye calisiyorum diye belirtmek icin:
            intent.putExtra("intent_info", "old_place")
            // mainde bunu new'e cevirecegim.
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  placeList.size
    }


}