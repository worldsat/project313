package com.example.project313.ui.explore

import com.example.project313.data.model.FilterCategory
import com.example.project313.data.model.NavigationTab

sealed interface ExploreUiEvent {
    data class OnSearchQueryChanged(val query: String) : ExploreUiEvent
    data class OnCategorySelected(val category: FilterCategory) : ExploreUiEvent
    data class OnEstateClicked(val estateId: String) : ExploreUiEvent
    data class OnToggleSaveEstate(val estateId: String) : ExploreUiEvent
    data class OnNavigationTabSelected(val tab: NavigationTab) : ExploreUiEvent
    data object OnFilterClicked : ExploreUiEvent
    data object OnNotificationsClicked : ExploreUiEvent
    data object OnProfileClicked : ExploreUiEvent
    data object OnMapViewClicked : ExploreUiEvent
    data object OnViewAllPopularClicked : ExploreUiEvent
}
