package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavigationDrawer(
    selectedId: Any,
    items: List<AppNavigationUiModel>,
    onClick: (route: Any) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.small,
        vertical = AppTheme.dimensions.medium
    ),
    containerColor: Color = AppTheme.colors.background,
    contentColor: Color = AppTheme.colors.onBackground,
    itemColors: NavigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = AppTheme.colors.primaryContainer,
        selectedIconColor = AppTheme.colors.onPrimary,
        selectedTextColor = AppTheme.colors.onPrimary,
        unselectedContainerColor = Color.Transparent,
        unselectedIconColor = AppTheme.colors.onPrimary, //onSurfaceVariant
        unselectedTextColor = AppTheme.colors.onPrimary //onSurfaceVariant
    ),
    itemShape: Shape = AppTheme.shapes.button,
    tonalElevation: Dp = DrawerDefaults.PermanentDrawerElevation,
    windowInsets: WindowInsets = DrawerDefaults.windowInsets
) {
    PermanentNavigationDrawer(
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
                        icon = { Icon(painter = painterResource(item.icon), contentDescription = null) },
                        label = { Text(item.label) },
                        selected = item.route == selectedId,
                        onClick = { onClick(item.route) },
                        modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                        colors = itemColors,
                        shape = itemShape
                    )
                }
            }
        },
        modifier = modifier,
        content = {}
    )
}

@ComponentPreview
@Composable
private fun AppNavigationDrawerPreview() = AppTheme {
    AppNavigationDrawer(
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
