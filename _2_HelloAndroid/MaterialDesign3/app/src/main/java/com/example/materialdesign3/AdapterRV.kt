package com.example.materialdesign3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// context parametresi ekledim cunku adapter icerisinde androide ozgu bir yapiyi calistirmak isteyebilirim
// bunu istedigimde benden context isterse diye bunu buraya parametre olarak ekledim. mecburi degil ama
// adapterlarda genelde bu yapiliyormus. zaten biz de bu adapterda kullanacagiz.
// import android.content.Context otomatik yapildi.
//
// parametreleri private yapiyorum cunku sadece bu class ile kullanilabilir olsun istiyorum.
// parametreleri val yapiyorum cunku degerleri degistirmek istemiyorum. sadece iletecegim.
//
// gelenList parametresini List tanimladim cunku bu parametreye List de girilebilir ArrayList de.
// List ile ArrayList kalitimlidir. eger ArrayList tanimlarsam List kullanamam ama List tanimlarsam
// ikisini de kullanabilirim.

class AdapterRV(private val mContext: Context, private val gelenList: List<Heros>)
    : RecyclerView.Adapter<AdapterRV.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        // Holder classi ile card view'deki tasarim nesnelerini tutacagim.
        // view parametresi ile card view'deki tasarim nesnelerin erisecegim.
        // Holder classina RecyclerView.ViewHolder(view) sinifini implemente ettim.

        var cardView: CardView
        var textView: TextView
        var imageView: ImageView

        // view nesnesi ile design_card icindeki tasarim nesnelerinin idlerine erisip o idler ile
        // burada tanimladigim degiskenlerle eslestirecegim
        init {
            cardView = view.findViewById(R.id.cardViewInCard)
            textView = view.findViewById(R.id.textViewInCard)
            imageView = view.findViewById(R.id.imageViewInCard)
        }
    }
    // inner class: class icinde class. baska bir yerde de tanimlanabilirdi. ancak adapter ile ilgili
    // oldugu icin burada tanimlayip goze carpmasini istedik. kafa karistirmamasi icin.
    // : RecyclerView.Adapter<AdapterRV.Holder>()
    // islemi ile de bu sinifi adapter'a bagladik. AdapterRV Holder sinifiyla adapter ozelligi alan bir sinif oldu.
    // AdapterRV.Holder dedik cunku bu sinifin AdapterRV icinde bir sinif oldugunu soyledik.
    //
    // tasarim nesnelerine eristik ancak henuz tasarima erismedik.
    // AdapterRV uzerinde error olustu. cunku adapter ozelligini ekledikten sonra kullanmamiz gereken
    // fonksiyonlar var. onlari ekleyelim. uyariya tiklayip implement members diyorum.
    // otomatik ekledi. simdi onlari ayarlayalim:

    // holder ile tasarimi belirtecegiz:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // tasarimi design degiskenine aktaracagiz.
        val design = LayoutInflater.from(mContext).inflate(R.layout.design_card, parent, false)
        // parent parametresi ile design_card gorsel tasarimini, parent yazilimsal tasarimina bagliyorum.
        // false ile de baska bir tasarimi buraya baglamayacagimi belirttim.

        return Holder(design)
    }

    // card tasariminin kac tane olacagini belirtecegiz:
    override fun getItemCount(): Int {
        return gelenList.size
        // listeden kac nesne uretilmisse o kadar card yaratilacak.
    }

    // asil adapter islemi budur. Heros classindan olusturacagim nesnelerin ozelliklerini
    // tasarimda hangi alana aktaracagimi burada ayarliyorum:
    override fun onBindViewHolder(holder: Holder, position: Int) {
        // position indextir. veri kumesindeki veriler uzerinde indexleri alir
        // bu fonskiyor bir dongu gibidir. sirayla indexlere gore calisir.
        val hero = gelenList[position]

        holder.textView.text = hero.heroName
        holder.cardView.setOnClickListener {
            // card'a tiklandiginda ne yapacagini belirleyebiliriz.
            Toast.makeText(mContext, "Sectiginiz kahraman: ${hero.heroName}", Toast.LENGTH_SHORT).show()
        }

        // 3 noktaya tiklayinca popup acilma islemi:

        holder.imageView.setOnClickListener {
            val popup = PopupMenu(mContext, holder.imageView)

            popup.menuInflater.inflate(R.menu.menu_popup_for_cardview, popup.menu)

            popup.show()

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_review -> {
                        Toast.makeText(mContext, "Incelendi: ${hero.heroName}", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_edit -> {
                        Toast.makeText(mContext, "Duzenlendi: ${hero.heroName}", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_delete -> {
                        Toast.makeText(mContext, "Silindi: ${hero.heroName}", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

        }

    }

}

