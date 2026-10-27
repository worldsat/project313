package com.example.project313.ui.saved

import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.saved.components.SavedTab

sealed interface SavedPropertiesUiEvent {
    data class OnTabSelected(val tab: SavedTab) : SavedPropertiesUiEvent
    data class OnEstateClicked(val estateId: String) : SavedPropertiesUiEvent
    data class OnToggleSave(val estateId: String) : SavedPropertiesUiEvent
    data class OnBookTourClicked(val estateId: String) : SavedPropertiesUiEvent
    data class OnShareClicked(val estateId: String) : SavedPropertiesUiEvent
    data class OnNotesClicked(val estateId: String) : SavedPropertiesUiEvent
    data class OnCollectionSelected(val collectionName: String) : SavedPropertiesUiEvent
    data class OnNavigationTabSelected(val tab: NavigationTab) : SavedPropertiesUiEvent
    data object OnSortClicked : SavedPropertiesUiEvent
    data object OnNewCollectionClicked : SavedPropertiesUiEvent
    data object OnViewAllCollectionsClicked : SavedPropertiesUiEvent
    data object OnConsultAdvisorClicked : SavedPropertiesUiEvent
    data object OnNotificationsClicked : SavedPropertiesUiEvent
    data object OnProfileClicked : SavedPropertiesUiEvent
    data object OnDismissFeedback : SavedPropertiesUiEvent
}
