package com.example.weathercast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathercast.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("select * from Favourite")
    fun getAllFavourite():Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)


}