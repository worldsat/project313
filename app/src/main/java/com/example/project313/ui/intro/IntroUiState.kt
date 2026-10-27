package com.example.project313.ui.intro

data class IntroUiState(
    val currentSlideIndex: Int = 0,
    val totalSlidesCount: Int = 3,
    val curatedHomesCount: Int = 42,
    val leedRating: String = "LEED Platinum",
    val reserveCategory: String = "PRIVATE RESERVE",
    val reserveTitle: String = "Austin Enclaves",
    val headline: String = "Discover Nature's Most\nRefined Living",
    val description: String = "Immerse yourself in handpicked eco-luxury estates, architectural sanctuaries, and off-market Austin reserves designed in harmony with nature.",
    val feedbackMessage: String? = null
)
