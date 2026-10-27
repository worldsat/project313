package com.example.project313.data.model

data class MapPin(
    val estateId: String,
    val priceLabel: String,
    val xPercent: Float = 0.5f,
    val yPercent: Float = 0.5f,
    val latitude: Double = 30.2672,
    val longitude: Double = -97.7431
)
