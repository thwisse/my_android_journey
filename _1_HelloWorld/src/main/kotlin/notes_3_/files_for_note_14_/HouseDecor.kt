package notes_3_.files_for_note_14_

interface HouseDecor {
    // interface'ler %100 abstract'tir. ancak farkli ozellikleri de vardir.
    // oncelikle burada tanimlanan property, function vs'lerin hepsinin genel adi member'dir. bunu unutma.

    // kotlinde interface'lerde tanimlanan propertyler initialize edilmezler.
    var roomName: String
    // ancak java'da initialize etmek zorunlu imis. boyle bir degisiklik olmus kotlinde.

    var roomNumber: Int
}