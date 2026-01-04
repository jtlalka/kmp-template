package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppText
import kmp.template.design.theme.AppTheme

@Composable
fun AppNavigationDrawer(
    selectedId: Any,
    items: List<AppNavigationUiModel>,
    onSelected: (Any) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.background,
    contentColor: Color = AppTheme.colors.onBackground,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.spaceSm,
        vertical = AppTheme.dimensions.spaceMd
    ),
    itemColors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = AppTheme.colors.primaryContainer,
        selectedIconColor = AppTheme.colors.onPrimaryContainer,
        selectedTextColor = AppTheme.colors.onPrimaryContainer,
        unselectedContainerColor = AppTheme.colors.surface,
        unselectedIconColor = AppTheme.colors.onSurface,
        unselectedTextColor = AppTheme.colors.onSurface
    ),
    itemShape: Shape = AppTheme.shapes.button,
    tonalElevation: Dp = DrawerDefaults.PermanentDrawerElevation,
    windowInsets: WindowInsets = DrawerDefaults.windowInsets,
    content: @Composable () -> Unit = {}
) {
    PermanentNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            PermanentDrawerSheet(
                drawerContainerColor = containerColor,
                drawerContentColor = contentColor,
                drawerTonalElevation = tonalElevation,
                windowInsets = windowInsets,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
            ) {
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { AppIcon(icon = item.icon) },
                        label = { AppText(text = item.label) },
                        selected = item.id == selectedId,
                        onClick = { onSelected(item.id) },
                        modifier = Modifier.padding(
                            horizontal = AppTheme.dimensions.spaceSm,
                            vertical = AppTheme.dimensions.spaceXs
                        ),
                        colors = itemColors,
                        shape = itemShape
                    )
                }
            }
        },
        content = content
    )
}

@ComponentPreview
@Composable
private fun AppNavigationDrawerPreview() = AppTheme {
    AppNavigationDrawer(
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
