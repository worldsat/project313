package com.example.project313.ui.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.FilterCategory
import com.example.project313.ui.theme.EcoPillBorder
import com.example.project313.ui.theme.EcoPillContainer
import com.example.project313.ui.theme.EcoPillText
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer
import com.example.project313.ui.theme.SurfaceContainerLow

@Composable
fun FilterChipRow(
    selectedCategory: FilterCategory,
    onCategorySelect: (FilterCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterCategory.entries.forEach { category ->
            val isSelected = category == selectedCategory

            val (backgroundColor, textColor, borderModifier) = when {
                category == FilterCategory.ECO_HOMES -> {
                    Triple(
                        EcoPillContainer,
                        EcoPillText,
                        Modifier.border(1.dp, EcoPillBorder, RoundedCornerShape(50))
                    )
                }
                isSelected -> {
                    Triple(
                        SecondaryContainer,
                        Primary,
                        Modifier
                    )
                }
                else -> {
                    Triple(
                        SurfaceContainerLow,
                        MaterialTheme.colorScheme.onSurfaceVariant,
                        Modifier
                    )
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .then(borderModifier)
                    .background(backgroundColor)
                    .clickable { onCategorySelect(category) }
                    .padding(horizontal = 14.dp, vertical = 9.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isSelected && category != FilterCategory.ECO_HOMES) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Primary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    } else if (category == FilterCategory.ECO_HOMES) {
                        Icon(
                            imageVector = Icons.Outlined.Eco,
                            contentDescription = "Eco",
                            tint = EcoPillText,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }

                    Text(
                        text = category.label,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 13.sp,
                            fontWeight = if (isSelected || category == FilterCategory.ECO_HOMES) {
                                FontWeight.SemiBold
                            } else {
                                FontWeight.Medium
                            }
                        ),
                        color = textColor
                    )
                }
            }
        }
    }
}
