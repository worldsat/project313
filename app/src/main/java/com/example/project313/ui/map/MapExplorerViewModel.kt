package com.example.project313.ui.map

import androidx.lifecycle.ViewModel
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.MapPin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MapExplorerViewModel : ViewModel() {

    private val defaultPins = listOf(
        MapPin("the-glass-pavilions", "$3.85M", 0.38f, 0.23f, latitude = 30.2985, longitude = -97.8050),
        MapPin("emerald-grove-residence", "$2.45M", 0.16f, 0.18f, latitude = 30.2540, longitude = -97.8220),
        MapPin("the-cedar-ridge-villa", "$1.89M", 0.78f, 0.17f, latitude = 30.2485, longitude = -97.7485),
        MapPin("skyline-botanica-penthouse", "$4.20M", 0.68f, 0.34f, latitude = 30.2610, longitude = -97.7405),
        MapPin("oakwood-sanctuary-cottage", "$1.45M", 0.24f, 0.41f, latitude = 30.3010, longitude = -97.7660)
    )

    private val _uiState = MutableStateFlow(
        MapExplorerUiState(
            pins = defaultPins,
            selectedEstateId = "the-glass-pavilions",
            selectedEstate = MockData.featuredEstate,
            savedEstateIds = setOf("the-glass-pavilions")
        )
    )
    val uiState: StateFlow<MapExplorerUiState> = _uiState.asStateFlow()

    fun onEvent(event: MapExplorerUiEvent) {
        when (event) {
            is MapExplorerUiEvent.OnPinSelected -> {
                val estate = MockData.getEstateById(event.estateId)
                _uiState.update {
                    it.copy(
                        selectedEstateId = event.estateId,
                        selectedEstate = estate
                    )
                }
            }
            is MapExplorerUiEvent.OnToggleSave -> {
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
            is MapExplorerUiEvent.OnDirectionsClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Navigating to ${it.selectedEstate?.title ?: "Estate"}...")
                }
            }
            is MapExplorerUiEvent.OnLayersClicked -> {
                val nextLayer = when (_uiState.value.currentLayerType) {
                    MapLayerType.STREET -> MapLayerType.TOPO
                    MapLayerType.TOPO -> MapLayerType.SATELLITE
                    MapLayerType.SATELLITE -> MapLayerType.STREET
                }
                _uiState.update {
                    it.copy(
                        currentLayerType = nextLayer,
                        feedbackMessage = "Switched to ${nextLayer.displayName}"
                    )
                }
            }
            is MapExplorerUiEvent.OnLocateClicked -> {
                _uiState.update {
                    it.copy(
                        locateTrigger = System.currentTimeMillis(),
                        feedbackMessage = "Centered on Austin Hill Country reserve"
                    )
                }
            }
            is MapExplorerUiEvent.OnFiltersClicked -> {
                _uiState.update { it.copy(feedbackMessage = "3 luxury filters active") }
            }
            is MapExplorerUiEvent.OnPriceFilterClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Price filter: $1.5M - $5M") }
            }
            is MapExplorerUiEvent.OnBedsFilterClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Filter: 3+ Bedrooms selected") }
            }
            is MapExplorerUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
            is MapExplorerUiEvent.OnNotificationsClicked -> {
                _uiState.update { it.copy(unreadNotificationsCount = 0) }
            }
            else -> {}
        }
    }
}
