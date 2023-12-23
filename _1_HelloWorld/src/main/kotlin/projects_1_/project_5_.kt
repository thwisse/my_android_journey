package projects_1_

import java.util.Scanner
import kotlin.random.Random

// Bir okul kayit uygulamasi yap. Ogrencinin isimSoyisim'ni kullanici girsin. Ogrencinin okulNo'sunu
// 100 ile 1000 arasindaki degerlerden sen rastgele olarak belirle.

class Ogrenci (var isimSoyisim: String, var okulNo: Int)

fun main() {
    var secim: Int

    val ogrenciList = ArrayList<Ogrenci>()

    fun okulNoYarat (): Int {
        val yeniNo = Random.nextInt(100, 1000)
        return yeniNo
    }

    println("Yeni ogrenci kaydi programina hosgeldiniz.")

    val girdi = Scanner(System.`in`)

    do {
        println("1- Yeni Kayit\n2- Ogrencileri Sirala\n3- Okul Mevcudunu Goruntule\n4- Ogrenci Sil\n5- Cikis Yap")
        print("Yapmak istediginiz secimi giriniz: ")

        tekrardene@do {
            try {
                secim = girdi.next().toInt()
                girdi.nextLine() // fflush islemi
            } catch (e: NumberFormatException) {
                println("Bir hata oldu. Lutfen tekrar girin.")
                continue@tekrardene
            }
            break
        } while (true)

        when (secim) {
            1 -> {
                var isimSoyisim: String

                tekrardene2@ do {
                    print("Kaydi yapilacak ogrencinin adi soyadi: ")
                    // useDelimeter fonksiyonu ile string icindeki bosluklari da stringe dahil etmesini istedik.
                    // aksi taktirde girdiyi yazdirdigimda bosluktan sonrasini yazdirmiyordu.
                    girdi.useDelimiter("\n")

                    try {
                        isimSoyisim = girdi.next().toString()
                        girdi.nextLine()  // fflush islemi
                    } catch (e: NumberFormatException) {
                        println("Bir hata oldu. Lutfen tekrar deneyin.")
                        continue@tekrardene2
                    }
                    break
                } while (true)

                val okulNo = okulNoYarat()
                println("Ogrenci kaydi basariyla yapildi. Yeni okul numarasi belirlendi: $okulNo")
                ogrenciList.add(Ogrenci(isimSoyisim, okulNo))
            }
            2 -> {
                //TODO ogrenci listesini okul numarasina gore mi yoksa isme gore mi sirala diye sor
                //TODO ogr listesi bossa bos desin

                println("----------- Ogrenci Listesi -----------\n")
                for (i in ogrenciList) {
                    println("Okul No: ${i.okulNo}")
                    println("Ogrenci adi soyadi: ${i.isimSoyisim}")
                }
            }
            3 -> {
                println("Okul mevcudu: ${ogrenciList.size}")
            }
            4 -> {
                //TODO burayi yarim biraktim. tamamlanmali. bide ogrenci nolari ayni olan ogrenci olusmasini engelle

                var silNo: Int

                tekrardene3@do {
                    print("Silinmesini istediginiz ogrencinin numarasini giriniz: ")

                    try {
                        silNo = girdi.next().toInt()
                        girdi.nextLine()  // fflush islemi

                        for (nesne in ogrenciList) {
                            if (nesne.okulNo == silNo) {
                                ogrenciList.remove(nesne)
                                println("Ogrenci basariyla silindi.")
                                break
                            }
                            else {
                                println("Ogrenci bulunamadi.")
                            }
                        }
                    } catch (e: NumberFormatException) {
                        println("Bir hata oldu. Lutfen tekrar deneyin.")
                        continue@tekrardene3
                    }
                    break
                } while (true)
            }
            5 -> {
                println("Cikis yapiliyor...")
                break
            }
            else -> {
                println("Hatali giris. Tekrar deneyin.")
            }
        }
    } while (true)
}