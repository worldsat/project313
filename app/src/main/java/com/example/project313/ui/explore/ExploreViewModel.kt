package com.example.project313.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Estate
import com.example.project313.data.model.FilterCategory
import com.example.project313.data.repository.FirebaseEstateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val repository: FirebaseEstateRepository = FirebaseEstateRepository.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ExploreUiState(
            featuredEstate = MockData.featuredEstate,
            popularEstates = MockData.popularEstates,
            savedEstateIds = setOf("the-glass-pavilions")
        )
    )
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    private var allEstates: List<Estate> = listOf(MockData.featuredEstate) + MockData.popularEstates

    init {
        viewModelScope.launch {
            repository.getEstatesFlow().collect { estates ->
                if (estates.isNotEmpty()) {
                    allEstates = estates
                    val featured = estates.firstOrNull { it.isFeatured } ?: estates.first()
                    val popular = estates.filter { it.id != featured.id }

                    _uiState.update { state ->
                        state.copy(
                            featuredEstate = featured,
                            popularEstates = filterEstates(popular, state.searchQuery, state.selectedCategory)
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ExploreUiEvent) {
        when (event) {
            is ExploreUiEvent.OnSearchQueryChanged -> {
                _uiState.update { state ->
                    val popular = allEstates.filter { it.id != state.featuredEstate?.id }
                    state.copy(
                        searchQuery = event.query,
                        popularEstates = filterEstates(popular, event.query, state.selectedCategory)
                    )
                }
            }
            is ExploreUiEvent.OnCategorySelected -> {
                _uiState.update { state ->
                    val popular = allEstates.filter { it.id != state.featuredEstate?.id }
                    state.copy(
                        selectedCategory = event.category,
                        popularEstates = filterEstates(popular, state.searchQuery, event.category)
                    )
                }
            }
            is ExploreUiEvent.OnToggleSaveEstate -> {
                val willBeSaved = !_uiState.value.savedEstateIds.contains(event.estateId)
                repository.toggleSaveEstate(event.estateId, willBeSaved)

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
                // Handled in NavHost
            }
            is ExploreUiEvent.OnFilterClicked -> {
                // Opens filter sheet
            }
            is ExploreUiEvent.OnNotificationsClicked -> {
                _uiState.update { it.copy(unreadNotificationsCount = 0) }
            }
            is ExploreUiEvent.OnProfileClicked -> {
                // Profile action
            }
            is ExploreUiEvent.OnMapViewClicked -> {
                _uiState.update { it.copy(currentNavigationTab = com.example.project313.data.model.NavigationTab.MAP) }
            }
            is ExploreUiEvent.OnViewAllPopularClicked -> {
                // View all handler
            }
        }
    }

    private fun filterEstates(estates: List<Estate>, query: String, category: FilterCategory): List<Estate> {
        return estates.filter { estate ->
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
