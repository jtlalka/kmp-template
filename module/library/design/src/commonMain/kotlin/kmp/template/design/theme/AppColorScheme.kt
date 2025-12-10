package kmp.template.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { lightColorScheme() }

@Immutable
data class AppColorScheme(
    val primary: Color,
    val primaryVariant: Color,
    val primaryContainer: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val secondaryContainer: Color,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val surfaceContainer: Color,
    val error: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onError: Color,
    val isDarkMode: Boolean
)

internal fun lightColorScheme() = AppColorScheme(
    primary = AppColorPalette.tealLight,
    primaryVariant = AppColorPalette.tealLight.colorVariantAlpha(),
    primaryContainer = AppColorPalette.tealLight.colorContainerAlpha(),
    secondary = AppColorPalette.violetLight,
    secondaryVariant = AppColorPalette.violetLight.colorVariantAlpha(),
    secondaryContainer = AppColorPalette.violetLight.colorContainerAlpha(),
    background = AppColorPalette.white,
    surface = AppColorPalette.grayLight,
    surfaceVariant = AppColorPalette.grayLight.colorVariantAlpha(),
    surfaceContainer = AppColorPalette.grayLight.colorContainerAlpha(),
    error = AppColorPalette.redLight,
    onPrimary = AppColorPalette.black,
    onSecondary = AppColorPalette.white,
    onBackground = AppColorPalette.black,
    onSurface = AppColorPalette.black,
    onError = AppColorPalette.white,
    isDarkMode = false
)

internal fun darkColorScheme() = AppColorScheme(
    primary = AppColorPalette.tealDark,
    primaryVariant = AppColorPalette.tealDark.colorVariantAlpha(),
    primaryContainer = AppColorPalette.tealDark.colorContainerAlpha(),
    secondary = AppColorPalette.violetDark,
    secondaryVariant = AppColorPalette.violetDark.colorVariantAlpha(),
    secondaryContainer = AppColorPalette.violetDark.colorContainerAlpha(),
    background = AppColorPalette.black,
    surface = AppColorPalette.grayDark,
    surfaceVariant = AppColorPalette.grayDark.colorVariantAlpha(),
    surfaceContainer = AppColorPalette.grayDark.colorContainerAlpha(),
    error = AppColorPalette.redDark,
    onPrimary = AppColorPalette.white,
    onSecondary = AppColorPalette.white,
    onBackground = AppColorPalette.white,
    onSurface = AppColorPalette.white,
    onError = AppColorPalette.white,
    isDarkMode = true
)
