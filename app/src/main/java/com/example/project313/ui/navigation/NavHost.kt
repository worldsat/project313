package com.example.project313.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.agent.AgentAdvisorScreen
import com.example.project313.ui.agent.AgentAdvisorViewModel
import com.example.project313.ui.detail.EstateDetailScreen
import com.example.project313.ui.detail.EstateDetailViewModel
import com.example.project313.ui.explore.ExploreEstatesScreen
import com.example.project313.ui.explore.ExploreUiEvent
import com.example.project313.ui.explore.ExploreViewModel
import com.example.project313.ui.map.MapExplorerScreen
import com.example.project313.ui.map.MapExplorerUiEvent
import com.example.project313.ui.map.MapExplorerViewModel

import com.example.project313.ui.intro.VerdantIntroScreen
import com.example.project313.ui.intro.IntroViewModel
import com.example.project313.ui.saved.SavedPropertiesScreen
import com.example.project313.ui.saved.SavedPropertiesUiEvent
import com.example.project313.ui.saved.SavedPropertiesViewModel
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.project313.data.mock.MockData
import com.example.project313.ui.explore.ExploreUiState
import com.example.project313.data.model.FilterCategory
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun NavHost(
    mainViewModel: MainViewModel = viewModel(),
    introViewModel: IntroViewModel = viewModel(),
    exploreViewModel: ExploreViewModel = viewModel(),
    estateDetailViewModel: EstateDetailViewModel = viewModel(),
    mapExplorerViewModel: MapExplorerViewModel = viewModel(),
    savedPropertiesViewModel: SavedPropertiesViewModel = viewModel(),
    agentAdvisorViewModel: AgentAdvisorViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val currentScreen by mainViewModel.currentScreen.collectAsState()

    BackHandler(enabled = currentScreen != VerdantScreen.Explore && currentScreen != VerdantScreen.Intro) {
        mainViewModel.navigateBack()
    }

    Crossfade(
        targetState = currentScreen,
        animationSpec = tween(durationMillis = 300),
        label = "VerdantScreenTransition",
        modifier = modifier
    ) { screen ->
        when (screen) {
            is VerdantScreen.Intro -> {
                val introUiState by introViewModel.uiState.collectAsState()
                VerdantIntroScreen(
                    uiState = introUiState,
                    onEvent = introViewModel::onEvent,
                    onNavigateToExplore = { mainViewModel.navigateToExplore() }
                )
            }
            is VerdantScreen.Explore -> {
                val exploreUiState by exploreViewModel.uiState.collectAsState()
                ExploreEstatesScreen(
                    uiState = exploreUiState,
                    onEvent = { event ->
                        when (event) {
                            is ExploreUiEvent.OnEstateClicked -> {
                                estateDetailViewModel.loadEstate(event.estateId)
                                mainViewModel.navigateToDetail(event.estateId)
                            }
                            is ExploreUiEvent.OnMapViewClicked -> {
                                mainViewModel.navigateToMap()
                            }
                            is ExploreUiEvent.OnNavigationTabSelected -> {
                                when (event.tab) {
                                    NavigationTab.MAP -> mainViewModel.navigateToMap()
                                    NavigationTab.SAVED -> mainViewModel.navigateToSaved()
                                    NavigationTab.AGENT -> mainViewModel.navigateToAgent()
                                    else -> exploreViewModel.onEvent(event)
                                }
                            }
                            else -> exploreViewModel.onEvent(event)
                        }
                    }
                )
            }
            is VerdantScreen.EstateDetail -> {
                val detailUiState by estateDetailViewModel.uiState.collectAsState()
                EstateDetailScreen(
                    uiState = detailUiState,
                    onEvent = estateDetailViewModel::onEvent,
                    onNavigateBack = { mainViewModel.navigateBack() }
                )
            }
            is VerdantScreen.Map -> {
                val mapUiState by mapExplorerViewModel.uiState.collectAsState()
                MapExplorerScreen(
                    uiState = mapUiState,
                    onEvent = { event ->
                        when (event) {
                            is MapExplorerUiEvent.OnViewDetailsClicked -> {
                                estateDetailViewModel.loadEstate(event.estateId)
                                mainViewModel.navigateToDetail(event.estateId)
                            }
                            is MapExplorerUiEvent.OnNavigationTabSelected -> {
                                when (event.tab) {
                                    NavigationTab.EXPLORE -> mainViewModel.navigateBack()
                                    NavigationTab.SAVED -> mainViewModel.navigateToSaved()
                                    NavigationTab.AGENT -> mainViewModel.navigateToAgent()
                                    else -> mapExplorerViewModel.onEvent(event)
                                }
                            }
                            else -> mapExplorerViewModel.onEvent(event)
                        }
                    },
                    onNavigateToDetail = { estateId ->
                        estateDetailViewModel.loadEstate(estateId)
                        mainViewModel.navigateToDetail(estateId)
                    },
                    onNavigateToExplore = {
                        mainViewModel.navigateBack()
                    }
                )
            }
            is VerdantScreen.Saved -> {
                val savedUiState by savedPropertiesViewModel.uiState.collectAsState()
                SavedPropertiesScreen(
                    uiState = savedUiState,
                    onEvent = { event ->
                        when (event) {
                            is SavedPropertiesUiEvent.OnNavigationTabSelected -> {
                                when (event.tab) {
                                    NavigationTab.EXPLORE -> mainViewModel.navigateBack()
                                    NavigationTab.MAP -> mainViewModel.navigateToMap()
                                    NavigationTab.SAVED -> {}
                                    NavigationTab.AGENT -> mainViewModel.navigateToAgent()
                                }
                            }
                            else -> savedPropertiesViewModel.onEvent(event)
                        }
                    },
                    onNavigateToDetail = { estateId ->
                        estateDetailViewModel.loadEstate(estateId)
                        mainViewModel.navigateToDetail(estateId)
                    },
                    onNavigateToTab = { tab ->
                        when (tab) {
                            NavigationTab.EXPLORE -> mainViewModel.navigateBack()
                            NavigationTab.MAP -> mainViewModel.navigateToMap()
                            NavigationTab.SAVED -> {}
                            NavigationTab.AGENT -> mainViewModel.navigateToAgent()
                        }
                    }
                )
            }
            is VerdantScreen.Agent -> {
                val agentUiState by agentAdvisorViewModel.uiState.collectAsState()
                val savedUiState by savedPropertiesViewModel.uiState.collectAsState()
                AgentAdvisorScreen(
                    uiState = agentUiState,
                    savedCount = savedUiState.totalSavedCount,
                    onEvent = agentAdvisorViewModel::onEvent,
                    onNavigateToTab = { tab ->
                        when (tab) {
                            NavigationTab.EXPLORE -> mainViewModel.navigateToExplore()
                            NavigationTab.MAP -> mainViewModel.navigateToMap()
                            NavigationTab.SAVED -> mainViewModel.navigateToSaved()
                            NavigationTab.AGENT -> {}
                        }
                    }
                )
            }
        }
    }
}

@Preview(
    name = "NavHost Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun NavHostPreview() {
    VerdantEstatesTheme {
        ExploreEstatesScreen(
            uiState = ExploreUiState(
                featuredEstate = MockData.featuredEstate,
                popularEstates = MockData.popularEstates,
                savedEstateIds = MockData.savedEstates.map { it.id }.toSet(),
                selectedCategory = FilterCategory.ALL,
                currentNavigationTab = NavigationTab.EXPLORE
            ),
            onEvent = {}
        )
    }
}
