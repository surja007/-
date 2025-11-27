package com.smartweather.data.local.dao

import androidx.room.*
import com.smartweather.data.local.entity.WeatherCacheEntity

@Dao
interface WeatherCacheDao {
    @Query("SELECT * FROM weather_cache WHERE locationKey = :locationKey")
    suspend fun getWeatherCache(locationKey: String): WeatherCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherCache(cache: WeatherCacheEntity)

    @Query("DELETE FROM weather_cache WHERE cachedAt < :expiryTime")
    suspend fun deleteExpiredCache(expiryTime: Long)
}
