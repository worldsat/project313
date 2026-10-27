package com.example.project313.ui.map.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project313.data.model.MapPin
import com.example.project313.ui.theme.DarkFabBg
import com.example.project313.ui.theme.OutlineVariant
import kotlin.math.roundToInt

@Composable
fun ArchitecturalMapCanvas(
    pins: List<MapPin>,
    selectedEstateId: String,
    onPinClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val widthPx = constraints.maxWidth.toFloat()
        val heightPx = constraints.maxHeight.toFloat()

        // 1. Vector Map Background Canvas (River, Roads, Parcels, Eco-Zones)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawMapBackground()
        }

        // 2. Interactive Map Pins Overlay
        pins.forEach { pin ->
            val isSelected = pin.estateId == selectedEstateId

            val posX = (pin.xPercent * widthPx).roundToInt()
            val posY = (pin.yPercent * heightPx).roundToInt()

            Box(
                modifier = Modifier
                    .offset { IntOffset(posX - 60, posY - 40) }
                    .clickable { onPinClick(pin.estateId) }
            ) {
                MapPinBubble(
                    priceLabel = pin.priceLabel,
                    isSelected = isSelected
                )
            }
        }
    }
}

@Composable
private fun MapPinBubble(
    priceLabel: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(50),
            color = if (isSelected) DarkFabBg else Color.White,
            shadowElevation = if (isSelected) 8.dp else 4.dp,
            border = if (!isSelected) androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.35f)) else null
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = priceLabel,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Small Downward Pin Indicator Pointer
        Canvas(
            modifier = Modifier.size(width = 10.dp, height = 6.dp)
        ) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width / 2f, size.height)
                close()
            }
            drawPath(
                path = path,
                color = if (isSelected) DarkFabBg else Color.White
            )
        }
    }
}

private fun DrawScope.drawMapBackground() {
    val canvasBg = Color(0xFFE6EFE4)
    val riverColor = Color(0xFFBADBC5)
    val zoneGreen = Color(0xFFD6EBD7)
    val roadColor = Color.White
    val roadBorder = Color(0xFFD0DDD0)
    val parcelColor = Color(0xFFF0F6EE)
    val parcelBorder = Color(0xFFD3E2D1)
    val gridColor = Color(0xFFD9E6D8)

    // Base Canvas
    drawRect(color = canvasBg)

    // Architectural Grid Lines
    val dashEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
    for (x in 0..size.width.toInt() step 80) {
        drawLine(
            color = gridColor,
            start = Offset(x.toFloat(), 0f),
            end = Offset(x.toFloat(), size.height),
            strokeWidth = 1f,
            pathEffect = dashEffect
        )
    }
    for (y in 0..size.height.toInt() step 80) {
        drawLine(
            color = gridColor,
            start = Offset(0f, y.toFloat()),
            end = Offset(size.width, y.toFloat()),
            strokeWidth = 1f,
            pathEffect = dashEffect
        )
    }

    // Circular Eco-Reserve Zoning Patches
    drawCircle(
        color = zoneGreen.copy(alpha = 0.45f),
        radius = size.width * 0.28f,
        center = Offset(size.width * 0.25f, size.height * 0.32f)
    )
    drawCircle(
        color = zoneGreen.copy(alpha = 0.35f),
        radius = size.width * 0.22f,
        center = Offset(size.width * 0.82f, size.height * 0.24f)
    )

    // River / Lake Austin S-Curve Ribbon
    val riverPath = Path().apply {
        moveTo(0f, size.height * 0.35f)
        cubicTo(
            size.width * 0.25f, size.height * 0.38f,
            size.width * 0.45f, size.height * 0.32f,
            size.width * 0.65f, size.height * 0.35f
        )
        cubicTo(
            size.width * 0.82f, size.height * 0.38f,
            size.width * 0.92f, size.height * 0.33f,
            size.width, size.height * 0.34f
        )
        lineTo(size.width, size.height * 0.44f)
        cubicTo(
            size.width * 0.88f, size.height * 0.46f,
            size.width * 0.68f, size.height * 0.44f,
            size.width * 0.5f, size.height * 0.42f
        )
        cubicTo(
            size.width * 0.32f, size.height * 0.45f,
            size.width * 0.15f, size.height * 0.47f,
            0f, size.height * 0.44f
        )
        close()
    }
    drawPath(path = riverPath, color = riverColor)

    // Architectural Parcels / Plots
    fun drawRotatedParcel(center: Offset, width: Float, height: Float, degrees: Float) {
        rotate(degrees, pivot = center) {
            drawRect(
                color = parcelColor,
                topLeft = Offset(center.x - width / 2, center.y - height / 2),
                size = Size(width, height)
            )
            drawRect(
                color = parcelBorder,
                topLeft = Offset(center.x - width / 2, center.y - height / 2),
                size = Size(width, height),
                style = Stroke(width = 1.5f)
            )
        }
    }

    drawRotatedParcel(Offset(size.width * 0.22f, size.height * 0.48f), 65f, 50f, 12f)
    drawRotatedParcel(Offset(size.width * 0.72f, size.height * 0.26f), 70f, 55f, -15f)
    drawRotatedParcel(Offset(size.width * 0.65f, size.height * 0.52f), 80f, 60f, 8f)

    // Road Highways Network
    fun drawRoad(path: Path, width: Float) {
        drawPath(path = path, color = roadBorder, style = Stroke(width = width + 2f))
        drawPath(path = path, color = roadColor, style = Stroke(width = width))
    }

    val highway1 = Path().apply {
        moveTo(size.width * 0.3f, 0f)
        cubicTo(
            size.width * 0.38f, size.height * 0.22f,
            size.width * 0.55f, size.height * 0.36f,
            size.width * 0.75f, size.height * 0.52f
        )
        lineTo(size.width, size.height * 0.62f)
    }
    drawRoad(highway1, width = 7f)

    val highway2 = Path().apply {
        moveTo(0f, size.height * 0.18f)
        cubicTo(
            size.width * 0.22f, size.height * 0.24f,
            size.width * 0.42f, size.height * 0.28f,
            size.width * 0.6f, size.height * 0.4f
        )
        lineTo(size.width * 0.85f, size.height * 0.65f)
    }
    drawRoad(highway2, width = 6f)
}
