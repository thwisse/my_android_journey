package com.example.materialdesign3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterMovies (private val mContext: Context, private val listMovies: List<Movies>)
    : RecyclerView.Adapter<AdapterMovies.Holder>() {

    inner class Holder (view: View): RecyclerView.ViewHolder(view) {

        var imageViewPoster: ImageView
        var textViewTitle: TextView
        var textViewRating: TextView
        var buttonWatch: Button

        init {
            imageViewPoster = view.findViewById(R.id.imageViewPoster)
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewRating = view.findViewById(R.id.textViewRating)
            buttonWatch = view.findViewById(R.id.buttonWatch)
            // gorsel nesneler ile buradaki degiskenlerin isimleri ayni oldugu icin yazimi kolay oldu
        }

    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // : Holder yazdigi icin bizden holder sinifindan bir nesne dondurmemizi istiyor.
        val design = LayoutInflater.from(mContext).inflate(R.layout.desing_card_2nd, parent, false)

        return Holder(design)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // her card yani her nesne icin dongu gibi calisacak olan fonksiyon.

        val movie = listMovies[position]

        holder.textViewTitle.text = movie.movie_title
        holder.textViewRating.text = "${ movie.movie_rating }/10"

        // resimleri gostermek icin farkli bir yol kullanacagiz.
        // normalde direkt setImageResource icine resim path'ini yaziyorduk. ancak her card'da farkli resim
        // gorunmesini istedigimiz icin setImageResource'un farkli bir overload'unu kullanacagiz.
        holder.imageViewPoster.setImageResource(
            mContext.resources.getIdentifier(movie.movie_posterName, "drawable", mContext.packageName))

        holder.buttonWatch.setOnClickListener {
            Toast.makeText(mContext, "Yukleniyor...", Toast.LENGTH_SHORT).show()
        }
    }


}
