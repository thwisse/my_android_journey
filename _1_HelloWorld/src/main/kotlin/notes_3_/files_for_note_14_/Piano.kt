package notes_3_.files_for_note_14_

class Piano(override var roomNumber: Int) : HouseDecor, Instrument {
    var brand: String? = null
    var isDigital: Boolean? = null

    ///////////////////////
    // HouseDecor

    // ister gel burada roomName member'ini implement et, istersen de bu member'i constructor parametresi
    // olarak implement et. zaten HouseDecor'u implement edince hata veriyor, ikisinden birini secmeni istiyor.
    // ya da HouseDecor'u abstract'a donustur diyor.
    override var roomName: String
        // kotlinde interface icindeki memberi initialize etmek yerine, interface'i implement ettigin
        // classta get()'in degerini verirsin.
        get() = "Saloon"
        set(value) {}

    // roomNumber member'ini da cons parametresi olarak implemente ettim.

    ////////////////////////////
    // Instrument

    // Redundant overriding method - diyor. cunku fonksiyonun kendisi tamamen interface icinde tanimli.
    // yani gelip burda aynisini boyle tanimlamana gerek yok diyor. ancak icindeki super.information()
    // kodunu silip codu cidden override edip baska bir islem yaptirirsak iste o zaman redundant olmaz,
    // cunku interface'de tanimladigimiz fonksiyonu ayni isimle bu classta farkli sekilde kullanmis olduk.
    override fun information() {
        println("piano info...")
    }

    override fun pricing() {
        println("piano is so cheap!")
    }


}