package com.example.project313.data.model

import androidx.annotation.DrawableRes

data class Estate(
    val id: String,
    val title: String,
    val price: Long,
    val formattedPrice: String,
    val estimatedMonthly: String,
    val address: String,
    val beds: Int? = null,
    val baths: Int? = null,
    val sqft: Int? = null,
    val rating: Float? = null,
    val reviewCount: Int? = null,
    @param:DrawableRes val imageResId: Int,
    val statusTag: String? = null,
    val areaTag: String? = null,
    val verifiedLuxury: Boolean = false,
    val isFeatured: Boolean = false,
    val category: FilterCategory = FilterCategory.VILLAS,
    val description: String? = null,
    val advisor: Advisor? = null,
    val acreage: String? = null,
    val leedRating: String? = null,
    val narrative: String? = null,
    val originalPrice: String? = null,
    val priceDropNotice: String? = null,
    val openHouseNotice: String? = null,
    val availabilityTag: String? = null,
    val terraceSpec: String? = null,
    val isUnderContract: Boolean = false,
    val photoCount: Int = 4,
    val activePhotoIndex: Int = 0
)
