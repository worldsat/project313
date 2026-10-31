package com.example.project313.ui.explore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.R
import com.example.project313.data.model.NavigationTab
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer
import com.example.project313.ui.theme.VerdantEstatesTheme

@Composable
fun VerdantBottomNav(
    selectedTab: NavigationTab,
    onTabSelect: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
    savedCount: Int = 0
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = OutlineVariant.copy(alpha = 0.35f),
                    start = Offset(0f, strokeWidth / 2),
                    end = Offset(size.width, strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            },
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                containerColor = Color.White,
                tonalElevation = 0.dp,
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                NavigationTab.entries.forEach { tab ->
                    val isSelected = tab == selectedTab

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { onTabSelect(tab) },
                        icon = {
                            when (tab) {
                                NavigationTab.EXPLORE -> {
                                    Icon(
                                        imageVector = if (isSelected) Icons.Filled.Explore else Icons.Outlined.Explore,
                                        contentDescription = tab.label
                                    )
                                }
                                NavigationTab.MAP -> {
                                    Icon(
                                        imageVector = if (isSelected) Icons.Filled.Map else Icons.Outlined.Map,
                                        contentDescription = tab.label
                                    )
                                }
                                NavigationTab.SAVED -> {
                                    BadgedBox(
                                        badge = {
                                            if (savedCount > 0) {
                                                Badge(
                                                    containerColor = Primary,
                                                    contentColor = Color.White
                                                ) {
                                                    Text(
                                                        text = savedCount.toString(),
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            fontSize = 10.sp,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (isSelected) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                                            contentDescription = tab.label
                                        )
                                    }
                                }
                                NavigationTab.AGENT -> {
                                    if (isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .size(26.dp)
                                                .border(width = 2.dp, color = Primary, shape = CircleShape)
                                                .padding(2.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.agent),
                                                contentDescription = tab.label,
                                                modifier = Modifier
                                                    .size(22.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    } else {
                                        Image(
                                            painter = painterResource(id = R.drawable.agent),
                                            contentDescription = tab.label,
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        },
                        label = {
                            Text(
                                text = tab.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                                )
                            )
                        },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            unselectedIconColor = Outline,
                            selectedTextColor = Primary,
                            unselectedTextColor = Outline,
                            indicatorColor = SecondaryContainer
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerdantBottomNavPreview() {
    VerdantEstatesTheme {
        VerdantBottomNav(
            selectedTab = NavigationTab.EXPLORE,
            savedCount = 6,
            onTabSelect = {}
        )
    }
}
