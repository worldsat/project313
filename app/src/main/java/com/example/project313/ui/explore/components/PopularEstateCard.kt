package com.example.project313.ui.explore.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.SingleBed
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.project313.data.model.Estate
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary

@Composable
fun PopularSectionHeader(
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Popular Near You",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "High-demand properties across Central Texas",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp
                ),
                color = Outline
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onViewAllClick)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "View all",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                color = Primary
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "View all",
                tint = Primary,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun PopularEstateCard(
    estate: Estate,
    isSaved: Boolean,
    onEstateClick: (String) -> Unit,
    onToggleSave: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onEstateClick(estate.id) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.4f))
    ) {
        Column {
            // Image with Badges & Carousel Dots
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Image(
                    painter = painterResource(id = estate.imageResId),
                    contentDescription = estate.title,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                // Top-Left Badges Row
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    estate.statusTag?.let { status ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Primary)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
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
                                .background(Color.White.copy(alpha = 0.95f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = area,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Top-Right Bookmark Heart Button
                Box(
                    modifier = Modifier
                        .padding(14.dp)
                        .size(36.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.88f))
                        .clickable { onToggleSave(estate.id) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Save Estate",
                        tint = if (isSaved) Color(0xFFD32F2F) else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Bottom Center Carousel Dots Indicator
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(estate.photoCount) { index ->
                        if (index == estate.activePhotoIndex) {
                            Box(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White)
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.6f))
                            )
                        }
                    }
                }
            }

            // Details below image
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Price Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = estate.formattedPrice,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = estate.estimatedMonthly,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp
                        ),
                        color = Outline
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // Title
                Text(
                    text = estate.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Address
                Text(
                    text = estate.address,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp
                    ),
                    color = Outline
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Spec Pills
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    estate.beds?.let {
                        SpecPill(
                            icon = Icons.Outlined.SingleBed,
                            text = "$it Beds"
                        )
                    }
                    estate.baths?.let {
                        SpecPill(
                            icon = Icons.Outlined.Bathtub,
                            text = "$it Baths"
                        )
                    }
                    estate.sqft?.let {
                        val formattedSqft = "%,d".format(it)
                        SpecPill(
                            icon = Icons.Outlined.SquareFoot,
                            text = "$formattedSqft sqft"
                        )
                    }
                }
            }
        }
    }
}
