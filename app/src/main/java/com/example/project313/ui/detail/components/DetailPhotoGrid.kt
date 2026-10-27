package com.example.project313.ui.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.Estate
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SurfaceContainerLow

@Composable
fun DetailPhotoGrid(
    estate: Estate,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Property Gallery",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "${estate.photoCount} Photos",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                ),
                color = Outline
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Main Showcase Tile
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f))
        ) {
            Image(
                painter = painterResource(id = estate.imageResId),
                contentDescription = "Showcase Photo",
                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Gallery Secondary Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Perspective Tile 1
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLow,
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.4f))
            ) {
                Box(
                    modifier = Modifier.padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CameraAlt,
                            contentDescription = "Exterior View",
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "South Elevation",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Perspective Tile 2
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLow,
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.4f))
            ) {
                Box(
                    modifier = Modifier.padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CameraAlt,
                            contentDescription = "Pavilion Terrace",
                            tint = Primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Pavilion Terrace",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
