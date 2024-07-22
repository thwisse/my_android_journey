package io.github.thwisse.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            // veri tabani olusturma:

            val myDatabase = openOrCreateDatabase("Musicians", MODE_PRIVATE, null)
            //TODO hoca 2. arguman olarak Context.MODE_PRIVATE yazdi. yani otomatik oyle geldi. bende oyle olmadi.

            // tablo olusturma (CREATE TABLE IF NOT EXISTS):

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                    "musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)")
            // execSQL = execute SQL = write a sql command
            // id ekledik ve duzenli olarak artis olmasini sagladik.

            // veri ekleme (INSERT INTO):

            // id kismini yazmayacagiz.
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Hayko', 50)")

            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Sebo', 45)")
            // ustteki hayko verisini ekledigim kodu iptal edip bunu calistirdim. o veri silinmedi ve
            // id: 2 olarak sebo verisi eklendi.

            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Teo', 55)")

            // filtreleme (WHERE):

            // cursor'a WHERE ozelligi ekleriz.
            // WHERE name = 'Sebo' - yazdigimda adi Sebo olanlari getiriyor.
            // WHERE id = 3 - yazdigimda idsi 3 olanlari getiriyor.
            // WHERE name LIKE '%o' - yazdigimda sonu o ile bitenleri getiriyor.
            // WHERE name LIKE 'T%' yazdigimda T ile baslayanlari getiriyor.

            // veri guncelleme (UPDATE):

            //myDatabase.execSQL("UPDATE musicians SET age = 60 WHERE name = 'Teo'")
            //myDatabase.execSQL("UPDATE musicians SET name = 'Goddess' WHERE id = '2'")

            // veri silme (DELETE):

            //myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Teo'")

            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Teo', 55)")
            // teo'yu tekrar ekledigimde tekrar id'si 3 olarak veritabanina ekleniyor.

            // veri cekme (SELECT):

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)
            // rawQuery ile sorgu yapilir. * hepsi demek. selectionArgs ile filtreleme yapilir.

            // id, name ve age degerlerinin hangi indexlerde kayitli oldugunu ogrenme:
            val idIx = cursor.getColumnIndex("id")
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            // logcat'e verileri yazdirma:
            while (cursor.moveToNext()) {
                println("ID: " + cursor.getInt(idIx)) // id -> integer primary key -> int
                println("Name: " + cursor.getString(nameIx)) // n-ame -> varchar -> string
                println("Age: " + cursor.getInt(ageIx))
            }
            ///ID: 1
            ///Name: Hayko
            ///Age: 50
            // ilk yazdirmada bu ciktiyi aldik. artik bu veriler uygulama icinde veritabanina kaydedildi.
            // eger yukaridaki insert kodunu silip tekrar calistirsak bile veriler okunacak.

            cursor.close() // imleci kapatarak daha verimli bir kod insa ederiz.


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}