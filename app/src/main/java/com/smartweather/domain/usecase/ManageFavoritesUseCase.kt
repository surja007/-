package com.smartweather.domain.usecase

import com.smartweather.domain.model.FavoriteCity
import com.smartweather.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    fun getAllFavorites(): Flow<List<FavoriteCity>> = repository.getAllFavorites()

    suspend fun addFavorite(city: FavoriteCity): Result<Unit> = repository.addFavorite(city)

    suspend fun removeFavorite(cityId: String): Result<Unit> = repository.removeFavorite(cityId)

    suspend fun reorderFavorites(cities: List<FavoriteCity>): Result<Unit> =
        repository.reorderFavorites(cities)

    suspend fun isFavorite(cityId: String): Boolean = repository.isFavorite(cityId)
}
