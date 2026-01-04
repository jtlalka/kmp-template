package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppText
import kmp.template.design.extension.disableContentAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppNavigationBar(
    selectedId: Any,
    items: List<AppNavigationUiModel>,
    onSelected: (Any) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface,
    itemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppTheme.colors.onPrimaryContainer,
        selectedTextColor = AppTheme.colors.primary,
        indicatorColor = AppTheme.colors.primaryContainer,
        unselectedIconColor = AppTheme.colors.onSurfaceVariant,
        unselectedTextColor = AppTheme.colors.onSurfaceVariant,
        disabledIconColor = AppTheme.colors.onSurfaceVariant.disableContentAlpha(),
        disabledTextColor = AppTheme.colors.onSurfaceVariant.disableContentAlpha()
    ),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        windowInsets = windowInsets,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { AppIcon(icon = item.icon) },
                label = { AppText(text = item.label) },
                selected = item.id == selectedId,
                onClick = { onSelected(item.id) },
                colors = itemColors
            )
        }
    }
}

@ComponentPreview
@Composable
private fun AppNavigationBarPreview() = AppTheme {
    AppNavigationBar(
        selectedId = "HOME",
        items = listOf(
            AppNavigationUiModel(
                id = "HOME",
                label = "Home",
                icon = AppTheme.icons.home
            ),
            AppNavigationUiModel(
                id = "ABOUT",
                label = "About",
                icon = AppTheme.icons.infoCircle
            )
        ),
        onSelected = {}
    )
}
