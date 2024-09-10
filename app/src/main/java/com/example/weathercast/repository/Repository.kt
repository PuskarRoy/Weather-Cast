package com.example.weathercast.repository

import com.example.weathercast.data.AppDao
import com.example.weathercast.data.Data
import com.example.weathercast.model.Favourite
import com.example.weathercast.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val weatherApi: WeatherApi, private val dao: AppDao) {

    suspend fun getWeather(city: String): Data {

        try {
            val response = weatherApi.getData(q = city)
            return Data(data = response, loading = false)


        } catch (e: Exception) {
            return Data(loading = false, error = e)

        }
    }

    fun getAllFavourite(): Flow<List<Favourite>> {
        return dao.getAllFavourite()
    }


    suspend fun insertFavourite(favourite: Favourite) {
        dao.insertFavourite(favourite)
    }

    suspend fun deleteFavourite(favourite: Favourite) {
        dao.deleteFavourite(favourite)
    }




}