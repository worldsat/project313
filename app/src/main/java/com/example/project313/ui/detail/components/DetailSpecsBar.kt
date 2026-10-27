package com.example.project313.ui.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material.icons.outlined.SingleBed
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.Estate
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SurfaceContainerLow

@Composable
fun DetailSpecsBar(
    estate: Estate,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLow,
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            estate.beds?.let {
                DetailSpecItem(
                    icon = Icons.Outlined.SingleBed,
                    label = "Beds",
                    value = "$it"
                )
            }

            estate.baths?.let {
                DetailSpecItem(
                    icon = Icons.Outlined.Bathtub,
                    label = "Baths",
                    value = "$it"
                )
            }

            estate.sqft?.let {
                val formattedSqft = "%,d".format(it)
                DetailSpecItem(
                    icon = Icons.Outlined.SquareFoot,
                    label = "Living Space",
                    value = "$formattedSqft sqft"
                )
            }

            estate.acreage?.let {
                DetailSpecItem(
                    icon = Icons.Outlined.Landscape,
                    label = "Lot Size",
                    value = it
                )
            }

            estate.leedRating?.let {
                DetailSpecItem(
                    icon = Icons.Outlined.EnergySavingsLeaf,
                    label = "Eco Rating",
                    value = it
                )
            }
        }
    }
}

@Composable
private fun DetailSpecItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 11.sp
            ),
            color = Outline
        )
    }
}
