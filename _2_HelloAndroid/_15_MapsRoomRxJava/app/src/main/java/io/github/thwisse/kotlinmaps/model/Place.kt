package io.github.thwisse.kotlinmaps.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Place isimli bir model olusturuyoruz. bu sinif data class olabilir de olmayabilir de.
// genelde internetten veri cekecegimiz ve uzerinde degisiklik yapmayacagimiz zamanlarda
// data class kullaniriz. simdi class kullanacagiz.

// Entity ile db baslangicini yaptik. @Entity(tableName) ile tablo ismini verebiliriz. ancak
// bos birakirsak da class adi table name olacaktir. constructor icindeki parametreler de
// db'deki kolonlar olacaktir.

@Entity
class Place (

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double

    // bir de id vermemiz gerek. ancak bunu cons icinde yapmiyoruz. cunku place ile nesne
    // yaratmak istedigimde id de ister. eger boyle bir isle ugrasiyorsan cons icine koy
    // ama biz otomatik artmasini istiyoruz su an. bu yuzden {} icine yazacagiz.
) : Serializable {
    // modeli Serializable yaptim. cunku adapter'dan verileri yollayacagim.

    @PrimaryKey(autoGenerate = true)
    var id = 0
}