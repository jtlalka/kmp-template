package kmp.template.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {

    val colors: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val icons: AppIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = AppThemeProvider(
    colors = if (isDarkTheme) darkColorScheme() else lightColorScheme(),
    content = content
)

@Composable
private fun AppThemeProvider(
    colors: AppColorScheme = AppTheme.colors,
    dimensions: AppDimensions = AppTheme.dimensions,
    icons: AppIcons = AppTheme.icons,
    shapes: AppShapes = AppTheme.shapes,
    typography: AppTypography = AppTheme.typography,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalColors provides colors,
    LocalDimensions provides dimensions,
    LocalIcons provides icons,
    LocalShapes provides shapes,
    LocalTypography provides typography
) {
    Surface(
        color = AppTheme.colors.background,
        contentColor = AppTheme.colors.onBackground
    ) {
        ProvideTextStyle(
            value = typography.bodyRegular,
            content = content
        )
    }
}
