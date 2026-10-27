package com.example.project313.ui.saved.components

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Bathtub
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.Deck
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.SingleBed
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.Estate
import com.example.project313.ui.explore.components.SpecPill
import com.example.project313.ui.theme.DarkFabBg
import com.example.project313.ui.theme.EcoPillContainer
import com.example.project313.ui.theme.EcoPillText
import com.example.project313.ui.theme.Outline
import com.example.project313.ui.theme.OutlineVariant
import com.example.project313.ui.theme.Primary
import com.example.project313.ui.theme.SecondaryContainer
import com.example.project313.ui.theme.SurfaceContainerLow

@Composable
fun SavedEstateCard(
    estate: Estate,
    onEstateClick: (String) -> Unit,
    onToggleSave: (String) -> Unit,
    onBookTourClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onNotesClick: (String) -> Unit,
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
            // Hero Photo with Badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp)
            ) {
                Image(
                    painter = painterResource(id = estate.imageResId),
                    contentDescription = estate.title,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                // Top-Left Badges Stack
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Price Drop Badge (Peach)
                    estate.priceDropNotice?.let { notice ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(EcoPillContainer)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = notice,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                ),
                                color = EcoPillText
                            )
                        }
                    }

                    // Open House Badge (Evergreen)
                    estate.openHouseNotice?.let { openHouse ->
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(DarkFabBg)
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = openHouse,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                ),
                                color = Color.White
                            )
                        }
                    }

                    // Status Availability Badges
                    estate.availabilityTag?.let { tag ->
                        val (bgColor, textColor) = when (tag) {
                            "Available" -> Pair(SecondaryContainer, Primary)
                            "New Price" -> Pair(EcoPillContainer, EcoPillText)
                            else -> Pair(Color.White.copy(alpha = 0.95f), MaterialTheme.colorScheme.onSurface)
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(bgColor)
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val icon = when (tag) {
                                "Available" -> Icons.Outlined.CheckCircle
                                "New Price" -> Icons.Outlined.Tag
                                else -> Icons.Outlined.Description
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = textColor,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                ),
                                color = textColor
                            )
                        }
                    }
                }

                // Top-Right Favorite Heart (Always Saved)
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(36.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.88f))
                        .clickable { onToggleSave(estate.id) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Saved",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Estate Details Below Image
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Price Row (Current + Original Strikethrough)
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

                    estate.originalPrice?.let { original ->
                        Text(
                            text = original,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 13.sp,
                                textDecoration = TextDecoration.LineThrough
                            ),
                            color = Outline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.NearMe,
                        contentDescription = null,
                        tint = Outline,
                        modifier = Modifier.size(12.dp)
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

                Spacer(modifier = Modifier.height(12.dp))

                // Spec Pills Row
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
                    estate.terraceSpec?.let {
                        SpecPill(
                            icon = Icons.Outlined.Deck,
                            text = it
                        )
                    }
                    estate.acreage?.let {
                        SpecPill(
                            icon = Icons.Outlined.Landscape,
                            text = it
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Action Buttons Row: "Book Tour" / "Backup Offer" + Share + Notes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (estate.isUnderContract) {
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(50))
                                .clickable { onBookTourClick(estate.id) },
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFEBEFEA)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Description,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Backup Offer",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    } else {
                        Button(
                            onClick = { onBookTourClick(estate.id) },
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp),
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkFabBg,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Book Tour",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            )
                        }
                    }

                    // Share Button
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerLow)
                            .clickable { onShareClick(estate.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Notes Button
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerLow)
                            .clickable { onNotesClick(estate.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.EditNote,
                            contentDescription = "Notes",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
