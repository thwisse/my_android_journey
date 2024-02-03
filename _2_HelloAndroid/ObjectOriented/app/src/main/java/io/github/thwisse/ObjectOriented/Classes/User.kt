package io.github.thwisse.ObjectOriented.Classes

open class User(name: String, age: Int, gender: String, maritalStatus: Boolean) {
    // properties
    var name: String? = name

    var age: Int? = age
        set
        get
    // bunlari yazmasan da default olarak zaten get/set'e erisilebildigi icin gereksiz tanim diyor.

    var gender: String? = gender
        private set
        get

    private var maritalStatus: Boolean? = maritalStatus

    open fun drink() {
        println("Hold my beer!")
    }


    // classta sadece primary constructor parametreleri varken, yani propertyler yokken, nesne
    // olusturdugumuzda ilk degerlerini atariz. atadiktan sonra ne degistirebilir ne de okuyabiliriz.
    // ancak propertyleri verirsek o zaman nesnelerde degisiklik ya da okuma yapabiliriz. iste
    // constructor bu ise yarar. okuma ve yazmanin kontrolu.

    /*
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age

        // constructor, bir nesne uretildiginde ilk cagirilan fonksiyondur.
        //println("new user created")
        // ancak constructor kotlinde bu amacla kullanilmaz. cunku primary cons kullanilir.
        // ilk cagirilan fonksiyon islevini de init yapar.
    }
    */

    init {
        println("new user created")
    }

}