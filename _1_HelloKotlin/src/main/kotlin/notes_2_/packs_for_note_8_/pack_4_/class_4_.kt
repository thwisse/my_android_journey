package notes_2_.packs_for_note_8_.pack_4_

import notes_2_.packs_for_note_8_.pack_3_.class_3_

// class_3_ classi, class_4_ classina miras yoluyla eklendi:
class class_4_: class_3_() {

    // kalitildiginda nesne uretmeye de gerek olmuyor.
    // ancak class import edilmis olmali. o sart.

    fun func () {
        println(varsayilanDegisken)

        println(publicDegisken)

        // private degiskene baska classta erisilmiyor. kalitim olsa bile.
        //println(privateDegisken)

        println(internalDegisken)

        // protected degisken class_3_ sinifindan turetildigi icin artik erisilebiliyor.
        println(protectedDegisken)
    }
}