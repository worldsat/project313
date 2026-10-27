package com.example.project313.ui.explore

import androidx.lifecycle.ViewModel
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Estate
import com.example.project313.data.model.FilterCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExploreViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ExploreUiState(
            featuredEstate = MockData.featuredEstate,
            popularEstates = MockData.popularEstates,
            savedEstateIds = setOf("the-glass-pavilions")
        )
    )
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    fun onEvent(event: ExploreUiEvent) {
        when (event) {
            is ExploreUiEvent.OnSearchQueryChanged -> {
                _uiState.update { state ->
                    state.copy(
                        searchQuery = event.query,
                        popularEstates = filterEstates(event.query, state.selectedCategory)
                    )
                }
            }
            is ExploreUiEvent.OnCategorySelected -> {
                _uiState.update { state ->
                    state.copy(
                        selectedCategory = event.category,
                        popularEstates = filterEstates(state.searchQuery, event.category)
                    )
                }
            }
            is ExploreUiEvent.OnToggleSaveEstate -> {
                _uiState.update { state ->
                    val newSaved = state.savedEstateIds.toMutableSet()
                    if (newSaved.contains(event.estateId)) {
                        newSaved.remove(event.estateId)
                    } else {
                        newSaved.add(event.estateId)
                    }
                    state.copy(savedEstateIds = newSaved)
                }
            }
            is ExploreUiEvent.OnNavigationTabSelected -> {
                _uiState.update { it.copy(currentNavigationTab = event.tab) }
            }
            is ExploreUiEvent.OnEstateClicked -> {
                // Navigates to detail view
            }
            is ExploreUiEvent.OnFilterClicked -> {
                // Opens filter sheet/dialog
            }
            is ExploreUiEvent.OnNotificationsClicked -> {
                // Clears unread or opens notification dialog
                _uiState.update { it.copy(unreadNotificationsCount = 0) }
            }
            is ExploreUiEvent.OnProfileClicked -> {
                // Handles profile action
            }
            is ExploreUiEvent.OnMapViewClicked -> {
                _uiState.update { it.copy(currentNavigationTab = com.example.project313.data.model.NavigationTab.MAP) }
            }
            is ExploreUiEvent.OnViewAllPopularClicked -> {
                // View all handler
            }
        }
    }

    private fun filterEstates(query: String, category: FilterCategory): List<Estate> {
        return MockData.popularEstates.filter { estate ->
            val matchesQuery = query.isBlank() ||
                estate.title.contains(query, ignoreCase = true) ||
                estate.address.contains(query, ignoreCase = true) ||
                estate.areaTag?.contains(query, ignoreCase = true) == true
            val matchesCategory = when (category) {
                FilterCategory.ALL -> true
                FilterCategory.VILLAS -> estate.category == FilterCategory.VILLAS
                FilterCategory.PENTHOUSES -> estate.category == FilterCategory.PENTHOUSES
                FilterCategory.ECO_HOMES -> estate.category == FilterCategory.ECO_HOMES
            }
            matchesQuery && matchesCategory
        }
    }
}
