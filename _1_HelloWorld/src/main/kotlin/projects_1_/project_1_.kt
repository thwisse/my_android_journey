package projects_1_

// parametre olarak girilen dereceyi fahrenheit'a donusturdukten sonra geri donduren fonksiyonu yazin.
// T(F) = T(C) x 1.8 + 32

class convert {
    fun CtoF (C: Double): Double {
        return C * 1.8 + 32
    }
}

fun main () {
    //print("Fahrenheit'a donusturmek istediginiz degeri girin: ")
    //val input = Scanner(System.`in`)
    //val c = input.nextDouble()
    val convert = convert()
    val f = convert.CtoF(40.8)
    println("Fahrenheit: $f")
    //println("$c celcius, $f fahrenheit yapar.")
}