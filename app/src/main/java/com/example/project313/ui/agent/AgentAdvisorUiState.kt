package com.example.project313.ui.agent

import com.example.project313.data.mock.MockData
import com.example.project313.data.model.Advisor
import com.example.project313.data.model.NavigationTab

data class AgentAdvisorUiState(
    val advisor: Advisor = MockData.sarahJenkins,
    val confidentialPortfoliosCount: Int = 4,
    val currentNavigationTab: NavigationTab = NavigationTab.AGENT,
    val feedbackMessage: String? = null
)

