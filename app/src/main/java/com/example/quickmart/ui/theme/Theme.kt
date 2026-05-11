package com.example.quickmart.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(

    primary = Color(0xFFFCCB17),
    secondary = Color(0xFFFFE082),
    tertiary = Color(0xFF4CAF50),

    background = Color(0xFFF5F5F5),
    surface = Color.White,

    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.White,

    onBackground = Color(0xFF111827),
    onSurface = Color(0xFF111827)
)

private val DarkColors = darkColorScheme(

    primary = Color(0xFFFCCB17),
    secondary = Color(0xFFFFE082),
    tertiary = Color(0xFF4CAF50),

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),

    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.White,

    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun QuickMartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}