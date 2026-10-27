package com.example.project313.ui.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SurfaceContainerLow
import com.example.project313.ui.theme.Tertiary

@Composable
fun ExploreSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(50))
            .background(SurfaceContainerLow)
            .padding(start = 16.dp, end = 6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = Outline,
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = "Search modern villas, penthouse...",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Outline,
                            fontSize = 14.sp
                        ),
                        maxLines = 1
                    )
                }

                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(Primary)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Dark Circular Filter Button with brass notification pip
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Primary)
                    .clickable(onClick = onFilterClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = "Filters",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )

                // Brass indicator dot
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-8).dp, y = (9).dp)
                        .clip(CircleShape)
                        .background(Tertiary)
                )
            }
        }
    }
}
