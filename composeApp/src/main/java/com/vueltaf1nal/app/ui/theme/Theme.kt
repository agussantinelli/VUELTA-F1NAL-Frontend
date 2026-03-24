package com.vueltaf1nal.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RacingRed,
    secondary = SpeedYellow,
    tertiary = AeroSilver,
    background = CircuitNavy,
    surface = CardDark,
    onPrimary = Color.White,
    onSecondary = AsphaltBlack,
    onTertiary = AsphaltBlack,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun VueltaF1nalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // For now, we only implement a dark, premium racing theme
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // We will define this next if needed
        content = content
    )
}
