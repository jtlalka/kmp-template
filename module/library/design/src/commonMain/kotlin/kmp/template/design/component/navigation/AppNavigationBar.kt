package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContentAlpha
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavigationBar(
    selectedId: Any,
    items: List<AppNavigationUiModel>,
    onClick: (route: Any) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.surface,
    contentColor: Color = AppTheme.colors.onSurface,
    itemColors: NavigationBarItemColors = NavigationBarItemColors(
        selectedIconColor = AppTheme.colors.onSurface,
        selectedTextColor = AppTheme.colors.onSurface,
        selectedIndicatorColor = AppTheme.colors.primaryContainer,
        unselectedIconColor = AppTheme.colors.onSurface, //onSurfaceVariant
        unselectedTextColor = AppTheme.colors.onSurface, //onSurfaceVariant
        disabledIconColor = AppTheme.colors.onSurface.disableContentAlpha(), //onSurfaceVariant
        disabledTextColor = AppTheme.colors.onSurface.disableContentAlpha() //onSurfaceVariant
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
                icon = { Icon(painter = painterResource(item.icon), contentDescription = null) },
                label = { Text(item.label) },
                selected = selectedId == item.route,
                onClick = { onClick(item.route) },
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
                route = "HOME",
                label = "Home",
                icon = AppTheme.icons.home
            ),
            AppNavigationUiModel(
                route = "ABOUT",
                label = "About",
                icon = AppTheme.icons.info
            )
        ),
        onClick = {}
    )
}
