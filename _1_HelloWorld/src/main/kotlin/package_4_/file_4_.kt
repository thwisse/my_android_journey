package package_4_

// func 1
// burada mass'e parametre denirken, main icinde func cagirilinca icine girilen seye arguman denir.
fun calculateForce(mass: Int, acceleration: Int) {
    var force = mass * acceleration
    println("The force is $force Newtons.")
}

// func 2
fun greetCustomer(name: String = "Customer") {
    println("Hello, $name.")
}

// func 3
fun getPrice(price: Double, couponCode: String = "None") {
    var finalPrice: Double

    if (couponCode == "save15") {
        finalPrice = price * .85
    }
    else {
        finalPrice = price
    }
    println("The total price is $finalPrice.")
}

// func 4
// parantezden sonra gelen (: Int) kismi, fonksiyonun donecegi degerin tipini belirtir
fun listSum(myList: List<Int>): Int {
    var sum = 0
    for (i in myList) {
        sum += i
    }
    // return statement
    return sum
}

// func 5
// fonksiyonda yalnizca bir tane return olacaksa single expression functions ozelligi
// sayesinde fonksiyonu bu kadar kisa hale getirebiliriz.
fun pyramidVolume(l: Int, w: Int, h: Int) = (l * w * h) / 3
// su alttaki fonksiyonu kisaltip ustteki gibi yazabildik.
///fun pyramidVolume(l: Int, w: Int, h: Int){
///    return (l * w * h) / 3
///    }

fun main () {
    // func 1 (calculateForce())

    calculateForce(5, 12)
    ///The force of this object is 60 Newtons.

    // func 2 (greetCustomer())

    // named and default arguments ornegi:
    greetCustomer("Cynara")
    ///Hello, Cynara.
    // arguman olarak bir sey girmediginde default argumani girdi olarak aldi.
    greetCustomer()
    ///Hello, Customer.

    // func 3 (getPrice())

    // kupon uygulandi
    getPrice(couponCode = "save15", price = 48.0)
    ///The total price is 40.8.
    // kupon uygulanmadi
    getPrice(price = 48.0)
    ///The total price is 48.0.

    // func 4 (listSum())

    var billsToPay = mutableListOf(44, 29, 104, 79)

    var total = listSum(billsToPay)
    println("Your bill total is $total dollars.")
    ///Your bill total is 256 dollars.

    // func 5 (pyramidVolume())

    var length = 5
    var width = 8
    var height = 14
    var volume = pyramidVolume(length, width, height)

    println("The volume of this pyramid is $volume.")
    ///The volume of this pyramid is 186.

    ///////////////////
    // function literals

    // fonksiyonlari bir degiskene atayarak anonim olarak kullanabilmemize yariyor
    val quotient = fun(num1: Int, num2: Int): Double {
        // .toDouble kullanabilmemiz icin fonksiyonun boyle (: Double)
        // dondurme tipinin belirlenmis olmasi gerekiyor
        return num1.toDouble() / num2.toDouble()
    }
    println(quotient(12, 5)) ///2.4

    // lambda expression

    // function literals yerine lambda kullanmak daha kolay. fun bile yazmana gerek kalmiyor.
    // return olacak kismi -> dan sonra yaziyorsun
    val quotient2 = { num1: Int, num2: Int -> num1.toDouble() / num2.toDouble() }
    println(quotient2(12, 5)) ///2.4




}

