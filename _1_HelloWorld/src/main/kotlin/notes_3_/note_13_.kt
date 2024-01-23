package notes_3_

fun main() {
    //////////////////////
    // for each dongusu

    val mySet1 = mutableSetOf(1,2,2,3,4,4,5)
    println("Size:" + mySet1.size)

    mySet1.forEach { print("$it ") }
    ///1 2 3 4 5

    ////////////////////
    // hashMapOf / HashMap

    val calories = hashMapOf<String, Int>()
    calories.put("Apple", 100)
    calories.put("Banana", 150)

    // sik kullanilan:
    val prices = HashMap<String, Int>()
    prices.put("Toy", 20)
    prices.put("Book", 5)
}