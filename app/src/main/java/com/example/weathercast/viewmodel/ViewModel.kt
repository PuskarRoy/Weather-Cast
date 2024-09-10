package com.example.weathercast.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercast.data.Data
import com.example.weathercast.model.Favourite
import com.example.weathercast.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var apidata = mutableStateOf(Data())
    var FavList = MutableStateFlow<List<Favourite>>(emptyList())
    val unit = mutableStateOf("c")

    init {
        getWeather("barasat")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavourite().collect() {
                FavList.value = it
            }
        }
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            apidata.value.loading = true
            val responce = repository.getWeather(city)
            if (responce.data != null) {
                apidata.value = responce
            }
        }

    }

    fun insertFavourite(favourite: Favourite) {
        viewModelScope.launch {
            repository.insertFavourite(favourite)
        }
    }

    fun deleteFavourite(favourite: Favourite) {
        viewModelScope.launch { repository.deleteFavourite(favourite) }
    }



}