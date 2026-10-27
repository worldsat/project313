package com.example.project313.ui.intro

sealed interface IntroUiEvent {
    data object OnExploreEstatesClicked : IntroUiEvent
    data object OnSkipTourClicked : IntroUiEvent
    data object OnTalkToAdvisorClicked : IntroUiEvent
    data object OnMemberSignInClicked : IntroUiEvent
    data class OnSlideChanged(val index: Int) : IntroUiEvent
    data object OnDismissFeedback : IntroUiEvent
}
