package com.example.project313.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.explore.components.VerdantBottomNav
import com.example.project313.ui.map.components.RealInteractiveMap
import com.example.project313.ui.map.components.ListViewFab
import com.example.project313.ui.map.components.MapEstatePreviewSheet
import com.example.project313.ui.map.components.MapFilterChipsRow
import com.example.project313.ui.map.components.MapSearchBar
import com.example.project313.ui.map.components.MapTopBar
import com.example.project313.ui.theme.Background
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun MapExplorerScreen(
    uiState: MapExplorerUiState,
    onEvent: (MapExplorerUiEvent) -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToExplore: () -> Unit,
    modifier: Modifier = Modifier
) {
    val estate = uiState.selectedEstate ?: MockData.featuredEstate
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(MapExplorerUiEvent.OnDismissFeedback)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MapTopBar(
                location = uiState.userLocation,
                unreadNotifications = uiState.unreadNotificationsCount,
                onNotificationsClick = { onEvent(MapExplorerUiEvent.OnNotificationsClicked) },
                onProfileClick = { onEvent(MapExplorerUiEvent.OnProfileClicked) }
            )
        },
        bottomBar = {
            VerdantBottomNav(
                selectedTab = NavigationTab.MAP,
                onTabSelect = { tab ->
                    if (tab == NavigationTab.EXPLORE) {
                        onNavigateToExplore()
                    } else {
                        onEvent(MapExplorerUiEvent.OnNavigationTabSelected(tab))
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 1. Real Interactive Map (OpenStreetMap & Esri layers, Austin, TX)
            RealInteractiveMap(
                pins = uiState.pins,
                selectedEstateId = uiState.selectedEstateId,
                onPinClick = { onEvent(MapExplorerUiEvent.OnPinSelected(it)) },
                layerType = uiState.currentLayerType.id,
                locateTrigger = uiState.locateTrigger,
                modifier = Modifier.fillMaxSize()
            )

            // 2. Top Floating Search and Filter Controls
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
            ) {
                MapSearchBar(
                    regionTitle = uiState.regionTitle,
                    activeReservesCount = uiState.activeReservesCount,
                    onSearchClick = { onEvent(MapExplorerUiEvent.OnSearchClicked) },
                    onLayersClick = { onEvent(MapExplorerUiEvent.OnLayersClicked) },
                    onLocateClick = { onEvent(MapExplorerUiEvent.OnLocateClicked) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                MapFilterChipsRow(
                    onFiltersClick = { onEvent(MapExplorerUiEvent.OnFiltersClicked) },
                    onPriceFilterClick = { onEvent(MapExplorerUiEvent.OnPriceFilterClicked) },
                    onBedsFilterClick = { onEvent(MapExplorerUiEvent.OnBedsFilterClicked) }
                )
            }

            // 3. Bottom Layer: Floating List View FAB + Active Estate Preview Sheet
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                // Floating "List View (28)" Pill Button
                ListViewFab(
                    count = uiState.activeReservesCount,
                    onClick = {
                        onEvent(MapExplorerUiEvent.OnListViewClicked)
                        onNavigateToExplore()
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 12.dp)
                )

                // Bottom Estate Preview Sheet
                MapEstatePreviewSheet(
                    estate = estate,
                    isSaved = uiState.savedEstateIds.contains(estate.id),
                    onToggleSave = { onEvent(MapExplorerUiEvent.OnToggleSave(estate.id)) },
                    onDirectionsClick = { onEvent(MapExplorerUiEvent.OnDirectionsClicked) },
                    onViewDetailsClick = onNavigateToDetail
                )
            }
        }
    }
}

@Preview(
    name = "Map Explorer Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Preview(
    name = "Map Explorer Screen - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun MapExplorerScreenPreview() {
    VerdantEstatesTheme {
        MapExplorerScreen(
            uiState = MapExplorerUiState(
                selectedEstate = MockData.featuredEstate,
                pins = listOf(
                    com.example.project313.data.model.MapPin("the-glass-pavilions", "$3.85M", 0.38f, 0.23f, latitude = 30.2985, longitude = -97.8050),
                    com.example.project313.data.model.MapPin("emerald-grove-residence", "$2.45M", 0.16f, 0.18f, latitude = 30.2540, longitude = -97.8220),
                    com.example.project313.data.model.MapPin("the-cedar-ridge-villa", "$1.89M", 0.78f, 0.17f, latitude = 30.2485, longitude = -97.7485),
                    com.example.project313.data.model.MapPin("skyline-botanica-penthouse", "$4.20M", 0.68f, 0.34f, latitude = 30.2610, longitude = -97.7405),
                    com.example.project313.data.model.MapPin("oakwood-sanctuary-cottage", "$1.45M", 0.24f, 0.41f, latitude = 30.3010, longitude = -97.7660)
                ),
                savedEstateIds = setOf("the-glass-pavilions")
            ),
            onEvent = {},
            onNavigateToDetail = {},
            onNavigateToExplore = {}
        )
    }
}
