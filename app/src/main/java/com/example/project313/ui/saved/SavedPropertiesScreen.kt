package com.example.project313.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.explore.components.VerdantBottomNav
import com.example.project313.ui.saved.components.CuratedCollectionsRow
import com.example.project313.ui.saved.components.OffMarketAdvisorCard
import com.example.project313.ui.saved.components.PortfolioIntelligenceCard
import com.example.project313.ui.saved.components.SavedEstateCard
import com.example.project313.ui.saved.components.SavedHeaderAndFilterBar
import com.example.project313.ui.saved.components.SavedTab
import com.example.project313.ui.saved.components.SavedTopBar
import com.example.project313.ui.theme.Background
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun SavedPropertiesScreen(
    uiState: SavedPropertiesUiState,
    onEvent: (SavedPropertiesUiEvent) -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToTab: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(SavedPropertiesUiEvent.OnDismissFeedback)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            SavedTopBar(
                location = uiState.userLocation,
                unreadNotifications = uiState.unreadNotificationsCount,
                onNotificationsClick = { onEvent(SavedPropertiesUiEvent.OnNotificationsClicked) },
                onProfileClick = { onEvent(SavedPropertiesUiEvent.OnProfileClicked) }
            )
        },
        bottomBar = {
            VerdantBottomNav(
                selectedTab = NavigationTab.SAVED,
                onTabSelect = onNavigateToTab
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Background)
        ) {
            // 1. Screen Title ("Saved Estates"), Count Badge & Segmented Tab Control
            item {
                SavedHeaderAndFilterBar(
                    selectedTab = uiState.selectedTab,
                    onTabSelected = { onEvent(SavedPropertiesUiEvent.OnTabSelected(it)) },
                    onSortClick = { onEvent(SavedPropertiesUiEvent.OnSortClicked) },
                    propertiesCount = uiState.totalSavedCount
                )
            }

            // 2. Curated Collections Horizontal Carousel
            item {
                Spacer(modifier = Modifier.height(18.dp))
                CuratedCollectionsRow(
                    onNewCollectionClick = { onEvent(SavedPropertiesUiEvent.OnNewCollectionClicked) },
                    onCollectionClick = { onEvent(SavedPropertiesUiEvent.OnCollectionSelected(it)) },
                    onViewAllClick = { onEvent(SavedPropertiesUiEvent.OnViewAllCollectionsClicked) }
                )
            }

            // 3. Portfolio Intelligence Card (+2.4% Appraisal Metric)
            item {
                Spacer(modifier = Modifier.height(14.dp))
                PortfolioIntelligenceCard()
            }

            // 4. Saved Estate Cards List
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(
                items = uiState.savedEstates,
                key = { it.id }
            ) { estate ->
                SavedEstateCard(
                    estate = estate,
                    onEstateClick = onNavigateToDetail,
                    onToggleSave = { onEvent(SavedPropertiesUiEvent.OnToggleSave(it)) },
                    onBookTourClick = { onEvent(SavedPropertiesUiEvent.OnBookTourClicked(it)) },
                    onShareClick = { onEvent(SavedPropertiesUiEvent.OnShareClicked(it)) },
                    onNotesClick = { onEvent(SavedPropertiesUiEvent.OnNotesClicked(it)) }
                )
            }

            // 5. Off-Market Advisor Consultation Card
            item {
                OffMarketAdvisorCard(
                    onConsultClick = { onEvent(SavedPropertiesUiEvent.OnConsultAdvisorClicked) }
                )
            }

            // Bottom Spacer for Navigation Bar clearance
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(
    name = "Saved Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Preview(
    name = "Saved Screen - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun SavedPropertiesScreenPreview() {
    VerdantEstatesTheme {
        SavedPropertiesScreen(
            uiState = SavedPropertiesUiState(
                savedEstates = MockData.savedEstates,
                totalSavedCount = 6,
                selectedTab = SavedTab.ALL
            ),
            onEvent = {},
            onNavigateToDetail = {},
            onNavigateToTab = {}
        )
    }
}

@Preview(
    name = "Saved Screen - Empty State",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun SavedPropertiesScreenEmptyPreview() {
    VerdantEstatesTheme {
        SavedPropertiesScreen(
            uiState = SavedPropertiesUiState(
                savedEstates = emptyList(),
                totalSavedCount = 0,
                selectedTab = SavedTab.ALL
            ),
            onEvent = {},
            onNavigateToDetail = {},
            onNavigateToTab = {}
        )
    }
}
