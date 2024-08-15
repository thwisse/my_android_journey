package io.github.thwisse.kotlinmaps.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.thwisse.kotlinmaps.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PlaceDao {

    @Query("SELECT * FROM Place") // place: model name
    fun getAll(): Flowable<List<Place>>
    // bu bana bir liste dondurecek. listede de Place objeleri olacak.

    @Insert
    fun insert (place: Place): Completable

    @Delete
    fun delete (place: Place): Completable

}