package projects

// ascii tabloya gore bir metni donusturen iki ayri fonksiyon yap. biri metni koda donustursun, biri kodu metne.
// kullanicidan da istedigi metni girmesini iste, ardindan ciktiyi ver.

fun main() {
    print("text giriniz: ")
    val str1: String = readln()
    println("girilen text: $str1")

    val asciiListe = mutableListOf<Int>()

    for (x in str1) {
        asciiListe.add(x.code)
    }
    val convertedToAscii = asciiListe.joinToString(" ")
    println("donusturuldu: $convertedToAscii")

    print("ascii liste giriniz: ")
    val str2: String = readln()
    println("girilen liste: $str2")

    val decimals = str2.split(' ')
    val convertedToText = StringBuilder()

    for (x in decimals) {
        val decimalDeger = x.toInt()
        convertedToText.append(decimalDeger.toChar())
    }
    println("donusturuldu: $convertedToText")

    // daha profesyonel bir kod uretilebilir tabii. hatali girislerin kontrolleri saglanabilir vs.
    // simdilik temel algoritmayi olusturdum. daha sonra gelistirebilirim.
}