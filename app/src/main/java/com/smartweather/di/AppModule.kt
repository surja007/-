package com.smartweather.di

import android.content.Context
import androidx.room.Room
import com.smartweather.data.local.WeatherDatabase
import com.smartweather.data.local.dao.FavoriteCityDao
import com.smartweather.data.local.dao.WeatherCacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCityDao(database: WeatherDatabase): FavoriteCityDao {
        return database.favoriteCityDao()
    }

    @Provides
    @Singleton
    fun provideWeatherCacheDao(database: WeatherDatabase): WeatherCacheDao {
        return database.weatherCacheDao()
    }
}
