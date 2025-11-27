package com.smartweather.data.local.dao

import androidx.room.*
import com.smartweather.data.local.entity.FavoriteCityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCityDao {
    @Query("SELECT * FROM favorite_cities ORDER BY `order` ASC")
    fun getAllFavorites(): Flow<List<FavoriteCityEntity>>

    @Query("SELECT * FROM favorite_cities WHERE id = :id")
    suspend fun getFavoriteById(id: String): FavoriteCityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(city: FavoriteCityEntity)

    @Delete
    suspend fun deleteFavorite(city: FavoriteCityEntity)

    @Query("DELETE FROM favorite_cities WHERE id = :id")
    suspend fun deleteFavoriteById(id: String)

    @Update
    suspend fun updateFavorite(city: FavoriteCityEntity)

    @Query("SELECT MAX(`order`) FROM favorite_cities")
    suspend fun getMaxOrder(): Int?
}
