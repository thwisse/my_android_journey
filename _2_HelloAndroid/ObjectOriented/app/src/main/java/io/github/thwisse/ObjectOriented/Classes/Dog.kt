package io.github.thwisse.ObjectOriented.Classes

class Dog : Animal() {
    fun bark () {
        println("dog is barking...")
    }

    override fun walk () {
        println("dog is walking...")
    }

    // Redundant overriding method (cunku zaten bu classtan uretilen nesnede superclasstaki
    // ozellliklere erisilebiliyor.
    //override fun walk() {
    //    super.walk()
    //}

    fun test () {
        super.walk()
        // super keywordu superclasstaki ozelliklere referans verir.
        // superclasstaki walk fonksiyonu ne yapiyorsa bu fonk da onu yapacak.
    }
}