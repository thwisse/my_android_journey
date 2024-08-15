package com.example.cannonball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.example.cannonball.databinding.ActivityTwoBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_two)

        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pop up menu olusturma
        // bir menu olusturuyorum. iki menu item ekleyip idlerini girdim. action_blabla seklinde
        // belirleniyormus genelde. activityde bir button olusturdum. bu butona basinca menu acilacak.

        binding.buttonOptions.setOnClickListener {
            //TODO iki tane PopupMenu sinifi var. biz androidx olani tercih edecegiz. neden bilmiyorum.
            val popupmenu = PopupMenu(this@ActivityTwo, binding.buttonOptions)
            popupmenu.menuInflater.inflate(R.menu.menu_pop_up, popupmenu.menu)

            // menudeki itemlara tiklayinca islem yaptirma
            popupmenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.action_delete -> {
                        Toast.makeText(this@ActivityTwo, "Deleted", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_edit -> {
                        Toast.makeText(this@ActivityTwo, "Edited", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                    // when icindeki conditionlarin ve elsein sonuna true ve false degerlerini ekledik
                    // bunlar menu icin bir gereklilik. tiklandiginda true, tiklanmazsa false donecek
                    // menuitemclicklistener'in bunu ogrenmeye ihtiyaci var.
                }
            }
            // popup'i gosterelim.
            popupmenu.show()
        }

        binding.buttonGoToActivityThree.setOnClickListener {
            startActivity(Intent(this@ActivityTwo, ActivityThree::class.java))
        }








    }
}