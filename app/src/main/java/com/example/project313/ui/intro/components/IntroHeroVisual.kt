package com.example.project313.ui.intro.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IntroHeroVisual(
    curatedCount: Int = 42,
    leedRating: String = "LEED Platinum",
    reserveCategory: String = "PRIVATE RESERVE",
    reserveTitle: String = "Austin Enclaves",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp),
        contentAlignment = Alignment.Center
    ) {
        // 1. Organic Undulating Topographic Landscape Waves & Resonant Radar Rings
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val centerX = canvasWidth / 2f
            val centerY = canvasHeight / 2f + 10f

            // Background subtle topographic contour curve 1
            val path1 = Path().apply {
                moveTo(0f, canvasHeight * 0.22f)
                cubicTo(
                    canvasWidth * 0.35f, canvasHeight * 0.12f,
                    canvasWidth * 0.65f, canvasHeight * 0.32f,
                    canvasWidth, canvasHeight * 0.18f
                )
                lineTo(canvasWidth, canvasHeight)
                lineTo(0f, canvasHeight)
                close()
            }
            drawPath(
                path = path1,
                color = Color(0x18164E35)
            )

            // Background subtle topographic contour curve 2
            val path2 = Path().apply {
                moveTo(0f, canvasHeight * 0.52f)
                cubicTo(
                    canvasWidth * 0.3f, canvasHeight * 0.42f,
                    canvasWidth * 0.7f, canvasHeight * 0.62f,
                    canvasWidth, canvasHeight * 0.48f
                )
                lineTo(canvasWidth, canvasHeight)
                lineTo(0f, canvasHeight)
                close()
            }
            drawPath(
                path = path2,
                color = Color(0x2213422C)
            )

            // Outer resonant ring
            drawCircle(
                color = Color(0x247ED4A2),
                radius = 152.dp.toPx(),
                center = Offset(centerX, centerY),
                style = Stroke(width = 1.dp.toPx())
            )

            // Middle resonant ring
            drawCircle(
                color = Color(0x3B7ED4A2),
                radius = 120.dp.toPx(),
                center = Offset(centerX, centerY),
                style = Stroke(width = 1.2.dp.toPx())
            )

            // Inner glowing disc background
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x4C327855),
                        Color(0x2E14422C),
                        Color(0x140B2B1B)
                    ),
                    center = Offset(centerX, centerY),
                    radius = 90.dp.toPx()
                ),
                radius = 88.dp.toPx(),
                center = Offset(centerX, centerY)
            )

            // Inner disc border
            drawCircle(
                color = Color(0x608ADBB0),
                radius = 88.dp.toPx(),
                center = Offset(centerX, centerY),
                style = Stroke(width = 1.5.dp.toPx())
            )
        }

        // 2. Central Sanctuary Card ("PRIVATE RESERVE" + "Austin Enclaves")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = 5.dp)
        ) {
            // Modern Architecture Villa Icon Squircle
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF6ED998),
                                Color(0xFF45A86E)
                            )
                        )
                    )
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Architectural Modern Villa Vector Graphic
                Canvas(modifier = Modifier.size(26.dp)) {
                    val w = size.width
                    val h = size.height
                    val darkGreen = Color(0xFF0C2C1C)

                    // Main house structure
                    val housePath = Path().apply {
                        // Left tall block
                        moveTo(w * 0.18f, h * 0.28f)
                        lineTo(w * 0.52f, h * 0.28f)
                        lineTo(w * 0.52f, h * 0.82f)
                        lineTo(w * 0.18f, h * 0.82f)
                        close()

                        // Right lower cantilevered block
                        moveTo(w * 0.52f, h * 0.44f)
                        lineTo(w * 0.82f, h * 0.44f)
                        lineTo(w * 0.82f, h * 0.82f)
                        lineTo(w * 0.52f, h * 0.82f)
                        close()
                    }

                    drawPath(
                        path = housePath,
                        color = darkGreen,
                        style = Stroke(width = 2.4.dp.toPx())
                    )

                    // Architectural panoramic window line
                    drawLine(
                        color = darkGreen,
                        start = Offset(w * 0.28f, h * 0.42f),
                        end = Offset(w * 0.42f, h * 0.42f),
                        strokeWidth = 2.2.dp.toPx()
                    )
                    drawLine(
                        color = darkGreen,
                        start = Offset(w * 0.62f, h * 0.58f),
                        end = Offset(w * 0.74f, h * 0.58f),
                        strokeWidth = 2.2.dp.toPx()
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = reserveCategory,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.4.sp
                ),
                color = Color(0xFFAFE4C7)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = reserveTitle,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = Color.White
            )
        }

        // 3. Top-Right Satellite Pill: "• 42 Curated Homes"
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 68.dp, y = (-78).dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(50)),
            shape = RoundedCornerShape(50),
            color = Color(0xFFD6E4DB)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 13.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF008955))
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "$curatedCount Curated Homes",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    color = Color(0xFF133627)
                )
            }
        }

        // 4. Bottom-Left Satellite Pill: "LEED Platinum"
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-82).dp, y = 72.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(50)),
            shape = RoundedCornerShape(50),
            color = Color(0xFFD6E4DB)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 13.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.EnergySavingsLeaf,
                    contentDescription = null,
                    tint = Color(0xFF008955),
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = leedRating,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    color = Color(0xFF133627)
                )
            }
        }
    }
}
