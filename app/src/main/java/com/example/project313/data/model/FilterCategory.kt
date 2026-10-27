package com.example.project313.data.model

enum class FilterCategory(
    val label: String,
    val isEco: Boolean = false
) {
    ALL("All Homes"),
    VILLAS("Villas"),
    PENTHOUSES("Penthouses"),
    ECO_HOMES("Eco-Homes", isEco = true)
}
