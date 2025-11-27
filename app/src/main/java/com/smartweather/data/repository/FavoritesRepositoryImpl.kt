package com.smartweather.data.repository

import com.smartweather.data.local.dao.FavoriteCityDao
import com.smartweather.data.local.entity.FavoriteCityEntity
import com.smartweather.domain.model.FavoriteCity
import com.smartweather.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteCityDao
) : FavoritesRepository {

    override fun getAllFavorites(): Flow<List<FavoriteCity>> =
        dao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun addFavorite(city: FavoriteCity): Result<Unit> = try {
        val maxOrder = dao.getMaxOrder() ?: -1
        val cityWithOrder = city.copy(order = maxOrder + 1)
        dao.insertFavorite(FavoriteCityEntity.fromDomain(cityWithOrder))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeFavorite(cityId: String): Result<Unit> = try {
        dao.deleteFavoriteById(cityId)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun reorderFavorites(cities: List<FavoriteCity>): Result<Unit> = try {
        cities.forEachIndexed { index, city ->
            val updated = city.copy(order = index)
            dao.updateFavorite(FavoriteCityEntity.fromDomain(updated))
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun isFavorite(cityId: String): Boolean =
        dao.getFavoriteById(cityId) != null
}
