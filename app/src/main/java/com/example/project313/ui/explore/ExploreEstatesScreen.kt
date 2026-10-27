package com.example.project313.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.FilterCategory
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.explore.components.EcoLuxuryBannerCard
import com.example.project313.ui.explore.components.ExploreSearchBar
import com.example.project313.ui.explore.components.ExploreTopBar
import com.example.project313.ui.explore.components.FeaturedEstateSection
import com.example.project313.ui.explore.components.FilterChipRow
import com.example.project313.ui.explore.components.MapViewFab
import com.example.project313.ui.explore.components.PopularEstateCard
import com.example.project313.ui.explore.components.PopularSectionHeader
import com.example.project313.ui.explore.components.VerdantBottomNav
import com.example.project313.ui.theme.Background
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun ExploreEstatesScreen(
    uiState: ExploreUiState,
    onEvent: (ExploreUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        topBar = {
            ExploreTopBar(
                location = uiState.userLocation,
                unreadNotifications = uiState.unreadNotificationsCount,
                onNotificationsClick = { onEvent(ExploreUiEvent.OnNotificationsClicked) },
                onProfileClick = { onEvent(ExploreUiEvent.OnProfileClicked) }
            )
        },
        bottomBar = {
            VerdantBottomNav(
                selectedTab = uiState.currentNavigationTab,
                onTabSelect = { onEvent(ExploreUiEvent.OnNavigationTabSelected(it)) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
            ) {
                // 1. Search Bar
                item {
                    ExploreSearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = { onEvent(ExploreUiEvent.OnSearchQueryChanged(it)) },
                        onFilterClick = { onEvent(ExploreUiEvent.OnFilterClicked) }
                    )
                }

                // 2. Filter Category Chips Carousel
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    FilterChipRow(
                        selectedCategory = uiState.selectedCategory,
                        onCategorySelect = { onEvent(ExploreUiEvent.OnCategorySelected(it)) }
                    )
                }

                // 3. Featured Estate Section ("FEATURED ESTATE" + The Glass Pavilions Card)
                uiState.featuredEstate?.let { featured ->
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        FeaturedEstateSection(
                            estate = featured,
                            isSaved = uiState.savedEstateIds.contains(featured.id),
                            onEstateClick = { onEvent(ExploreUiEvent.OnEstateClicked(it)) },
                            onToggleSave = { onEvent(ExploreUiEvent.OnToggleSaveEstate(it)) }
                        )
                    }
                }

                // 4. "Curated Eco-Luxury Living" Banner
                item {
                    EcoLuxuryBannerCard()
                }

                // 5. Popular Near You Header
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    PopularSectionHeader(
                        onViewAllClick = { onEvent(ExploreUiEvent.OnViewAllPopularClicked) }
                    )
                }

                // 6. Popular Estates Vertical List
                items(
                    items = uiState.popularEstates,
                    key = { it.id }
                ) { estate ->
                    PopularEstateCard(
                        estate = estate,
                        isSaved = uiState.savedEstateIds.contains(estate.id),
                        onEstateClick = { onEvent(ExploreUiEvent.OnEstateClicked(it)) },
                        onToggleSave = { onEvent(ExploreUiEvent.OnToggleSaveEstate(it)) }
                    )
                }

                // Bottom spacer for clearance above FAB and Navigation Bar
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }

            // Floating "Map View" Pill Button
            MapViewFab(
                onClick = { onEvent(ExploreUiEvent.OnMapViewClicked) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview(
    name = "Explore Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Preview(
    name = "Explore Screen - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun ExploreScreenPreview() {
    VerdantEstatesTheme {
        ExploreEstatesScreen(
            uiState = ExploreUiState(
                featuredEstate = MockData.featuredEstate,
                popularEstates = MockData.popularEstates,
                savedEstateIds = setOf("the-glass-pavilions"),
                selectedCategory = FilterCategory.ALL,
                currentNavigationTab = NavigationTab.EXPLORE
            ),
            onEvent = {}
        )
    }
}
