package projects_1_

// parametre olarak girilen sayinin faktoriyelini geri donduren fonksiyonu yazin.

fun fact(number: Int): Int {
    var sonuc = 1
    for (i in number downTo 1) {
        sonuc *= i
    }
    return sonuc
}

fun main() {
    println(fact(4))
}