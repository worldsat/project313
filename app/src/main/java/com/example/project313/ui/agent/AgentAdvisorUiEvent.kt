package com.example.project313.ui.agent

import com.example.project313.data.model.NavigationTab

sealed interface AgentAdvisorUiEvent {
    data object OnMessageClicked : AgentAdvisorUiEvent
    data object OnCallDirectClicked : AgentAdvisorUiEvent
    data object OnConfidentialAccessClicked : AgentAdvisorUiEvent
    data class OnNavigationTabSelected(val tab: NavigationTab) : AgentAdvisorUiEvent
    data object OnDismissFeedback : AgentAdvisorUiEvent
}

