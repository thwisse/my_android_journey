package notes_3_.files_for_note_14_

class Dog : Animal() {
    override fun walk () {
        println("dog is walking...")
    }

    fun bark () {
        println("dog is barking...")
    }

    // "Redundant overriding method" hatasi aliyorum (cunku zaten bu classtan uretilen nesnede superclasstaki
    // ozellliklere erisilebiliyor. gerek yok bunu yazmana diyor yani. yazacaksan degistir diyor.
    //override fun walk() {
    //    super.walk()
    //}

    fun test () {
        super.walk()
        // super keywordu superclasstaki ozelliklere referans verir.
        // superclasstaki walk fonksiyonu ne yapiyorsa bu fonk da onu yapacak.
    }
}