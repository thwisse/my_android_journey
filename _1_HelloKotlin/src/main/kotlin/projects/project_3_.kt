package projects_1_

// parametre olarak girilen kelime ve harf icin harfin kelime icinde kac adet oldugunu gosteren fonksiyonu yazin.

fun func(kelime: String, harf: Char): Int {
    var count: Int = 0
    if (kelime.contains(harf)) {
        for (i in 0 until kelime.length) {
            if (kelime[i] == harf) {
                count += 1
            }
        }
    }
    return count
}

// daha kolay bir yolu varmis
fun func2(kelime: String, harf: Char): Int {
    var count = 0

    for (h in kelime) {
        if (h == harf) {
            count += 1
        }
    }
    return count
}

// daha da kisayolu varmis
fun func3(kelime: String, harf: Char): Int {
    return kelime.count { it == harf }
}

fun main() {
    println(func("abbasa", 'a'))
    println(func2("abbasa", 'a'))
    println(func3("abbasa", 'a'))
}