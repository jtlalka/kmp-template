package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.extension.disableContentAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppNavigationDropdown(
    expanded: Boolean,
    items: List<AppNavigationUiModel>,
    onSelected: (Any) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    containerShape: Shape = AppTheme.shapes.dropdown,
    containerColor: Color = AppTheme.colors.surface,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.spaceMd,
        vertical = AppTheme.dimensions.spaceXs
    ),
    itemColors: MenuItemColors = MenuDefaults.itemColors(
        textColor = AppTheme.colors.onSurface,
        leadingIconColor = AppTheme.colors.onSurface,
        trailingIconColor = AppTheme.colors.onSurface,
        disabledTextColor = AppTheme.colors.onSurface.disableContentAlpha(),
        disabledLeadingIconColor = AppTheme.colors.onSurface.disableContentAlpha(),
        disabledTrailingIconColor = AppTheme.colors.onSurface.disableContentAlpha()
    )
) = DropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismiss,
    modifier = modifier,
    offset = DpOffset.Zero,
    shape = containerShape,
    containerColor = containerColor,
    tonalElevation = MenuDefaults.TonalElevation,
    shadowElevation = MenuDefaults.ShadowElevation,
    border = null
) {
    items.forEach { item ->
        DropdownMenuItem(
            text = { AppText(text = item.label) },
            leadingIcon = { AppIcon(icon = item.icon) },
            trailingIcon = null,
            onClick = {
                onSelected(item.route)
                onDismiss()
            },
            enabled = true,
            colors = itemColors,
            contentPadding = contentPadding
        )
    }
}

@ComponentPreview
@Composable
private fun AppNavigationDropdownPreview() = AppTheme {
    AppScaffold {
        AppNavigationDropdown(
            expanded = true,
            items = listOf(
                AppNavigationUiModel(
                    route = "HOME",
                    label = "Home",
                    icon = AppTheme.icons.home
                ),
                AppNavigationUiModel(
                    route = "ABOUT",
                    label = "About",
                    icon = AppTheme.icons.infoCircle
                )
            ),
            onSelected = {},
            onDismiss = {}
        )
    }
}
