package kmp.template.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { lightColorScheme() }

@Immutable
data class AppColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val background: Color,
    val onBackground: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color
)

internal fun lightColorScheme() = AppColorScheme(
    primary = AppColorPalette.primary40,
    onPrimary = AppColorPalette.primary100,
    primaryContainer = AppColorPalette.primary90,
    onPrimaryContainer = AppColorPalette.primary10,

    secondary = AppColorPalette.secondary40,
    onSecondary = AppColorPalette.secondary100,
    secondaryContainer = AppColorPalette.secondary90,
    onSecondaryContainer = AppColorPalette.secondary10,

    background = AppColorPalette.neutral99,
    onBackground = AppColorPalette.neutral10,

    surface = AppColorPalette.neutral95,
    onSurface = AppColorPalette.neutral10,
    surfaceVariant = AppColorPalette.neutral90,
    onSurfaceVariant = AppColorPalette.neutral30,

    error = AppColorPalette.error40,
    onError = AppColorPalette.error100,
    errorContainer = AppColorPalette.error90,
    onErrorContainer = AppColorPalette.error10
)

internal fun darkColorScheme() = AppColorScheme(
    primary = AppColorPalette.primary80,
    onPrimary = AppColorPalette.primary20,
    primaryContainer = AppColorPalette.primary30,
    onPrimaryContainer = AppColorPalette.primary90,

    secondary = AppColorPalette.secondary80,
    onSecondary = AppColorPalette.secondary20,
    secondaryContainer = AppColorPalette.secondary30,
    onSecondaryContainer = AppColorPalette.secondary90,

    background = AppColorPalette.neutral10,
    onBackground = AppColorPalette.neutral90,

    surface = AppColorPalette.neutral20,
    onSurface = AppColorPalette.neutral90,
    surfaceVariant = AppColorPalette.neutral30,
    onSurfaceVariant = AppColorPalette.neutral80,

    error = AppColorPalette.error80,
    onError = AppColorPalette.error20,
    errorContainer = AppColorPalette.error30,
    onErrorContainer = AppColorPalette.error90
)
