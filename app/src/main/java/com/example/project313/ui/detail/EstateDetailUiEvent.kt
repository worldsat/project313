package com.example.project313.ui.detail

sealed interface EstateDetailUiEvent {
    data object OnBackClicked : EstateDetailUiEvent
    data object OnToggleSave : EstateDetailUiEvent
    data object OnShareClicked : EstateDetailUiEvent
    data object OnBookTourClicked : EstateDetailUiEvent
    data object OnCallAdvisorClicked : EstateDetailUiEvent
    data object OnChatAdvisorClicked : EstateDetailUiEvent
    data object OnDismissFeedback : EstateDetailUiEvent
}
