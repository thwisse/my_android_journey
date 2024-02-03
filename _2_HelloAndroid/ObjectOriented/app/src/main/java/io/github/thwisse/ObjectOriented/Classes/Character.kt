package io.github.thwisse.ObjectOriented.Classes

class Character(name: String, age: Int, gender: String, maritalStatus: Boolean):
    User(name, age, gender, maritalStatus) {

    fun resuscitate () {
        println("Diriltildi!")
    }

}