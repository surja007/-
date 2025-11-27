package com.smartweather.domain.repository

import com.smartweather.domain.model.FavoriteCity
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<FavoriteCity>>
    suspend fun addFavorite(city: FavoriteCity): Result<Unit>
    suspend fun removeFavorite(cityId: String): Result<Unit>
    suspend fun reorderFavorites(cities: List<FavoriteCity>): Result<Unit>
    suspend fun isFavorite(cityId: String): Boolean
}
