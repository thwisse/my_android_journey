package com.example.myfirstapp

import java.io.Serializable

// bir veritabani ozelligi gosterecegi icin data class haline getirdik.
// veri aktarimi islemini gerceklestirebilmek icin Serializable interface'ini eklememiz gerekiyor.
data class Kisiler (var nickname: String, var age: Int): Serializable