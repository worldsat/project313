package com.example.project313.ui.navigation

sealed class VerdantScreen {
    data object Intro : VerdantScreen()
    data object Explore : VerdantScreen()
    data class EstateDetail(val estateId: String = "the-glass-pavilions") : VerdantScreen()
    data object Map : VerdantScreen()
    data object Saved : VerdantScreen()
}
