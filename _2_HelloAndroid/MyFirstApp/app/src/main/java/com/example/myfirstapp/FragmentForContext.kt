package com.example.myfirstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentForContext: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // su yukaridaki kodlari ve return komutunu otomatik olusturdu. ben sadece onCreateView
        // fonksiyonunu olustur dedim. geri kalanini o halletti. Context belirtme islemi:

        Toast.makeText(activity, "Hi Fragment!", Toast.LENGTH_SHORT).show()
        // fragment'lar genelde activitylere bagli olur. bu yuzden hangi activity'e bagli ise
        // context olarak belirttigimizde o activityden ozellik ceker. biz sadece "activity" yazdik.

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}