package com.example.project313.ui.agent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.R
import com.example.project313.data.mock.MockData
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.explore.components.VerdantBottomNav
import com.example.project313.ui.theme.Background
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer
import com.example.project313.ui.theme.SurfaceContainerLow
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun AgentAdvisorScreen(
    uiState: AgentAdvisorUiState,
    onEvent: (AgentAdvisorUiEvent) -> Unit,
    onNavigateToTab: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(AgentAdvisorUiEvent.OnDismissFeedback)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            VerdantBottomNav(
                selectedTab = NavigationTab.AGENT,
                onTabSelect = onNavigateToTab
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Background)
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(20.dp)
        ) {
            // 1. Top Title Section
            Text(
                text = "Private Advisory",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp,
                    letterSpacing = (-0.5).sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Dedicated bespoke consultation for private estate transactions",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                ),
                color = Outline
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Advisor Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.4f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Circular Avatar (size 90dp)
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .border(3.dp, SecondaryContainer, CircleShape)
                            .shadow(8.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.agent),
                            contentDescription = uiState.advisor.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = uiState.advisor.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = uiState.advisor.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        ),
                        color = Outline
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = uiState.advisor.division,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        color = Primary
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Quick Action Buttons Row: "Message" & "Call Direct"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onEvent(AgentAdvisorUiEvent.OnMessageClicked) },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ChatBubbleOutline,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Message",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            )
                        }

                        OutlinedButton(
                            onClick = { onEvent(AgentAdvisorUiEvent.OnCallDirectClicked) },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.6f)),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Call Direct",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Unlisted Off-Market Parcels Section
            Text(
                text = "Exclusive Off-Market Access",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onEvent(AgentAdvisorUiEvent.OnConfidentialAccessClicked) },
                shape = RoundedCornerShape(20.dp),
                color = SurfaceContainerLow
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${uiState.confidentialPortfoliosCount} Confidential Portfolios Available",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Access discreet off-market transactions in Westlake, Barton Creek, and Lake Austin before public offering.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.5.sp,
                            lineHeight = 18.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(
    name = "Agent Advisor Screen - Phone",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun AgentAdvisorScreenPreview() {
    VerdantEstatesTheme {
        AgentAdvisorScreen(
            uiState = AgentAdvisorUiState(),
            onEvent = {},
            onNavigateToTab = {}
        )
    }
}

