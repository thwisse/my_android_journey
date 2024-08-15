package io.github.thwisse.myartgallery

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import io.github.thwisse.myartgallery.databinding.ActivityArtBinding
import java.io.ByteArrayOutputStream

class ActivityArt : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    // bunun String olma sebebi, READ_EXTERNAL_STORAGE vs permission'larin birer string olmasi.

    private lateinit var database: SQLiteDatabase

    var selectedBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // istenecek olan iznin "normal, dangerous" gibi seviyeleri var ve bu seviyelere gore islem
        // yapilmasi gerekiyor. ornegin INTERNET izni istememiz gerekseydi direkt manifeste yazar
        // gecerdik cunku "normal" bir izin. ancak burada yaptigimiz storage permission ise
        // dangerous old icin asagidaki gibi kontrol yapmamiz gerekiyor.

        registerLauncher()

        // bu imageViewSelect_Clicked icinde yapmaya basladigimiz permission kontrol isi baya vakit
        // aldi ve cok sey yaptik ancak bitti. simdi  buttonSave_Clicked islemini yapacagiz. o islem
        // icin de once yuklenecek olan resmi bitmap'e cevirip yani kucultup oyle sqlite'a ekleyecegiz.
        // resimleri veritabanina kaydetmek pek verimli bir sey degildir yani cloud'a kaydedip
        // ordan cekmek vs daha iyidir. ancak uygulamanin dogasi geregi internete baglanmamasi vs
        // gerekiyorsa ya da cloud vs yapmak istemezsek veritabanina kaydetme islemini yapabiliriz.
        // sql veritabani icerisinde 1mb'i asan bir row olusturmak sikintilara yol aciyor. mumkunse
        // 500 kb ve olabildigince daha kucuk boyutlara dusurulmesi gerekiyor. simdi bitmap
        // donusturme islemini makeSmallerBitmap fonksiyonu icinde yapalim.
        // donusturme kodu bitti. simdi buttonSave_Clicked islemine gecelim.
        // 3 edittext ve gorseli toplamda 4 degiskende tutacak sekilde butonun kodunu da yazdik.
        // artik butona bastikca verileri veritabanina kaydedecek kodu yazacagiz.

        // veritabanina kaydetme islemleri de tamamlandi. artik art'larin main activity'de gosterilme
        // islemini yapacagiz. main'e donelim.

        ///////
        database = this.openOrCreateDatabase("MyArtGallery", MODE_PRIVATE, null) // initialize

        // intent old new kontrolu:
        val intent = intent
        val info = intent.getStringExtra("info")
        if (info.equals("new")) {
            // iclerini bosaltalim ve butonu aktif hale getirelim
            binding.editTextArtName.setText("")
            binding.editTextArtistName.setText("")
            binding.editTextYear.setText("")
            binding.imageViewSelect.setImageResource(R.drawable.image_select)
            binding.buttonSave.visibility = View.VISIBLE

        } else if (info.equals("old")) {
            // bir eseri sergileyecek gibi hepsini dolduralim ve butonu inaktif edelim

            val selectedId = intent.getIntExtra("id", 0)
            val cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?",
                arrayOf(selectedId.toString()))
            // cursorda selectedArgs kismini hep null yazip geciyorduk ancak simdi bir arg istedi ve
            // onu da string dizisi olarak istedi. id'yi stringe donusturup array icinde verdik.

            val artNameIx = cursor.getColumnIndex("artName")
            val artistNameIx = cursor.getColumnIndex("artistName")
            val yearIx = cursor.getColumnIndex("year")
            val imageIx = cursor.getColumnIndex("image")

            while (cursor.moveToNext()) {
                binding.editTextArtName.setText(cursor.getString(artNameIx))
                binding.editTextArtistName.setText(cursor.getString(artistNameIx))
                binding.editTextYear.setText(cursor.getString(yearIx))
                //binding.imageViewSelect.setImageResource(cursor.getBlob(imageIx))
                // yapamiyorum. getBlob bir byteDizisi istedigi icin onu olusturmaliyim.
                val byteArray = cursor.getBlob(imageIx)
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                binding.imageViewSelect.setImageBitmap(bitmap)
            }
            cursor.close()

            binding.buttonSave.visibility = View.INVISIBLE

        }
    }

    private fun makeSmallerBitmap (image: Bitmap, maximumSize: Int) : Bitmap {
        //return Bitmap.createScaledBitmap(image, 500, 300, true)
        // eger eklenecek olan gorsellerin yatayligi ve dikeyligi degismez bi sey olsa idi
        // yukaridaki gibi bir kod ile hepsini standart bir boyuta kucultebilirdik. ancak biz
        // hem yatay hem dikey gorseller yukleyecegimiz icin ikisi icin de ayri bir kucultme
        // algoritmasi yazmamiz gerekir.

        // gercek width (genislik) ve height (yukseklik) degerlerini alalim
        var width = image.width
        var height = image.height

        // bir gorseli w/h yaptiginda 1 den buyuk cikarsa o gorsel yataydir. genisligi buyukse yataydir.
        // ancak gorseli w/h yaptiginda 1 den kucuk cikarsa o gorsel dikeydir. genisligi kucukse
        // yani yuksekligi buyukse o gorsel dikeydir. eger 1 e esit cikarsa da karedir iste.
        val bitmapRatio: Double = width.toDouble() / height.toDouble()
        // maximumSize degerini kac belirlediysem buyuk olan kenari o degere esitleyecek kadar bolecegim.
        // diger kenari da ayni oranda bolecegim.
        if (bitmapRatio > 1) {
            // landscape (yatay)
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()

        } else {
            // portrait (dikey) and square (kare)
            height = maximumSize
            val scaledWidth = height / bitmapRatio
            width = scaledWidth.toInt()
        }
        // bu fonksiyonu istedigin projede kullanabilirsin.
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    fun buttonSave_Clicked(view: View) {

        val artName = binding.editTextArtName.text.toString()
        val artistName = binding.editTextArtistName.text.toString()
        val year = binding.editTextYear.text.toString()

        if (selectedBitmap != null) {
            val smallBitmap = makeSmallerBitmap(selectedBitmap!!, 300)
            // bitmap'i ya da gorselleri direkt veritabanina kaydetmek yerine byte'a cevirip
            // kaydetmek daha mantikli. simdi bunu yapalim.
            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            val byteArray = outputStream.toByteArray()

            // verileri veritabanina kaydetme islemi:

            try {
                //val database = this.openOrCreateDatabase("MyArtGallery", MODE_PRIVATE, null)
                // database'i birden fazla yerde kullanacagim icin en yukarida tanimladim ve oncreate
                // icinde initialize ettim.
                database.execSQL("CREATE TABLE IF NOT EXISTS arts " +
                        "(id INTEGER PRIMARY KEY, artName VARCHAR, artistName VARCHAR, year VARCHAR, image BLOB)")
                // image'lar blob ile tutulacak.

                val sqlString = "INSERT INTO arts (artName, artistName, year, image) VALUES (?, ?, ?, ?)"
                // degiskenleri bu soru isaretlerine baglayacagiz.
                val statement = database.compileStatement(sqlString)
                // index 0dan baslamadi fark ettiysen.
                statement.bindString(1, artName)
                statement.bindString(2, artistName)
                statement.bindString(3, year)
                statement.bindBlob(4, byteArray)
                statement.execute()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            val intent = Intent(this@ActivityArt, ActivityMain::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // bu flag, bundan once acilmis ne kadar activity varsa hepsini kapat demek.
            // verileri kaydedip, activityleri kapatip, mainactivity'e geri donuyoruz.
            startActivity(intent)
        }
    }

    fun imageViewSelect_Clicked(view: View) {
        // api 33 ve sonrasi icin ekstra bir izin istenmesi gerekiyor. bu sebeple if icinde 33 oncesi
        // ve sonrasi icin neler yapilacagiyla ilgili kontrol yapacagiz. bu if kontrolunu daha sonra
        // yaptigim icin notlar else kisminda kaldi. once oraya bakarsin tekrar yapacaksan. 33 ve
        // sonrasi icin sadece permission adi degisti kodlarda baska bi degisiklik yok. bi de ekstra
        // olarak manifeste uses permission ekledim.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            // Android 33+ -> READ_MEDIA_IMAGES (hatta video erisimi de lazimsa READ_MEDIA_VIDEO)

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED) {
                // izin verilmediyse (DENIED)
                // rationale
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_MEDIA_IMAGES)) {
                    Snackbar.make(view, "Permission needed for gallery",
                        Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", View.OnClickListener {
                        //request permission
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }).show()

                } else {
                    // false donerse sadece izin istenir.
                    // request permission
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            } else {
                // izin verildiyse (GRANTED)
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                // intent
                activityResultLauncher.launch(intentToGallery)
            }

        } else {

            // Android 33- -> READ_EXTERNAL_STORAGE

            // daha once galeri izni verilmis mi onu kontrol edecegiz.
            // api 19dan onceki telefonlarda cokmeye sebep olmamasi icin contextcompat kullanacagiz.
            // asagidaki Manifest -> android Manifest
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                // izin verilmediyse (DENIED)
                // izin verilmediginde ve tekrar izne ihtiyac duyuldugunda bunu kullaniciya bir snackbar
                // ile vs tekrar iletip "izin vermek lazim" anlaminda uyarida bulunmaya "rationale" deniyor.
                // hem tikliyosun hem izin vermiyosun. bu islem icin izin lazim. anlaminda kullaniciyi darliyoruz :d
                // bu otomatik olan bir sey. yapmak zorunlu degil su an ancak ileride zorunlu olabilirmis.

                // rationale
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // bu true donerse rationale show edilecek demektir. ancak show etmek yeterli degil.
                    // artindan izin tekrar istenmelidir.
                    Snackbar.make(view, "Permission needed for gallery",
                        Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", View.OnClickListener {
                        //request permission
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

                    }).show()

                } else {
                    // false donerse sadece izin istenir.
                    // dedigim gibi bunu ayarlayan androidin kendisidir. ne ne zaman gosterilecek o ayarlar.
                    // request permission
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

                }
            } else {
                // izin verildiyse (GRANTED)
                // intent kullanacagiz. ancak intent ile action (islem) yaptiracagiz. hep kullandigimiz
                // gibi degil bu sefer farkli kullanacagiz.
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                // galeriye gidip oradan bir action pick yapacagiz dedim.
                // 2. argumanda bir uri istedi. yani url'nin bir ust sinifi gibi. bir baglanti istedi.

                // classin en basinda ActivityResultLauncher sinifindan 2 yeni degisken tanimladim.
                // android icinde bir yere gidip bir seyler yapip oradan veri getirmek icin kullaniyoruz.
                // bunlari oncreate icinde once initialize (register) etmeliyiz. kullanacagimiz zaman ise
                // "request permission" ve "intent" yazdigim yerlerde kullanacagiz. initialize islemi
                // icin bir fonk olusturuyoruz. bunu her zaman baska projelerde de vs kullanabiliriz.
                // activityresultlauncher olmadan intent yapamayiz.
                // intent
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }

    private fun registerLauncher() {

        // launcher 1 (for intents)
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        // parantez icine sadece StartActivityForResult yazdim, oncesini kendi ekledi.
        // activiteyi bir sonuc icin baslatiyor anlamina gelir.
        // 2. arguman callback olacak, yani geri donusunde ne verecegi. bunu 2. arguman olarak da
        // alabiliyoruz, kodun sonuna direkt {} ekleyerek de alabiliyoruz. ikisi de ayni isi yapar
        { result -> // it yerine result koyduk

            if (result.resultCode == RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    val imageData = intentFromResult.data
                    //binding.imageViewSelect.setImageURI(imageData)
                    // bu binding islemi ile galeriden cektigimiz resmi direkt gosterebilirdik
                    // ancak bu uygulamada bir kendi art gallerymizi olusturdugumuz icin
                    // galeriden yuklenecek olan resimlerin uri'leri bitmap'e
                    // cevirecegiz, resimleri kucultecegiz ve sqlite'a kaydedecegiz.
                    if(imageData != null) {
                        try {
                            // en yukarida bitmap degiskenimi olusturdum.
                            // iki farkli bitmape donusturme islemi var. bazi versiyonlarda biri, bazi
                            // versiyonlarda biri calisiyor. kullanicinin api'ini kontrol edip ona gore
                            // 2 islemden birini yapacagiz cihazinda.
                            if(Build.VERSION.SDK_INT >= 28) {
                                // 1 - ImageDecoder (sadece 28 ve ustunde calisiyor)
                                val source = ImageDecoder.createSource(
                                    this@ActivityArt.contentResolver, imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageViewSelect.setImageBitmap(selectedBitmap)
                            } else {
                                // 2 - getBitmap (deprecated) (tum surumlerde calisiyor)
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageData)
                                binding.imageViewSelect.setImageBitmap(selectedBitmap)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

        }

        // launcher 2 (for permissions)
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
        { result ->
            if (result) {
                // permission granted
                // intent
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)

            } else {
                // permission denied
                // permission daha ilk istenisinde denied edilirse burasi calisir.
                Toast.makeText(this@ActivityArt, "Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}