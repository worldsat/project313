package com.example.project313.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Estate
import com.example.project313.data.repository.FirebaseEstateRepository
import com.example.project313.ui.saved.components.SavedTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedPropertiesViewModel(
    private val repository: FirebaseEstateRepository = FirebaseEstateRepository.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SavedPropertiesUiState(
            savedEstates = MockData.savedEstates,
            totalSavedCount = MockData.savedEstates.size
        )
    )
    val uiState: StateFlow<SavedPropertiesUiState> = _uiState.asStateFlow()

    private var allSavedEstates: List<Estate> = MockData.savedEstates

    init {
        viewModelScope.launch {
            repository.getEstatesFlow().collect { estates ->
                val saved = estates.filter {
                    it.priceDropNotice != null ||
                    it.openHouseNotice != null ||
                    it.availabilityTag != null ||
                    it.id == "the-glass-pavilions" ||
                    it.id == "emerald-grove-residence"
                }
                if (saved.isNotEmpty()) {
                    allSavedEstates = saved
                    _uiState.update { state ->
                        state.copy(
                            savedEstates = filterByTab(allSavedEstates, state.selectedTab),
                            totalSavedCount = allSavedEstates.size
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: SavedPropertiesUiEvent) {
        when (event) {
            is SavedPropertiesUiEvent.OnTabSelected -> {
                _uiState.update { state ->
                    state.copy(
                        selectedTab = event.tab,
                        savedEstates = filterByTab(allSavedEstates, event.tab)
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
                repository.toggleSaveEstate(event.estateId, false)
                allSavedEstates = allSavedEstates.filter { it.id != event.estateId }

                _uiState.update { state ->
                    val updated = filterByTab(allSavedEstates, state.selectedTab)
                    state.copy(
                        savedEstates = updated,
                        totalSavedCount = allSavedEstates.size,
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
                    it.copy(feedbackMessage = "Estate link copied to clipboard")
                }
            }
            is SavedPropertiesUiEvent.OnNotesClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Opening private notes")
                }
            }
            is SavedPropertiesUiEvent.OnConsultAdvisorClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Connecting with private advisor Sarah Jenkins")
                }
            }
            is SavedPropertiesUiEvent.OnSortClicked -> {
                _uiState.update { state ->
                    val sorted = state.savedEstates.reversed()
                    state.copy(
                        savedEstates = sorted,
                        feedbackMessage = "Sorted saved properties"
                    )
                }
            }
            is SavedPropertiesUiEvent.OnNewCollectionClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Create collection dialog opened")
                }
            }
            is SavedPropertiesUiEvent.OnViewAllCollectionsClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Viewing all curated collections")
                }
            }
            is SavedPropertiesUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
            is SavedPropertiesUiEvent.OnNotificationsClicked -> {
                _uiState.update { it.copy(unreadNotificationsCount = 0) }
            }
            is SavedPropertiesUiEvent.OnProfileClicked -> {
                // Profile action
            }
            is SavedPropertiesUiEvent.OnEstateClicked -> {
                // Handled in NavHost
            }
            is SavedPropertiesUiEvent.OnNavigationTabSelected -> {
                _uiState.update { it.copy(currentNavigationTab = event.tab) }
            }
        }
    }

    private fun filterByTab(estates: List<Estate>, tab: SavedTab): List<Estate> {
        return when (tab) {
            SavedTab.ALL -> estates
            SavedTab.COLLECTIONS -> estates.take(3)
            SavedTab.ALERTS -> estates.filter {
                it.priceDropNotice != null || it.openHouseNotice != null || it.availabilityTag != null
            }
        }
    }
}
