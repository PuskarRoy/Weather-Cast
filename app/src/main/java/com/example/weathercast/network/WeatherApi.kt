package com.example.weathercast.network

import com.example.weathercast.model.WeatherInfo
import com.example.weathercast.utils.Utlis
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "v1/forecast.json")
    suspend fun getData(
        @Query("q") q: String,
        @Query("days") days: String = "5",
        @Query("aqi") aqi: String = "yes",
        @Query("key") key: String = Utlis.API_Key

    ): WeatherInfo
}