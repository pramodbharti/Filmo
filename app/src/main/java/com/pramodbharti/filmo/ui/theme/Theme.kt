package com.pramodbharti.filmo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val filmoDarkColorScheme = darkColorScheme(
    primary = filmoDarkPrimary,
    onPrimary = filmoDarkOnPrimary,
    primaryContainer = filmoDarkPrimaryContainer,
    onPrimaryContainer = filmoDarkOnPrimaryContainer,
    inversePrimary = filmoDarkPrimaryInverse,
    secondary = filmoDarkSecondary,
    onSecondary = filmoDarkOnSecondary,
    secondaryContainer = filmoDarkSecondaryContainer,
    onSecondaryContainer = filmoDarkOnSecondaryContainer,
    tertiary = filmoDarkTertiary,
    onTertiary = filmoDarkOnTertiary,
    tertiaryContainer = filmoDarkTertiaryContainer,
    onTertiaryContainer = filmoDarkOnTertiaryContainer,
    error = filmoDarkError,
    onError = filmoDarkOnError,
    errorContainer = filmoDarkErrorContainer,
    onErrorContainer = filmoDarkOnErrorContainer,
    background = filmoDarkBackground,
    onBackground = filmoDarkOnBackground,
    surface = filmoDarkSurface,
    onSurface = filmoDarkOnSurface,
    inverseSurface = filmoDarkInverseSurface,
    inverseOnSurface = filmoDarkInverseOnSurface,
    surfaceVariant = filmoDarkSurfaceVariant,
    onSurfaceVariant = filmoDarkOnSurfaceVariant,
    outline = filmoDarkOutline
)

private val filmoLightColorScheme = lightColorScheme(
    primary = filmoLightPrimary,
    onPrimary = filmoLightOnPrimary,
    primaryContainer = filmoLightPrimaryContainer,
    onPrimaryContainer = filmoLightOnPrimaryContainer,
    inversePrimary = filmoLightPrimaryInverse,
    secondary = filmoLightSecondary,
    onSecondary = filmoLightOnSecondary,
    secondaryContainer = filmoLightSecondaryContainer,
    onSecondaryContainer = filmoLightOnSecondaryContainer,
    tertiary = filmoLightTertiary,
    onTertiary = filmoLightOnTertiary,
    tertiaryContainer = filmoLightTertiaryContainer,
    onTertiaryContainer = filmoLightOnTertiaryContainer,
    error = filmoLightError,
    onError = filmoLightOnError,
    errorContainer = filmoLightErrorContainer,
    onErrorContainer = filmoLightOnErrorContainer,
    background = filmoLightBackground,
    onBackground = filmoLightOnBackground,
    surface = filmoLightSurface,
    onSurface = filmoLightOnSurface,
    inverseSurface = filmoLightInverseSurface,
    inverseOnSurface = filmoLightInverseOnSurface,
    surfaceVariant = filmoLightSurfaceVariant,
    onSurfaceVariant = filmoLightOnSurfaceVariant,
    outline = filmoLightOutline
)

@Composable
fun FilmoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val filmoColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> filmoDarkColorScheme
        else -> filmoLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = filmoColorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = filmoColorScheme,
        typography = filmoTypography,
        shapes = filmoShapes,
        content = content
    )
}