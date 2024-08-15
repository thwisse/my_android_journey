package com.example.cannonball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cannonball.databinding.ActivityThreeBinding

class ActivityThree : AppCompatActivity() {

    private lateinit var binding: ActivityThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_three)

        binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // klasik alertview olusturma

        binding.buttonKlasikAlertView.setOnClickListener {
            // AlertDialog secerken androidx olani tercih ediyorum. en guncel kutuphane bu.
            val alertdialog = AlertDialog.Builder(this@ActivityThree)

            alertdialog.setTitle("Baslik")
            alertdialog.setMessage("Mesaj")
            alertdialog.setIcon(R.drawable.icon_alert)
            alertdialog.setPositiveButton("Tamam") { dialogInterface, i ->
                Toast.makeText(this@ActivityThree, "Ok said", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setNegativeButton("Iptal et") { dialogInterface, i ->
                Toast.makeText(this@ActivityThree, "Canceled", Toast.LENGTH_SHORT).show()
            }
            alertdialog.create().show()
        }

        // ozel alertview olusturma
        // bu alertview uzerinde bir editText olacak ve icine veri girecegiz.
        // bunun icin bir tasarim xmli olusturacagim. bu xmli olusturacagim alertviewin icine aktaracagim
        // bu tasarimi yaptiktan sonra yukaridaki klasik alert view icin yazdigim kodlari copy paste yapiyorum

        binding.buttonOzelAlertView.setOnClickListener {
            // AlertDialog secerken androidx olani tercih ediyorum. en guncel kutuphane bu.
            val alertdialog = AlertDialog.Builder(this@ActivityThree)

            // ozel olusturugum xmli alertdialog'a aktariyorum:
            val design = layoutInflater.inflate(R.layout.layout_alert_view_ozel, null)

            val editTextForAlertVieW = design.findViewById(R.id.editTextForAlertView) as EditText
            // tasarimi setView ile alertDialog'a ekledim:
            alertdialog.setView(design)

            alertdialog.setTitle("Uyari")
            alertdialog.setMessage("Bu islem saglikli bir sekilde gerceklesmeyebilir. " +
                    "Eger belirtmek istediginiz bir sey var ise...")
            alertdialog.setIcon(R.drawable.icon_alert)
            alertdialog.setPositiveButton("Gonder") { dialogInterface, i ->

                // edittexte girilen degeri alip bir yerlere boyle iletebiliriz.
                val ediTextData = editTextForAlertVieW.text.toString()

                Toast.makeText(this@ActivityThree, "Sent. \"$ediTextData\"", Toast.LENGTH_SHORT).show()
            }
            alertdialog.setNegativeButton("Iptal et") { dialogInterface, i ->
                Toast.makeText(this@ActivityThree, "Canceled", Toast.LENGTH_SHORT).show()
            }
            alertdialog.create().show()
        }

        binding.buttonGoToActivityFour.setOnClickListener {
            startActivity(Intent(this@ActivityThree, ActivityFour::class.java))
        }


    }
}