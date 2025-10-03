package projects_1_

// parametre olarak girilen gun sayisina gore maas hesabi yapan fonksiyonu yazin.
// 1 gunde 8 saat calisilabilir. saatlik ucret 10 tl. mesai saatlik ucret 20 tl. 160 saat ustu mesaidir.

fun maasHesapla(gunSayisi: Int): Int {
    val toplamCalismaSaati = gunSayisi * 8
    val normalSaatlikUcret = toplamCalismaSaati * 10
    var mesaiSaatlikUcret: Int
    val maas: Int

    if (toplamCalismaSaati > 160) {
        val toplamMesaiSaati = toplamCalismaSaati - 160
        mesaiSaatlikUcret = toplamMesaiSaati * 20
        maas = 160 * 10 + mesaiSaatlikUcret
    } else {
        maas = normalSaatlikUcret
    }
    return maas
}

fun main() {
    println(maasHesapla(21))
}