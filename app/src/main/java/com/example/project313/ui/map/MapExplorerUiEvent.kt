package com.example.project313.ui.map

import com.example.project313.data.model.NavigationTab

sealed interface MapExplorerUiEvent {
    data class OnPinSelected(val estateId: String) : MapExplorerUiEvent
    data class OnToggleSave(val estateId: String) : MapExplorerUiEvent
    data class OnNavigationTabSelected(val tab: NavigationTab) : MapExplorerUiEvent
    data class OnViewDetailsClicked(val estateId: String) : MapExplorerUiEvent
    data object OnSearchClicked : MapExplorerUiEvent
    data object OnLayersClicked : MapExplorerUiEvent
    data object OnLocateClicked : MapExplorerUiEvent
    data object OnFiltersClicked : MapExplorerUiEvent
    data object OnPriceFilterClicked : MapExplorerUiEvent
    data object OnBedsFilterClicked : MapExplorerUiEvent
    data object OnDirectionsClicked : MapExplorerUiEvent
    data object OnListViewClicked : MapExplorerUiEvent
    data object OnNotificationsClicked : MapExplorerUiEvent
    data object OnProfileClicked : MapExplorerUiEvent
    data object OnDismissFeedback : MapExplorerUiEvent
}
