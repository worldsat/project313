package com.example.project313.ui.intro

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IntroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState: StateFlow<IntroUiState> = _uiState.asStateFlow()

    fun onEvent(event: IntroUiEvent) {
        when (event) {
            is IntroUiEvent.OnSlideChanged -> {
                _uiState.update { it.copy(currentSlideIndex = event.index) }
            }
            is IntroUiEvent.OnTalkToAdvisorClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Connecting with Private Client Advisor Sarah Jenkins...")
                }
            }
            is IntroUiEvent.OnMemberSignInClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Private Client Member Portal opening...")
                }
            }
            is IntroUiEvent.OnDismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
            else -> {}
        }
    }
}
