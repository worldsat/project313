package com.example.project313.ui.explore

import com.example.project313.data.model.Estate
import com.example.project313.data.model.FilterCategory
import com.example.project313.data.model.NavigationTab

data class ExploreUiState(
    val searchQuery: String = "",
    val selectedCategory: FilterCategory = FilterCategory.ALL,
    val featuredEstate: Estate? = null,
    val popularEstates: List<Estate> = emptyList(),
    val savedEstateIds: Set<String> = emptySet(),
    val currentNavigationTab: NavigationTab = NavigationTab.EXPLORE,
    val userLocation: String = "Austin, TX",
    val unreadNotificationsCount: Int = 1,
    val isLoading: Boolean = false
)
