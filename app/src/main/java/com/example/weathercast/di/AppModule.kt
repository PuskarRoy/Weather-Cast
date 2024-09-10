package com.example.weathercast.di

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Database
import androidx.room.Room
import com.example.weathercast.data.AppDao
import com.example.weathercast.data.AppDatabase
import com.example.weathercast.network.WeatherApi
import com.example.weathercast.repository.Repository
import com.example.weathercast.utils.Utlis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl(Utlis.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api:WeatherApi,dao: AppDao):Repository{
        return Repository(api,dao)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"Favourite_db").build()
    }

    @Singleton
    @Provides
    fun provideAppDao(appDatabase: AppDatabase):AppDao{
        return appDatabase.getAppDao()

    }


}