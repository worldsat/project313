package com.example.project313.ui.saved

import androidx.lifecycle.ViewModel
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Estate
import com.example.project313.ui.saved.components.SavedTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SavedPropertiesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SavedPropertiesUiState(
            savedEstates = MockData.savedEstates,
            totalSavedCount = 6
        )
    )
    val uiState: StateFlow<SavedPropertiesUiState> = _uiState.asStateFlow()

    fun onEvent(event: SavedPropertiesUiEvent) {
        when (event) {
            is SavedPropertiesUiEvent.OnTabSelected -> {
                _uiState.update { state ->
                    val filtered = when (event.tab) {
                        SavedTab.ALL -> MockData.savedEstates
                        SavedTab.COLLECTIONS -> MockData.savedEstates.take(3)
                        SavedTab.ALERTS -> MockData.savedEstates.filter {
                            it.priceDropNotice != null || it.openHouseNotice != null || it.availabilityTag != null
                        }
                    }
                    state.copy(
                        selectedTab = event.tab,
                        savedEstates = filtered
                    )
                }
            }
            is SavedPropertiesUiEvent.OnCollectionSelected -> {
                _uiState.update { state ->
                    state.copy(
                        selectedCollection = event.collectionName,
                        feedbackMessage = "Filtered by ${event.collectionName}"
                    )
                }
            }
            is SavedPropertiesUiEvent.OnToggleSave -> {
                _uiState.update { state ->
                    val updated = state.savedEstates.filter { it.id != event.estateId }
                    state.copy(
                        savedEstates = updated,
                        totalSavedCount = updated.size,
                        feedbackMessage = "Removed from saved shortlist"
                    )
                }
            }
            is SavedPropertiesUiEvent.OnBookTourClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Viewing tour requested for estate")
                }
            }
            is SavedPropertiesUiEvent.OnShareClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Listing link copied to clipboard")
                }
            }
            is SavedPropertiesUiEvent.OnNotesClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Opening private client notes...")
                }
            }
            is SavedPropertiesUiEvent.OnConsultAdvisorClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Consultation requested with advisor Sarah Jenkins")
                }
            }
            is SavedPropertiesUiEvent.OnNewCollectionClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Creating new curated collection...")
                }
            }
            is SavedPropertiesUiEvent.OnSortClicked -> {
                _uiState.update { state ->
                    val sorted = state.savedEstates.reversed()
                    state.copy(
                        savedEstates = sorted,
                        feedbackMessage = "Estates sorted by recent updates"
                    )
                }
            }
            is SavedPropertiesUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
            is SavedPropertiesUiEvent.OnNotificationsClicked -> {
                _uiState.update { it.copy(unreadNotificationsCount = 0) }
            }
            else -> {}
        }
    }
}
