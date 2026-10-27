package com.example.project313.ui.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.ui.intro.components.IntroBottomCard
import com.example.project313.ui.intro.components.IntroHeroVisual
import com.example.project313.ui.intro.components.IntroTopBar
import com.example.project313.ui.theme.IntroGradientDark
import com.example.project313.ui.theme.IntroGradientMid
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun VerdantIntroScreen(
    uiState: IntroUiState,
    onEvent: (IntroUiEvent) -> Unit,
    onNavigateToExplore: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(IntroUiEvent.OnDismissFeedback)
        }
    }

    val backgroundBrush = Brush.verticalGradient(
        listOf(
            IntroGradientDark,
            Color(0xFF09281C),
            IntroGradientMid,
            Color(0xFF0A2317)
        )
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 1. Top Bar: Frosted "VERDANT ESTATES" badge + "Skip Tour"
                IntroTopBar(
                    onSkipClick = onNavigateToExplore
                )

                // 2. Central Hero Visual: Concentric Resonant Rings, Squircle Emblem & Satellite Badges
                IntroHeroVisual(
                    curatedCount = uiState.curatedHomesCount,
                    leedRating = uiState.leedRating,
                    reserveCategory = uiState.reserveCategory,
                    reserveTitle = uiState.reserveTitle
                )

                // 3. Floating Bottom Content Card
                IntroBottomCard(
                    currentSlideIndex = uiState.currentSlideIndex,
                    totalSlidesCount = uiState.totalSlidesCount,
                    headline = uiState.headline,
                    description = uiState.description,
                    onExploreClick = onNavigateToExplore,
                    onTalkToAdvisorClick = { onEvent(IntroUiEvent.OnTalkToAdvisorClicked) },
                    onMemberSignInClick = { onEvent(IntroUiEvent.OnMemberSignInClicked) }
                )

                Spacer(modifier = Modifier.height(14.dp))

                // 4. Footer Attribution Tagline
                Text(
                    text = "Verdant Estates™ • Sustainable Luxury Living • Austin, TX",
                    style = androidx.compose.material3.MaterialTheme.typography.labelSmall.copy(
                        fontSize = 11.sp,
                        letterSpacing = 0.3.sp
                    ),
                    color = Color(0xFF7FA392),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp, start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}

@Preview(
    name = "Intro Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Preview(
    name = "Intro Screen - Compact",
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun VerdantIntroScreenPreview() {
    VerdantEstatesTheme {
        VerdantIntroScreen(
            uiState = IntroUiState(),
            onEvent = {},
            onNavigateToExplore = {}
        )
    }
}
