package com.example.weathercast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathercast.model.Favourite

@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
   abstract fun getAppDao():AppDao
}