
package com.example.passions.forDrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.passions.R
import com.example.passions.databinding.FragmentWebPageBinding

//class MyWebViewClient : WebViewClient() {
//    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//        if (url != null) {
//            view?.loadUrl(url)
//        }
//        return true
//    }
//}
//import android.webkit.WebView
//import android.webkit.WebViewClient
// imported

class FragmentWebPage : Fragment() {

    private lateinit var binding: FragmentWebPageBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebPageBinding.inflate(inflater, container, false)

        // webView

        // deneme 1

        // javaScript ozelligini aktif ettik
        binding.webView.settings.javaScriptEnabled = true
        //import android.annotation.SuppressLint
        // bunu import etmemi istedi

        // sayfa aciliyordu ancak sayfada bir yere tikladigimda tarayiciya yonlendiriyordu.
        // bunun icin arastirma yaptim ve bu kodlari verdi. ancak bunlar ise yaramadi.
        //TODO neden ise yaramadi alttakiler? bunlarin da olayini ogren.
        //binding.webView.settings.setSupportMultipleWindows(true)
        //binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true

        //binding.webView.loadUrl("https://thwisse.wordpress.com/")

        // eger acmak istedigimiz sitenin url'i https degil de http ise bunun iznini de bizim vermemiz
        // gerekiyor. manifest dosyasinda application kisminda usesCleartextTraffic ozelligini true
        // yapmamiz gerekir.

        // deneme 2

        // su usttekiler ise yaramayinca baska bir yol buldum. MyWebViewClient isimli bir class olusturdum
        // WebViewClient isimli android sinifini icine aktardim. shouldOverrideUrlLoading isimli fonksiyonla
        // bir yere tiklandiginda ulasilmak istenen o sitenin linkini de loadurl olarak belirleyecek ve
        // webView icinden hic cikilmayacak. en ustten class'i ve alttaki kodlari incele.

//        val web_view = binding.webView.findViewById<WebView>(R.id.webView)
//        web_view.webViewClient = MyWebViewClient()

        // boyle bir sey varmis. ogrenmis olduk.

//        web_view.loadUrl("https://thwisse.wordpress.com/")

        // deneme 3

        // bundan da sonra soyle bir kod buldum. bu da calisiyor. class falan yaratmana da gerek kalmiyor.
        // hepsi uzerine tartisilip hangisi daha verimli vs. belirlenebilir
        //TODO simdilik boyle dursun. calisiyor:

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl("https://thwisse.wordpress.com/")
        }


        return binding.root
    }
}