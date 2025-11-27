package com.smartweather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartweather.domain.model.FavoriteCity

@Entity(tableName = "favorite_cities")
data class FavoriteCityEntity(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val order: Int,
    val addedAt: Long
) {
    fun toDomain() = FavoriteCity(
        id = id,
        name = name,
        latitude = latitude,
        longitude = longitude,
        order = order,
        addedAt = addedAt
    )

    companion object {
        fun fromDomain(city: FavoriteCity) = FavoriteCityEntity(
            id = city.id,
            name = city.name,
            latitude = city.latitude,
            longitude = city.longitude,
            order = city.order,
            addedAt = city.addedAt
        )
    }
}
