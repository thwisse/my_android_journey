package notes_1_

fun main() {
    // donguler

    //for dongusu

    // range kullanarak olusturulan for donguleri:
    for (i in 1..5) {   // i = loop variable, 1..5 = range (iterator element)
        println("Hello, Codey!")
        println("i = $i")
    }
    ///Hello, Codey!
    ///i = 1
    ///Hello, Codey!
    ///i = 2
    ///Hello, Codey!
    ///i = 3
    ///Hello, Codey!
    ///i = 4
    ///Hello, Codey!
    ///i = 5

    // buyukten kucuge yani geriye dogru saymasi icin downTo kullanilir
    for (i in 4 downTo 1) {
        println("i = $i")
    }
    ///i = 4
    ///i = 3
    ///i = 2
    ///i = 1

    // 1 den 4 e kadar don ama 4 dahil degil. bunun until kullanilir
    for (i in 1 until 4) {
        println("i = $i")
    }
    ///i = 1
    ///i = 2
    ///i = 3

    // 1 den 8e kadar 2 2 artirarak dondur. bunun icin step 2 kullanilir. 3 3 olsa step 3. bu sekilde.
    for (i in 1..8 step 2) {
        println("i = $i")
    }
    ///i = 1
    ///i = 3
    ///i = 5
    ///i = 7

    /////////////////////
    // collection kullanarak olusturulan for donguleri:
    // listOf
    val fruitList = listOf("apples", "oranges", "bananas")
    // burada dongu degiskeni fruit olur. range yerine de list gelir. list = iterator element
    for (fruit in fruitList) {
        println("I have $fruit.")
    }
    ///I have apples.
    ///I have oranges.
    ///I have bananas.

    // setOf
    val fruitSet = setOf("apples", "oranges", "bananas")
    // setin indislerini for icinde kullanmak icin .indices propunu kullanabiliriz
    for (setIndex in fruitSet.indices) {
        println("Index = $setIndex")
    }
    ///Index = 0
    ///Index = 1
    ///Index = 2

    //////////////////////
    // collection ile olusturulan for dongusunde destructure islemi:
    val fruitList2 = listOf("apples", "oranges", "bananas")
    // koleksiyonun withIndex() fonksiyonunu kullanarak indeksi ve iterator ogeyi ayni anda alabiliriz
    // destructuring ve withIndex() i de for icinde birlikte kullanarak fruitList2'nin
    // hem elemanini hem de indeksini isleme sokabiliriz
    // listIndex and fruit = destructured loop variables, fruitList2.withIndex() = iterator element
    for ((listIndex, fruit) in fruitList2.withIndex()) {
        println("$listIndex is the index of the element $fruit")
    }
    ///0 is the index of the element apples
    ///1 is the index of the element oranges
    ///2 is the index of the element bananas

    // bir for ornegi:

    val mySchedule = listOf("Eat Breakfast", "Clean Up", "Work", "Eat Lunch",
        "Clean Up", "Work", "Eat Dinner", "Clean Up")

    val myTasks = setOf("Eat Breakfast", "Clean Up", "Work", "Eat Lunch",
        "Clean Up", "Work", "Eat Dinner", "Clean Up")

    println("-- mySchedule Output --")
    for (task in mySchedule) {
        println(task)
    }
    ///-- mySchedule Output --
    ///Eat Breakfast
    ///Clean Up
    ///Work
    ///Eat Lunch
    ///Clean Up
    ///Work
    ///Eat Dinner
    ///Clean Up

    println("\n-- myTasks Output --")
    // myTasks set oldugu icin ayni olan degerler tekillestirildi
    for ((taskIndex, task) in myTasks.withIndex()) {
        println("$taskIndex: $task")
    }
    ///-- myTasks Output --
    ///0: Eat Breakfast
    ///1: Clean Up
    ///2: Work
    ///3: Eat Lunch
    ///4: Eat Dinner

    // mapOf
    val myClothes = mapOf("Shirts" to 7, "Pairs of Pants" to 4, "Jackets" to 2)
    // loop variable = itemEntry (key ve value ciftlerine entry denir)
    // iterator element = myClothes (mapName)
    for (itemEntry in myClothes) {
        // burada string template($) ve curly bracket kullanarak deger cagirdik
        println("I have ${itemEntry.value} ${itemEntry.key}")
        // istesek map propertylerini kullanarak da istedigimizi yapabilirdik
        println("I have " + itemEntry.value + " " + itemEntry.key)
    }

    // mapOf destructure ornegi:
    for ((itemName, itemCount) in myClothes) {
        println("I have $itemCount $itemName")
    }
    ///I have 7 Shirts
    ///I have 4 Pairs of Pants
    ///I have 2 Jackets

    // iterator element myClothes oldugunda key'e erismek icin:
    // ${loopVariable.key}
    // iterator element myClothes.keys oldugunda key'e erismek icin:
    // ${loopVariable}

    // sadece keyleri kullanmak istesek:
    println("KEYS")
    for (itemName in myClothes.keys) {
        println(itemName)
    }
    ///KEYS
    ///Shirts
    ///Pairs of Pants
    ///Jackets
    // sadece degerleri kullanmak istesek:
    println("VALUES")
    for (itemCount in myClothes.values) {
        println(itemCount)
    }
    ///VALUES
    ///7
    ///4
    ///2

    // while dongusu

    // while (condition) {
    //  loop body
    //  counterVariable += 1 (increase or decrease)
    // }

    var myAge = 16
    while (myAge < 20) {
        println("I'm $myAge years old. I am a teenager.")
        myAge += 1
    }
    println ("I'm $myAge years old. I am not a teenager.")
    ///I'm 16 years old. I am a teenager.
    ///I'm 17 years old. I am a teenager.
    ///I'm 18 years old. I am a teenager.
    ///I'm 19 years old. I am a teenager.
    ///I'm 20 years old. I am not a teenager.

    // do while dongusu

    var index = 0
    val celsiusTemps = listOf(0.0, 87.0, 16.0, 33.0, 100.0, 65.0)
    val fahr_ratio = 1.8
    var fahr: Double

    println("-- Celsius to Fahrenheit --")
    do {
        fahr = celsiusTemps[index] * fahr_ratio + 32.0
        println("${celsiusTemps[index]}C = ${fahr}F")
        index += 1
    }
    while (fahr != 212.0)
    ///-- Celsius to Fahrenheit --
    ///0.0C = 32.0F
    ///87.0C = 188.6F
    ///16.0C = 60.8F
    ///33.0C = 91.4F
    ///100.0C = 212.0F

    // ic ice donguler

    for (i in 1..2) {
        for (j in 'A'..'C') {
            println("Outer loop: $i - Inner loop: $j")
        }
    }
    ///Outer loop: 1 - Inner loop: A
    ///Outer loop: 1 - Inner loop: B
    ///Outer loop: 1 - Inner loop: C
    ///Outer loop: 2 - Inner loop: A
    ///Outer loop: 2 - Inner loop: B
    ///Outer loop: 2 - Inner loop: C

    /////////////////
    // jump expresions (break & continue)

    // break donguyu istenen yerde durdurur ve kodu dongunun disina ziplatir
    // continue dongude bulundugu yeri ve sonrasini calistirmaz, donguyu bir sonraki donuse goturur

    // break ornek:
    val rooms = listOf("Kitchen", "Living Room", "Basement", "Bathroom")
    println("-- Room Search --")
    for (room in rooms) {
        print("$room: ")
        if (room == "Living Room") {
            println("Found It!")
            break
        }
        println("Found Nothing.")
    }
    ///-- Room Search --
    ///Kitchen: Found Nothing.
    ///Living Room: Found It!

    // continue ornek:
    println("\n-- Divide By Zero --")
    for (number in 12 downTo -12 step 4) {
        if (number == 0) {
            continue
        }
        println("120/number = ${120/number}")
    }
    ///-- Divide By Zero --
    ///120/number = 10
    ///120/number = 15
    ///120/number = 30
    ///120/number = -30
    ///120/number = -15
    ///120/number = -10

    // Labeled Jump Expressions (etiketli jump ifadeleri)

    for (i in 1..6) {
        for (j in 'A'..'F') {
            print("$j$i ")
        }
        println()
    }
    ///A1 B1 C1 D1 E1 F1
    ///A2 B2 C2 D2 E2 F2
    ///A3 B3 C3 D3 E3 F3
    ///A4 B4 C4 D4 E4 F4
    ///A5 B5 C5 D5 E5 F5
    ///A6 B6 C6 D6 E6 F6

    // grid gibi rastgele bi isim vererek ve @ kullanarak herhangi bir donguyu etiketledik
    grid@ for (i in 1..6) {
        for (j in 'A'..'F') {
            if (j == 'C') {
                continue@grid  // etiketli olan donguyu continue etmesini sagladik
            }
            print("$j$i ")
        }
        println()
    }
    ///A1 B1 A2 B2 A3 B3 A4 B4 A5 B5 A6 B6

}