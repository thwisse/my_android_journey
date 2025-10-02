package projects_1_

import java.util.*

// parametre olarak girilen dereceyi fahrenheit'a donusturdukten sonra geri donduren fonksiyonu yazin.
// T(F) = T(C) x 1.8 + 32

class convert {
    fun CtoF(C: Double): Double {
        return C * 1.8 + 32
    }
}

fun main() {
    print("Fahrenheit'a donusturmek istediginiz degeri girin: ")
    val input = Scanner(System.`in`)
    val c = input.next().toDouble()
    // buradaki toDouble islemini asagidaki convert icinde c nin yaninda da kullanabilirdik.
    val convert = convert()
    val f = convert.CtoF(c)
    println("Fahrenheit: $f")
    println("$c celcius, $f fahrenheit'a esittir.")
}