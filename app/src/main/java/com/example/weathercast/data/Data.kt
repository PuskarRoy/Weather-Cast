package com.example.weathercast.data

import androidx.compose.runtime.MutableState
import com.example.weathercast.model.WeatherInfo
import java.lang.Exception

data class Data(
    var data: WeatherInfo? = null,
    var loading: Boolean ?= true,
    var error: Exception? = null
)