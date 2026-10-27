package com.example.project313.ui.detail

import androidx.lifecycle.ViewModel
import com.example.project313.data.mock.MockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EstateDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        EstateDetailUiState(
            estate = MockData.featuredEstate,
            isSaved = true
        )
    )
    val uiState: StateFlow<EstateDetailUiState> = _uiState.asStateFlow()

    fun loadEstate(estateId: String) {
        val estate = MockData.getEstateById(estateId)
        _uiState.update {
            it.copy(
                estate = estate,
                isSaved = estate.id == "the-glass-pavilions"
            )
        }
    }

    fun onEvent(event: EstateDetailUiEvent) {
        when (event) {
            is EstateDetailUiEvent.OnToggleSave -> {
                _uiState.update { it.copy(isSaved = !it.isSaved) }
            }
            is EstateDetailUiEvent.OnShareClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Listing link copied to clipboard") }
            }
            is EstateDetailUiEvent.OnBookTourClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Private viewing consultation requested") }
            }
            is EstateDetailUiEvent.OnCallAdvisorClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Connecting with advisor Sarah Jenkins...") }
            }
            is EstateDetailUiEvent.OnChatAdvisorClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Starting private chat with Sarah Jenkins...") }
            }
            is EstateDetailUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
            is EstateDetailUiEvent.OnBackClicked -> {
                // Handled in UI layer / Navigation
            }
        }
    }
}
