package notes_3_.files_for_note_14_

class Character(name: String, age: Int, gender: String, maritalStatus: Boolean):
    User(name, age, gender, maritalStatus) {

    fun resuscitate () {
        println("Diriltildi!")
    }

}