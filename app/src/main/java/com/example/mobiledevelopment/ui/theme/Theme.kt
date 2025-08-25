package com.example.mobiledevelopment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ColorScheme

private val AppTypography = Typography(
    // tamaños un poco mayores por defecto para mejor legibilidad
    titleLarge = Typography().titleLarge.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    bodyLarge  = Typography().bodyLarge.copy(fontSize = 18.sp),
    bodyMedium = Typography().bodyMedium.copy(fontSize = 16.sp),
    labelLarge = Typography().labelLarge.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
)

@Composable
fun NutriGoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorScheme: ColorScheme = if (darkTheme) DarkScheme else LightScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes(),
        content = content
    )
}
