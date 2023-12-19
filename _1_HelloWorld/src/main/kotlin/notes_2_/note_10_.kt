package notes_2_

open class Animals () {
    open fun speak () {
        println("Silence...")
    }
}

class Cat (): Animals() {
    override fun speak() {
        println("Meowwww!")
    }
}

class Dog (): Animals() {
    override fun speak() {
        println("Hav hav!")
    }
}

fun main () {

}