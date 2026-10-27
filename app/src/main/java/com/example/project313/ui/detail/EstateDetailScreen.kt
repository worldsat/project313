package com.example.project313.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.mock.MockData
import com.example.project313.ui.detail.components.AdvisorCard
import com.example.project313.ui.detail.components.DetailBookingBottomBar
import com.example.project313.ui.detail.components.DetailHeroHeader
import com.example.project313.ui.detail.components.DetailNarrativeSection
import com.example.project313.ui.detail.components.DetailPhotoGrid
import com.example.project313.ui.detail.components.DetailPropertyHeader
import com.example.project313.ui.detail.components.DetailSpecsBar
import com.example.project313.ui.theme.Background
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun EstateDetailScreen(
    uiState: EstateDetailUiState,
    onEvent: (EstateDetailUiEvent) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val estate = uiState.estate ?: MockData.featuredEstate
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(EstateDetailUiEvent.OnDismissFeedback)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            DetailBookingBottomBar(
                formattedPrice = estate.formattedPrice,
                estimatedMonthly = estate.estimatedMonthly,
                onBookTourClick = { onEvent(EstateDetailUiEvent.OnBookTourClicked) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
                .background(Background)
        ) {
            // 1. Edge-to-Edge Parallax Hero Header with Navigation Controls
            DetailHeroHeader(
                estate = estate,
                isSaved = uiState.isSaved,
                onBackClick = onNavigateBack,
                onToggleSave = { onEvent(EstateDetailUiEvent.OnToggleSave) },
                onShareClick = { onEvent(EstateDetailUiEvent.OnShareClicked) }
            )

            // Content Container with 16dp Horizontal Gutter
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                // 2. Property Header (Tags, Title, Address, Price)
                DetailPropertyHeader(estate = estate)

                Spacer(modifier = Modifier.height(18.dp))

                // 3. Floating Specs Bar (Beds, Baths, Sqft, Lot, Eco)
                DetailSpecsBar(estate = estate)

                Spacer(modifier = Modifier.height(26.dp))

                // 4. Architectural Overview & Narrative
                val narrativeText = estate.narrative
                    ?: estate.description
                    ?: "Perched above Lake Austin, this architectural estate embodies modern transparency, sustainable design, and harmonious integration with the surrounding Texas Hill Country landscape."
                DetailNarrativeSection(narrative = narrativeText)

                Spacer(modifier = Modifier.height(26.dp))

                // 5. Curated Property Photo Gallery Grid
                DetailPhotoGrid(estate = estate)

                Spacer(modifier = Modifier.height(26.dp))

                // 6. Dedicated Listing Advisor Representation Card (Sarah Jenkins)
                val advisor = estate.advisor ?: MockData.sarahJenkins
                Text(
                    text = "Listing Representation",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(12.dp))
                AdvisorCard(
                    advisor = advisor,
                    onCallClick = { onEvent(EstateDetailUiEvent.OnCallAdvisorClicked) },
                    onChatClick = { onEvent(EstateDetailUiEvent.OnChatAdvisorClicked) }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(
    name = "Estate Detail Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Preview(
    name = "Estate Detail Screen - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun EstateDetailScreenPreview() {
    VerdantEstatesTheme {
        EstateDetailScreen(
            uiState = EstateDetailUiState(
                estate = MockData.featuredEstate,
                isSaved = true
            ),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}
