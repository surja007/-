package com.smartweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartweather.data.local.dao.FavoriteCityDao
import com.smartweather.data.local.dao.WeatherCacheDao
import com.smartweather.data.local.entity.FavoriteCityEntity
import com.smartweather.data.local.entity.WeatherCacheEntity

@Database(
    entities = [
        FavoriteCityEntity::class,
        WeatherCacheEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun favoriteCityDao(): FavoriteCityDao
    abstract fun weatherCacheDao(): WeatherCacheDao
}
