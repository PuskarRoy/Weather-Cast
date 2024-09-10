package com.example.weathercast.model

data class WeatherInfo(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)