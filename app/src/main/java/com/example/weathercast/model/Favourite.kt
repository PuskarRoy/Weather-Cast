package com.example.weathercast.model

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(

    @PrimaryKey
    val city: String,
    @ColumnInfo
    val country: String
)
