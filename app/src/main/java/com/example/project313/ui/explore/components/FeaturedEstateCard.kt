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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.SingleBed
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.project313.ui.theme.StarGold
import com.example.project313.ui.theme.SurfaceContainerLow

@Composable
fun FeaturedEstateSection(
    estate: Estate,
    isSaved: Boolean,
    onEstateClick: (String) -> Unit,
    onToggleSave: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Section Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Primary)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "FEATURED ESTATE",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        fontSize = 13.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = "Updated today",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp
                ),
                color = Outline
            )
        }

        // Featured Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onEstateClick(estate.id) },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.4f))
        ) {
            Column {
                // Hero Image Container with Badges
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {
                    Image(
                        painter = painterResource(id = estate.imageResId),
                        contentDescription = estate.title,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    // Scrim
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .align(Alignment.TopCenter)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.35f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.45f)
                                    )
                                )
                            )
                    )

                    // Top-Left: "Verified Luxury" Warm Frosted Pill
                    Row(
                        modifier = Modifier
                            .padding(14.dp)
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFFFDF4EC).copy(alpha = 0.94f))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.VerifiedUser,
                            contentDescription = "Verified Luxury",
                            tint = Color(0xFFAC7740),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Verified Luxury",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            ),
                            color = Color(0xFF6B4720)
                        )
                    }

                    // Top-Right: Translucent Wishlist Heart Button
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

                    // Bottom-Left: Rating Pill
                    Row(
                        modifier = Modifier
                            .padding(14.dp)
                            .align(Alignment.BottomStart)
                            .clip(RoundedCornerShape(50))
                            .background(Color.Black.copy(alpha = 0.65f))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = StarGold,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${estate.rating ?: 4.95}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "(${estate.reviewCount ?: 38} reviews)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp
                            ),
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                // Estate Details Below Image
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
                                fontSize = 23.sp
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

                    Spacer(modifier = Modifier.height(4.dp))

                    // Address with NearMe Navigation Icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.NearMe,
                            contentDescription = "Address",
                            tint = Outline,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = estate.address,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp
                            ),
                            color = Outline
                        )
                    }

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
}

@Composable
fun SpecPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
