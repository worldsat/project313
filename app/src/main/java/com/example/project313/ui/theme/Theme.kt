package com.example.project313.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Material 3 ColorScheme using DESIGN.md tokens
private val VerdantLightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    surface = Surface,
    onSurface = OnSurface,
    surfaceDim = SurfaceDim,
    surfaceBright = SurfaceBright,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    background = Background,
    onBackground = OnBackground,
    outline = Outline,
    outlineVariant = OutlineVariant
)

@Composable
fun VerdantEstatesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VerdantLightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// Project313Theme alias for seamless compatibility
@Composable
fun Project313Theme(
    content: @Composable () -> Unit
) = VerdantEstatesTheme(content = content)