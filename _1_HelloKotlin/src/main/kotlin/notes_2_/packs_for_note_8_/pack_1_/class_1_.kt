package notes_2_.packs_for_note_8_.pack_1_

class class_1_ {

    // visibility modifier (erisim)
    // public, private, internal ve protected adi verilen erisim belirleyicileri ile
    // degiskenlerin classlarda erisimlerini kontrol ederiz.

    // her yerden erisilebilir. zaten public ile ayni seydir.
    var varsayilanDegisken = 1

    // her yerden erisilebilir. public yazilmasa da olur.
    public var publicDegisken = 2

    // private degiskene yalnizca bulundugu class icinde erisilebilir.
    private var privateDegisken = 3

    // butun MODULLERDEN erisilebilir
    internal var internalDegisken = 4

    // protected degiskene erisilebilmesi icin bu classtan kalitimla turetilmesi gerekir.
    protected var protectedDegisken = 5
}