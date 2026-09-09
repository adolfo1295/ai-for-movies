package com.adolfochavez.aiformovies.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF4F4D5B),
    secondary = Color(0xFF5F5A6C),
    tertiary = Color(0xFF77536E),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFC9C5D5),
    secondary = Color(0xFFC8C1D2),
    tertiary = Color(0xFFE9B8D6),
)

@Composable
fun AIForMoviesTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
