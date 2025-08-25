package com.example.mobiledevelopment.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Light
val Primary = Color(0xFF0D47A1)        // Blue 900
val OnPrimary = Color(0xFFFFFFFF)
val Secondary = Color(0xFFEF6C00)      // Orange 800
val OnSecondary = Color(0xFFFFFFFF)
val Tertiary = Color(0xFF00695C)       // Teal 800
val OnTertiary = Color(0xFFFFFFFF)
val Background = Color(0xFFF9FAFB)     // near-white
val OnBackground = Color(0xFF111827)   // gray-900
val Surface = Color(0xFFFFFFFF)
val OnSurface = Color(0xFF111827)
val Error = Color(0xFFB00020)
val OnError = Color(0xFFFFFFFF)

val LightScheme = lightColorScheme(
    primary = Primary, onPrimary = OnPrimary,
    secondary = Secondary, onSecondary = OnSecondary,
    tertiary = Tertiary, onTertiary = OnTertiary,
    background = Background, onBackground = OnBackground,
    surface = Surface, onSurface = OnSurface,
    error = Error, onError = OnError
)

// Dark
val PrimaryDark = Color(0xFF82B1FF)     // lighter blue
val SecondaryDark = Color(0xFFFFB74D)   // orange 300
val TertiaryDark = Color(0xFF4DB6AC)    // teal 300
val BackgroundDark = Color(0xFF0B0F14)  // deep gray
val OnBackgroundDark = Color(0xFFE5E7EB)
val SurfaceDark = Color(0xFF121923)
val OnSurfaceDark = Color(0xFFE5E7EB)

val DarkScheme = darkColorScheme(
    primary = PrimaryDark, onPrimary = Color.Black,
    secondary = SecondaryDark, onSecondary = Color.Black,
    tertiary = TertiaryDark, onTertiary = Color.Black,
    background = BackgroundDark, onBackground = OnBackgroundDark,
    surface = SurfaceDark, onSurface = OnSurfaceDark,
    error = Error, onError = OnError
)
