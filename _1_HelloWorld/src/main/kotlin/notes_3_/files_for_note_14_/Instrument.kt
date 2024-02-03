package notes_3_.files_for_note_14_

interface Instrument {

    // body'li fun
    fun information () {
        println("instrument info...")
    }

    // body'siz fun
    fun pricing ()

    // kotlinde interfacelerde bu iki sekilde de fonskiyon yaratilabilir. bir class'a implement ettigimde
    // ikisini de kullanabilirim. ancak bu eskiden javada boyle degilmis. sadece su 2. member gibi olusturup
    // birakabiliyormussun, curly bracket ile icinde bir islem yapip onu da baska class'a implement etmek
    // gibi bir sey yapamiyormussun. javada body olmuyor, kotlinde olabiliyor.

    fun information2 () {
        println("instrument info...")
    }
    // bu fonksiyonu piano sinifina implement etmedim ve override etmedim. cunku zaten burada body'si tanimlanmis.
    // implement etmememe ragmen piano sinifindan bir nesne uretince bu fonksiyonu da kullanabiliyorum.
    // burada bodysi nasil tanimlandi ise o nesnede o isi yapacak.

}