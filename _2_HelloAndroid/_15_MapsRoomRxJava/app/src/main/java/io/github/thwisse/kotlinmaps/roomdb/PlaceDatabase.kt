package io.github.thwisse.kotlinmaps.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.thwisse.kotlinmaps.model.Place

// Place'de bir degisiklik yaparsan gelip burdan versionu degistirmen gerekirmis.

// [Place::class] kisminda bir array istiyor. arrayOf(Place::class) seklinde de yazilabilirdi.
// ama oyle yazinca [] haline replace etmeni tavsiye ediyor.

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}

// bu database'in kodlarini zaten tutorialdan aldim. anlamasi zor degil. hatirlamazsan
// ordan alirsin bidaki sefere.

// bu dosyada yapilan tek islem PlaceDao'yu return etmek.