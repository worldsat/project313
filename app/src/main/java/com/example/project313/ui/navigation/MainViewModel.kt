package com.example.project313.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow<VerdantScreen>(VerdantScreen.Intro)
    val currentScreen: StateFlow<VerdantScreen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: VerdantScreen) {
        _currentScreen.update { screen }
    }

    fun navigateToIntro() {
        _currentScreen.update { VerdantScreen.Intro }
    }

    fun navigateToExplore() {
        _currentScreen.update { VerdantScreen.Explore }
    }

    fun navigateToDetail(estateId: String) {
        _currentScreen.update { VerdantScreen.EstateDetail(estateId) }
    }

    fun navigateToMap() {
        _currentScreen.update { VerdantScreen.Map }
    }

    fun navigateToSaved() {
        _currentScreen.update { VerdantScreen.Saved }
    }

    fun navigateBack() {
        _currentScreen.update { VerdantScreen.Explore }
    }
}
