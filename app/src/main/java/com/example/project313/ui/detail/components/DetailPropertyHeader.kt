package com.example.project313.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.Estate
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer

@Composable
fun DetailPropertyHeader(
    estate: Estate,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Status & Area Tags Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            estate.statusTag?.let { status ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Primary)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = status,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                        color = Color.White
                    )
                }
            }

            estate.areaTag?.let { area ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(SecondaryContainer)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = area,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                        color = Primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Estate Title
        Text(
            text = estate.title,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 30.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Address Row
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.NearMe,
                contentDescription = "Address",
                tint = Outline,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = estate.address,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                ),
                color = Outline
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Price Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = estate.formattedPrice,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = estate.estimatedMonthly,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp
                ),
                color = Outline
            )
        }
    }
}
