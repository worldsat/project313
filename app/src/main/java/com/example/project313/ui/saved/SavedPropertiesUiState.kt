package com.example.project313.ui.saved

import com.example.project313.data.model.Estate
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.saved.components.SavedTab

data class SavedPropertiesUiState(
    val selectedTab: SavedTab = SavedTab.ALL,
    val savedEstates: List<Estate> = emptyList(),
    val totalSavedCount: Int = 6,
    val selectedCollection: String? = null,
    val currentNavigationTab: NavigationTab = NavigationTab.SAVED,
    val unreadNotificationsCount: Int = 1,
    val userLocation: String = "Austin, TX",
    val feedbackMessage: String? = null
)
