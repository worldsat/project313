package com.example.project313.ui.agent

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AgentAdvisorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AgentAdvisorUiState())
    val uiState: StateFlow<AgentAdvisorUiState> = _uiState.asStateFlow()

    fun onEvent(event: AgentAdvisorUiEvent) {
        when (event) {
            is AgentAdvisorUiEvent.OnMessageClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Opening secure messaging...") }
            }
            is AgentAdvisorUiEvent.OnCallDirectClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Calling +1 (512) 840-3130...") }
            }
            is AgentAdvisorUiEvent.OnConfidentialAccessClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Requesting confidential portfolio dossier...") }
            }
            is AgentAdvisorUiEvent.OnNavigationTabSelected -> {
                _uiState.update { it.copy(currentNavigationTab = event.tab) }
            }
            is AgentAdvisorUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
        }
    }
}

