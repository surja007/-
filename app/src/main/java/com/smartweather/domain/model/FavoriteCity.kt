package com.smartweather.domain.model

data class FavoriteCity(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val order: Int,
    val addedAt: Long
)
