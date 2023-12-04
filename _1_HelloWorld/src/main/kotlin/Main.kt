fun main()
{
    println("Hello World!")

    print("Learn ")
    println("Kotlin")

    // not al

    /*
    not al
    not al
    */

    println(35+34)
    println("69")

    // * asteris operatoru ile carpim yapiyoruz.
    println(3 * 5)

    ////////////////////
    // var - val

    var number1 = 15
    number1 = 16
    var name1 = "oguzhan"

    val city1 = "hatay"
    // val degiskenlerin degeri daha sonra degistirilemez.
    //city1 = "maras"

    ////////////////////////
    // int float double

    val a:Int = 5
    // float'in sonunda F gelmek zorunda. double ile ayirt edilebilmesi icin. F yoksa double'dir.
    val b:Float = 6.14F
    // float double'in aksine kusuratli olmak zorunda degildir.
    val b2:Float = 6F
    val c:Double = 7.567

    /////////////////////

    var today: String  // declaration
    today = "Sunday"  // initialization
    var tomorrow: String = "Monday"  // declaration and initializaton

    /////////////////////
    // type inferance

    var name2 = "Aziz Vefa"  ///String
    var age2 = 39            ///Int
    var maritalStatus = true ///Boolean

    /////////////////////
    // string templates

    val plant = "orchid"
    val potSize = 6 // in inches
    val dayNum = 7

    println("An $plant in a $potSize inch pot must be watered every $dayNum days.")

    //////////////////
    // variableName.capitalize() function

    var word = "supercalifragilisticexpialidocious"

    word = word.capitalize()

    // .length property

    var wordSize = word.length

    println("$word has $wordSize letters.")

    ////////////////////////////
    // character escape sequences

    println("Soylenir turkumuz \ndaglardan daglara")
    println("Benim adim \"Ersan Kuneri\"")

    ///////////////////////
    // Math library

    // ussunu alir (power=kuvvet)
    Math.pow(5.0, 3.0)  ///125.0
    // iki deger arasindan min olan degeri dondurur
    Math.min(5, 3)      ///3
    // tam tersi
    Math.max(5, 3)      ///5
    // 0 ile 1 arasinda random bir sayi uretir
    Math.random()       ///0.3335735290527727
    // en yakin integer degere yuvarlar. asagi ya da yukari. (round up=yuvarlamak)
    Math.round(15.7)    ///16
    // tavana yuvarla (ceil)
    Math.ceil(3.5)      ///4.0
    // tabana yuvarla (floor)
    Math.floor(6.9)     ///6.0
    // karekokunu al (square root)
    Math.sqrt(25.0)     ///5.0
    // mutlak degerini al (absolute value)
    Math.abs(-20)       ///20






}