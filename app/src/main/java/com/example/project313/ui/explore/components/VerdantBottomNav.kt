package com.example.project313.ui.explore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.R
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer

@Composable
fun VerdantBottomNav(
    selectedTab: NavigationTab,
    onTabSelect: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = OutlineVariant.copy(alpha = 0.35f),
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
            ),
        color = Color.White,
        tonalElevation = 3.dp,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationTab.entries.forEach { tab ->
                val isSelected = tab == selectedTab

                val icon = when (tab) {
                    NavigationTab.EXPLORE -> Icons.Outlined.Explore
                    NavigationTab.MAP -> Icons.Outlined.Map
                    NavigationTab.SAVED -> Icons.Outlined.BookmarkBorder
                    NavigationTab.AGENT -> null
                }

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onTabSelect(tab) }
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(SecondaryContainer)
                                .padding(horizontal = 18.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (tab == NavigationTab.AGENT) {
                                Image(
                                    painter = painterResource(id = R.drawable.agent),
                                    contentDescription = tab.label,
                                    modifier = Modifier
                                        .size(22.dp)
                                        .clip(CircleShape)
                                        .border(1.5.dp, Primary, CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else if (icon != null) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = tab.label,
                                    tint = Primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (tab == NavigationTab.AGENT) {
                                Image(
                                    painter = painterResource(id = R.drawable.agent),
                                    contentDescription = tab.label,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Outline.copy(alpha = 0.5f), CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else if (icon != null) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = tab.label,
                                    tint = Outline,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        ),
                        color = if (isSelected) Primary else Outline
                    )
                }
            }
        }
    }
}
