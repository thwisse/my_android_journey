package com.example.tablayouteg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayouteg.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listFragment = ArrayList<Fragment>()

    private val listFragmentTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ////////////////////////
        // tab layout kullanimi
        // bottom navigation view'in ilkel hali.
        //
        // bir tablayout komponenti ekledim. icindeki itemlari sildim. tabLayout'a idsini verdim.
        // background, tabTextColor, tabIconTint, tabIndicator degerlerini belirledim.
        //
        // bir viewPager2 komp ekledim. ekranlari burada goruntuleyecegiz. idsini verdim.
        //
        // layout klasorunde 3 farkli fragment icin layout olusturdum. daha sonra bunlar icin ana
        // klasorde (tablayouteg) 3 farkli fragment icin kt uzantili dosya olusturuyorum.
        // bundan sonraki fragmentlarla ilgili notlari fragmentlar icine alacagim.
        // normalde fragmentlari otomatik olusturdugumuzda fonskiyonlari baglanmalari vs otomatik
        // geliyordu. burada ise layoutu ayri classi ayri olusturup bagladik.
        // fragmentlar yaratildi ve baglantilar yapildi.
        //
        // activityde yani burda bir arraylist olusturacagiz ve fragmentlari burada tutacagiz.
        // fragment tutacak bir arraylist. simdi bu listeye fragmentlari ekleyelim:

        listFragment.add(FragmentFirst())
        listFragment.add(FragmentSecond())
        listFragment.add(FragmentThird())

        // simdi bir adapter olusturarak bu fragmentlarin activity main layout uzerindeki viewpager'da
        // sergilenmesini saglayacagiz. buna adapter saglayacak.
        //
        // adapter yaratildi. simdi adapterdan bir nesne olusturup viewpager'a iletecegiz.
        val adapter = AdapterViewPager(this@ActivityMain)
        binding.viewPager2.adapter = adapter

        // simdi tab layout'ta goruntulenecek olan basliklari yaratalim. yine burada yukarida bunun da
        // listesini yaratiyorum. basliklari listeye ekle:

        listFragmentTitles.add("First")
        listFragmentTitles.add("Second")
        listFragmentTitles.add("Third")

        // simdi de viewpager ile tablayout'u birlestirecegiz:
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            // bu fonksiyonda her bir tab'i ve bu tab'in position degerlerini veriyor.

            // sirasiyla tablarin basliklari tab'lara aktarilacak
            tab.setText(listFragmentTitles[position])

        }.attach()
        // bu attach fonksiyonuyla da viewpager ve tablayout birlestirilmis oldu.

        // tablara icon ekleyebiliriz:
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_launcher_foreground)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_launcher_foreground)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_launcher_foreground)

        // evet tablayout olayi burada bitti. bu bottom navigation view'in ilkel hali gibi geldi bana.
        // daha onceden bu kullaniliyordu da artik nc kullaniliyor gibi. bilemedim. bunu kullanacagimi
        // sanmiyorum ama gordugum iyi oldu.
        // bu arada baktim oyleymis. sadece elle kaydirarak fragmentlar arasi gecis yokmus. o da herhalde
        // baska sekillerde ayarlanir eklenir.

        // bu arada bu kursta izledigim son video buydu ve kursu burada
        // birakiyorum. yuzde 50sini bitirdim kursun ancak yeterince iyi olmadigini dusunuyorum nedense.
        // atil hocanin kursuna baslayacagim. bu da buraya bi not olsun bakalim.

    }

    // adapterimizi activity icinde ic sinif olarak olusturduk. gereken baglantilari yaptik.
    inner class AdapterViewPager(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        // kac fragment goruntulenecek organize edilecek, bunu bilmek istiyor.
        override fun getItemCount(): Int {
            return listFragment.size
        }

        // fragmentlar sirayla bu fonksiyondan return edilecek. ve fragmentlar sergilenecek.
        override fun createFragment(position: Int): Fragment {
            return listFragment[position]
            // posisiton = dizideki index
        }
    }
}