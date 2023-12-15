package notes_2_.packs_for_note_8_.pack_2_

import notes_2_.packs_for_note_8_.pack_1_.class_1_

class class_2_ {
    // nesne olusturunca notes_3_.pack_1_.class_1_ classi import edildi.
    val nesne = class_1_()

    fun func () {
        println(nesne.varsayilanDegisken)

        println(nesne.publicDegisken)

        // private degiskene baska classta erisilmiyor:
        //println(nesne.privateDegisken)

        // ayni modulde olduklari icin erisilebildi.
        println(nesne.internalDegisken)

        // protected degisken class_1_ sinifindan turetilmedigi icin erisilemiyor:
        //println(nesne.protectedDegisken)
    }
}