package com.example.project313.data.model

import androidx.annotation.DrawableRes

data class Advisor(
    val name: String,
    val title: String,
    val division: String,
    @param:DrawableRes val avatarResId: Int,
    val licenseId: String? = null
)
